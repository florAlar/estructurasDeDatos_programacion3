package repo;

import model.Paquete;
import java.util.ArrayList;
import java.util.HashMap;

public class PaqueteRepository {

    private ArrayList<Paquete> paquetes;
    private HashMap<Integer, Paquete> paquetesPorId;

    public PaqueteRepository(ArrayList<Paquete> paquetes) {
        setPaquetes(paquetes);
    }

    public Paquete buscarPorId(int id) {
        return paquetesPorId.get(id);
    }

    public ArrayList<Paquete> obtenerTodos() {
        return paquetes;
    }

    private void setPaquetes(ArrayList<Paquete> paquetes) {

        this.paquetes = paquetes;

        // O(n) una sola vez al inicializar, luego la lectura la hago por O(1),sacrifico memoria fisica pero si tuviera que recorrer array paquetes cada vez que me piden un camion con id, seria O(n);
        paquetesPorId = new HashMap<>();

        for (Paquete paquete : paquetes) {
            paquetesPorId.put(paquete.getID(), paquete);
        }
    }

}