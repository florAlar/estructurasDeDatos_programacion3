import algoritmos.Solucion;
import model.Paquete;
import service.Service;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

    /*
     Los archivos csv se modificaron según los casos de prueba propuestos por la cátedra

      Archivos de paquetes:
      - paquetesT1.csv : 6 paquetes;
      - paquetesT2.csv : 3 paquetes;
      - paquetesT3.csv : 5 paquetes;
      - paquetesT4.csv : 5 paquetes;
      - paquetesT5.csv : 5 paquetes;


      Archivos de camiones:
      - camionesT1.csv : 3 camiones;
      - camionesT2.csv : 3 camiones;
      - camionesT3.csv : 2 camiones;
      - camionesT4.csv : 2 camiones;
      - camionesT5.csv : 2 camiones;

    */

        Service servicio = new Service("camionesT1.csv", "paquetesT1.csv");


        // Service servicio = new Service("camionesT2.csv", "paquetesT2.csv");
        // Service servicio = new Service("camionesT3.csv", "paquetesT3.csv");
        // Service servicio = new Service("camionesT4.csv", "paquetesT4.csv");
        // Service servicio = new Service("camionesT5.csv", "paquetesT5.csv");

        Paquete respuesta1 = servicio.servicio1("P005");

        ArrayList<Paquete> respuesta2 = servicio.servicio2(true);

        ArrayList<Paquete> respuesta3 = servicio.servicio3(20, 90);

        Solucion solucionBacktracking = servicio.servicio4();

        Solucion solucionGreedy = servicio.servicio5();

        servicio.servicio6(solucionBacktracking, solucionGreedy);
    }
}
