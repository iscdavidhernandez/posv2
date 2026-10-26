package posv2.service;

import java.util.List;

import posv2.model.Modulo;
import posv2.repository.ModuloRepository;

public class ModuloService
        implements CrudService<Modulo> {

    private final ModuloRepository repository;

    public ModuloService() {

        this.repository =
                new ModuloRepository();
    }

    @Override
    public Modulo guardar(
            Modulo modulo) {

        validarModulo(modulo);

        if(repository.existeNombre(
                modulo.getNombre().trim())) {

            throw new IllegalArgumentException(
                    "Ya existe un módulo con ese nombre."
            );
        }

        int idGenerado =
                repository.guardar(modulo);

        modulo.setIdModulo(
                idGenerado
        );

        return modulo;
    }

    @Override
    public Modulo actualizar(
            Modulo modulo) {

        validarModulo(modulo);

        Modulo existente =
                repository.buscarPorId(
                        modulo.getIdModulo()
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El módulo no existe."
            );
        }

        repository.actualizar(modulo);

        return modulo;
    }

    @Override
    public void eliminar(
            int idModulo) {

        if(idModulo <= 0) {

            throw new IllegalArgumentException(
                    "ID inválido."
            );
        }

        Modulo existente =
                repository.buscarPorId(
                        idModulo
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El módulo no existe."
            );
        }

        repository.eliminar(
                idModulo
        );
    }

    @Override
    public Modulo buscarPorId(
            int idModulo) {

        return repository.buscarPorId(
                idModulo
        );
    }

    @Override
    public List<Modulo> listar() {

        return repository.listar();
    }

    public List<Modulo> buscarPorNombre(
            String nombre) {

        return repository.buscarPorNombre(
                nombre
        );
    }

    private void validarModulo(
            Modulo modulo) {

        if(modulo == null) {

            throw new IllegalArgumentException(
                    "El módulo es nulo."
            );
        }

        if(modulo.getNombre() == null
                || modulo.getNombre()
                         .trim()
                         .isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        if(modulo.getDescripcion() == null
                || modulo.getDescripcion()
                         .trim()
                         .isEmpty()) {

            throw new IllegalArgumentException(
                    "La descripción es obligatoria."
            );
        }
    }
}