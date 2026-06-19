package algoritmos;

import model.Camion;
import model.Paquete;

import java.util.ArrayList;

  /**
    La estrategia consiste en explorar recursivamente todas las posibles
    asignaciones de todos los paquetes a camiones. Para cada paquete se evalúan
    todas las alternativas válidas, en cada paso se decide qué hacer con el paquete actual: cargarlo en
    alguno de los camiones compatibles o dejarlo sin cargar.
    Cada decisión genera una rama del árbol de exploracion, construyendo las soluciones.

    Cuando se alcanza una solución completa (la hoja), se compara con la mejor encontrada hasta el momento
    y se conserva aquella que minimiza el peso total que no fue cargado en ningun camion.
  */

public class Backtracking {

    private Solucion mejorSolucion;

    public Solucion resolver(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes) {

        mejorSolucion = new Solucion("Backtracking");

        Estado estado = new Estado(camiones);

        backtracking(camiones, paquetes, 0, estado);

        return mejorSolucion;
    }


    private void backtracking(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes, int index, Estado estado) {

        mejorSolucion.sumarIteracion();

        if (index == paquetes.size()) {
            if (estado.getPesoNoAsignado() < mejorSolucion.getPesoNoAsignado()) {
                mejorSolucion.copiarDesde(estado);
            }

            mejorSolucion.sumarSolucionEvaluada();
            return;
        }

        Paquete actual = paquetes.get(index);

        for (Camion camion : camiones) {

            if (estado.puedeCargar(camion, actual)) {

                estado.cargar(camion, actual);
                backtracking(camiones, paquetes, index + 1, estado);
                estado.descargar(camion, actual);
            }
        }

        estado.sumarPesoNoAsignado(actual);
        backtracking(camiones, paquetes,index + 1, estado);
        estado.restarPesoNoAsignado(actual);
    }
}