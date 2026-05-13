package algoritmos;

import model.Camion;
import model.Paquete;
import model.Solucion;
import java.util.ArrayList;
import java.util.List;


public class Backtracking extends Algoritmo {

    private Solucion mejorSolucion;

    @Override
    public Solucion resolver(List<Camion> camiones, List<Paquete> paquetes) {

        mejorSolucion = new Solucion();

        backtracking(camiones, paquetes, 0, new Solucion());

        return mejorSolucion;
    }

    private void backtracking(List<Camion> camiones, List<Paquete> paquetes, int index, Solucion actual) {

    }
}