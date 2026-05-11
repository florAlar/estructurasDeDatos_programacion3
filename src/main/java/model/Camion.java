package model;

public class Camion {

    private Integer id_camion;
    private String patente;
    private boolean esta_refrigerado;
    private Double capacidad_kg;

    public Camion(Integer id_camion, String patente, boolean esta_refrigerado, Double capacidad_kg) {
       setID(id_camion);
       setPatente(patente);
       setRefrigerado(esta_refrigerado);
       setCapacidad(capacidad_kg);
    }

    public Integer getID() {
        return id_camion;
    }
    public String getPatente() {
        return patente;
    }
    public boolean estaRefrigerado() {
        return esta_refrigerado;
    }

    public Double getCapacidad() {
        return capacidad_kg;
    }


    public void setID(Integer id_camion) {
        this.id_camion = id_camion;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setRefrigerado(boolean esta_refrigerado) {
        this.esta_refrigerado = esta_refrigerado;
    }

    public void setCapacidad(Double capacidad_kg) {
        this.capacidad_kg = capacidad_kg;
    }

}
