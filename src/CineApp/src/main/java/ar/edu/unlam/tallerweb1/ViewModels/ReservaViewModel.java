package ar.edu.unlam.tallerweb1.ViewModels;

public class ReservaViewModel {
	private int filas[];
	private int columnas[];
	private Long funcionId;
	
	public int[] getFilas() {
		return filas;
	}
	public void setFilas(int[] filas) {
		this.filas = filas;
	}
	public int[] getColumnas() {
		return columnas;
	}
	public void setColumnas(int[] columnas) {
		this.columnas = columnas;
	}
	public Long getFuncionId() {
		return funcionId;
	}
	public void setFuncionId(Long funcionId) {
		this.funcionId = funcionId;
	}
}
