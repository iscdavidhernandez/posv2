package posv2.service;

import java.util.List;

import posv2.model.Rol;
import posv2.repository.RolRepository;

public class RolService
implements CrudService<Rol> {
	
	 private final RolRepository repository;

	    public RolService() {

	        this.repository = new RolRepository();
	    }

	    public Rol guardar(Rol rol) {

	        validarRol(rol);

	        if(repository.existeDescripcion(
	                rol.getDescripcion().trim())) {

	            throw new IllegalArgumentException(
	                    "Ya existe un rol con esa descripción."
	            );
	        }

	        int idGenerado =
	                repository.guardar(rol);

	        rol.setIdRol(idGenerado);

	        return rol;
	    }

	    public Rol actualizar(Rol rol) {

	        validarRol(rol);

	        Rol existente =
	                repository.buscarPorId(
	                        rol.getIdRol()
	                );

	        if(existente == null) {

	            throw new IllegalArgumentException(
	                    "El rol no existe."
	            );
	        }

	        repository.actualizar(rol);

	        return rol;
	    }

	    public void eliminar(int idRol) {

	        if(idRol <= 0) {

	            throw new IllegalArgumentException(
	                    "ID inválido."
	            );
	        }

	        Rol rol =
	                repository.buscarPorId(idRol);

	        if(rol == null) {

	            throw new IllegalArgumentException(
	                    "El rol no existe."
	            );
	        }

	        repository.eliminar(idRol);
	    }

	    public Rol buscarPorId(int idRol) {

	        return repository.buscarPorId(idRol);
	    }

	    public List<Rol> listar() {

	        return repository.listar();
	    }

	    public List<Rol> buscarPorDescripcion(
	            String descripcion) {

	        return repository.buscarPorDescripcion(
	                descripcion
	        );
	    }

	    private void validarRol(Rol rol) {

	        if(rol == null) {

	            throw new IllegalArgumentException(
	                    "El rol es nulo."
	            );
	        }

	        if(rol.getDescripcion() == null
	                || rol.getDescripcion()
	                        .trim()
	                        .isEmpty()) {

	            throw new IllegalArgumentException(
	                    "La descripción es obligatoria."
	            );
	        }
	    }


}
