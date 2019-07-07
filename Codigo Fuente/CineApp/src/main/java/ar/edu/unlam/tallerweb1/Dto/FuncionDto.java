package ar.edu.unlam.tallerweb1.Dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class FuncionDto {

    private Long peliculaId;
    private Long cineId;
    private Long tipoFuncionId;
    private Date dia;

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

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
