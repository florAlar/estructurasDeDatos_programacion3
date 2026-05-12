package repo;
import model.Camion;
import java.util.ArrayList;
import java.util.HashMap;



    public class CamionRepository {

        private ArrayList<Camion> camiones;
        private HashMap<Integer, Camion> camionesPorId;

        public CamionRepository(ArrayList<Camion> camiones) {
            setCamiones(camiones);
        }

        public Camion buscarPorId(int id) {
            return camionesPorId.get(id);
        }

        private void setCamiones(ArrayList<Camion> camiones) {


            // para busqueda posicional es O(1), mas eficiente para recorridos completos
            // aunque sea O(n) el recorrido es por celdas constiguas de memoria, menos costoso
            this.camiones = camiones;

            // para busqueda por acceso por clave (idCamion)
            // sacrifico un poco de memoria pero el costo computacional es menor para ese tipo de busqueda.
            camionesPorId = new HashMap<>();
            // O(n) 1 unica vez cuando inicializo el hashmap, luego accedo por O(1) en la lectura;
            for (Camion camion : camiones) {
                camionesPorId.put(camion.getID(), camion);
            }
        }
    }