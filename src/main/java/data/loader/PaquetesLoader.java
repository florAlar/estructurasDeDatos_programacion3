package data.loader;

import model.Paquete;

public class PaquetesLoader extends CsvLoader<Paquete> {

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
}