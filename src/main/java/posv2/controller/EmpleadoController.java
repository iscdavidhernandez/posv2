package posv2.controller;

import java.util.List;

import posv2.model.Empleado;
import posv2.service.EmpleadoService;

public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController() {

        this.service =
                new EmpleadoService();
    }

    public Empleado guardar(
            Empleado empleado) {

        return service.guardar(
                empleado
        );
    }

    public Empleado actualizar(
            Empleado empleado) {

        return service.actualizar(
                empleado
        );
    }

    public void eliminar(
            int idEmpleado) {

        service.eliminar(
                idEmpleado
        );
    }

    public Empleado buscarPorId(
            int idEmpleado) {

        return service.buscarPorId(
                idEmpleado
        );
    }

    public List<Empleado> listar() {

        return service.listar();
    }

    public List<Empleado> buscarPorNombre(
            String nombre) {

        return service.buscarPorNombre(
                nombre
        );
    }
}
