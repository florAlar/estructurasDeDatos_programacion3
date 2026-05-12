package service;

import model.Paquete;

import java.util.List;

public class Service {


    //Completar con las estructuras y métodos privados que se requieran.
    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Service(String pathCamiones, String pathPaquetes) {
    }
    /*
     * Expresar la complejidad temporal del servicio 1.
     * Dado un código de paquete (String), retornar toda la información del paquete asociado. En caso de no existir, retornar null.
     */
    public Paquete servicio1(String codigoPaquete) { return null;}


    /*
     * Expresar la complejidad temporal del servicio 2.
     * Dado un booleano que indica si se buscan paquetes que contienen alimentos (true) o que no contienen alimentos (false), retornar el listado de paquetes correspondiente.
     */
    public List<Paquete> servicio2(boolean contieneAlimentos) {return null; }


    /*
     * Expresar la complejidad temporal del servicio 3.
     * Dados dos valores enteros que representan un nivel de urgencia mínimo y máximo,
     * retornar todos los paquetes cuyo nivel de urgencia se encuentre dentro de ese rango (inclusive).
     */
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) { return null; }
}

