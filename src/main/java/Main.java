import algoritmos.Algoritmo;
import algoritmos.Backtracking;
import algoritmos.Greedy;
import data.loader.CamionesLoader;
import data.loader.PaquetesLoader;
import model.Paquete;
import model.Solucion;
import repo.CamionRepository;
import repo.PaquetesRepository;
import service.PaqueteService;
import service.Servicios;
import util.Consola;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String pathCamiones = "camiones.csv";
        String pathPaquetes = "paquetes.csv";

        CamionesLoader camionesLoader = new CamionesLoader(pathCamiones);
        PaquetesLoader paquetesLoader = new PaquetesLoader(pathPaquetes);

        CamionRepository camionRepository = new CamionRepository(camionesLoader.cargarDatos(pathCamiones));
        PaquetesRepository paquetesRepository = new PaquetesRepository(paquetesLoader.cargarDatos(pathPaquetes));
        PaqueteService paqueteService = new PaqueteService(paquetesRepository);

        Algoritmo backtracking = new Backtracking();
        Algoritmo greedy = new Greedy();

        Servicios servicio = new Servicios(paquetesRepository, camionRepository, backtracking, greedy, paqueteService);

        Consola.titulo("Consultas de servicios");
        Paquete respuesta1 = servicio.servicio1("P025");
        Consola.presentarPaquete("Servicio 1 - paquete por código", respuesta1);

        List<Paquete> respuesta2 = servicio.servicio2(false);
        Consola.lista("Servicio 2 - paquetes filtrados por alimentos", respuesta2);

        List<Paquete> respuesta3 = servicio.servicio3(70, 95);
        Consola.lista("Servicio 3 - paquetes por rango de urgencia", respuesta3);

        Solucion respuesta4 = servicio.servicio4();
        Consola.presentarSolucionAlgoritmo("Backtracking", respuesta4);

        Solucion respuesta5 = servicio.servicio5();
        Consola.presentarSolucionAlgoritmo("Greedy", respuesta5);
    }
}
