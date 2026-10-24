package posv2.model;

public class Permiso {

	 private int idPermiso;

	    private String nombre;
	    private String descripcion;
	    private String formularioDestino;

	    private Grupo grupo;

	    public Permiso() {
	    }

	    public Permiso(
	            int idPermiso,
	            String nombre,
	            String descripcion,
	            String formularioDestino,
	            Grupo grupo) {

	        this.idPermiso = idPermiso;
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	        this.formularioDestino = formularioDestino;
	        this.grupo = grupo;
	    }

	    public int getIdPermiso() {
	        return idPermiso;
	    }

	    public void setIdPermiso(int idPermiso) {
	        this.idPermiso = idPermiso;
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

	    public String getFormularioDestino() {
	        return formularioDestino;
	    }

	    public void setFormularioDestino(String formularioDestino) {
	        this.formularioDestino = formularioDestino;
	    }

	    public Grupo getGrupo() {
	        return grupo;
	    }

	    public void setGrupo(Grupo grupo) {
	        this.grupo = grupo;
	    }

	    @Override
	    public String toString() {
	        return nombre;
	    }
	    
}
