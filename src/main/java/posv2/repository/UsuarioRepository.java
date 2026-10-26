package posv2.repository;

import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.model.Empleado;
import posv2.model.Rol;
import posv2.model.Usuario;

public class UsuarioRepository
        implements CrudRepository<Usuario> {

    private final MySQLManager db;

    private static final String SQL_BASE =
            """
            SELECT

                u.idusuario,
                u.nick,
                u.contrasenia,

                r.idrol,
                r.descripcion AS rolDescripcion,

                e.idempleado,
                e.nombre AS empleadoNombre,
                e.rfc,
                e.correoelectronico

            FROM usuario u

            INNER JOIN rol r
                ON u.rolorigen = r.idrol

            INNER JOIN empleado e
                ON u.empleadoorigen = e.idempleado
            """;

    public UsuarioRepository() {

        this.db =
                MySQLManager.getInstance();
    }

    @Override
    public int guardar(
            Usuario usuario) {

        return db.insertAndReturnId(
                "usuario",
                new String[] {
                        "nick",
                        "contrasenia",
                        "rolorigen",
                        "empleadoorigen"
                },
                new Object[] {
                        usuario.getNick(),
                        usuario.getContrasenia(),
                        usuario.getRol()
                               .getIdRol(),
                        usuario.getEmpleado()
                               .getIdEmpleado()
                }
        );
    }

    @Override
    public int actualizar(
            Usuario usuario) {

        return db.update(
                "usuario",
                new String[] {
                        "nick",
                        "contrasenia",
                        "rolorigen",
                        "empleadoorigen"
                },
                new Object[] {
                        usuario.getNick(),
                        usuario.getContrasenia(),
                        usuario.getRol()
                               .getIdRol(),
                        usuario.getEmpleado()
                               .getIdEmpleado()
                },
                "idusuario=?",
                usuario.getIdUsuario()
        );
    }

    @Override
    public int eliminar(
            int idUsuario) {

        return db.delete(
                "usuario",
                "idusuario=?",
                idUsuario
        );
    }

    @Override
    public Usuario buscarPorId(
            int idUsuario) {

        return db.selectOne(
                SQL_BASE +
                """
                WHERE u.idusuario = ?
                """,
                this::mapRow,
                idUsuario
        );
    }

    @Override
    public List<Usuario> listar() {

        return db.selectList(
                SQL_BASE +
                """
                ORDER BY nick
                """,
                this::mapRow
        );
    }

    public Usuario buscarPorNick(
            String nick) {

        return db.selectOne(
                SQL_BASE +
                """
                WHERE u.nick = ?
                """,
                this::mapRow,
                nick
        );
    }

    public boolean existeNick(
            String nick) {

        var result =
                db.select(
                        """
                        SELECT
                            COUNT(*) AS total
                        FROM usuario
                        WHERE nick = ?
                        """,
                        nick
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

    private Usuario mapRow(
            DataRow row) {

        Rol rol =
                new Rol();

        rol.setIdRol(
                row.getInt("idrol")
        );

        rol.setDescripcion(
                row.getString(
                        "rolDescripcion"
                )
        );

        Empleado empleado =
                new Empleado();

        empleado.setIdEmpleado(
                row.getInt(
                        "idempleado"
                )
        );

        empleado.setNombre(
                row.getString(
                        "empleadoNombre"
                )
        );

        empleado.setRfc(
                row.getString("rfc")
        );

        empleado.setCorreoElectronico(
                row.getString(
                        "correoelectronico"
                )
        );

        Usuario usuario =
                new Usuario();

        usuario.setIdUsuario(
                row.getInt(
                        "idusuario"
                )
        );

        usuario.setNick(
                row.getString("nick")
        );

        usuario.setContrasenia(
                row.getString(
                        "contrasenia"
                )
        );

        usuario.setRol(
                rol
        );

        usuario.setEmpleado(
                empleado
        );

        return usuario;
    }
    
    public boolean existeNickExcepto(
            String nick,
            int idUsuario) {

        var result =
                db.select(
                        """
                        SELECT
                            COUNT(*) AS total
                        FROM usuario
                        WHERE nick = ?
                          AND idusuario <> ?
                        """,
                        nick,
                        idUsuario
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
}