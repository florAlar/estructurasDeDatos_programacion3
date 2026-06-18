package service;

import data.loader.PaquetesLoader;
import model.Paquete;
import repo.PaquetesRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PaqueteService {

    public enum CriterioOrden {
        PESO,
        URGENCIA
    }

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

    /* Metodos del servicio */

    public ArrayList<Paquete> getPaquetesConAlimentos(boolean conAlimento){

        ArrayList<Paquete> paquetes = paqueteRepo.obtenerTodos();
        ArrayList<Paquete> paquetesFiltrados = new ArrayList<>();

        for (Paquete paq : paquetes){
            if(paq.contieneAlimentos() == conAlimento){
                paquetesFiltrados.add(paq);
            }
        }

        System.out.println("Complejidad computacional asociada a busqueda en array: O(n). - total de paquetes con alimentos encontrados: " + paquetesFiltrados.size()+".-");

        return paquetesFiltrados;

        //O(n) -> si o si recorro todos los elementos para filtrarlos.

    }


    public ArrayList<Paquete> getPaquetesEnRango(int min, int max) {

        ArrayList<Paquete> paquetes = paqueteRepo.obtenerTodos();
        ArrayList<Paquete> paquetesFiltrados = new ArrayList<>();

        for (Paquete paquete : paquetes) {

            if (paquete.getUrgencia() >= min && paquete.getUrgencia() <= max) {

                    paquetesFiltrados.add(paquete);
            }
        }

        System.out.println( "Complejidad computacional asociada a la búsqueda secuencial: O(n). " + "Paquetes encontrados con urgencia entre " + min + " y " + max + ": " + paquetesFiltrados.size() + ".-");

        return paquetesFiltrados;
    }


}

