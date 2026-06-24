package dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

//Interfaz de Operaciones DAO para las distintas clases.
public interface IOperaciones<T> {
    boolean insertar(T objeto);
    List<T> listar();
    Optional<T> buscarPorId(int id);
    boolean actualizar(T objeto);
    boolean eliminar(int id);
}
