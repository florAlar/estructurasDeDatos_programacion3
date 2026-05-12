package service;

import model.Paquete;
import service.filters.CondicionUrgencia;
import service.filters.ContieneAlimentos;

import java.util.List;

public class Service {

    CamionService camionService;
    PaqueteService paqueteService;

    /*
     *----------->>>>>> hacer!!!  Expresar la complejidad temporal del constructor.
     */

    public Service(String pathCamiones, String pathPaquetes) {
        this.camionService = new CamionService(pathCamiones);
        this.paqueteService = new PaqueteService(pathPaquetes);
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     * Dado un código de paquete (String), retornar toda la información del paquete asociado. En caso de no existir, retornar null.
     */

    public Paquete servicio1(String codigoPaquete) {
        return paqueteService.getPaquete(codigoPaquete);
    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     * Dado un booleano que indica si se buscan paquetes que contienen alimentos (true)
     * o que no contienen alimentos (false), retornar el listado de paquetes correspondiente.
     */

    public List<Paquete> servicio2(boolean contiene) {
        return paqueteService.getPaquetesFiltrados(new ContieneAlimentos(contiene));

    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     * Dados dos valores enteros que representan un nivel de urgencia mínimo y máximo,
     * retornar todos los paquetes cuyo nivel de urgencia se encuentre dentro de ese rango (inclusive).
     */

    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        return paqueteService.getPaquetesFiltrados(new CondicionUrgencia(urgenciaMinima, urgenciaMaxima)); }
}

