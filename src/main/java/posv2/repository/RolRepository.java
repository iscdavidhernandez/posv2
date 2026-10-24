package posv2.repository;

import java.util.ArrayList;
import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.database.QueryResult;
import posv2.model.Rol;

public class RolRepository {
	

	private final MySQLManager db;

    public RolRepository() {
        this.db = MySQLManager.getInstance();
    }

    /**
     * Guarda un nuevo rol.
     */
    public int guardar(Rol rol) {

        String[] columnas = {
                "descripcion"
        };

        Object[] valores = {
                rol.getDescripcion()
        };

        return db.insert(
                "rol",
                columnas,
                valores
        );
    }

    /**
     * Actualiza un rol existente.
     */
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

    /**
     * Elimina un rol por su ID.
     */
    public int eliminar(int idRol) {

        return db.delete(
                "rol",
                "idrol=?",
                idRol
        );
    }

    /**
     * Busca un rol por ID.
     */
    public Rol buscarPorId(int idRol) {

        QueryResult result = db.select(
                "SELECT idrol, descripcion FROM rol WHERE idrol = ? ",
                idRol
        );

        if (result.isEmpty()) {
            return null;
        }

        return mapRow(result.first());
    }

    /**
     * Obtiene todos los roles.
     */
    public List<Rol> listar() {

        List<Rol> roles = new ArrayList<>();

        QueryResult result = db.select(
                " SELECT idrol, descripcion FROM rol ORDER BY descripcion "
        );

        for (DataRow row : result.getRows()) {

            roles.add(
                    mapRow(row)
            );
        }

        return roles;
    }

    /**
     * Busca roles por descripción.
     */
    public List<Rol> buscarPorDescripcion(String descripcion) {

        List<Rol> roles = new ArrayList<>();

        QueryResult result = db.select(
                " SELECT idrol, descripcion FROM rol WHERE descripcion LIKE ? ORDER BY descripcion ",
                "%" + descripcion + "%"
        );

        for (DataRow row : result.getRows()) {

            roles.add(
                    mapRow(row)
            );
        }

        return roles;
    }

    /**
     * Verifica si existe un rol con la descripción indicada.
     */
    public boolean existeDescripcion(String descripcion) {

        QueryResult result = db.select(
                " SELECT COUNT(*) AS total FROM rol WHERE descripcion = ? ",
                descripcion
        );

        if (result.isEmpty()) {
            return false;
        }

        Integer total = result
                .first()
                .getInt("total");

        return total != null && total > 0;
    }

    /**
     * Convierte una fila de datos en un objeto Rol.
     */
    private Rol mapRow(DataRow row) {

        Rol rol = new Rol();

        rol.setIdRol(
                row.getInt("idrol")
        );

        rol.setDescripcion(
                row.getString("descripcion")
        );

        return rol;
    }
	
}
