package posv2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

public class MySQLManager {

    private static MySQLManager instance;

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "pos";

    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String URL =
            "jdbc:mysql://"
                    + HOST
                    + ":"
                    + PORT
                    + "/"
                    + DATABASE
                    + "?serverTimezone=UTC";

    private static final Set<String> VALID_TABLES =
            Set.of(
                    "empleado",
                    "rol",
                    "usuario",
                    "modulo",
                    "grupo",
                    "permiso",
                    "rolpermiso"
            );

    private static final Pattern SQL_IDENTIFIER =
            Pattern.compile(
                    "^[a-zA-Z][a-zA-Z0-9_]*$"
            );

    private static final int MAX_STRING_LENGTH = 1000;

    private MySQLManager() {

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(
                    "No se pudo cargar el Driver MySQL.",
                    e
            );
        }
    }

    public static synchronized MySQLManager getInstance() {

        if (instance == null) {

            instance = new MySQLManager();
        }

        return instance;
    }

    public Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    
    public <T> List<T> selectList(
            String sql,
            Function<DataRow, T> mapper,
            Object... params) {

        QueryResult result =
                select(sql, params);

        List<T> list =
                new ArrayList<>();

        for(DataRow row : result.getRows()) {

            list.add(
                    mapper.apply(row)
            );
        }

        return list;
    }
    
    public <T> T selectOne(
            String sql,
            Function<DataRow,T> mapper,
            Object... params) {

        QueryResult result =
                select(sql, params);

        if(result.isEmpty()) {

            return null;
        }

        return mapper.apply(
                result.first()
        );
    }
    // =======================================================
    // SELECT
    // =======================================================

    public QueryResult select(String sql) {

        return select(
                sql,
                new Object[]{}
        );
    }

    public QueryResult select(
            String sql,
            Object... params) {

        if (sql == null
                || sql.isBlank()) {

            throw new IllegalArgumentException(
                    "El SQL es obligatorio."
            );
        }

        String sqlUpper =
                sql.trim()
                        .toUpperCase();

        if (!sqlUpper.startsWith("SELECT")) {

            throw new IllegalArgumentException(
                    "Solo se permiten consultas SELECT."
            );
        }

        QueryResult result =
                new QueryResult();

        try (
                Connection cn =
                        getConnection();

                PreparedStatement ps =
                        cn.prepareStatement(sql)
        ) {

            setParameters(
                    ps,
                    params
            );

            ResultSet rs =
                    ps.executeQuery();

            ResultSetMetaData meta =
                    rs.getMetaData();

            int columnCount =
                    meta.getColumnCount();

            while (rs.next()) {

                DataRow row =
                        new DataRow();

                for (int col = 1;
                     col <= columnCount;
                     col++) {

                    row.put(
                            meta.getColumnLabel(col),
                            rs.getObject(col)
                    );
                }

                result.addRow(row);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error ejecutando SELECT.",
                    e
            );
        }

        return result;
    }

    // =======================================================
    // INSERT
    // =======================================================

    public int insert(
            String tabla,
            String[] columnas,
            Object[] valores) {

        validateTableName(tabla);
        validateColumns(columnas);
        validateColumnsAndValues(
                columnas,
                valores
        );

        String sql =
                buildInsertSql(
                        tabla,
                        columnas
                );

        return executeUpdate(
                sql,
                valores
        );
    }

    public int insertAndReturnId(
            String tabla,
            String[] columnas,
            Object[] valores) {

        validateTableName(tabla);
        validateColumns(columnas);
        validateColumnsAndValues(
                columnas,
                valores
        );

        String sql =
                buildInsertSql(
                        tabla,
                        columnas
                );

        try (
                Connection cn =
                        getConnection();

                PreparedStatement ps =
                        cn.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            setParameters(
                    ps,
                    valores
            );

            ps.executeUpdate();

            ResultSet rs =
                    ps.getGeneratedKeys();

            if(rs.next()) {
                return rs.getInt(1);
            }

            throw new RuntimeException(
                    "No fue posible recuperar el ID generado."
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error insertando registro.",
                    e
            );
        }
    }

    // =======================================================
    // UPDATE
    // =======================================================

    public int update(
            String tabla,
            String[] columnas,
            Object[] valores,
            String where,
            Object... whereParams) {
    	
    	if(whereParams == null
    	        || whereParams.length == 0) {

    	    throw new IllegalArgumentException(
    	            "Los parámetros WHERE son obligatorios."
    	    );
    	}

        validateTableName(tabla);
        validateColumns(columnas);
        validateColumnsAndValues(
                columnas,
                valores
        );
        validateWhere(where);

        StringBuilder sql =
                new StringBuilder();

        sql.append("UPDATE ")
                .append(tabla)
                .append(" SET ");

        for (int i = 0;
             i < columnas.length;
             i++) {

            sql.append(columnas[i])
                    .append("=?");

            if (i < columnas.length - 1) {

                sql.append(",");
            }
        }

        sql.append(" WHERE ")
                .append(where);

        Object[] params =
                mergeArrays(
                        valores,
                        whereParams
                );

        return executeUpdate(
                sql.toString(),
                params
        );
    }

    // =======================================================
    // DELETE
    // =======================================================

    public int delete(
            String tabla,
            String where,
            Object... params) {
    	
    	if(params == null
    	        || params.length == 0) {

    	    throw new IllegalArgumentException(
    	            "Debe proporcionar parámetros para el WHERE."
    	    );
    	}

        validateTableName(tabla);
        validateWhere(where);

        String sql =
                "DELETE FROM "
                        + tabla
                        + " WHERE "
                        + where;

        return executeUpdate(
                sql,
                params
        );
    }

    // =======================================================
    // EXECUTE UPDATE
    // =======================================================

    private int executeUpdate(
            String sql,
            Object... params) {

        try (
                Connection cn =
                        getConnection();

                PreparedStatement ps =
                        cn.prepareStatement(sql)
        ) {

            setParameters(
                    ps,
                    params
            );

            return ps.executeUpdate();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error ejecutando operación.",
                    e
            );
        }
    }

    // =======================================================
    // UTILIDADES
    // =======================================================

    private void setParameters(
            PreparedStatement ps,
            Object... params)
            throws SQLException {

        for (int i = 0;
             i < params.length;
             i++) {

            validateParameter(
                    params[i]
            );

            ps.setObject(
                    i + 1,
                    params[i]
            );
        }
    }

    private String buildInsertSql(
            String tabla,
            String[] columnas) {

        StringBuilder sql =
                new StringBuilder();

        sql.append("INSERT INTO ")
                .append(tabla)
                .append(" (");

        sql.append(
                String.join(
                        ",",
                        columnas
                )
        );

        sql.append(") VALUES (");

        for (int i = 0;
             i < columnas.length;
             i++) {

            sql.append("?");

            if (i < columnas.length - 1) {

                sql.append(",");
            }
        }

        sql.append(")");

        return sql.toString();
    }

    private Object[] mergeArrays(
            Object[] values,
            Object[] whereValues) {

        Object[] result =
                new Object[
                        values.length
                                + whereValues.length
                        ];

        System.arraycopy(
                values,
                0,
                result,
                0,
                values.length
        );

        System.arraycopy(
                whereValues,
                0,
                result,
                values.length,
                whereValues.length
        );

        return result;
    }

    // =======================================================
    // SEGURIDAD
    // =======================================================

    private void validateTableName(
            String table) {

        if (table == null
                || table.isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de tabla es obligatorio."
            );
        }

        if (!VALID_TABLES.contains(
                table.toLowerCase())) {

            throw new IllegalArgumentException(
                    "Tabla no permitida: "
                            + table
            );
        }
    }

    private void validateColumns(
            String[] columns) {

        if (columns == null
                || columns.length == 0) {

            throw new IllegalArgumentException(
                    "Debe existir al menos una columna."
            );
        }

        for (String column : columns) {

            if (column == null
                    || column.isBlank()) {

                throw new IllegalArgumentException(
                        "Nombre de columna inválido."
                );
            }

            if (!SQL_IDENTIFIER
                    .matcher(column)
                    .matches()) {

                throw new IllegalArgumentException(
                        "Nombre de columna inválido: "
                                + column
                );
            }
        }
    }

    private void validateColumnsAndValues(
            String[] columns,
            Object[] values) {

        if (values == null) {

            throw new IllegalArgumentException(
                    "Los valores no pueden ser nulos."
            );
        }

        if (columns.length
                != values.length) {

            throw new IllegalArgumentException(
                    "Cantidad de columnas y valores incompatible."
            );
        }
    }

    private void validateWhere(
            String where) {

        if (where == null
                || where.isBlank()) {

            throw new IllegalArgumentException(
                    "La cláusula WHERE es obligatoria."
            );
        }
    }

    private void validateParameter(
            Object value) {

        if (value instanceof String texto) {

            if (texto.length()
                    > MAX_STRING_LENGTH) {

                throw new IllegalArgumentException(
                        "Parámetro demasiado grande."
                );
            }
        }
    }
}