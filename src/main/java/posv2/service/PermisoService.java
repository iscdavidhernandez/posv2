package posv2.service;

import java.util.List;

import posv2.model.Permiso;
import posv2.repository.PermisoRepository;

public class PermisoService
        implements CrudService<Permiso> {

    private final PermisoRepository repository;

    public PermisoService() {

        repository =
                new PermisoRepository();
    }

    @Override
    public Permiso guardar(
            Permiso permiso) {

        validarPermiso(permiso);

        int id =
                repository.guardar(
                        permiso
                );

        permiso.setIdPermiso(id);

        return permiso;
    }

    @Override
    public Permiso actualizar(
            Permiso permiso) {

        validarPermiso(permiso);

        repository.actualizar(
                permiso
        );

        return permiso;
    }

    @Override
    public void eliminar(
            int idPermiso) {

        repository.eliminar(
                idPermiso
        );
    }

    @Override
    public Permiso buscarPorId(
            int idPermiso) {

        return repository.buscarPorId(
                idPermiso
        );
    }

    @Override
    public List<Permiso> listar() {

        return repository.listar();
    }

    private void validarPermiso(
            Permiso permiso) {

        if(permiso == null) {

            throw new IllegalArgumentException(
                    "Permiso nulo."
            );
        }

        if(permiso.getNombre() == null
                || permiso.getNombre()
                          .isBlank()) {

            throw new IllegalArgumentException(
                    "Nombre obligatorio."
            );
        }

        if(permiso.getGrupo() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un grupo."
            );
        }

        if(permiso.getFormularioDestino() == null
                || permiso.getFormularioDestino()
                          .isBlank()) {

            throw new IllegalArgumentException(
                    "Formulario destino obligatorio."
            );
        }
    }
}