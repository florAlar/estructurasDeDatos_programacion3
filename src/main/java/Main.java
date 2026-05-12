import data.loader.CamionesLoader;
import data.loader.PaquetesLoader;
import model.Camion;
import model.Paquete;
import repo.CamionRepository;
import repo.PaquetesRepository;
import repo.Repository;
import service.Service;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Service servicio = new Service("camiones.csv","paquetes.csv");

        Paquete respuesta1 = servicio.servicio1("P025");

        ArrayList<Paquete> respuesta2 = servicio.servicio2(false);

        ArrayList<Paquete> respuesta3 = servicio.servicio3(70,95);




    }
}