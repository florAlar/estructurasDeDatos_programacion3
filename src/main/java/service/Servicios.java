package service;

import algoritmos.Algoritmo;
import model.Camion;
import model.Paquete;
import model.Solucion;
import repo.CamionRepository;
import repo.PaquetesRepository;
import service.filters.CondicionUrgencia;
import service.filters.ContieneAlimentos;
import util.Consola;

import java.util.List;

public class Servicios {

    private final CamionRepository camionRepo;
    private final PaqueteService paqueteService;
    private final Algoritmo backtracking;
    private final Algoritmo greedy;

    public Servicios(PaquetesRepository paquetesRepository, CamionRepository camionRepository,
                     Algoritmo backtracking, Algoritmo greedy, PaqueteService paqueteService) {
        Consola.titulo("Servicios creados");

        this.camionRepo = camionRepository;
        this.paqueteService = paqueteService;
        this.backtracking = backtracking;
        this.greedy = greedy;
    }

    public Paquete servicio1(String codigoPaquete) {
        return paqueteService.getPaquete(codigoPaquete);
    }

    public List<Paquete> servicio2(boolean contiene) {
        return paqueteService.getPaquetesFiltrados(new ContieneAlimentos(contiene));
    }

    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        return paqueteService.getPaquetesFiltrados(new CondicionUrgencia(urgenciaMinima, urgenciaMaxima));
    }

    public Solucion servicio4() {
        return backtracking.resolver(camionRepo.obtenerTodos(), paqueteService.getPaquetes());
    }

    public Solucion servicio5() {
        return greedy.resolver(camionRepo.obtenerTodos(), paqueteService.getPaquetes());
    }

    public void servicio6() {
        Solucion backtracking = servicio4();
        Solucion greedy = servicio5();
    }

    public Camion getCamion(Integer id) {
        return camionRepo.buscarPorIdentificador(id);
    }

    public int cantidadCamiones() {
        return camionRepo.cantidad();
    }

    public List<Camion> getCamiones() {
        return camionRepo.obtenerTodos();
    }

    public boolean existeCamion(Integer id) {
        return camionRepo.existe(id);
    }
}
