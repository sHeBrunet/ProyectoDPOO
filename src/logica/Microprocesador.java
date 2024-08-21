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
		conexiones = InicializacionDeDatos.conexiones();
		for(String r: conexiones) {
			if(tipoDeConexion.equalsIgnoreCase(r)) {
				correcto = true;
				this.tipoDeConexion = tipoDeConexion;
			}
		}
		if(!correcto) {
			throw new IllegalArgumentException("Las conexiones deben de ser de tipo(Primaria, Secundaria, Técnico Medio, Bachillerato, Universitario");
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
