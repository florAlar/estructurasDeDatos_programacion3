import algoritmos.Solucion;
import model.Paquete;
import service.Service;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Service servicio = new Service("camiones.csv","paquetes.csv");

        Paquete respuesta1 = servicio.servicio1("P005");

        ArrayList<Paquete> respuesta2 = servicio.servicio2(true);

        ArrayList<Paquete> respuesta3 = servicio.servicio3(20,50);

        Solucion solucionBacktracking = servicio.servicio4();

        Solucion solucionGreedy = servicio.servicio5();

        servicio.servicio6(solucionBacktracking,solucionGreedy);
    }
}
