package logica;

public class Teclado extends ComponenteOrdenador {
private boolean retroiluminacion;
	public Teclado(int cantDisponible, String numSerie, String marca, String modelo, float precio, boolean retroiluminacion) {
		super(cantDisponible, numSerie, marca, modelo, precio);
		setRetroiluminacion(retroiluminacion);
	}
	public boolean isRetroiluminacion() {
		return retroiluminacion;
	}
	public void setRetroiluminacion(boolean retroiluminacion) {
		this.retroiluminacion = retroiluminacion;
	}

}
