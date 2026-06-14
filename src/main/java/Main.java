import algoritmos.Solucion;
import model.Paquete;
import service.Service;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Service servicio = new Service("camiones.csv","paquetes.csv");

        Paquete respuesta1 = servicio.servicio1("P025");

        ArrayList<Paquete> respuesta2 = servicio.servicio2(false);

        ArrayList<Paquete> respuesta3 = servicio.servicio3(70,95);

        Solucion solucionBacktracking = servicio.servicio4();

        Solucion solucionGreedy = servicio.servicio5();

        servicio.servicio6(solucionBacktracking,solucionGreedy);
    }
}
