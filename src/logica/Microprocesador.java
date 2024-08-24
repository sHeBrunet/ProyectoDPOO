package logica;

import java.util.ArrayList;

import inicializaciones.InicializacionDeDatos;

public class Microprocesador extends ComponenteOrdenador {
	private String tipoDeConexion;
	private float velocidadDeProcesamiento;
	private boolean velocidad; // true MHz  false GHz.
	


	public Microprocesador(int cantDisponible, String numSerie, String marca, String modelo, float precio,String tipoDeConexion,float velocidadDeProcesamiento) {
		super(cantDisponible, numSerie, marca, modelo, precio);
		setTipoDeConexion(tipoDeConexion);
		setVelocidadDeProcesamiento(velocidadDeProcesamiento);
		setVelocidad(velocidad);

	}

	public String getTipoDeConexion() {
		return tipoDeConexion;
	}

	public void setTipoDeConexion(String tipoDeConexion) {
		boolean correcto = false;
		ArrayList<String> conexiones = new ArrayList<String>();
		conexiones = InicializacionDeDatos.conexionesIntel();
		conexiones.addAll(InicializacionDeDatos.conexionesAMD());
		for(String r: conexiones) {
			if(tipoDeConexion.equalsIgnoreCase(r)) {
				correcto = true;
				this.tipoDeConexion = tipoDeConexion;
			}
		}
		if(!correcto) {
			throw new IllegalArgumentException("Las conexiones deben de ser tipo (LGA 1151,LGA 1200,LGA 1700,AM3,AM4,AM5,TR4).");
		}
	}

	public float getVelocidadDeProcesamiento() {
		return velocidadDeProcesamiento;
	}

	public void setVelocidadDeProcesamiento(float velocidadDeProcesamiento) {
		if(isVelocidad() == true) {
			if(velocidadDeProcesamiento >= 2000 && velocidadDeProcesamiento <= 8000) {
				this.velocidadDeProcesamiento = velocidadDeProcesamiento;
			}
		}
		else if(isVelocidad() == false) {
			if(velocidadDeProcesamiento >= 2 && velocidadDeProcesamiento <= 8 ) {
				this.velocidadDeProcesamiento = velocidadDeProcesamiento;
			}
		}
	}

	public boolean isVelocidad() {
		return velocidad;
	}

	public void setVelocidad(boolean velocidad) {
		this.velocidad = velocidad;
	}


}
