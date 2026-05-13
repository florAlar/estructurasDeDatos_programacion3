package util;

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
}
