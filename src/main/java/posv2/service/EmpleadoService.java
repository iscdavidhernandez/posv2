package posv2.service;

import java.util.List;

import posv2.model.Empleado;
import posv2.repository.EmpleadoRepository;

public class EmpleadoService
        implements CrudService<Empleado> {

    private final EmpleadoRepository repository;

    public EmpleadoService() {

        this.repository =
                new EmpleadoRepository();
    }

    @Override
    public Empleado guardar(
            Empleado empleado) {

        validarEmpleado(empleado);

        if(repository.existeRFC(
                empleado.getRfc().trim())) {

            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese RFC."
            );
        }

        if(repository.existeCorreo(
                empleado.getCorreoElectronico()
                        .trim())) {

            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese correo electrónico."
            );
        }

        int idGenerado =
                repository.guardar(
                        empleado
                );

        empleado.setIdEmpleado(
                idGenerado
        );

        return empleado;
    }

    @Override
    public Empleado actualizar(
            Empleado empleado) {

        validarEmpleado(empleado);

        Empleado existente =
                repository.buscarPorId(
                        empleado.getIdEmpleado()
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El empleado no existe."
            );
        }

        repository.actualizar(
                empleado
        );

        return empleado;
    }

    @Override
    public void eliminar(
            int idEmpleado) {

        if(idEmpleado <= 0) {

            throw new IllegalArgumentException(
                    "ID inválido."
            );
        }

        Empleado existente =
                repository.buscarPorId(
                        idEmpleado
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El empleado no existe."
            );
        }

        repository.eliminar(
                idEmpleado
        );
    }

    @Override
    public Empleado buscarPorId(
            int idEmpleado) {

        return repository.buscarPorId(
                idEmpleado
        );
    }

    @Override
    public List<Empleado> listar() {

        return repository.listar();
    }

    public List<Empleado> buscarPorNombre(
            String nombre) {

        return repository.buscarPorNombre(
                nombre
        );
    }

    private void validarEmpleado(
            Empleado empleado) {

        if(empleado == null) {

            throw new IllegalArgumentException(
                    "El empleado es nulo."
            );
        }

        if(empleado.getNombre() == null
                || empleado.getNombre()
                           .trim()
                           .isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        if(empleado.getRfc() == null
                || empleado.getRfc()
                           .trim()
                           .isEmpty()) {

            throw new IllegalArgumentException(
                    "El RFC es obligatorio."
            );
        }

        if(empleado.getCorreoElectronico() == null
                || empleado.getCorreoElectronico()
                           .trim()
                           .isEmpty()) {

            throw new IllegalArgumentException(
                    "El correo electrónico es obligatorio."
            );
        }

        validarCorreo(
                empleado.getCorreoElectronico()
        );
    }

    private void validarCorreo(
            String correo) {

        String regex =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(!correo.matches(regex)) {

            throw new IllegalArgumentException(
                    "Formato de correo electrónico inválido."
            );
        }
    }
}