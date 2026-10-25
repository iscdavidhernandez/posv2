package posv2.repository;

import java.util.ArrayList;
import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.database.QueryResult;
import posv2.model.Rol;

public class RolRepository
        implements CrudRepository<Rol> {

    private final MySQLManager db;

    public RolRepository() {

        this.db = MySQLManager.getInstance();
    }

    @Override
    public int guardar(Rol rol) {

        String[] columnas = {
                "descripcion"
        };

        Object[] valores = {
                rol.getDescripcion()
        };

        return db.insertAndReturnId(
                "rol",
                columnas,
                valores
        );
    }

    @Override
    public int actualizar(Rol rol) {

        String[] columnas = {
                "descripcion"
        };

        Object[] valores = {
                rol.getDescripcion()
        };

        return db.update(
                "rol",
                columnas,
                valores,
                "idrol=?",
                rol.getIdRol()
        );
    }

    @Override
    public int eliminar(int idRol) {

        return db.delete(
                "rol",
                "idrol=?",
                idRol
        );
    }

    @Override
    public Rol buscarPorId(
            int idRol) {

        QueryResult result =
                db.select(
                        """
                        SELECT *
                        FROM rol
                        WHERE idrol=?
                        """,
                        idRol
                );

        if(result.isEmpty()) {

            return null;
        }

        return mapRow(
                result.first()
        );
    }

    @Override
    public List<Rol> listar() {

        return db.selectList(
                """
                SELECT *
                FROM rol
                ORDER BY descripcion
                """,
                this::mapRow
        );
    }

    public List<Rol> buscarPorDescripcion(
            String descripcion) {

        List<Rol> roles =
                new ArrayList<>();

        QueryResult result =
                db.select(
                        """
                        SELECT
                            idrol,
                            descripcion
                        FROM rol
                        WHERE descripcion LIKE ?
                        ORDER BY descripcion
                        """,
                        "%" + descripcion + "%"
                );

        for(DataRow row : result.getRows()) {

            roles.add(
                    mapRow(row)
            );
        }

        return roles;
    }

    public boolean existeDescripcion(
            String descripcion) {

        QueryResult result =
                db.select(
                        """
                        SELECT COUNT(*) AS total
                        FROM rol
                        WHERE descripcion = ?
                        """,
                        descripcion
                );

        if(result.isEmpty()) {

            return false;
        }

        Integer total =
                result.first()
                      .getInt("total");

        return total != null
                && total > 0;
    }

    /**
     * Convierte una fila de BD en un objeto Rol.
     */
    private Rol mapRow(DataRow row) {

        Rol rol = new Rol();

        rol.setIdRol(
                row.getInt("IdRol")
        );

        rol.setDescripcion(
                row.getString("Descripcion")
        );

        return rol;
    }
}