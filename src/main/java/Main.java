import data.loader.CamionesLoader;
import data.loader.PaquetesLoader;
import model.Camion;
import repo.CamionRepository;
import repo.PaquetesRepository;
import repo.Repository;

public class Main {

    public static void main(String[] args) {

        CamionesLoader camionesLoader = new CamionesLoader("camiones.csv");
        PaquetesLoader paquetesLoader = new PaquetesLoader("paquetes.csv");

        CamionRepository camionRepo = camionesLoader.almacenarEnRepo();
        PaquetesRepository paquetesRepo = paquetesLoader.almacenarEnRepo();

        camionRepo.imprimirCamiones();
        paquetesRepo.imprimirPaquetes();
    }
}