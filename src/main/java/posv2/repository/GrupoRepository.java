package posv2.repository;

import java.util.List;

import posv2.database.DataRow;
import posv2.database.MySQLManager;
import posv2.model.Grupo;
import posv2.model.Modulo;

public class GrupoRepository
        implements CrudRepository<Grupo> {

    private final MySQLManager db;

    public GrupoRepository() {

        this.db = MySQLManager.getInstance();
    }

    @Override
    public int guardar(Grupo grupo) {

        return db.insertAndReturnId(
                "grupo",
                new String[] {
                        "moduloOrigen",
                        "nombre",
                        "descripcion"
                },
                new Object[] {
                        grupo.getModulo()
                             .getIdModulo(),
                        grupo.getNombre(),
                        grupo.getDescripcion()
                }
        );
    }

    @Override
    public int actualizar(Grupo grupo) {

        return db.update(
                "grupo",
                new String[] {
                        "moduloOrigen",
                        "nombre",
                        "descripcion"
                },
                new Object[] {
                        grupo.getModulo()
                             .getIdModulo(),
                        grupo.getNombre(),
                        grupo.getDescripcion()
                },
                "idgrupo=?",
                grupo.getIdGrupo()
        );
    }

    @Override
    public int eliminar(int idGrupo) {

        return db.delete(
                "grupo",
                "idgrupo=?",
                idGrupo
        );
    }

    @Override
    public Grupo buscarPorId(int idGrupo) {

        return db.selectOne(
                """
                SELECT
                    g.idgrupo,
                    g.nombre,
                    g.descripcion,

                    m.idmodulo,
                    m.nombre AS moduloNombre,
                    m.descripcion AS moduloDescripcion

                FROM grupo g
                INNER JOIN modulo m
                    ON g.moduloOrigen = m.idmodulo

                WHERE g.idgrupo = ?
                """,
                this::mapRow,
                idGrupo
        );
    }

    @Override
    public List<Grupo> listar() {

        return db.selectList(
                """
                SELECT
                    g.idgrupo,
                    g.nombre,
                    g.descripcion,

                    m.idmodulo,
                    m.nombre AS moduloNombre,
                    m.descripcion AS moduloDescripcion

                FROM grupo g
                INNER JOIN modulo m
                    ON g.moduloOrigen = m.idmodulo

                ORDER BY
                    m.nombre,
                    g.nombre
                """,
                this::mapRow
        );
    }

    public List<Grupo> buscarPorModulo(
            int idModulo) {

        return db.selectList(
                """
                SELECT
                    g.idgrupo,
                    g.nombre,
                    g.descripcion,

                    m.idmodulo,
                    m.nombre AS moduloNombre,
                    m.descripcion AS moduloDescripcion

                FROM grupo g
                INNER JOIN modulo m
                    ON g.moduloOrigen = m.idmodulo

                WHERE m.idmodulo = ?

                ORDER BY g.nombre
                """,
                this::mapRow,
                idModulo
        );
    }

    public List<Grupo> buscarPorNombre(
            String nombre) {

        return db.selectList(
                """
                SELECT
                    g.idgrupo,
                    g.nombre,
                    g.descripcion,

                    m.idmodulo,
                    m.nombre AS moduloNombre,
                    m.descripcion AS moduloDescripcion

                FROM grupo g
                INNER JOIN modulo m
                    ON g.moduloOrigen = m.idmodulo

                WHERE g.nombre LIKE ?

                ORDER BY g.nombre
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
                        FROM grupo
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

    private Grupo mapRow(
            DataRow row) {

        Modulo modulo =
                new Modulo();

        modulo.setIdModulo(
                row.getInt("idmodulo")
        );

        modulo.setNombre(
                row.getString(
                        "moduloNombre"
                )
        );

        modulo.setDescripcion(
                row.getString(
                        "moduloDescripcion"
                )
        );

        Grupo grupo =
                new Grupo();

        grupo.setIdGrupo(
                row.getInt("idgrupo")
        );

        grupo.setNombre(
                row.getString("nombre")
        );

        grupo.setDescripcion(
                row.getString(
                        "descripcion"
                )
        );

        grupo.setModulo(
                modulo
        );

        return grupo;
    }
}