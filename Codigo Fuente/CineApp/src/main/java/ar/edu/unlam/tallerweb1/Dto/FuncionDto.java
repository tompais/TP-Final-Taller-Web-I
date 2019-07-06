package ar.edu.unlam.tallerweb1.Dto;

public class FuncionDto {

    private Long peliculaId;
    private Long cineId;
    private Long tipoFuncionId;

    public Long getTipoFuncionId() {
        return tipoFuncionId;
    }

    public void setTipoFuncionId(Long tipoFuncionId) {
        this.tipoFuncionId = tipoFuncionId;
    }

    public Long getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Long peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
    }
}
