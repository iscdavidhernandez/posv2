package posv2.repository;

import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.model.Modulo;

public class ModuloRepository
        implements CrudRepository<Modulo> {

    private final MySQLManager db;

    public ModuloRepository() {

        this.db = MySQLManager.getInstance();
    }

    @Override
    public int guardar(Modulo modulo) {

        return db.insertAndReturnId(
                "modulo",
                new String[] {
                        "nombre",
                        "descripcion"
                },
                new Object[] {
                        modulo.getNombre(),
                        modulo.getDescripcion()
                }
        );
    }

    @Override
    public int actualizar(Modulo modulo) {

        return db.update(
                "modulo",
                new String[] {
                        "nombre",
                        "descripcion"
                },
                new Object[] {
                        modulo.getNombre(),
                        modulo.getDescripcion()
                },
                "idmodulo=?",
                modulo.getIdModulo()
        );
    }

    @Override
    public int eliminar(int idModulo) {

        return db.delete(
                "modulo",
                "idmodulo=?",
                idModulo
        );
    }

    @Override
    public Modulo buscarPorId(int idModulo) {

        return db.selectOne(
                """
                SELECT
                    idmodulo,
                    nombre,
                    descripcion
                FROM modulo
                WHERE idmodulo = ?
                """,
                this::mapRow,
                idModulo
        );
    }

    @Override
    public List<Modulo> listar() {

        return db.selectList(
                """
                SELECT
                    idmodulo,
                    nombre,
                    descripcion
                FROM modulo
                ORDER BY nombre
                """,
                this::mapRow
        );
    }

    public List<Modulo> buscarPorNombre(
            String nombre) {

        return db.selectList(
                """
                SELECT
                    idmodulo,
                    nombre,
                    descripcion
                FROM modulo
                WHERE nombre LIKE ?
                ORDER BY nombre
                """,
                this::mapRow,
                "%" + nombre + "%"
        );
    }

    public boolean existeNombre(
            String nombre) {

        var result =
                db.select(
                        """
                        SELECT
                            COUNT(*) AS total
                        FROM modulo
                        WHERE nombre = ?
                        """,
                        nombre
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

    private Modulo mapRow(
            DataRow row) {

        Modulo modulo =
                new Modulo();

        modulo.setIdModulo(
                row.getInt("idmodulo")
        );

        modulo.setNombre(
                row.getString("nombre")
        );

        modulo.setDescripcion(
                row.getString("descripcion")
        );

        return modulo;
    }
}