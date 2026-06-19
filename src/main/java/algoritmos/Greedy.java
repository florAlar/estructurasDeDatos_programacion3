package algoritmos;

import model.Camion;
import model.Paquete;

import java.util.ArrayList;
import java.util.Comparator;

public class Greedy {

     /**
      Para esta estrategia se considero como seleccion de candidato el paquetes de mayor peso,
      asignando cada uno al primer camion que pueda transportarlo (respetando las restricciones del problema)
      una vez asignado cada paquete, no se reevalúan asignaciones previas.
      Si ningún camión puede cargarlo, el paquete queda sin asignar.
      El algoritmo busca construir una solución rápidamente sin explorar alternativas ni realizar retrocesos.
     */

    public Solucion resolver(ArrayList<Paquete> paquetes, ArrayList<Camion> camiones) {

        //Ordenamos los paquetes los que tienen mayor peso primero.
        paquetes.sort(Comparator.comparingDouble(Paquete::getPeso).reversed());

        Solucion solucion = new Solucion("Greedy");

        Estado estado = new Estado(camiones);

        for (Paquete paquete : paquetes) {

            boolean cargado = false;

            for (Camion camion : camiones) {

                solucion.sumarIteracion();

                if (estado.puedeCargar(camion, paquete)) {

                    estado.cargar(camion, paquete);

                    cargado = true;

                    break;// se puede incluir el break para cortar antes??

                }
            }

            if (!cargado) {
                estado.sumarPesoNoAsignado(paquete);
            }
        }

        solucion.sumarSolucionEvaluada();
        solucion.copiarDesde(estado);

        return solucion;


    }
}

