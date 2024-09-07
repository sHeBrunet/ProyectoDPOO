package logica;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private ArrayList<ComponenteOrdenador> com;
	private ArrayList<Integer> cantidadXPieza;
	// Date fecha;

	public Factura() {
		com = new ArrayList<>();
		cantidadXPieza = new ArrayList<>();
	}

	/*public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}*/

	public ArrayList<ComponenteOrdenador> getCom() {
		return com;
	}

	public void setCom(ArrayList<ComponenteOrdenador> com) {
		this.com = com;
	}
	public ArrayList<Integer> getCantidadXPieza() {
		return cantidadXPieza;
	}

	public void setCantidadXPieza(ArrayList<Integer> cantidadXPieza) {
		this.cantidadXPieza = cantidadXPieza;
	}


	public float calcularMontoXPieza(ComponenteOrdenador c) {
		float dinero = 0;
		for(int i = 0; i < com.size(); i++) {
			if(com.get(i).equals(c)) {
				dinero = cantidadXPieza.get(i) * com.get(i).getPrecio();
				System.out.println("Componente: " + c + ", Cantidad: " + cantidadXPieza.get(i) + ", Precio: " + com.get(i).getPrecio() + ", Monto: " + dinero);
			}
		}
		return dinero;
	}
}
