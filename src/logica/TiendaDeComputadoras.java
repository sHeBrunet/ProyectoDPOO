package logica;

import java.util.ArrayList;


public class TiendaDeComputadoras {
	private String nombre;
	private String ID;
	private String direccion;
	private String telefono;

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

	@SuppressWarnings("unchecked")
	public ArrayList<ComponenteOrdenador> getComponentes(){
		return (ArrayList<ComponenteOrdenador>) componentes.clone();
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
			trabajadores.add(trabajador);
			agregado = true;
		}
		return agregado;
	}

	public boolean agregarP(ArrayList<ComponenteOrdenador> piezas) {
		boolean act = false;
		if(!piezas.isEmpty()) {
			for(ComponenteOrdenador c: piezas) {
				componentes.add(c);
				act = true;
			}
		}
		return act;
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
	public int eliminarTrabajadores(ArrayList <String> trabAElim){
		int count = 0; int i = 0; int j = 0;
		boolean stop = false;
		while(i < trabajadores.size() && !trabAElim.isEmpty()) {
			while(j < trabAElim.size() && !stop) {
				if(trabajadores.get(i).getCI().equalsIgnoreCase(trabAElim.get(j))) {
					trabajadores.remove(i);
					stop = true;
					count++;
					trabAElim.remove(j);
				} else 
					j++;
			}
			if(!stop)
				i++;
			j = 0;
			stop = false;
		}
		return count;
	}


	public int hallarGerentes(ArrayList <String> eliminados) {
		int count = 0;
		for(Trabajador t: trabajadores) {
			if(t instanceof Gerente) {
				for(int i = 0; i < eliminados.size(); i++) {
					if(t.getCI().equals(eliminados.get(i)))
						count++;
				}
			}
		}
		return getGerentes().size() - count;
	}

	public int eliminarPiezas(ArrayList <String> piezasAElim) {
		int count = 0; int i = 0; int j = 0;
		boolean stop = false;
		while(i < componentes.size() && !piezasAElim.isEmpty()) {
			while(j < piezasAElim.size() && !stop) {
				if(componentes.get(i).getNumSerie().equalsIgnoreCase(piezasAElim.get(j))) {
					componentes.remove(i);
					stop = true;
					count++;
					piezasAElim.remove(j);
				} else 
					j++;
			}
			if(!stop)
				i++;
			j = 0;
			stop = false;
		}
		return count;
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
	public ArrayList<Teclado> encontrarModeloTeclado(String componenteNombre, String marca, String retroiluminacion) {
		ArrayList<Teclado> teclado = new ArrayList<>();
		boolean r = false;
		System.out.println("Entro en la funcion");

		if (retroiluminacion.equals("Sí")) {
			r = true;
		}
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Teclado) {
					if (c.getMarca().equals(marca)) {
						if (((Teclado) c).isRetroiluminacion() == r) {
							teclado.add((Teclado) c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return teclado;
	}
	public ArrayList<String> modelo(String componenteNombre, String marca, String retroiluminacion){
		ArrayList<Teclado> t = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		t = encontrarModeloTeclado(componenteNombre, marca, retroiluminacion);
		for(int i = 0; i < t.size(); i++) {
			if(!m.contains(t.get(i).getModelo())) {
				m.add(t.get(i).getModelo());
			}

		}
		return m;
	}
	public Teclado encontTeclado(String componenteNombre, String marca, String retroiluminacion, String modelo) {
		ArrayList<Teclado> teclado = new ArrayList<>();
		Teclado t = null;
		boolean parada = false;
		teclado = encontrarModeloTeclado(componenteNombre, marca, retroiluminacion);
		for(int i = 0; i < teclado.size() && !parada ; i++) {
			if(teclado.get(i).getModelo().equals(modelo)) {
				t = teclado.get(i);
				parada = true;
			}
		}
		return t;
	}

	public ArrayList<Adaptador> encontrarModeloAdaptador(String componenteNombre, String marca) {
		ArrayList<Adaptador> adap = new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Adaptador) {
					if (c.getMarca().equals(marca)) {
						adap.add((Adaptador) c);

					}
				}
			}
		}
		return adap;
	}
	public ArrayList<String> modeloAdaptador(String componenteNombre, String marca){
		ArrayList<Adaptador> t = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		t = encontrarModeloAdaptador(componenteNombre, marca);
		for(int i = 0; i < t.size(); i++) {
			if(!m.contains(t.get(i).getModelo())) {
				m.add(t.get(i).getModelo());
			}

		}
		return m;
	}
	public Adaptador encontAdaptador(String componenteNombre, String marca, String modelo) {
		ArrayList<Adaptador> adaptador = new ArrayList<>();
		Adaptador a = null;
		boolean parada = false;
		adaptador = encontrarModeloAdaptador(componenteNombre, marca);
		for(int i = 0; i < adaptador.size() && !parada ; i++) {
			if(adaptador.get(i).getModelo().equals(modelo)) {
				a = adaptador.get(i);
				parada = true;
			}
		}
		return a;
	}

	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<String> modeloBocina(String nombre, String marca, String conectividad) {
		ArrayList<Bocina> b = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		b = encontrarModeloBocina(nombre, marca, conectividad);
		for(int i = 0; i < b.size(); i++) {
			if(!b.contains(b.get(i).getModelo())) {
				m.add(b.get(i).getModelo());
			}

		}
		return m;
	}

	private ArrayList<Bocina> encontrarModeloBocina(String nombre2, String marca, String conectividad) {
		ArrayList<Bocina> bocina= new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Bocina) {
					if (c.getMarca().equals(marca)) {
						if (((Bocina) c).getConectividad().equals(conectividad)) {
							bocina.add((Bocina) c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return bocina;
	}
	public Bocina encontBocina(String componenteNombre, String marca, String conectividad, String modelo) {
		ArrayList<Bocina> bocina = new ArrayList<>();
		Bocina t = null;
		boolean parada = false;
		bocina = encontrarModeloBocina(componenteNombre, marca, conectividad);
		for(int i = 0; i < bocina.size() && !parada ; i++) {
			if(bocina.get(i).getModelo().equals(modelo)) {
				t = bocina.get(i);
				parada = true;
			}
		}
		return t;
	}

	public ArrayList<String> modeloChasis(String nombre, String marca, String material) {
		ArrayList<Chasis> c = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		c = encontrarModeloChasis(nombre, marca, material);
		for(int i = 0; i < c.size(); i++) {
			if(!c.contains(c.get(i).getModelo())) {
				m.add(c.get(i).getModelo());
			}

		}
		return m;
	}

	private ArrayList<Chasis> encontrarModeloChasis(String nombre2, String marca, String material) {
		ArrayList<Chasis> chasis = new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Chasis) {
					if (c.getMarca().equals(marca)) {
						if (((Chasis) c).getMaterial().equals(material)) {
							chasis.add((Chasis) c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return chasis;
	}
	public Chasis encontChasis(String componenteNombre, String marca, String material, String modelo) {
		ArrayList<Chasis> chasis = new ArrayList<>();
		Chasis c = null;
		boolean parada = false;
		chasis = encontrarModeloChasis(componenteNombre, marca, material);
		for(int i = 0; i < chasis.size() && !parada ; i++) {
			if(chasis.get(i).getModelo().equals(modelo)) {
				c = chasis.get(i);
				parada = true;
			}
		}
		return c;
	}

	public ArrayList<String> modeloMonitor(String nombre, String marca, String resolucion) {
		ArrayList<Monitor> monitor= new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		monitor = encontrarModeloMonitor(nombre, marca, resolucion);
		for(int i = 0; i < monitor.size(); i++) {
			if(!monitor.contains(monitor.get(i).getModelo())) {
				m.add(monitor.get(i).getModelo());
			}

		}
		return m;
	}

	private ArrayList<Monitor> encontrarModeloMonitor(String nombre2, String marca, String resolucion) {
		ArrayList<Monitor> monitores = new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Monitor) {
					if (c.getMarca().equals(marca)) {
						if (((Monitor) c).getResolucion().equals(resolucion)) {
							monitores.add((Monitor) c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return monitores;
	}
	public Monitor encontMonitor(String componenteNombre, String marca, String resolucion, String modelo) {
		ArrayList<Monitor> monitores = new ArrayList<>();
		Monitor m = null;
		boolean parada = false;
		monitores = encontrarModeloMonitor(componenteNombre, marca, resolucion);
		for(int i = 0; i < monitores.size() && !parada ; i++) {
			if(monitores.get(i).getModelo().equals(modelo)) {
				m = monitores.get(i);
				parada = true;
			}
		}
		return m;
	}

	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<String> modeloTarjetaVideo(String nombre, String marca, String refrigeracion) {
		ArrayList<TarjetaDeVideo> tarjetaVideo = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		tarjetaVideo = encontrarModeloTarjetaVideo(nombre, marca, refrigeracion);
		for(int i = 0; i < tarjetaVideo.size(); i++) {
			if(!tarjetaVideo.contains(tarjetaVideo.get(i).getModelo())) {
				m.add(tarjetaVideo.get(i).getModelo());
			}

		}
		return m;
	}
	private ArrayList<TarjetaDeVideo> encontrarModeloTarjetaVideo(String nombre2, String marca, String refrigeracion) {
		ArrayList<TarjetaDeVideo> tarjetasVideo = new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof TarjetaDeVideo) {
					if (c.getMarca().equals(marca)) {
						if (((TarjetaDeVideo) c).getRefrigeracion().equals(refrigeracion)) {
							tarjetasVideo.add((TarjetaDeVideo) c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return tarjetasVideo;
	}
	public TarjetaDeVideo encontTarjetaVideo(String componenteNombre, String marca, String refrigeracion, String modelo) {
		ArrayList<TarjetaDeVideo> tarjetasV = new ArrayList<>();
		TarjetaDeVideo t = null;
		boolean parada = false;
		tarjetasV = encontrarModeloTarjetaVideo(componenteNombre, marca, refrigeracion);
		for(int i = 0; i < tarjetasV.size() && !parada ; i++) {
			if(tarjetasV.get(i).getModelo().equals(modelo)) {
				t = tarjetasV.get(i);
				parada = true;
			}
		}
		return t;
	}
	/***************************************Mouse********************************************/
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<String> modeloMouse(String nombre, String marca, String conectividad) {
		ArrayList<Mouse> b = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		b = encontrarModeloMouse(nombre, marca, conectividad);
		for(int i = 0; i < b.size(); i++) {
			if(!b.contains(b.get(i).getModelo())) {
				m.add(b.get(i).getModelo());
			}

		}
		return m;
	}

	private ArrayList<Mouse> encontrarModeloMouse(String nombre2, String marca, String conectividad) {
		ArrayList<Mouse> raton = new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Mouse) {
					if (c.getMarca().equals(marca)) {
						if (((Mouse) c).getConectividad().equals(conectividad)) {
							raton.add((Mouse)c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return raton;
	}
	public Mouse encontMouse(String componenteNombre, String marca, String conectividad, String modelo) {
		ArrayList<Mouse> raton = new ArrayList<>();
		Mouse t = null;
		boolean parada = false;
		raton = encontrarModeloMouse(componenteNombre, marca, conectividad);
		for(int i = 0; i < raton.size() && !parada ; i++) {
			if(raton.get(i).getModelo().equals(modelo)) {
				t = raton .get(i);
				parada = true;
			}
		}
		return t;
	}
	/********************************************Fuente**************************************/
	public ArrayList<String> modeloFuente(String nombre, String marca, String eficiencia) {
		ArrayList<Fuente> b = new ArrayList<>();
		ArrayList<String> m = new ArrayList<String>();
		b = encontrarModeloFuente(nombre, marca, eficiencia);
		for(int i = 0; i < b.size(); i++) {
			if(!b.contains(b.get(i).getModelo())) {
				m.add(b.get(i).getModelo());
			}

		}
		return m;
	}
	public Fuente encontFuente(String componenteNombre, String marca, String eficiencia, String modelo) {
		ArrayList<Fuente> fuente = new ArrayList<>();
		Fuente f = null;
		boolean parada = false;
		fuente = encontrarModeloFuente(componenteNombre, marca, eficiencia);
		for(int i = 0; i < fuente.size() && !parada ; i++) {
			if(fuente.get(i).getModelo().equals(modelo)) {
				f = fuente .get(i);
				parada = true;
			}
		}
		return f;
	}

	public ArrayList<Fuente> encontrarModeloFuente(String componenteNombre, String marca, String eficiencia) {
		ArrayList<Fuente> f = new ArrayList<>();
		if (componentes != null) {
			for (ComponenteOrdenador c : componentes) {
				if (c instanceof Fuente) {
					if (c.getMarca().equals(marca)) {
						if (((Fuente) c).getEficiencia().equals(eficiencia)) {
							f.add((Fuente)c); 
						}
					}
				}
			}
		} else {
			System.out.println("La lista de componentes es null");
		}

		return f;
	}
}