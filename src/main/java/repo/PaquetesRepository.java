package repo;

import model.Camion;
import model.Paquete;
import java.util.ArrayList;
import java.util.HashMap;

public class PaquetesRepository implements Repository<Paquete,Integer> {

    private ArrayList<Paquete> paquetes;
    private HashMap<Integer, Paquete> paquetesPorId;

    public PaquetesRepository(ArrayList<Paquete> paquetes) {
        setPaquetes(paquetes);
    }

    private void setPaquetes(ArrayList<Paquete> paquetes) {

        this.paquetes = paquetes;

        // O(n) una sola vez al inicializar, luego la lectura la hago por O(1),sacrifico memoria fisica pero si tuviera que recorrer array paquetes cada vez que me piden un camion con id, seria O(n);
        paquetesPorId = new HashMap<>();

        for (Paquete paquete : paquetes) {
            paquetesPorId.put(paquete.getID(), paquete);
        }
    }

    @Override
    public Paquete buscarPorId(Integer id) {
        return paquetesPorId.get(id);
    }

    @Override
    public boolean existe(Integer id) {
        return paquetesPorId.get(id) != null;
    }

    @Override
    public ArrayList<Paquete> obtenerTodos() {
        return new ArrayList<>(paquetes);
    }

    @Override
    public int cantidad() {
        return paquetes.size();
    }

    @Override
    public boolean estaVacio() {
        return this.cantidad() == 0;
    }

    public void imprimirPaquetes() {

        if (paquetes == null || this.estaVacio()) {
            System.out.println("No hay paquetes.");
            return;
        }

        System.out.println("Camiones cargados:");

        for (Paquete paquete : paquetes) {
            System.out.println(paquete);
        }
    }

}