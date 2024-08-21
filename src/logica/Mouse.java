package logica;

public class Mouse extends ComponenteOrdenador {
	private int numDeBotones;

	public Mouse(int cantDisponible, String numSerie, String marca, String modelo, float precio, int numDeBotones) {
		super(cantDisponible, numSerie, marca, modelo, precio);
		setNumDeBotones(numDeBotones);
	}
	public int getNumDeBotones() {
		return numDeBotones;
	}
	public void setNumDeBotones(int numDeBotones) {
		if(numDeBotones >= 2 && numDeBotones <= 12) {
			this.numDeBotones = numDeBotones;
		}
		else {
			throw new IllegalArgumentException("La cantidad de botones de un mouse debe de estar entre 2 y 12");
		}
	}

}
