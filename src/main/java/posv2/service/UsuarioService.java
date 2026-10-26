package posv2.service;

import java.util.List;

import posv2.model.Usuario;
import posv2.repository.UsuarioRepository;
import posv2.util.HashUtil;

public class UsuarioService
        implements CrudService<Usuario> {

    private final UsuarioRepository repository;

    public UsuarioService() {

        this.repository =
                new UsuarioRepository();
    }

    @Override
    public Usuario guardar(
            Usuario usuario) {

        validarUsuario(usuario);

        if(repository.existeNick(
                usuario.getNick().trim())) {

            throw new IllegalArgumentException(
                    "Ya existe un usuario con ese nick."
            );
        }

        usuario.setContrasenia(
                HashUtil.sha256(
                        usuario.getContrasenia()
                )
        );

        int idGenerado =
                repository.guardar(
                        usuario
                );

        usuario.setIdUsuario(
                idGenerado
        );

        return usuario;
    }

    @Override
    public Usuario actualizar(
            Usuario usuario) {

        validarUsuarioActualizacion(
                usuario
        );

        Usuario existente =
                repository.buscarPorId(
                        usuario.getIdUsuario()
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El usuario no existe."
            );
        }

        if(repository.existeNickExcepto(
                usuario.getNick().trim(),
                usuario.getIdUsuario())) {

            throw new IllegalArgumentException(
                    "Ya existe un usuario con ese nick."
            );
        }

        if(usuario.getContrasenia() == null
                || usuario.getContrasenia()
                           .isBlank()) {

            usuario.setContrasenia(
                    existente.getContrasenia()
            );

        } else {

            usuario.setContrasenia(
                    HashUtil.sha256(
                            usuario.getContrasenia()
                    )
            );
        }

        repository.actualizar(
                usuario
        );

        return usuario;
    }
    
    private void validarUsuarioActualizacion(
            Usuario usuario) {

        if(usuario == null) {

            throw new IllegalArgumentException(
                    "El usuario es nulo."
            );
        }

        if(usuario.getNick() == null
                || usuario.getNick()
                          .trim()
                          .isEmpty()) {

            throw new IllegalArgumentException(
                    "El nick es obligatorio."
            );
        }

        if(usuario.getRol() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un rol."
            );
        }

        if(usuario.getEmpleado() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un empleado."
            );
        }
    }

    @Override
    public void eliminar(
            int idUsuario) {

        if(idUsuario <= 0) {

            throw new IllegalArgumentException(
                    "ID inválido."
            );
        }

        Usuario existente =
                repository.buscarPorId(
                        idUsuario
                );

        if(existente == null) {

            throw new IllegalArgumentException(
                    "El usuario no existe."
            );
        }

        repository.eliminar(
                idUsuario
        );
    }

    @Override
    public Usuario buscarPorId(
            int idUsuario) {

        return repository.buscarPorId(
                idUsuario
        );
    }

    @Override
    public List<Usuario> listar() {

        return repository.listar();
    }

    public Usuario buscarPorNick(
            String nick) {

        return repository.buscarPorNick(
                nick
        );
    }

    public boolean validarLogin(
            String nick,
            String password) {

        Usuario usuario =
                repository.buscarPorNick(
                        nick
                );

        if(usuario == null) {

            return false;
        }

        String hash =
                HashUtil.sha256(
                        password
                );

        return hash.equals(
                usuario.getContrasenia()
        );
    }

    private void validarUsuario(
            Usuario usuario) {

        if(usuario == null) {

            throw new IllegalArgumentException(
                    "El usuario es nulo."
            );
        }

        if(usuario.getNick() == null
                || usuario.getNick()
                          .trim()
                          .isEmpty()) {

            throw new IllegalArgumentException(
                    "El nick es obligatorio."
            );
        }

        if(usuario.getContrasenia() == null
                || usuario.getContrasenia()
                          .trim()
                          .isEmpty()) {

            throw new IllegalArgumentException(
                    "La contraseña es obligatoria."
            );
        }

        if(usuario.getRol() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un rol."
            );
        }

        if(usuario.getEmpleado() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un empleado."
            );
        }

        if(usuario.getRol()
                  .getIdRol() <= 0) {

            throw new IllegalArgumentException(
                    "Rol inválido."
            );
        }

        if(usuario.getEmpleado()
                  .getIdEmpleado() <= 0) {

            throw new IllegalArgumentException(
                    "Empleado inválido."
            );
        }
    }
}