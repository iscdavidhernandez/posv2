package posv2.model;

public class RolPermiso {

	 private Rol rol;
	    private Modulo modulo;
	    private Grupo grupo;
	    private Permiso permiso;

	    public RolPermiso() {
	    }

	    public RolPermiso(Rol rol,
	                      Modulo modulo,
	                      Grupo grupo,
	                      Permiso permiso) {

	        this.rol = rol;
	        this.modulo = modulo;
	        this.grupo = grupo;
	        this.permiso = permiso;
	    }

	    public Rol getRol() {
	        return rol;
	    }

	    public void setRol(Rol rol) {
	        this.rol = rol;
	    }

	    public Modulo getModulo() {
	        return modulo;
	    }

	    public void setModulo(Modulo modulo) {
	        this.modulo = modulo;
	    }

	    public Grupo getGrupo() {
	        return grupo;
	    }

	    public void setGrupo(Grupo grupo) {
	        this.grupo = grupo;
	    }

	    public Permiso getPermiso() {
	        return permiso;
	    }

	    public void setPermiso(Permiso permiso) {
	        this.permiso = permiso;
	    }
}
