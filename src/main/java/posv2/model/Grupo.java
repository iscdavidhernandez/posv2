package posv2.model;

public class Grupo {

	  private int idGrupo;
	    private String nombre;
	    private String descripcion;

	    private Modulo modulo;

	    public Grupo() {
	    }

	    public Grupo(int idGrupo,
	                 String nombre,
	                 String descripcion,
	                 Modulo modulo) {

	        this.idGrupo = idGrupo;
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	        this.modulo = modulo;
	    }

	    public int getIdGrupo() {
	        return idGrupo;
	    }

	    public void setIdGrupo(int idGrupo) {
	        this.idGrupo = idGrupo;
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

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    public Modulo getModulo() {
	        return modulo;
	    }

	    public void setModulo(Modulo modulo) {
	        this.modulo = modulo;
	    }

	    @Override
	    public String toString() {
	        return nombre;
	    }
	    
}
