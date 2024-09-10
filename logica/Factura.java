package logica;

import java.time.LocalDate;
import java.util.ArrayList;


public class Factura {
	private ArrayList<ComponenteOrdenador> com;
<<<<<<< HEAD
	private ArrayList <Integer> cantidadXPieza;
	private LocalDate fecha;
	private ArrayList <Boolean> ensamblado;
	private float precioEnsamblado;
=======
	private ArrayList<Integer> cantidadXPieza;
	private LocalDate fecha;
>>>>>>> 021edd5cabcbeb23c277cd230348697e0267e1eb

	public Factura(LocalDate fecha) {
		com = new ArrayList<>();
		cantidadXPieza = new ArrayList<>();
<<<<<<< HEAD
		ensamblado = new ArrayList<Boolean>();
		setFecha(fecha);
		setPrecioEnsamblado(precioEnsamblado);
	}


	public float getPrecioEnsamblado() {
		return precioEnsamblado;
	}


	public void setPrecioEnsamblado(float precioEnsamblado) {
	if(precioEnsamblado >= 0) {
		this.precioEnsamblado = precioEnsamblado;
	}
	else {
		throw new IllegalArgumentException("Error. El precio del ensamblado debe de ser mayor que cero si se realiza el ensamblado");
	}
	}


	public ArrayList<Boolean> getEnsamblado() {
		return ensamblado;
	}


	public void setEnsamblado(ArrayList<Boolean> ensamblado) {
		this.ensamblado = ensamblado;
	}


=======
		setFecha(fecha);
	}

>>>>>>> 021edd5cabcbeb23c277cd230348697e0267e1eb
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

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
