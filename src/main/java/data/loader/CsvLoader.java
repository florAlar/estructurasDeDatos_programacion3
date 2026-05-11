package data.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public abstract class CsvLoader<T> {



        public ArrayList<T> cargar(String archivo) {

            ArrayList<T> elementos = new ArrayList<>();

            try {

                InputStream is = getClass().getResourceAsStream(archivo);

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

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
    }