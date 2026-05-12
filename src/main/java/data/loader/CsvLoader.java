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
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                if (is == null) { throw new RuntimeException("no se encontro el archivo " + nombreArchivo);}

                String linea;

                br.readLine();

                while ((linea = br.readLine()) != null) {
                    T elemento = parsearLinea(linea);
                    elementos.add(elemento);
                }

                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return elementos;
        }

        protected abstract T parsearLinea(String linea);

        public abstract Repository almacenarEnRepo();
    }