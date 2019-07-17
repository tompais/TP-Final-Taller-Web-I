package ar.edu.unlam.tallerweb1.Enums;

public enum TipoFuncion {
    DOSD(1L, "2D"),
    TRESD(2L, "3D"),
    CUATROD(3L, "4D");

    private Long id;
    private String tipo;

    TipoFuncion() {
    }

    TipoFuncion(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
