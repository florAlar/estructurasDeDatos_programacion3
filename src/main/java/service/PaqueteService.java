package service;

import data.loader.PaquetesLoader;
import model.Paquete;
import repo.PaquetesRepository;
import service.filters.Condicion;

import java.util.ArrayList;


public class PaqueteService {

    PaquetesRepository paqueteRepo;

    public  PaqueteService(String pathPaquetes) {
        PaquetesLoader paquetesLoader = new PaquetesLoader(pathPaquetes);
        paqueteRepo = paquetesLoader.almacenarEnRepo();
    }


    /* metodos de repo */

    public Paquete getPaquete(String codigo){
        return paqueteRepo.buscarPorIdentificador(codigo);
    }

    public int cantidadPaquetes(){
        return paqueteRepo.cantidad();
    }

    public ArrayList<Paquete> getPaquetes(){
        return paqueteRepo.obtenerTodos();
    }

    public boolean existePaquete(String codigo){
        return paqueteRepo.existe(codigo);
    }

    public boolean estaVacio(){
        return paqueteRepo.existenElementos();
    }


    /* Metodos del servicio */

    public ArrayList<Paquete> getPaquetesFiltrados(Condicion c1){

        ArrayList<Paquete> paquetes = paqueteRepo.obtenerTodos();
        ArrayList<Paquete> paquetesFiltrados = new ArrayList<>();

        for (Paquete paq : paquetes){

            if(c1.cumple(paq)){
                paquetesFiltrados.add(paq);
            }
        }

        return paquetesFiltrados;
        //O(n) -> si o si recorro todos los elementos para filtrarlos.
        //en este caso no utilizo una estructura aparte porque la lista principal es dinamica
        // y el costo de mantener otra estructura en memoria actualizada no esta justificando la frecuencia de esta consulta en particular;
    }


    // - buscar por código
    // - filtrar urgentes
    // - calcular prioridades
    // - asignar camiones */


}
