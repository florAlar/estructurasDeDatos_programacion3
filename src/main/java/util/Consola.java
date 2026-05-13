package util;

import model.Paquete;
import model.Solucion;

import java.util.List;

public class Consola {

    private static final String SEPARADOR = "─".repeat(50);

    public static void info(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }

    public static void resultado(String titulo, Object valor) {
        System.out.println(">> " + titulo + ": " + valor);
    }

    public static void separador() {
        System.out.println(SEPARADOR);
    }

    public static void titulo(String texto) {
        separador();
        System.out.println("  " + texto);
        separador();
    }

    public static void lista(String titulo, List<?> elementos) {
        if (elementos == null || elementos.isEmpty()) {
            resultado(titulo, "sin resultados");
            return;
        }

        System.out.println(">> " + titulo + ":");
        for (Object elemento : elementos) {
            System.out.println("   - " + elemento);
        }
    }

    public static void presentarSolucionAlgoritmo(String nombreAlgoritmo, Solucion solucion) {
        titulo("Resultado " + nombreAlgoritmo);
        System.out.println("Solución obtenida:");

        // TODO: Imprimir solución con toString delegando a Solución
        System.out.println("Falta implementar presentación de solución");
    }

    public static void presentarPaquete(String titulo, Paquete paquete) {
        if (paquete == null) {
            resultado(titulo, "no encontrado");
            return;
        }
        resultado(titulo, paquete);
    }
}
