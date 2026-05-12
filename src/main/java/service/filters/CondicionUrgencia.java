package service.filters;

import model.Paquete;

public class CondicionUrgencia implements Condicion {

    private int min;
    private int max;




    public CondicionUrgencia(int min, int max) {
        this.min = min;
        this.max = max;
       System.out.println("Condicion de busqueda por urgencia con valores: "+"min: " + min + " max: " + max);
    }

    @Override
    public boolean cumple(Paquete paquete) {

        return paquete.getUrgencia() >= min
                && paquete.getUrgencia() <= max;
    }
}