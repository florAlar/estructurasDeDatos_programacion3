package algoritmos;

import model.Camion;
import model.Paquete;
import java.util.ArrayList;
import java.util.HashMap;

public class Solucion {

    private HashMap<Camion, ArrayList<Paquete>> cargas;
    private double pesoNoAsignado;
    private long iteraciones;
    private long solucionesEvaluadas;
    private String algoritmo;

    public Solucion(String algoritmo) {
        this.algoritmo = algoritmo;
        cargas = new HashMap<>();
        pesoNoAsignado = Double.MAX_VALUE; //peso en infinito;
        iteraciones = 0; //tiempo computacional.
        solucionesEvaluadas = 0;
    }

    public boolean esMejorSolucion(Solucion otra) {
        return this.pesoNoAsignado < otra.pesoNoAsignado;
    }

    public void copiarDesde(Estado estado) {

        cargas = new HashMap<>();

        for (Camion camion : estado.getCargas().keySet()) { //O(n)
            cargas.put( camion, new ArrayList<>(estado.getCargas().get(camion))
            );
        }
        pesoNoAsignado = estado.getPesoNoAsignado();
    }

    public void sumarIteracion() {
        iteraciones++;
    }

    public double getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public long getIteraciones() {
        return iteraciones;
    }

    public long solucionesEvaluadas(){
        return solucionesEvaluadas;
    }

    public void sumarSolucionEvaluada(){
        solucionesEvaluadas++;
    }

    @Override
    public String toString() {

        System.out.println("\n------Solucion para " +  algoritmo + "------");
        System.out.println("Peso de paquetes sin cargar: " + pesoNoAsignado + " kg");
        System.out.println("Cantidad de iteraciones para cargar: " + iteraciones );
        System.out.println("Cantidad de soluciones evaluadas: " + solucionesEvaluadas );
        System.out.println("---------------------------------\n");

        String resultado = "";

        resultado += "Detalle de cargas: \n";

        resultado += "\n";

        for (Camion camion : cargas.keySet()) {

            resultado += "Para Camion con Patente " + camion.getPatente() +" se carga: \n" ;

            Double cargaParcial = 0.0;

            for (Paquete paquete : cargas.get(camion)) {
                cargaParcial+=paquete.getPeso();
                resultado += " - Cód.Paquete: " + paquete.getCodigo_Paquete() + " - Peso: " + paquete.getPeso() +"\n";

            }
            resultado += "Peso parcial de carga: " + cargaParcial + " kg / " + camion.getCapacidad() + " Kg. \n";
            resultado += "\n";
        }


        return resultado;
    }
}
