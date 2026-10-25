package posv2.controller;

import java.util.List;

import posv2.model.Rol;
import posv2.service.RolService;

public class RolController {

	private final RolService service;

    public RolController() {

        this.service = new RolService();
    }

    public Rol guardar(Rol rol) {

        return service.guardar(rol);
    }

    public Rol actualizar(Rol rol) {

        return service.actualizar(rol);
    }

    public void eliminar(int idRol) {

        service.eliminar(idRol);
    }

    public Rol buscarPorId(int idRol) {

        return service.buscarPorId(idRol);
    }

    public List<Rol> listar() {

        return service.listar();
    }

    public List<Rol> buscarPorDescripcion(
            String descripcion) {

        return service.buscarPorDescripcion(
                descripcion
        );
    }
}
