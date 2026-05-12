package service;

import algoritmos.Backtracking;
import data.loader.CamionesLoader;
import model.Camion;
import model.Paquete;
import repo.CamionRepository;
import algoritmos.Solucion;
import java.util.ArrayList;

//consultar si conviene tener esta clase o no, por separacion de responsabilidad.

public class CamionService {

    CamionRepository camionRepo;

    public CamionService(String pathCamiones) {

        CamionesLoader camionesLoader = new CamionesLoader(pathCamiones);
        this.camionRepo = camionesLoader.almacenarEnRepo();
    }

    /* Metodos del repo */

    public Camion getCamion(Integer id){
        return camionRepo.buscarPorIdentificador(id);
    }

    public int cantidadCamiones(){
        return camionRepo.cantidad();
    }

    public ArrayList<Camion> getCamiones(){
        return camionRepo.obtenerTodos();
    }

    public boolean existeCamion(Integer id){
        return camionRepo.existe(id);
    }


    /* Metodos del servicio */

    public boolean puedeTransportar(Paquete paquete){

        // chequear el espacio libre del camion
        //comparar con la del paquete
        return true;
    }

    public Double getCargaActual(Camion camion){
        return null;
    }

    public boolean tienePaqueteAsignado(Paquete paquete){
        return true;
    }

    // algoritmos

    public Solucion resolverConBacktracking(ArrayList<Paquete> paquetes){
        Backtracking bt = new Backtracking();
        return bt.resolver(camionRepo.obtenerTodos(), paquetes);
    }

    public Solucion resolverConGreedy(ArrayList<Paquete> paquetes){
        //cuadno veamos greedy
        return null;
    }

}

