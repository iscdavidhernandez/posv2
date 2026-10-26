package posv2.controller;

import java.util.List;

import posv2.model.Modulo;
import posv2.service.ModuloService;

public class ModuloController {

    private final ModuloService service;

    public ModuloController() {

        this.service =
                new ModuloService();
    }

    public Modulo guardar(
            Modulo modulo) {

        return service.guardar(
                modulo
        );
    }

    public Modulo actualizar(
            Modulo modulo) {

        return service.actualizar(
                modulo
        );
    }

    public void eliminar(
            int idModulo) {

        service.eliminar(
                idModulo
        );
    }

    public Modulo buscarPorId(
            int idModulo) {

        return service.buscarPorId(
                idModulo
        );
    }

    public List<Modulo> listar() {

        return service.listar();
    }

    public List<Modulo> buscarPorNombre(
            String nombre) {

        return service.buscarPorNombre(
                nombre
        );
    }
}
