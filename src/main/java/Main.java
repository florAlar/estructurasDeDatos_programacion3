import algoritmos.Solucion;
import model.Paquete;
import service.Service;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

    /*
      Para probar distintos escenarios, modificar la ruta de los archivos CSV
      utilizados al instanciar el Service.

      Archivos de paquetes:
      - paquetes.csv    : 12 paquetes;
      - paquetesOP2.csv : 10 paquetes;
      - paquetesOP3.csv : 20 paquetes;

      Archivos de camiones:
      - camiones.csv    : 3 camiones;
      - camionesOP2.csv : 4 camiones;
      - camionesOP3.csv : 8 camiones;

    */

        Service servicio = new Service("camiones.csv", "paquetes.csv");

        // Service servicio = new Service("camionesOP2.csv", "paquetesOP3.csv");

        Paquete respuesta1 = servicio.servicio1("P005");

        ArrayList<Paquete> respuesta2 = servicio.servicio2(true);

        ArrayList<Paquete> respuesta3 = servicio.servicio3(20, 90);

        Solucion solucionBacktracking = servicio.servicio4();

        Solucion solucionGreedy = servicio.servicio5();

        servicio.servicio6(solucionBacktracking, solucionGreedy);
    }
}
