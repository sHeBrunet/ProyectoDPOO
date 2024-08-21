package logica;

public class Fuente extends ComponenteOrdenador {
	private float potencia;
	
	public Fuente(int cantDisponible, String numSerie, String marca, String modelo, float precio, float potencia) {
		super(cantDisponible, numSerie, marca, modelo, precio);
		setPotencia(potencia);
	}
	public float getPotencia() {
		return potencia;
	}
	public void setPotencia(float potencia) {
		if(potencia >= 300 || potencia <= 2000) {
		this.potencia = potencia;
		}
		else {
			throw new IllegalArgumentException("La potencia de una fuente debe de estar entre 300 y 2000 W");
		}
	}



}
