package repo;

import model.Paquete;
import service.PaqueteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/*  Para el diseño de esta clase se eligieron dos estructuras de almacenamiento:

    // ArrayList:
       utilizado para recorridos completos y acceso posicional O(1).
       Aunque el recorrido total sea O(n), resulta eficiente por trabajar sobre memoria contigua.


    // HashMap:
       utilizado para búsquedas directas por código.
       Se sacrifica memoria adicional para obtener búsquedas O(1) promedio.
       El costo de construcción del HashMap es O(n) y ocurre una única vez al inicializar el repositorio.

       Aprovechando el recorrido realizado en setPaquetes(), se construye un índice
       HashMap que almacena los paquetes según contengan o no alimentos.
       Esto evita recorrer todo el arreglo en cada consulta, reduciendo la búsqueda
       de O(n) a un acceso directo O(1) promedio por clave booleana.

        En caso de incorporarse operaciones de alta, baja o modificación de paquetes,
        ambos índices hash deberán mantenerse sincronizados para preservar la
        consistencia de las búsquedas optimizadas.

       */

public class PaquetesRepository
        implements Repository<Paquete, String> {

    private ArrayList<Paquete> paquetes; // arrayListOrdenado por urgencia ascendente
    private HashMap<String, Paquete> paquetesPorCodigo;
    private HashMap<Boolean, ArrayList<Paquete>> paquetesPorAlimento;

    public PaquetesRepository(ArrayList<Paquete> paquetes) {
        setPaquetes(paquetes); // O(n).
    }

    private void setPaquetes(ArrayList<Paquete> paquetes) {

        paquetes.sort(Comparator.comparingDouble(Paquete::getUrgencia)); //O n.log(n)

        this.paquetes = paquetes;

        paquetesPorCodigo = new HashMap<>();
        paquetesPorAlimento = new HashMap<>();

        paquetesPorAlimento.put(true, new ArrayList<>());
        paquetesPorAlimento.put(false, new ArrayList<>());

        for (Paquete paquete : paquetes) {

            //paquetesPorCodigo;
            paquetesPorCodigo.put(paquete.getCodigo_Paquete(), paquete);

            //paquetesPorAlimento;
            paquetesPorAlimento.get(paquete.contieneAlimentos()).add(paquete);
        }

        System.out.println(paquetes.size() + " paquetes cargados en repositorio." + " Complejidad asociada: O(n)");

    }

    @Override
    public Paquete buscarPorIdentificador(String codigo) {
        return paquetesPorCodigo.get(codigo); // O(1).
    }

    @Override
    public boolean existe(String codigo) {
        return buscarPorIdentificador(codigo) != null; // O(1).
    }

    @Override
    public ArrayList<Paquete> obtenerTodos() {
        return new ArrayList<>(paquetes); // O(n) - copia defensiva.
    }

    @Override
    public int cantidad() {
        return paquetes.size();  // O(1).
    }

    public ArrayList<Paquete> obtenerConAlimentos(boolean conAlimento) {
        return paquetesPorAlimento.get(conAlimento);
    }


}