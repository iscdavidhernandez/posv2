package posv2.controller;

import java.util.List;

import posv2.model.Rol;
import posv2.service.RolService;

public class RolController {

	 private final RolService service;

	    public RolController() {

	        this.service = new RolService();
	    }

	    public boolean guardar(Rol rol) {

	        try {

	            service.guardar(rol);

	            return true;

	        } catch(Exception e) {

	            System.err.println(
	                    e.getMessage()
	            );

	            return false;
	        }
	    }

	    public boolean actualizar(Rol rol) {

	        try {

	            service.actualizar(rol);

	            return true;

	        } catch(Exception e) {

	            System.err.println(
	                    e.getMessage()
	            );

	            return false;
	        }
	    }

	    public boolean eliminar(int idRol) {

	        try {

	            service.eliminar(idRol);

	            return true;

	        } catch(Exception e) {

	            System.err.println(
	                    e.getMessage()
	            );

	            return false;
	        }
	    }

	    public Rol buscarPorId(int idRol) {

	        try {

	            return service.buscarPorId(idRol);

	        } catch(Exception e) {

	            System.err.println(
	                    e.getMessage()
	            );

	            return null;
	        }
	    }

	    public List<Rol> listar() {

	        try {

	            return service.listar();

	        } catch(Exception e) {

	            System.err.println(
	                    e.getMessage()
	            );

	            return List.of();
	        }
	    }

	    public List<Rol> buscarPorDescripcion(
	            String descripcion) {

	        try {

	            return service.buscarPorDescripcion(
	                    descripcion
	            );

	        } catch(Exception e) {

	            System.err.println(
	                    e.getMessage()
	            );

	            return List.of();
	        }
	    }
}
