package ar.edu.unlam.tallerweb1.ViewModels;

public class AsientoViewModel {
	private Long id;
	private Long tipoAsientoId;
	private Long estadoAsientoId;
	private char columna;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTipoAsientoId() {
		return tipoAsientoId;
	}
	public void setTipoAsientoId(Long tipoAsientoId) {
		this.tipoAsientoId = tipoAsientoId;
	}
	public Long getEstadoAsientoId() {
		return estadoAsientoId;
	}
	public void setEstadoAsientoId(Long estadoAsientoId) {
		this.estadoAsientoId = estadoAsientoId;
	}
	public char getColumna() {
		return columna;
	}
	public void setColumna(char columna) {
		this.columna = columna;
	}
}
