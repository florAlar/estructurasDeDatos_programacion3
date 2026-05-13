package model;

public class Paquete implements Comparable<Paquete> {

    private Integer id_paquete;
    private String codigoPaquete;
    private Double pesoKg;
    private boolean contieneAlimentos;
    private int nivelUrgencia;

    public Paquete(Integer id_paquete, String codigoPaquete, Double pesoKg, boolean contieneAlimentos, int nivelUrgencia) {
        this.id_paquete = id_paquete;
        this.codigoPaquete = codigoPaquete;
        this.pesoKg = pesoKg;
        this.contieneAlimentos = contieneAlimentos;
        this.nivelUrgencia = nivelUrgencia;
    }

    public Integer getID() {
        return id_paquete;
    }

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public Double getPeso() {
        return pesoKg;
    }

    public boolean contieneAlimentos() {
        return contieneAlimentos;
    }

    public int getUrgencia() {
        return nivelUrgencia;
    }

    private void setID(Integer id_paquete) {
        this.id_paquete = id_paquete;
    }

    private void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    private void setPeso(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    private void setAlimentos(boolean contieneAlimentos) {
        this.contieneAlimentos = contieneAlimentos;
    }

    private void setUrgencia(int nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    @Override
    public int compareTo(Paquete otro) {

        // Mayor urgencia primero
        return Integer.compare(otro.nivelUrgencia, this.nivelUrgencia);
    }

    @Override
    public String toString() {
        return "Paquete " + id_paquete + " { " +
                "id_paquete= " + id_paquete +
                ", codigoPaquete= '" + codigoPaquete + '\'' +
                ", pesoKg= " + pesoKg +
                ", contieneAlimentos= " + contieneAlimentos +
                ", nivelUrgencia= " + nivelUrgencia +
                '}';
    }
}