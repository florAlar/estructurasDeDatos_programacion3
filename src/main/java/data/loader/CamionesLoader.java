package data.loader;
import model.Camion;
import repo.CamionRepository;

import java.util.ArrayList;

public class CamionesLoader extends CsvLoader<Camion> {

    private String ruta;

    public CamionesLoader(String ruta) {
        this.ruta = ruta;
    }

    @Override
    protected Camion parsearLinea(String linea) {

        String[] partes = linea.split(";");

        if (partes.length != 4) {
            throw new CsvFormatoInvalidoExcepcion("Un camión debe tener 4 datos.");
        }

        int id_camion = Integer.parseInt(partes[0]);
        String patente = partes[1];

        if (!partes[2].equals("0") && !partes[2].equals("1")) {
            throw new CsvFormatoInvalidoExcepcion("El campo refrigerado debe ser 0 o 1.");
        }

        boolean esta_refrigerado = partes[2].equals("1");
        double capacidad_kg = Double.parseDouble(partes[3]);

        if (capacidad_kg <= 0) {
            throw new CsvFormatoInvalidoExcepcion("El camion tiene que tener capacidad de carga.");
        }

        return new Camion(id_camion, patente, esta_refrigerado, capacidad_kg);
    }

    @Override
    public CamionRepository almacenarEnRepo() {
        ArrayList<Camion> camiones = new ArrayList<>(super.cargarDatos(this.ruta));
        return new CamionRepository(camiones);
    }
}