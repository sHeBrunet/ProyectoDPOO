package logica;

public class MemoriaRam extends ComponenteOrdenador {
	private boolean espacio; // MB true, GB false 
	private float cantEspacio;
	private String tipoDeMemoria;

	public MemoriaRam(int cantDisponible, String numSerie, String marca, String modelo, float precio, float cantEspacio, String tipoDeMemoria) {
		super(cantDisponible, numSerie, marca, modelo, precio);
		setCantEspacio(cantEspacio);
		setTipoDeMemoria(tipoDeMemoria);
		setEspacio(espacio);
	}

	public float getCantEspacio() {
		return cantEspacio;
	}
	public void setCantEspacio(float cantEspacio) {
		if(isEspacio() == true) {
			if(cantEspacio >= 4096 && cantEspacio <= 131072) {
				this.cantEspacio = cantEspacio;
			}
			else if(isEspacio() == false) {
				if(cantEspacio >= 4 && cantEspacio <= 128) {
					this.cantEspacio = cantEspacio;
				}
			}
		}
		else {
			throw new IllegalArgumentException("La cantidad de espacio de una memoria RAM debe de estar entre 4096-131072 MB  lo que equivale a 4-128 GB");
		}
	}
	public String getTipoDeMemoria() {
		return tipoDeMemoria;
	}
	public void setTipoDeMemoria(String tipoDeMemoria) {
		if(tipoDeMemoria.equalsIgnoreCase("DDR")|| tipoDeMemoria.equalsIgnoreCase("DDR-2" )|| tipoDeMemoria.equalsIgnoreCase("DDR-3")) {
			this.tipoDeMemoria = tipoDeMemoria;
		}
		else {
			throw new IllegalArgumentException("El tipo de memoria debe de ser de tipo (DDR, DDR-2, DDR-3)");
		}
	}

	public boolean isEspacio() {
		return espacio;
	}

	public void setEspacio(boolean espacio) {
		this.espacio = espacio;
	}


}
