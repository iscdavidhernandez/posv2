package posv2.repository;

import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.model.Grupo;
import posv2.model.Modulo;
import posv2.model.Permiso;

public class PermisoRepository
        implements CrudRepository<Permiso> {

    private final MySQLManager db;

    private static final String SQL_BASE =
            """
            SELECT
                p.idpermiso,
                p.nombre,
                p.descripcion,
                p.formulariodestino,

                g.idgrupo,
                g.nombre AS grupoNombre,
                g.descripcion AS grupoDescripcion,

                m.idmodulo,
                m.nombre AS moduloNombre,
                m.descripcion AS moduloDescripcion

            FROM permiso p

            INNER JOIN grupo g
                ON p.grupoOrigen = g.idgrupo

            INNER JOIN modulo m
                ON g.moduloOrigen = m.idmodulo
            """;

    public PermisoRepository() {

        db = MySQLManager.getInstance();
    }

    @Override
    public int guardar(Permiso permiso) {

        return db.insertAndReturnId(
                "permiso",
                new String[]{
                        "grupoOrigen",
                        "nombre",
                        "descripcion",
                        "formulariodestino"
                },
                new Object[]{
                        permiso.getGrupo().getIdGrupo(),
                        permiso.getNombre(),
                        permiso.getDescripcion(),
                        permiso.getFormularioDestino()
                }
        );
    }

    @Override
    public int actualizar(Permiso permiso) {

        return db.update(
                "permiso",
                new String[]{
                        "grupoOrigen",
                        "nombre",
                        "descripcion",
                        "formulariodestino"
                },
                new Object[]{
                        permiso.getGrupo().getIdGrupo(),
                        permiso.getNombre(),
                        permiso.getDescripcion(),
                        permiso.getFormularioDestino()
                },
                "idpermiso=?",
                permiso.getIdPermiso()
        );
    }

    @Override
    public int eliminar(int idPermiso) {

        return db.delete(
                "permiso",
                "idpermiso=?",
                idPermiso
        );
    }

    @Override
    public Permiso buscarPorId(int idPermiso) {

        return db.selectOne(
                SQL_BASE +
                """
                WHERE p.idpermiso = ?
                """,
                this::mapRow,
                idPermiso
        );
    }

    @Override
    public List<Permiso> listar() {

        return db.selectList(
                SQL_BASE +
                """
                ORDER BY
                    m.nombre,
                    g.nombre,
                    p.nombre
                """,
                this::mapRow
        );
    }

    public List<Permiso> buscarPorGrupo(
            int idGrupo) {

        return db.selectList(
                SQL_BASE +
                """
                WHERE g.idgrupo = ?
                ORDER BY p.nombre
                """,
                this::mapRow,
                idGrupo
        );
    }

    private Permiso mapRow(
            DataRow row) {

        Modulo modulo = new Modulo();

        modulo.setIdModulo(
                row.getInt("idmodulo")
        );

        modulo.setNombre(
                row.getString("moduloNombre")
        );

        modulo.setDescripcion(
                row.getString("moduloDescripcion")
        );

        Grupo grupo = new Grupo();

        grupo.setIdGrupo(
                row.getInt("idgrupo")
        );

        grupo.setNombre(
                row.getString("grupoNombre")
        );

        grupo.setDescripcion(
                row.getString("grupoDescripcion")
        );

        grupo.setModulo(modulo);

        Permiso permiso =
                new Permiso();

        permiso.setIdPermiso(
                row.getInt("idpermiso")
        );

        permiso.setNombre(
                row.getString("nombre")
        );

        permiso.setDescripcion(
                row.getString("descripcion")
        );

        permiso.setFormularioDestino(
                row.getString("formulariodestino")
        );

        permiso.setGrupo(grupo);

        return permiso;
    }
}