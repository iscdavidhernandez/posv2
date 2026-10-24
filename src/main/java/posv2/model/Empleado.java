package posv2.model;

public class Empleado {

	private int idEmpleado;
    private String nombre;
    private String rfc;
    private String correoElectronico;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombre,
                     String rfc,
                     String correoElectronico) {

        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.rfc = rfc;
        this.correoElectronico = correoElectronico;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
