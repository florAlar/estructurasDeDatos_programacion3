package algoritmos;

import model.Camion;
import model.Paquete;

import java.util.ArrayList;
import java.util.Comparator;

public class Greedy {

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

                    break;
                }
            }

            if (!cargado) {
                estado.sumarPesoNoAsignado(paquete);
            }
        }

            solucion.sumarSolucionEvaluada();
            solucion.copiarDesde(estado);

            return solucion;


        }}

