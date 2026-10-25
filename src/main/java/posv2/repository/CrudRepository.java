package posv2.repository;

import java.util.List;

public interface CrudRepository<T> {

    int guardar(T entidad);

    int actualizar(T entidad);

    int eliminar(int id);

    T buscarPorId(int id);

    List<T> listar();
}