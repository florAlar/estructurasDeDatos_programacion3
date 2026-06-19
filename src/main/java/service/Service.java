package service;

import algoritmos.Solucion;
import model.Paquete;
import java.util.ArrayList;

public class Service {

    private CamionService camionService;
    private PaqueteService paqueteService;

    /*
        Complejidad temporal: O(n)

        El constructor inicializa ambos servicios.
        Cada servicio carga los datos desde archivos CSV
        y construye sus estructuras internas.

    */

    public Service(String pathCamiones, String pathPaquetes) {
        //System.out.println("**Servicios Creados**");
        System.out.println("\n");
        this.camionService = new CamionService(pathCamiones);
        this.paqueteService = new PaqueteService(pathPaquetes);

        System.out.println("\n");

    }

   /*
       Complejidad temporal: O(1) promedio.

       La búsqueda se realiza mediante HashMap
       utilizando el código del paquete como clave.

    */

    public Paquete servicio1(String codigoPaquete) {

        System.out.println("------Servicio 1------");

        Paquete paquete = paqueteService.getPaquete(codigoPaquete);

        if (paquete != null) {

            System.out.println("\nEl paquete encontrado es:" +  paquete.toString());

        }else{

            System.out.println("\nEl paquete no existe");
        }

        System.out.println("\n");

        return paquete;


    }

    /*
        Complejidad temporal de búsqueda para servicio 2 y 3: O(n)

        El arreglo se encuentra ordenado mediante
        Collections.sort() con complejidad O(n log n),
        por criterio de urgencia de manera tal que reduzca el tiempo
        de ejecución para las búsquedas por rango del servicio 3.

      */

    public ArrayList<Paquete> servicio2(boolean contiene) {

        System.out.println("------Servicio 2------");

        ArrayList<Paquete> paquetes = paqueteService.getPaquetesConAlimentos(contiene);

        if (!paquetes.isEmpty()) {
            System.out.println("\nSe encontró que los siguientes paquetes cumplen con lo solicitado:" + paquetes.toString());
        }else {
            System.out.println("\nNo se encontraron paquetes que coincidan con el criterio de búsqueda");
        }
        System.out.println("\n");
        return paquetes;
    }

    public ArrayList<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {

        System.out.println("------Servicio 3------");

        ArrayList<Paquete> paquetes = paqueteService.getPaquetesEnRango( urgenciaMinima, urgenciaMaxima);

        if (!paquetes.isEmpty()) {
            System.out.println("\nSe encontró que los siguientes paquetes cumplen con lo solicitado:" + paquetes.toString());
        }else {
            System.out.println("\nNo hay paquetes con urgencias solicitada");
        }
        System.out.println("\n");
        return paquetes;
    }

    /*
        Complejidad temporal: O(C^P)

        Ejecuta la resolución utilizando Backtracking,
        donde C representa la cantidad de camiones
        y P la cantidad de paquetes.

      */


    public Solucion servicio4() {

        Solucion solucionBT = camionService.resolverConBacktracking(paqueteService.getPaquetes());
        System.out.println(solucionBT.toString());

        return solucionBT;
    }

    /*
        Complejidad temporal: O(n²)

        Ejecuta la resolución utilizando Greedy.
    */

    public Solucion servicio5() {

        Solucion solucionG = camionService.resolverConGreedy(paqueteService.getPaquetes());
        System.out.println(solucionG.toString());

        return solucionG;
    }

    // Compara métricas y calidad de ambas soluciones.

    public void servicio6(Solucion backtracking,Solucion greedy) {

        System.out.println("\n------ Analisis de resultados ------");

        System.out.println("\n");

        /*
            Cada algoritmo intenta resolver el problema
            de manera diferente.

            Backtracking mide estados del espacio de búsqueda
            explorados, mientras que Greedy mide candidatos
            evaluados para construir una única solución.

            La solución evaluada por Greedy es solamente una,
            construida tomando la decisión localmente óptima
            en cada paso.

         */

        System.out.println(
                "Backtracking generó "
                        + backtracking.getIteraciones()
                        + " estados intermedios del espacio de búsqueda y evaluó "
                        + backtracking.solucionesEvaluadas()
                        + " soluciones candidatas antes de seleccionar la mejor."
        );

        System.out.println(
                "Greedy consideró "
                        + greedy.getIteraciones()
                        + " candidatos durante la construcción de una única solución, "
                        + "que constituye el resultado final de la estrategia.\n"
        );

        System.out.println(
                "Esto refleja la diferencia entre ambas técnicas: \n Backtracking explora todas las alternativas válidas para encontrar la mejor solución,\n mientras que Greedy construye una única solución tomando decisiones localmente óptimas en cada paso."
        );

        System.out.println("\n");

        /*
            Evaluamos la calidad de la solución respecto
            del objetivo del problema: minimizar el peso
            total de los paquetes que quedaron sin cargar.

            Bajo esta óptica, la solución que obtenga
            el menor peso sin asignar será considerada
            la mejor.

            Esto no implica que sea el algoritmo más eficiente
            para resolver el problema. Debe evaluarse si se
            prioriza precisión o aproximación frente al costo
            computacional.

        */

        System.out.println( "Para este escenario planteado con " + camionService.cantidadCamiones()
                + " camiones y " + paqueteService.cantidadPaquetes() + " paquetes: " );


        if (backtracking.getPesoNoAsignado() < greedy.getPesoNoAsignado()) {

            System.out.println( "Backtracking obtuvo mejor solucion." );

        } else if (backtracking.getPesoNoAsignado() > greedy.getPesoNoAsignado()) {
            //teóricamente nunca debería ejecutarse.
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
            }

            /*
                No debería ocurrir que:

                greedy.getIteraciones() > backtracking.getIteraciones()

                debido a la diferencia de complejidad computacional
                entre ambos algoritmos.
             */
        }

        System.out.println("Dado que el peso no asignado BT fue de " + backtracking.getPesoNoAsignado());

        System.out.println("mientras que el peso no asignado por Greedy fue: " + greedy.getPesoNoAsignado());
    }

}
