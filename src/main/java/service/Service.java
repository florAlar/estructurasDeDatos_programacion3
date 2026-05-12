package service;

import algoritmos.Solucion;
import model.Paquete;
import service.filters.CondicionUrgencia;
import service.filters.ContieneAlimentos;

import java.util.ArrayList;


public class Service {

    CamionService camionService;
    PaqueteService paqueteService;

     //La complejidad computacional asociada al constructor del servicio es de 2 * O(2.n) dado que inicializa dos servicios
    // encargados de cada entidad a su evz cada servicio le pide los datos a un repositorio. La complejidad final siempre se mantiene lineal en o(n);

    public Service(String pathCamiones, String pathPaquetes) {
        this.camionService = new CamionService(pathCamiones);
        this.paqueteService = new PaqueteService(pathPaquetes);
    }

    /* La complejidad de la busqueda por codigo de paquete es O(1) dado que en el repositorio de paquetes está almacenado como un hashmap */

    public Paquete servicio1(String codigoPaquete) {

        Paquete paq = paqueteService.getPaquete(codigoPaquete);
        return paq;
    }

    /* La complejidad de la busqueda por condiciones utilizan el mismo metodo: "getPaquetesFiltrados()"
    que busca los elementos que cumplen con la condicion dada en un arreglo haciendo que la Complejidad computacional del metodo sea O(n) */

    public ArrayList<Paquete> servicio2(boolean contiene) {

        ArrayList<Paquete> paquetes = paqueteService.getPaquetesFiltrados(new ContieneAlimentos(contiene));
        return paquetes;
    }

    public ArrayList<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {

        ArrayList<Paquete> paquetes = paqueteService.getPaquetesFiltrados(new CondicionUrgencia(urgenciaMinima, urgenciaMaxima));
        return paquetes;
    }

    public Solucion Servicio4(){
        //devuelve solucion con backtracking
        return null;
    }

    public Solucion Servicio5(){
        // devuelve Solucion con Greedy
        return null;
    }

    public void Servicio6(){
        //compara soluciones obtenidas de servicio 4 y 5;
    }
}

