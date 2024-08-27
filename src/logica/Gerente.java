package logica;

import java.sql.Date;

public class Gerente extends Trabajador {
	private Date fechaOcupCargo;
	
	public Gerente(String string, String nombre, String apellidos, String cI, float salarioB, String nivelEscolar, String cargo,Date fechaOcupCargo) {
		super(string, nombre, apellidos, cI, salarioB, nivelEscolar, cargo);
		setFechaOcupCargo(fechaOcupCargo);
	}

	public Date getFechaOcupCargo() {
		return fechaOcupCargo;
	}

	public void setFechaOcupCargo(Date date) {
		this.fechaOcupCargo = date;
	}

}
