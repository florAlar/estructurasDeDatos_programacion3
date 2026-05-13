package data.loader;

import model.Paquete;

public class PaquetesLoader extends CsvLoader<Paquete> {

    private String ruta ;

    public PaquetesLoader(String ruta) {
        this.ruta = ruta;
    }


    @Override
    protected Paquete parsearLinea(String linea) {

        String[] partes = linea.split(";");

        int id_paquete = Integer.parseInt(partes[0]);
        String codigoPaquete = partes[1];
        double pesoKg = Double.parseDouble(partes[2]);
        boolean contieneAlimentos = partes[3].equals("1");
        int nivelUrgencia = Integer.parseInt(partes[4]);

        return new Paquete(id_paquete, codigoPaquete, pesoKg, contieneAlimentos, nivelUrgencia);
    }
}
