package service.filters;

import model.Paquete;

public class ContieneAlimentos implements Condicion {

    private boolean contiene;

    public ContieneAlimentos(boolean contiene) {
        this.contiene = contiene;
        System.out.println("Condicion de busqueda por contiene alimentos con valores: "+contiene);
    }

    @Override
    public boolean cumple(Paquete paquete) {
        return paquete.contieneAlimentos() == contiene;
    }
}