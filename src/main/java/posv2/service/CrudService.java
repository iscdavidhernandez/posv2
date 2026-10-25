package posv2.service;

import java.util.List;

public interface CrudService<T> {

    T guardar(T entidad);

    T actualizar(T entidad);

    void eliminar(int id);

    T buscarPorId(int id);

    List<T> listar();
}