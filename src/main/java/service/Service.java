package service;

import algoritmos.Solucion;
import model.Paquete;
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

        System.out.println("\n");

    }

    /*  Complejidad temporal: O(1) promedio.
        La búsqueda se realiza mediante HashMap utilizando el código del paquete como clave. */

    public Paquete servicio1(String codigoPaquete) {

        System.out.println("------Ejercicio 1------");

        Paquete paquete = paqueteService.getPaquete(codigoPaquete);

        if (paquete != null) {

            System.out.println("El paquete encontrado es:" +  paquete.toString());

            System.out.println("\n");

            return paquete;
        }else{
            System.out.println("El paquete no existe");
        }

        System.out.println("\n");

        return paquete;


    }

    /*  Complejidad temporal de busqueda en array: O(n).
        servicios 2 y 3 utilizan el mismo recorrido secuencial de la colección ordenada
        filtrando los paquetes que cumplan la condición solicitada.
        el array se encuentra ordenado por collection.sort O(n.logN) por orden de urgencia de camion de manera tal que reduzca el tiempo de eejccucion
        para la vbusqueda por rango de urgencia.
        para el tamaño de la entrada del problema no es necesario duplicar nuevamente la memoria para almacenar
        los paquetes en un hasmap con criterio de contiene o no contiene alimentos.

     */

    public ArrayList<Paquete> servicio2(boolean contiene) {

        System.out.println("------Ejercicio 2------");

        ArrayList<Paquete> paquetes = paqueteService.getPaquetesConAlimentos(contiene);

        if (!paquetes.isEmpty()) {
            System.out.println("Se encontró que los siguientes paquetes cumplen con lo solicitado:" + paquetes.toString());
        }else {
            System.out.println("no se encontraron paquetes que coincidan con el criterio de búsqueda");
        }
        System.out.println("\n");
        return paquetes;
    }

    public ArrayList<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {

        System.out.println("------Ejercicio 1------");

        ArrayList<Paquete> paquetes = paqueteService.getPaquetesEnRango( urgenciaMinima, urgenciaMaxima);

        if (!paquetes.isEmpty()) {
            System.out.println("Se encontró que los siguientes paquetes cumplen con lo solicitado:" + paquetes.toString());
        }else {
            System.out.println("No hay paquetes con urgencias solicitada");
        }
        System.out.println("\n");
        return paquetes;
    }

    // Ejecuta la resolución utilizando Backtracking. sera O(C^P) camiones elevado a la cantidad de paquetes

    public Solucion servicio4() {

        Solucion solucionBT = camionService.resolverConBacktracking(paqueteService.getPaquetes());
        System.out.println(solucionBT.toString());

        return solucionBT;
    }

    // Ejecuta la resolución utilizando Greedy. O(n^2)

    public Solucion servicio5() {

        Solucion solucionG = camionService.resolverConGreedy(paqueteService.getPaquetes());
        System.out.println(solucionG.toString());

        return solucionG;
    }

    // Compara métricas y calidad de ambas soluciones.

    public void servicio6(Solucion backtracking,Solucion greedy) {

        System.out.println("\n------ Analisis de resultados ------");

        System.out.println("\n");

        System.out.println("Peso no asignado BT: " + backtracking.getPesoNoAsignado());

        System.out.println("Peso no asignado Greedy: " + greedy.getPesoNoAsignado());

        System.out.println("\n");

        System.out.println( "Soluciones evaluadas por Backtracking: " + backtracking.solucionesEvaluadas());

        System.out.println("Solucion evaluada por greedy solo 1 , la mejor local. demostrado por: " + greedy.solucionesEvaluadas());

        System.out.println("\n");

        System.out.println( "Iteraciones BT: " + backtracking.getIteraciones());

        System.out.println("Iteraciones Greedy: "+ greedy.getIteraciones() );

        System.out.println("\n");

        //evaluamos la calidad de la solucion respecto del objetivo en este caso fué minimizar el peso total de los paquetes que quedaron sin cargar.
        //bajo esta optica, la solucion que obtenga el menor peso sin cargar será la mejor.
        //no implica que sea el algoritmo mas eficiente para resolverlo.
        //debemos evaluar si queremos precision o aproximacion vs. costo computacional.

        if (backtracking.getPesoNoAsignado() < greedy.getPesoNoAsignado()) {

            System.out.println( "Backtracking obtuvo mejor solucion." );

        } else if (backtracking.getPesoNoAsignado() > greedy.getPesoNoAsignado()) {

            System.out.println("Greedy obtuvo mejor solucion.");

        } else {

            System.out.println( "Ambos obtuvieron la misma calidad de solucion." );

            if (backtracking.getPesoNoAsignado() == 0 &&
                    greedy.getPesoNoAsignado() == 0) {

                if (greedy.getIteraciones() < backtracking.getIteraciones()) {

                    System.out.println(
                            "Ambos algoritmos encontraron una solución óptima (0 kg sin asignar). "
                                    + "Sin embargo, Greedy presentó un menor costo computacional al "
                                    + "requerir menos iteraciones que Backtracking."
                    );
                }

                // no va a ocurrir que : greedy.getIteraciones() > backtracking.getIteraciones(); por la complejidad computacional de cada algoritmo.
            }

        }

        System.out.println(
                "\n _______________________\n" +
                "|prog 3 :D              |h_ __\n" +
                "|                       ||=|##L_\n" +
                "|________________.====._||_|__._]\n" +
                " `(_)(_)`       `(_)(_)\"\"\"=\"=(_)");

    }
}
