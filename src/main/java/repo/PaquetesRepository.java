package repo;

import model.Paquete;
import java.util.ArrayList;
import java.util.HashMap;

public class PaquetesRepository implements Repository<Paquete,String> {

    private ArrayList<Paquete> paquetes;
    private HashMap<String, Paquete> paquetesPorCodigo;

    public PaquetesRepository(ArrayList<Paquete> paquetes) {
        setPaquetes(paquetes);
    }

    private void setPaquetes(ArrayList<Paquete> paquetes) {

        this.paquetes = paquetes;

        // O(n) una sola vez al inicializar, luego la lectura la hago por O(1),
        // sacrifico memoria fisica pero si tuviera que recorrer array paquetes cada vez que me piden un camion con id, seria O(n);
        paquetesPorCodigo = new HashMap<>();

        for (Paquete paquete : paquetes) {
            paquetesPorCodigo.put(paquete.getCodigo_Paquete(), paquete);
        }

        System.out.println(paquetes.size() + " paquetes cargados en repositorio: Complejidad Asociada O(n)" );
    }

    @Override
    public Paquete buscarPorIdentificador(String codigo) {
        return paquetesPorCodigo.get(codigo);
    }

    @Override
    public boolean existe(String codigo) {
        return buscarPorIdentificador(codigo) != null;
    }


    @Override
    public ArrayList<Paquete> obtenerTodos() {
        //siempre O(n) para no romper encapsulamiento; si expongo el array original es O(1);
        return new ArrayList<>(paquetes);
    }

    @Override
    public int cantidad() {
        return paquetes.size();
    }

     public void imprimirPaquetes() {

        if (paquetes == null || paquetes.isEmpty()) {
            System.out.println("No hay paquetes.");
            return;
        }

        System.out.println("Camiones cargados:");

        for (Paquete paquete : paquetes) {
            System.out.println(paquete);
        }
    }



}