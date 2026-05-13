package algoritmos;

import model.Camion;
import model.Paquete;
import model.Solucion;

import java.util.List;

public abstract class Algoritmo {

    public abstract Solucion resolver(List<Camion> camiones, List<Paquete> paquetes);

    /**
     * Verifica si un paquete puede asignarse a un camión dado el peso ya cargado.
     * Condiciones:
     *   1. El peso resultante no supera la capacidad del camión.
     *   2. Si el paquete contiene alimentos, el camión debe estar refrigerado.
     */
    protected boolean puedeAsignar(Camion camion, Paquete paquete, double pesoActual) {
        if (pesoActual + paquete.getPeso() > camion.getCapacidad()) {
            return false;
        }
        if (paquete.contieneAlimentos() && !camion.estaRefrigerado()) {
            return false;
        }
        return true;
    }
}
