package model;

public class Camion implements Comparable<Camion> {

    private Integer idCamion;
    private String patente;
    private boolean estaRefrigerado;
    private Double capacidadKg;

    public Camion(Integer idCamion, String patente, boolean estaRefrigerado, Double capacidadKg) {
        this.idCamion = idCamion;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidadKg = capacidadKg;
    }

    public Integer getID() {
        return idCamion;
    }

    public String getPatente() {
        return patente;
    }

    public boolean estaRefrigerado() {
        return estaRefrigerado;
    }

    public Double getCapacidad() {
        return capacidadKg;
    }

    private void setID(Integer idCamion) {
        this.idCamion = idCamion;
    }

    private void setPatente(String patente) {
        this.patente = patente;
    }

    private void setRefrigerado(boolean estaRefrigerado) {
        this.estaRefrigerado = estaRefrigerado;
    }

    private void setCapacidad(Double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    @Override
    public int compareTo(Camion camion) {
        // Mayor capacidad primero
        return Double.compare(camion.capacidadKg, this.capacidadKg);
    }

    @Override
    public String toString() {
        return "Camion " + idCamion + " { " +
                "idCamion= " + idCamion +
                ", patente=' " + patente + '\'' +
                ", estaRefrigerado= " + estaRefrigerado +
                ", capacidadKg= " + capacidadKg +
                '}';
    }
}