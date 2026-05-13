package service;

import model.Paquete;
import repo.PaquetesRepository;
import service.filters.Condicion;

import java.util.ArrayList;
import java.util.List;

public class PaqueteService {

    private final PaquetesRepository paqueteRepo;

    public PaqueteService(PaquetesRepository paqueteRepo) {
        this.paqueteRepo = paqueteRepo;
    }


    /* metodos de repo */

    public Paquete getPaquete(String codigo){
        return paqueteRepo.buscarPorIdentificador(codigo);
    }

    public int cantidadPaquetes(){
        return paqueteRepo.cantidad();
    }

    public List<Paquete> getPaquetes(){
        return paqueteRepo.obtenerTodos();
    }

    public boolean existePaquete(String codigo){
        return paqueteRepo.existe(codigo);
    }

    /* Metodos del servicio */

    public List<Paquete> getPaquetesFiltrados(Condicion c1){

        List<Paquete> paquetes = paqueteRepo.obtenerTodos();
        List<Paquete> paquetesFiltrados = new ArrayList<>();

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

}
