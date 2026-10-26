package posv2.controller;

import java.util.List;

import posv2.model.Usuario;
import posv2.service.UsuarioService;

public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController() {

        this.service =
                new UsuarioService();
    }

    public Usuario guardar(
            Usuario usuario) {

        return service.guardar(
                usuario
        );
    }

    public Usuario actualizar(
            Usuario usuario) {

        return service.actualizar(
                usuario
        );
    }

    public void eliminar(
            int idUsuario) {

        service.eliminar(
                idUsuario
        );
    }

    public Usuario buscarPorId(
            int idUsuario) {

        return service.buscarPorId(
                idUsuario
        );
    }

    public Usuario buscarPorNick(
            String nick) {

        return service.buscarPorNick(
                nick
        );
    }

    public List<Usuario> listar() {

        return service.listar();
    }

    public boolean validarLogin(
            String nick,
            String password) {

        return service.validarLogin(
                nick,
                password
        );
    }
}