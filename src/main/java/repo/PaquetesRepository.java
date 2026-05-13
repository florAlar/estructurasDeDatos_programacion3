package repo;

import model.Paquete;
import java.util.ArrayList;
import java.util.HashMap;

/*  Para el diseño de esta clase se eligieron dos estructuras de almacenamiento:

    //ArrayList:
       utilizado para recorridos completos y acceso posicional O(1).
       Aunque el recorrido total sea O(n), resulta eficiente por trabajar sobre memoria contigua.

    // HashMap:
       utilizado para búsquedas directas por código.
       Se sacrifica memoria adicional para obtener búsquedas O(1) promedio.
       El costo de construcción del HashMap es O(n) y ocurre una única vez al inicializar el repositorio. */

public class PaquetesRepository
        implements Repository<Paquete, String> {

    private ArrayList<Paquete> paquetes;
    private HashMap<String, Paquete> paquetesPorCodigo;
    //buscar si conviene un arbol binario de busqueda balanceado para ejercicio 3) busqueda por rango de urgencia. con esto bajariamos a O(logN+K)

    public PaquetesRepository(ArrayList<Paquete> paquetes) {
        setPaquetes(paquetes); // O(n).
    }

    private void setPaquetes(ArrayList<Paquete> paquetes) {

        this.paquetes = paquetes;
        paquetesPorCodigo = new HashMap<>();

        for (Paquete paquete : paquetes) {
            paquetesPorCodigo.put(paquete.getCodigoPaquete(), paquete);
        }
    }

    @Override
    public Paquete buscarPorIdentificador(String codigo) {
        return paquetesPorCodigo.get(codigo); // O(1).
    }

    @Override
    public boolean existe(String codigo) {
        return buscarPorIdentificador(codigo)!= null; // O(1).
    }

    @Override
    public ArrayList<Paquete> obtenerTodos() {
        return new ArrayList<>(paquetes); // O(n) - copia defensiva.
    }

    @Override
    public int cantidad() {
        return paquetes.size();  // O(1).
    }
}