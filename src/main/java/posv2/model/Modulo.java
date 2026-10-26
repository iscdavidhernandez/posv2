package posv2.model;

public class Modulo {

    private int idModulo;
    private String nombre;
    private String descripcion;

    public Modulo() {
    }

    public Modulo(
            int idModulo,
            String nombre,
            String descripcion) {

        this.idModulo = idModulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(
            String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}