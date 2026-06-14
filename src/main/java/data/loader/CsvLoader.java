package data.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import repo.Repository;

public abstract class CsvLoader<T> {

        public ArrayList<T> cargarDatos(String nombreArchivo) {

            ArrayList<T> elementos = new ArrayList<>();

            try {
                InputStream is = getClass().getResourceAsStream("/" + nombreArchivo);

                if (is == null) {
                    throw new CsvFormatoInvalidoExcepcion("no se encontro el archivo " + nombreArchivo);
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String primeraLinea = br.readLine();

                if (primeraLinea == null || primeraLinea.isBlank()) {
                    throw new CsvFormatoInvalidoExcepcion("El archivo está vacío.");
                }

                int cantidadEsperada;

                try {
                    cantidadEsperada = Integer.parseInt(primeraLinea.trim());
                } catch (NumberFormatException e) {
                    throw new CsvFormatoInvalidoExcepcion("La primera línea debe indicar la cantidad de registros.");
                }

                String linea;
                int cantidadLeida = 0;
                int nroLinea = 2;

                while ((linea = br.readLine()) != null) {


                    if (linea.isBlank()) {
                        throw new CsvFormatoInvalidoExcepcion("Línea vacía: " + nroLinea);
                    }

                    try {
                    T elemento = parsearLinea(linea);
                    elementos.add(elemento);

                    }catch (Exception e) {
                            throw new CsvFormatoInvalidoExcepcion("Formato inválido en línea " + nroLinea + ": " + linea);
                    }
                    cantidadLeida++;
                    nroLinea++;
                }
                br.close();
                if (cantidadLeida != cantidadEsperada) {
                    throw new CsvFormatoInvalidoExcepcion("Cantidad de registros incorrecta. " + "Se esperaban " + cantidadEsperada + " y se encontraron " + cantidadLeida);
                }

            } catch (IOException e) {
                throw new RuntimeException("Error leyendo archivo", e);
            }
            return elementos;
        }

        protected abstract T parsearLinea(String linea);

        public abstract Repository almacenarEnRepo();
    }