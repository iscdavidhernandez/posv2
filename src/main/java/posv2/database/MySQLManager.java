package posv2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySQLManager {
	
    private static MySQLManager instance;

    private final String URL =
            "jdbc:mysql://localhost:3306/pos";

    private final String USER = "root";
    private final String PASSWORD = "1234";

    private MySQLManager() {

    }

    public static synchronized MySQLManager getInstance() {

        if(instance == null) {
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
    
    public QueryResult select(
            String sql,
            Object... params) {

        QueryResult result =
                new QueryResult();

        try(

            Connection cn =
                    getConnection();

            PreparedStatement ps =
                    cn.prepareStatement(sql)

        ) {

            for(int i = 0;
                    i < params.length;
                    i++) {

                ps.setObject(
                        i + 1,
                        params[i]
                );
            }

            ResultSet rs =
                    ps.executeQuery();

            ResultSetMetaData meta =
                    rs.getMetaData();

            int totalColumns =
                    meta.getColumnCount();

            while(rs.next()) {

                DataRow row =
                        new DataRow();

                for(int col = 1;
                        col <= totalColumns;
                        col++) {

                    String columnName =
                            meta.getColumnLabel(col);

                    Object value =
                            rs.getObject(col);

                    row.put(
                            columnName,
                            value
                    );
                }

                result.addRow(row);
            }

        }
        catch(Exception e) {

            throw new RuntimeException(
                "Error ejecutando SELECT",
                e
            );
        }

        return result;
    }
    
    private int executeUpdate(
            String sql,
            Object... params) {

        try (
            Connection cn = getConnection();
            PreparedStatement ps =
                    cn.prepareStatement(sql);
        ) {

            for(int i = 0; i < params.length; i++) {

                ps.setObject(
                        i + 1,
                        params[i]
                );
            }

            return ps.executeUpdate();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error ejecutando actualización",
                    e
            );
        }
    }

    
    public int delete(
            String tabla,
            String where,
            Object... params) {

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
    
    public int insert(
            String tabla,
            String[] columnas,
            Object[] valores) {

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

        for(int i = 0;
            i < columnas.length;
            i++) {

            sql.append("?");

            if(i < columnas.length - 1) {
                sql.append(",");
            }
        }

        sql.append(")");

        return executeUpdate(
                sql.toString(),
                valores
        );
    }
    
    public int update(
            String tabla,
            String[] columnas,
            Object[] valores,
            String where,
            Object... whereParams) {

        StringBuilder sql =
                new StringBuilder();

        sql.append("UPDATE ")
           .append(tabla)
           .append(" SET ");

        for(int i = 0;
            i < columnas.length;
            i++) {

            sql.append(columnas[i])
               .append("=?");

            if(i < columnas.length - 1) {
                sql.append(",");
            }
        }

        sql.append(" WHERE ")
           .append(where);

        Object[] allParams =
                new Object[
                        valores.length
                        + whereParams.length
                ];

        System.arraycopy(
                valores,
                0,
                allParams,
                0,
                valores.length
        );

        System.arraycopy(
                whereParams,
                0,
                allParams,
                valores.length,
                whereParams.length
        );

        return executeUpdate(
                sql.toString(),
                allParams
        );
    }

}
