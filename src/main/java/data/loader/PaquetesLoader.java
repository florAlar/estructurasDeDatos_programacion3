package data.loader;

import model.Paquete;
import repo.PaquetesRepository;

import java.util.ArrayList;

public class PaquetesLoader extends CsvLoader<Paquete> {

    private String ruta ;

    public PaquetesLoader(String ruta) {
        setRuta(ruta);
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    protected Paquete parsearLinea(String linea) {

        String[] partes = linea.split(";");

        int id_paquete = Integer.parseInt(partes[0]);
        String codigo_paquete = partes[1];
        double peso_kg = Double.parseDouble(partes[2]);
        boolean contiene_alimentos = partes[3].equals("1");
        int nivel_urgencia = Integer.parseInt(partes[4]);

        return new Paquete(id_paquete, codigo_paquete, peso_kg, contiene_alimentos, nivel_urgencia);
    }

    @Override
    public PaquetesRepository almacenarEnRepo() {
        ArrayList<Paquete> elementos = new ArrayList<>(super.cargarDatos(this.ruta));
        return new PaquetesRepository(elementos);
    }
}