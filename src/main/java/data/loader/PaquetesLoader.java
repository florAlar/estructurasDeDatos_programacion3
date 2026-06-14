package data.loader;

import model.Paquete;
import repo.PaquetesRepository;

import java.util.ArrayList;

public class PaquetesLoader extends CsvLoader<Paquete> {

    private String ruta ;

    public PaquetesLoader(String ruta) {
        this.ruta = ruta;
    }


    @Override
    protected Paquete parsearLinea(String linea) {

        String[] partes = linea.split(";");

        if (partes.length != 5) {
            throw new CsvFormatoInvalidoExcepcion("Formato de paquete inválido. Se esperaban 5 datos: " + linea);
        }

        try{

            int id_paquete = Integer.parseInt(partes[0]);
            String codigo_paquete = partes[1];

            if (codigo_paquete.isEmpty()) {
                throw new CsvFormatoInvalidoExcepcion("El código de paquete no puede estar vacío.");
            }

            double peso_kg = Double.parseDouble(partes[2]);

            if (peso_kg <= 0) {
                throw new CsvFormatoInvalidoExcepcion("El peso debe ser positivo.");
            }

            if (!partes[3].equals("0") && !partes[3].equals("1")) {
                throw new CsvFormatoInvalidoExcepcion("El campo contiene_alimentos debe ser 0 o 1.");
            }

            boolean contiene_alimentos = partes[3].equals("1");
            int nivel_urgencia = Integer.parseInt(partes[4]);

            if (nivel_urgencia < 1 ||nivel_urgencia > 100) {
                throw new CsvFormatoInvalidoExcepcion("La urgencia debe estar entre 1 y 100.");
            }

            return new Paquete(id_paquete, codigo_paquete, peso_kg, contiene_alimentos, nivel_urgencia);

        }catch (NumberFormatException e) {
            throw new CsvFormatoInvalidoExcepcion("Faltan datos en: " + linea);
        }
    }

    @Override
    public PaquetesRepository almacenarEnRepo() {
        ArrayList<Paquete> elementos = new ArrayList<>(super.cargarDatos(this.ruta));
        return new PaquetesRepository(elementos);
    }
}