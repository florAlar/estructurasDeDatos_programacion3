package service.filters;

import model.Paquete;

public class ContieneAlimentos implements Condicion {

    private boolean contiene;

    public ContieneAlimentos(boolean contiene) {
        this.contiene = contiene;
    }

    @Override
    public boolean cumple(Paquete paquete) {
        return paquete.contieneAlimentos() == contiene;
    }
}