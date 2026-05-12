package service.filters;

import model.Paquete;


public interface Condicion {

    boolean cumple(Paquete paquete);

}

