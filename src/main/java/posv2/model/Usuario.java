package posv2.model;

public class Usuario {

    private int idUsuario;

    private String nick;

    private String contrasenia;

    private Rol rol;

    private Empleado empleado;

    public Usuario() {
    }

    public Usuario(
            int idUsuario,
            String nick,
            String contrasenia,
            Rol rol,
            Empleado empleado) {

        this.idUsuario = idUsuario;
        this.nick = nick;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.empleado = empleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(
            int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(
            String nick) {
        this.nick = nick;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(
            String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(
            Rol rol) {
        this.rol = rol;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(
            Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {

        return nick;
    }
}