package ar.edu.unlam.tallerweb1.ViewModels;

import ar.edu.unlam.tallerweb1.Models.Pelicula;

public class PublicacionViewModel {

    private Pelicula pelicula;

    private Double promedioCalificacion;

    public PublicacionViewModel(Pelicula pelicula, Double promedioCalificacion) {
        this.pelicula = pelicula;
        this.promedioCalificacion = promedioCalificacion;
    }

    public PublicacionViewModel() {
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Double getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(Double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }
}
