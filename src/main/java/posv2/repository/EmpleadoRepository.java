package posv2.repository;

import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.model.Empleado;

public class EmpleadoRepository
        implements CrudRepository<Empleado> {

    private final MySQLManager db;

    public EmpleadoRepository() {

        this.db = MySQLManager.getInstance();
    }

    @Override
    public int guardar(Empleado empleado) {

        return db.insertAndReturnId(
                "empleado",
                new String[] {
                        "nombre",
                        "rfc",
                        "correoelectronico"
                },
                new Object[] {
                        empleado.getNombre(),
                        empleado.getRfc(),
                        empleado.getCorreoElectronico()
                }
        );
    }

    @Override
    public int actualizar(Empleado empleado) {

        return db.update(
                "empleado",
                new String[] {
                        "nombre",
                        "rfc",
                        "correoelectronico"
                },
                new Object[] {
                        empleado.getNombre(),
                        empleado.getRfc(),
                        empleado.getCorreoElectronico()
                },
                "idempleado=?",
                empleado.getIdEmpleado()
        );
    }

    @Override
    public int eliminar(int idEmpleado) {

        return db.delete(
                "empleado",
                "idempleado=?",
                idEmpleado
        );
    }

    @Override
    public Empleado buscarPorId(int idEmpleado) {

        return db.selectOne(
                """
                SELECT
                    idempleado,
                    nombre,
                    rfc,
                    correoelectronico
                FROM empleado
                WHERE idempleado = ?
                """,
                this::mapRow,
                idEmpleado
        );
    }

    @Override
    public List<Empleado> listar() {

        return db.selectList(
                """
                SELECT
                    idempleado,
                    nombre,
                    rfc,
                    correoelectronico
                FROM empleado
                ORDER BY nombre
                """,
                this::mapRow
        );
    }

    public List<Empleado> buscarPorNombre(
            String nombre) {

        return db.selectList(
                """
                SELECT
                    idempleado,
                    nombre,
                    rfc,
                    correoelectronico
                FROM empleado
                WHERE nombre LIKE ?
                ORDER BY nombre
                """,
                this::mapRow,
                "%" + nombre + "%"
        );
    }

    public boolean existeRFC(
            String rfc) {

        var result =
                db.select(
                        """
                        SELECT
                            COUNT(*) AS total
                        FROM empleado
                        WHERE rfc = ?
                        """,
                        rfc
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

    public boolean existeCorreo(
            String correoElectronico) {

        var result =
                db.select(
                        """
                        SELECT
                            COUNT(*) AS total
                        FROM empleado
                        WHERE correoelectronico = ?
                        """,
                        correoElectronico
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

    private Empleado mapRow(
            DataRow row) {

        Empleado empleado =
                new Empleado();

        empleado.setIdEmpleado(
                row.getInt("idempleado")
        );

        empleado.setNombre(
                row.getString("nombre")
        );

        empleado.setRfc(
                row.getString("rfc")
        );

        empleado.setCorreoElectronico(
                row.getString(
                        "correoelectronico"
                )
        );

        return empleado;
    }
}