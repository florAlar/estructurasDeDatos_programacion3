package repo;

import model.Camion;
import java.util.ArrayList;
import java.util.HashMap;


public class CamionRepository implements Repository<Camion,Integer> {

    private ArrayList<Camion> camiones;
    private HashMap<Integer, Camion> camionesPorId;
    //buscar si conviene un arbol binario de busqueda balanceado para ejercicio 3) busqueda por rango de urgencia. con esto bajariamos a O(logN+K)

    public CamionRepository(ArrayList<Camion> camiones) {
        setCamiones(camiones); // O(n).
    }

    private void setCamiones(ArrayList<Camion> camiones) {

        this.camiones = camiones;

        camionesPorId = new HashMap<>();

        for (Camion camion : camiones) {
            camionesPorId.put(camion.getID(), camion);
        }
        System.out.println(camiones.size() + " camiones cargados en repositorio: Complejidad Asociada O(n)" );
    }

    @Override
    public Camion buscarPorIdentificador(Integer id) {
        return camionesPorId.get(id); // O(1).
    }

    @Override
    public boolean existe(Integer id) {
        return this.buscarPorIdentificador(id) != null; // O(1).
    }

    @Override
    public ArrayList<Camion> obtenerTodos() {
       return new ArrayList<>(camiones); // O(n). - copia defensiva.
    }

    @Override
    public int cantidad() {
        return camiones.size(); // O(1).
    }

    public void imprimirCamiones() {

        if (camiones == null ||  camiones.isEmpty()) {
            System.out.println("No hay camiones cargados.");
            return;
        }

        System.out.println("Camiones cargados:");

        for (Camion camion : camiones) {
            System.out.println(camion);
        } // O(n).
    }
}

