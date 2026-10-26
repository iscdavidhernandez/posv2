package posv2.service;

import java.util.List;

import posv2.model.Grupo;
import posv2.repository.GrupoRepository;

public class GrupoService
        implements CrudService<Grupo> {

    private final GrupoRepository repository;

    public GrupoService() {

        this.repository =
                new GrupoRepository();
    }

    @Override
    public Grupo guardar(
            Grupo grupo) {

        validarGrupo(grupo);

        if(repository.existeNombre(
                grupo.getNombre().trim())) {

            throw new IllegalArgumentException(
                    "Ya existe un grupo con ese nombre."
            );
        }

        int idGenerado =
                repository.guardar(grupo);

        grupo.setIdGrupo(
                idGenerado
        );

        return grupo;
    }

    @Override
    public Grupo actualizar(
            Grupo grupo) {

        validarGrupo(grupo);

        Grupo existente =
                repository.buscarPorId(
                        grupo.getIdGrupo()
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El grupo no existe."
            );
        }

        repository.actualizar(
                grupo
        );

        return grupo;
    }

    @Override
    public void eliminar(
            int idGrupo) {

        if(idGrupo <= 0) {

            throw new IllegalArgumentException(
                    "ID inválido."
            );
        }

        Grupo existente =
                repository.buscarPorId(
                        idGrupo
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El grupo no existe."
            );
        }

        repository.eliminar(
                idGrupo
        );
    }

    @Override
    public Grupo buscarPorId(
            int idGrupo) {

        return repository.buscarPorId(
                idGrupo
        );
    }

    @Override
    public List<Grupo> listar() {

        return repository.listar();
    }

    public List<Grupo> buscarPorNombre(
            String nombre) {

        return repository.buscarPorNombre(
                nombre
        );
    }

    public List<Grupo> buscarPorModulo(
            int idModulo) {

        return repository.buscarPorModulo(
                idModulo
        );
    }

    private void validarGrupo(
            Grupo grupo) {

        if(grupo == null) {

            throw new IllegalArgumentException(
                    "El grupo es nulo."
            );
        }

        if(grupo.getNombre() == null
                || grupo.getNombre()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        if(grupo.getDescripcion() == null
                || grupo.getDescripcion()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "La descripción es obligatoria."
            );
        }

        if(grupo.getModulo() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un módulo."
            );
        }

        if(grupo.getModulo()
                 .getIdModulo() <= 0) {

            throw new IllegalArgumentException(
                    "El módulo seleccionado no es válido."
            );
        }
    }
}