package repo;

import java.util.ArrayList;

public interface Repository<T, K> {

    T buscarPorIdentificador(K id);

    boolean existe(K id);

    ArrayList<T> obtenerTodos();

    int cantidad();

   //aqui irian eliminar e insertar uno nuevo pero dado que son archivos csv no se como lo haria;

}