package service;

import algoritmos.Solucion;
import model.Paquete;
import service.filters.CondicionUrgencia;
import service.filters.ContieneAlimentos;

import java.util.ArrayList;

public class Service {

    private CamionService camionService;
    private PaqueteService paqueteService;

    /*  Complejidad temporal: O(n)
        El constructor inicializa ambos servicios.
        Cada servicio carga los datos desde archivos CSV
        y construye sus estructuras internas. */

    public Service(String pathCamiones, String pathPaquetes) {
        System.out.println("------Servicios Creados------");

        this.camionService = new CamionService(pathCamiones);
        this.paqueteService = new PaqueteService(pathPaquetes);

        System.out.println("-----------------------------");

    }

    /*  Complejidad temporal: O(1) promedio.
        La búsqueda se realiza mediante HashMap utilizando el código del paquete como clave. */

    public Paquete servicio1(String codigoPaquete) {
        return paqueteService.getPaquete(codigoPaquete);
    }

    /*  Complejidad temporal de busqueda en array: O(n).
        Ambos servicios (2 y 3) utilizan el mismo recorrido secuencial de la colección
        filtrando los paquetes que cumplan la condición solicitada. */

    public ArrayList<Paquete> servicio2(boolean contiene) {
        return paqueteService.getPaquetesFiltrados(new ContieneAlimentos(contiene));
    }

    public ArrayList<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        return paqueteService.getPaquetesFiltrados( new CondicionUrgencia(urgenciaMinima, urgenciaMaxima));
    }

    // Ejecuta la resolución utilizando Backtracking. sera O(un monton xD)

    public Solucion servicio4() {

        return camionService.resolverConBacktracking(paqueteService.getPaquetes());
    }

    // Ejecuta la resolución utilizando Greedy. sera O(ni idea cuantos xD)

    public Solucion servicio5() {

        return camionService.resolverConGreedy(paqueteService.getPaquetes());
    }

    // Compara métricas y calidad de ambas soluciones.

    public void servicio6() {

        Solucion backtracking = servicio4();

        Solucion greedy = servicio5();

        // comparar resultados
    }
}
