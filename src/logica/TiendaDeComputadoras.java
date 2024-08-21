package logica;

import java.util.ArrayList;
import inicializaciones.InicializacionDeDatos;

public class TiendaDeComputadoras {
	private String nombre;
	private String ID;
	private String direccion;
	private String telefono;
	private Gerente gerente;

	private static TiendaDeComputadoras instancia;
	private ArrayList<Trabajador> trabajadores;
	private ArrayList<ComponenteOrdenador> componentes;

	private TiendaDeComputadoras() {
		trabajadores = new ArrayList<>();
		componentes = new ArrayList<>();
	}

	public static TiendaDeComputadoras getInstancia() {
		if(instancia == null) {
			instancia = new TiendaDeComputadoras();
		}
		return instancia;
	}

	public int getCantTrabajadores() {
		return trabajadores.size();
	}
	public int getCantComponenetes() {
		return componentes.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Trabajador> getTrabajadores() {
		return (ArrayList<Trabajador>) trabajadores.clone();
	}

	public int buscarTrabajador(String ci){
		int pos = -1;
		int i = 0;
		boolean found = false;
		while (i < trabajadores.size() && !found){
			if (trabajadores.get(i).getCI() == ci) {
				pos = i;
				found = true;
			} else
				i++;
		}
		return pos;		
	}
	public boolean agregarT(ArrayList<Trabajador> trabaj) {
		boolean act = false;
		if(!trabaj.isEmpty()) {
			for(Trabajador t: trabaj) {
				trabajadores.add(t);
				act = true;
			}
		}
		return act;
	}

	public boolean agregarTrabajador(Trabajador trabajador){
		boolean agregado = true;
		if(trabajador == null || buscarTrabajador(trabajador.getCI()) != -1){
			agregado = false;
		}
		else{			
			agregado = trabajadores.add(trabajador);
		}
		return agregado;
	}
	public int getUltimoNoTrabajador() {
		return trabajadores.size();
	}
	public void eliminarTrabajador(int posicion, Trabajador trab){
		if(posicion >= 0 && posicion < trabajadores.size()) {
			for(Trabajador t: trabajadores)
				if(t.equals(trab)) {
					trabajadores.remove(trab);
				}
		}

	}
	public void eliminarTrabajador1(int posicion){
		if(posicion >= 0 && posicion < trabajadores.size()) {
			trabajadores.remove(posicion);
		}

	}

	public ArrayList<Gerente> getGerentes() {	
		ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
		for(Trabajador t : trabajadores) {
			if(t instanceof Gerente) {
				gerentes.add((Gerente) t);
			}
		}
		return gerentes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre != null && nombre != "") {
			this.nombre = nombre;
		}
		else {
			throw new IllegalArgumentException("El nombre de la tienda no puede estar vacio");
		}	
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		if(nombre != null && nombre != "") {
			this.direccion = direccion;
		}
		else {
			throw new IllegalArgumentException("La dirección de la tienda no puede estar vacio");
		}	
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public ArrayList<Integer> obtenerNo() {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(Trabajador t: trabajadores) 
			numeros.add(t.getNumero());
		return numeros;
	}
	public int noTrabajadorAct() {
		int j = 1;
		int num = 0;
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		numeros = obtenerNo();
		if(numeros.contains(j)) {
			num = getCantTrabajadores();
		}
		else {
			num = j;
		}

		return num;
	}
	
	public boolean agregarComponenteI(ArrayList<ComponenteOrdenador> c){
		boolean agregado = true;
		if(c.isEmpty()){
			agregado = false;
		}
		else{			
			agregado = componentes.addAll(c);
		}
		return agregado;
	}

}