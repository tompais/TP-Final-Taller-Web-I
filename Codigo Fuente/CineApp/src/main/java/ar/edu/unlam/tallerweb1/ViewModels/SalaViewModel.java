package ar.edu.unlam.tallerweb1.ViewModels;

public class SalaViewModel {
	private Long tipoAsiento;
	private Long estadoAsiento;
	private char columna;

	public Long getTipoAsiento() {
		return tipoAsiento;
	}
	public void setTipoAsiento(Long tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}
	public Long getEstadoAsiento() {
		return estadoAsiento;
	}
	public void setEstadoAsiento(Long estadoAsiento) {
		this.estadoAsiento = estadoAsiento;
	}
	public char getColumna() {
		return columna;
	}
	public void setColumna(char columna) {
		this.columna = columna;
	}
}
