package algoritmos;

import model.Camion;
import model.Paquete;

import java.util.ArrayList;
import java.util.HashMap;

public class Estado {

    private HashMap<Camion, ArrayList<Paquete>> cargas;
    private HashMap<Camion, Double> cargaActual;
    private double pesoNoAsignado;

    public Estado(ArrayList<Camion> camiones) {

        cargas = new HashMap<>();
        cargaActual = new HashMap<>();

        for (Camion c : camiones) {
            cargas.put(c, new ArrayList<>());
            cargaActual.put(c, 0.0);
        }
        pesoNoAsignado = 0;
    }

    public boolean puedeCargar(Camion camion, Paquete paquete) {

        if (paquete.contieneAlimentos() && !camion.estaRefrigerado()){ return false; }
        double carga = cargaActual.get(camion);

        return carga + paquete.getPeso() <= camion.getCapacidad();
    }

    public void cargar(Camion camion, Paquete paquete) {
        cargas.get(camion).add(paquete);
        cargaActual.put(camion, cargaActual.get(camion) + paquete.getPeso());
    }

    public void descargar(Camion camion, Paquete paquete) {
        cargas.get(camion).remove(paquete);
        cargaActual.put(camion, cargaActual.get(camion) - paquete.getPeso());
    }

    public void sumarPesoNoAsignado(Paquete paquete) {
        pesoNoAsignado += paquete.getPeso();
    }

    public void restarPesoNoAsignado(Paquete paquete) {
        pesoNoAsignado -= paquete.getPeso();
    }

    public double getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public HashMap<Camion, ArrayList<Paquete>> getCargas() {
        return cargas;
    }
}