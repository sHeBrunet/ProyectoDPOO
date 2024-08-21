package logica;

public class DiscoDuro extends ComponenteOrdenador {
	private boolean almacenamiento; // Si es de tipo GB true y  TB false
	private float capacidad;
	private String tipoDeConexion;

	public DiscoDuro(int cantDisponible, String numSerie, String marca, String modelo, float precio, float capacidad, String tipoDeConexion) {
		super(cantDisponible, numSerie, marca, modelo, precio);
		setTipoDeConexion(tipoDeConexion);
		setCapacidad(capacidad);
		setAlmacenamiento(almacenamiento);
	}
	public float getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(float capacidad) {
		if(isAlmacenamiento() == true) {
			if(capacidad >= 500 && capacidad <= 20000) {
		this.capacidad = capacidad;
			}
		}
		else if(isAlmacenamiento() == false) {
			if(capacidad >= 0.488 && capacidad <= 20) {
				this.capacidad = capacidad;
			}
		}
			else {	
				throw new IllegalArgumentException("El almacenamiento en GB debe de estar entre 1 y 20000 y el almacenamiento en TB entre 1 y 20");
		}
	}
	public String getTipoDeConexion() {
		return tipoDeConexion;
	}
	public void setTipoDeConexion(String tipoDeConexion) {
		if(tipoDeConexion.equalsIgnoreCase("IDE") || tipoDeConexion.equalsIgnoreCase("SATA")|| tipoDeConexion.equalsIgnoreCase("SATA-2")|| tipoDeConexion.equalsIgnoreCase("SATA-3")) {
		this.tipoDeConexion = tipoDeConexion;
		}
		else {
			throw new IllegalArgumentException("El tipo de conexion del disco duro debe de ser de tipo (IDE, SATA, SATA-2, SATA-3)");
		}
		
	}
	public boolean isAlmacenamiento() {
		return almacenamiento;
	}
	public void setAlmacenamiento(boolean almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

}
