package model;

public class Paquete implements Comparable<Paquete> {

    private Integer id_paquete;
    private String codigo_paquete;
    private Double peso_kg;
    private boolean contiene_alimentos;
    private int nivel_urgencia;

    public Paquete(Integer id_paquete, String codigo_paquete, Double peso_kg, boolean contiene_alimentos, int nivel_urgencia) {
        setID(id_paquete);
        setCodigo_Paquete(codigo_paquete);
        setPeso(peso_kg);
        setAlimentos(contiene_alimentos);
        setUrgencia(nivel_urgencia);
    }

    public Integer getID() {
        return id_paquete;
    }

    public String getCodigo_Paquete() {
        return codigo_paquete;
    }

    public Double getPeso() {
        return peso_kg;
    }

    public boolean contieneAlimentos() {
        return contiene_alimentos;
    }

    public int getUrgencia() {
        return nivel_urgencia;
    }

    public void setID(Integer id_paquete) {
        this.id_paquete = id_paquete;
    }

    public void setCodigo_Paquete(String codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public void setPeso(Double peso_kg) {
        this.peso_kg = peso_kg;
    }

    public void setAlimentos(boolean contiene_alimentos) {
        this.contiene_alimentos = contiene_alimentos;
    }

    public void setUrgencia(int nivel_urgencia) {
        this.nivel_urgencia = nivel_urgencia;
    }

    @Override
    public int compareTo(Paquete otro) {

        // Mayor urgencia primero
        return Integer.compare(otro.nivel_urgencia, this.nivel_urgencia);
    }

    @Override
    public String toString() {
        return "\n - Paquete " + id_paquete +": " + "Código = '" + codigo_paquete +  " - Peso = " + peso_kg + "Kg." +
                " - Contiene alimentos = " + contiene_alimentos  + " - Urgencia = " + nivel_urgencia ;
    }
}