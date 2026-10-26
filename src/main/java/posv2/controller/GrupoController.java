package posv2.controller;

import java.util.List;

import posv2.model.Grupo;
import posv2.service.GrupoService;

public class GrupoController {

    private final GrupoService service;

    public GrupoController() {

        this.service = new GrupoService();
    }

    public Grupo guardar(
            Grupo grupo) {

        return service.guardar(
                grupo
        );
    }

    public Grupo actualizar(
            Grupo grupo) {

        return service.actualizar(
                grupo
        );
    }

    public void eliminar(
            int idGrupo) {

        service.eliminar(
                idGrupo
        );
    }

    public Grupo buscarPorId(
            int idGrupo) {

        return service.buscarPorId(
                idGrupo
        );
    }

    public List<Grupo> listar() {

        return service.listar();
    }

    public List<Grupo> buscarPorNombre(
            String nombre) {

        return service.buscarPorNombre(
                nombre
        );
    }

    public List<Grupo> buscarPorModulo(
            int idModulo) {

        return service.buscarPorModulo(
                idModulo
        );
    }
}
