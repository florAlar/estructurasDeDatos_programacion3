package algoritmos;

import model.Camion;
import model.Paquete;

import java.util.ArrayList;

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