package ar.edu.unlam.tallerweb1.Enums;

public enum Rol {
    USUARIO ((long)1, "Usuario");

    private Long id;
    private String nombre;

    Rol() {
    }

    Rol(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
