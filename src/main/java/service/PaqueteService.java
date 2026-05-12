package service;

import data.loader.PaquetesLoader;
import model.Paquete;
import repo.PaquetesRepository;
import service.filters.Condicion;

import java.util.ArrayList;


public class PaqueteService {

    PaquetesRepository paqueteRepo;

    public  PaqueteService(String pathPaquetes) {
        System.out.println("**Servicio de paquetes creado**");

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

    /* Metodos del servicio */

    public ArrayList<Paquete> getPaquetesFiltrados(Condicion c1){

//        System.out.println("***condicion de Busqueda Activa: " + c1.getNombre());
       // private String nombre = "contiene alimentos";
        ArrayList<Paquete> paquetes = paqueteRepo.obtenerTodos();
        ArrayList<Paquete> paquetesFiltrados = new ArrayList<>();

        for (Paquete paq : paquetes){

            if(c1.cumple(paq)){
                paquetesFiltrados.add(paq);
            }
        }
        System.out.println("Complejidad comp. de busqueda en array: O(n). - total de paquetes encontrados: " + paquetesFiltrados.size());

        return paquetesFiltrados;
        //O(n) -> si o si recorro todos los elementos para filtrarlos.
        //en este caso no utilizo una estructura aparte porque la lista principal es dinamica
        // y el costo de mantener otra estructura en memoria actualizada no esta justificando la frecuencia de esta consulta en particular;
    }



}
