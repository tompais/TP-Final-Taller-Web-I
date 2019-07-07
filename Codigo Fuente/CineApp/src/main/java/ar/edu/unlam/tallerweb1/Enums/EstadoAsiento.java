package ar.edu.unlam.tallerweb1.Enums;

public enum EstadoAsiento {
    LIBRE (1L, "Libre"),
    OCUPADO (2L, "Ocupado"),
    RESERVADO (3L, "Reservado");

    private Long id;
    private String estado;

    EstadoAsiento() {
    }

    EstadoAsiento(Long id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
