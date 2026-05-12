package algoritmos;

import model.Camion;
import model.Paquete;
import java.util.ArrayList;


public class Backtracking {

    private Solucion mejorSolucion;

    public Solucion resolver(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes) {

        mejorSolucion = new Solucion();

        backtracking(camiones, paquetes,0, new Solucion());

        return mejorSolucion;
    }

    private void backtracking(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes, int index, Solucion actual) {

    }
}