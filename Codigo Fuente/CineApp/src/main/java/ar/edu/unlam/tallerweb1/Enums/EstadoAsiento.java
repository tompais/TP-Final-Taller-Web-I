package ar.edu.unlam.tallerweb1.Enums;

public enum EstadoAsiento {
	LIBRE ((long)1),
	OCUPADO ((long)2),
	RESERVADO ((long)3);
	
	private Long estadoAsiento;
	
	EstadoAsiento(Long estadoAsiento){
		this.estadoAsiento = estadoAsiento;
	}
	
	EstadoAsiento(){
	}

	public Long getEstadoAsiento() {
		return estadoAsiento;
	}
}
