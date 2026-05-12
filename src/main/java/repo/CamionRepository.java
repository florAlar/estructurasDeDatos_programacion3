package repo;

import model.Camion;
import java.util.ArrayList;
import java.util.HashMap;


public class CamionRepository implements Repository<Camion,Integer> {

    private ArrayList<Camion> camiones;
    private HashMap<Integer, Camion> camionesPorId;

    public CamionRepository(ArrayList<Camion> camiones) {
        setCamiones(camiones);
    }

    // para busqueda posicional es O(1), mas eficiente para recorridos completos
    // aunque sea O(n) el recorrido es por celdas constiguas de memoria, menos costoso
    // para busqueda por acceso por clave (idCamion)
    // sacrifico un poco de memoria pero el costo computacional es menor para ese tipo de busqueda.
    // O(n) 1 unica vez cuando inicializo el hashmap, luego accedo por O(1) en la lectura;

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
        return camionesPorId.get(id);
    }

    @Override
    public boolean existe(Integer id) {
        return this.buscarPorIdentificador(id) != null;
    }

    @Override
    public ArrayList<Camion> obtenerTodos() {
        //siempre O(n) para no romper encapsulamiento; si expongo el array original es O(1);
        return new ArrayList<>(camiones);
    }

    @Override
    public int cantidad() {
        return camiones.size();
    }

    public void imprimirCamiones() {

        if (camiones == null ||  camiones.isEmpty()) {
            System.out.println("No hay camiones cargados.");
            return;
        }

        System.out.println("Camiones cargados:");

        for (Camion camion : camiones) {
            System.out.println(camion);
        }
    }
}

