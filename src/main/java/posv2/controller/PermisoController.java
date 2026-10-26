package posv2.controller;

import java.util.List;

import posv2.model.Permiso;
import posv2.service.PermisoService;

public class PermisoController {

    private final PermisoService service;

    public PermisoController() {

        service =
                new PermisoService();
    }

    public Permiso guardar(
            Permiso permiso) {

        return service.guardar(
                permiso
        );
    }

    public Permiso actualizar(
            Permiso permiso) {

        return service.actualizar(
                permiso
        );
    }

    public void eliminar(
            int idPermiso) {

        service.eliminar(
                idPermiso
        );
    }

    public Permiso buscarPorId(
            int idPermiso) {

        return service.buscarPorId(
                idPermiso
        );
    }

    public List<Permiso> listar() {

        return service.listar();
    }
}