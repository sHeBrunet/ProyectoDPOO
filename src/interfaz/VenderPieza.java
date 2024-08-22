package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.TiendaDeComputadoras;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class VenderPieza extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private TiendaDeComputadoras tiendaC;
	private Principal p;
	private JComboBox comboBoxComponenetes;
	private JComboBox comboBoxMarca;
	private JComboBox comboBoxModelo;
	private String nombre;
	private String seleccionado;
	private JTextField Atributo1;
	private JTextField Atributo2;
	private JTextField Atributo3;
	private JTextField Atributo4;
	private JComboBox<String> comboBoxAtributo1;
	private JComboBox<String> comboBoxAtributo2;
	private JComboBox<String> comboBoxAtributo3;
	private JComboBox<String> comboBoxAtributo4;

	@SuppressWarnings("unchecked")
	public VenderPieza(Principal principal, TiendaDeComputadoras tienda, String nombreDeComponente) {
		nombre = nombreDeComponente;
		setTitle("Vender Piezas");
		p = principal;
		tiendaC = tienda;

		inicializaciones.InicializacionDeDatos.llamarInicializaciones(tienda);
		setBounds(100, 100, 900, 746);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			ArrayList<String> itemExp = new ArrayList<String>();
			JPanel FiltradodeProducto = new JPanel();
			FiltradodeProducto.setBackground(Color.WHITE);
			FiltradodeProducto.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
			FiltradodeProducto.setBounds(0, 0, 438, 535);
			contentPanel.add(FiltradodeProducto);
			FiltradodeProducto.setLayout(null);

			Atributo1 = new JTextField();
			Atributo1.setText("Atributo1");
			Atributo1.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo1.setBorder(null);
			Atributo1.setBounds(12, 130, 141, 20);
			FiltradodeProducto.add(Atributo1);
			Atributo1.setColumns(10);

			Atributo2 = new JTextField();
			Atributo2.setBorder(null);
			Atributo2.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo2.setText("Atributo2");
			Atributo2.setColumns(10);
			Atributo2.setBounds(12, 161, 141, 20);
			

			Atributo3 = new JTextField();
			Atributo3.setBorder(null);
			Atributo3.setText("Atributo3");
			Atributo3.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo3.setColumns(10);
			Atributo3.setBounds(12, 193, 141, 20);
			

			Atributo4 = new JTextField();
			Atributo4.setBorder(null);
			Atributo4.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo4.setText("Atributo4");
			Atributo4.setColumns(10);
			Atributo4.setBounds(12, 225, 141, 20);
			
			
			
			FiltradodeProducto.add(Atributo4);
			FiltradodeProducto.add(Atributo3);
			FiltradodeProducto.add(Atributo2);

			primeraVisualizacionAtributo();

			JLabel Componenetelabel = new JLabel("Componenetes:");
			Componenetelabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			Componenetelabel.setBounds(12, 78, 141, 16);
			FiltradodeProducto.add(Componenetelabel);

			comboBoxComponenetes = new JComboBox();
			comboBoxComponenetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object seleccionado = comboBoxComponenetes.getSelectedItem();
					limpiarComboBox();
					elegirMarca(seleccionado);
				}
			});
			comboBoxComponenetes.setBounds(157, 77, 257, 20);
			FiltradodeProducto.add(comboBoxComponenetes);


			JLabel lblModelo = new JLabel("Modelo:");
			lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblModelo.setBounds(12, 491, 141, 16);
			FiltradodeProducto.add(lblModelo);

			comboBoxMarca = new JComboBox();
			comboBoxMarca.setBounds(157, 105, 257, 20);
			FiltradodeProducto.add(comboBoxMarca);

			JLabel lblMarca = new JLabel("Marca:");
			lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblMarca.setBounds(12, 106, 141, 16);
			FiltradodeProducto.add(lblMarca);


			comboBoxModelo = new JComboBox();
			comboBoxModelo.setBounds(157, 490, 257, 20);
			FiltradodeProducto.add(comboBoxModelo);
			if (nombre == null) {
				llenarComboBox(comboBoxComponenetes, inicializaciones.InicializacionDeDatos.nameComponenete());
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTeclado());
				Atributo1.setText("Retroiluminacion:");
				componenetesVentaLibreNV();
			} else {
				List<String> itemExp1 = inicializaciones.InicializacionDeDatos.nameComponenete();
				itemExp1.remove(nombreDeComponente);
				itemExp1.add(0, nombreDeComponente);
				llenarComboBox(comboBoxComponenetes, (ArrayList<String>) itemExp1);
				componenetesVentaLibreNV();
			}


			JLabel lblNewLabel = new JLabel("Buscar Componente");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 12, 402, 51);
			FiltradodeProducto.add(lblNewLabel);

			comboBoxAtributo1 = new JComboBox();
			comboBoxAtributo1.setBounds(157, 133, 257, 20);


			comboBoxAtributo2 = new JComboBox();
			comboBoxAtributo2.setBounds(157, 161, 257, 20);
			

			comboBoxAtributo3 = new JComboBox<String>();
			comboBoxAtributo3.setBounds(157, 193, 257, 20);
		

			comboBoxAtributo4 = new JComboBox<String>();
			comboBoxAtributo4.setBounds(157, 225, 257, 20);
			
			
			
			FiltradodeProducto.add(comboBoxAtributo1);
			FiltradodeProducto.add(comboBoxAtributo2);
			FiltradodeProducto.add(comboBoxAtributo3);
			FiltradodeProducto.add(comboBoxAtributo4);

			primeraVisualizacionComponenetes();

		}
		{
			JPanel ProductoEncontrado = new JPanel();
			ProductoEncontrado.setBackground(Color.WHITE);
			ProductoEncontrado.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
			ProductoEncontrado.setBounds(438, 0, 452, 535);
			contentPanel.add(ProductoEncontrado);
			ProductoEncontrado.setLayout(null);

			JLabel lblComponenteEncontrado = new JLabel("Componente Encontrado");
			lblComponenteEncontrado.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblComponenteEncontrado.setBounds(12, 12, 402, 51);
			ProductoEncontrado.add(lblComponenteEncontrado);
		}
		{
			JPanel ProductoAgregado = new JPanel();
			ProductoAgregado.setBackground(Color.WHITE);
			ProductoAgregado.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			ProductoAgregado.setBounds(0, 536, 890, 148);
			contentPanel.add(ProductoAgregado);
			ProductoAgregado.setLayout(null);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void primeraVisualizacionComponenetes() {
	    if (nombre != null) {
	        if (nombre.equals("Tarjeta Madre") || nombre.equals("Microprocesador") || nombre.equals("Disco Duro") || nombre.equals("Memoria RAM")) {
	            if (comboBoxAtributo2 != null) comboBoxAtributo2.setVisible(true);
	            if (comboBoxAtributo3 != null) comboBoxAtributo3.setVisible(true);
	            if (comboBoxAtributo4 != null) comboBoxAtributo4.setVisible(true);
	        } else {
	            if (comboBoxAtributo2 != null) comboBoxAtributo2.setVisible(false);
	            if (comboBoxAtributo3 != null) comboBoxAtributo3.setVisible(false);
	            if (comboBoxAtributo4 != null) comboBoxAtributo4.setVisible(false);
	        }
	    } else {
	        System.out.println("Error: nombre es null");
	    }
	}
	private void primeraVisualizacionAtributo() {
	    if (nombre != null) {
	        if (nombre.equals("Tarjeta Madre") || nombre.equals("Microprocesador") || nombre.equals("Disco Duro") || nombre.equals("Memoria RAM")) {
	            if (Atributo2 != null) Atributo2.setVisible(true);
	            if (Atributo3 != null) Atributo3.setVisible(true);
	            if (Atributo4 != null) Atributo4.setVisible(true);
	        } else {
	            if (Atributo2 != null) Atributo2.setVisible(false);
	            if (Atributo3 != null) Atributo3.setVisible(false);
	            if (Atributo4 != null) Atributo4.setVisible(false);
	        }
	    } else {
	        System.out.println("Error: nombre es null");
	    }
	}
	private void componenetesVentaLibreNV() {
		if (Atributo2 != null) Atributo2.setVisible(false);
		if (comboBoxAtributo2 != null) comboBoxAtributo2.setVisible(false);
		if (Atributo3 != null) Atributo3.setVisible(false);
		if (comboBoxAtributo3 != null) comboBoxAtributo3.setVisible(false);
		if (Atributo4 != null) Atributo4.setVisible(false);
		if (comboBoxAtributo4 != null) comboBoxAtributo4.setVisible(false);

	}

	private void componenetesVentaLibreV() {
		if (Atributo2 != null) Atributo2.setVisible(true);
		if (comboBoxAtributo2 != null) comboBoxAtributo2.setVisible(true);
		if (Atributo3 != null) Atributo3.setVisible(true);
		if (comboBoxAtributo3 != null) comboBoxAtributo3.setVisible(true);
		if (Atributo4 != null) Atributo4.setVisible(true);
		if (comboBoxAtributo4 != null) comboBoxAtributo4.setVisible(true);
		repaint();
		validate();

	}
	private void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		if (comboBox == null || items == null) {
			System.out.println("comboBox o items es null");
		} else {
			comboBox.removeAllItems();
			for (String item : items) {
				comboBox.addItem(item);
			}
			System.out.println("llenarComboBox ejecutado correctamente con " + items.size() + " elementos.");
		}
	}

	/*****************Funcion para elegir las marcas************************************/
	@SuppressWarnings("unchecked")
	private void elegirMarca(Object seleccionado) {
		String nombreC = seleccionado.toString();
		ArrayList <String> Desicion = new ArrayList<>();
		Desicion.add("Sí");
		Desicion.add("No");
		switch (nombreC) {
		case "Teclado":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTeclado());
			Atributo1.setText("Retroiluminacion:");
			llenarComboBox(comboBoxAtributo1, Desicion);
			componenetesVentaLibreNV();
			break;
		case "Tarjeta de Video":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaVideos());
			Atributo1.setText("Refrigeracion:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.refrigeracion());
			componenetesVentaLibreNV();
			break;
		case "Tarjeta Madre":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaMadre());
			Atributo1.setText("Tipo de Conector:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectores());
			componenetesVentaLibreV();
			break;
		case "Microprocesador":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMicroProcesadores());
			Atributo1.setText("Tipo de Conexion:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexiones());
			componenetesVentaLibreV();
			break;
		case "Adaptador":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasExtension());
			Atributo1.setVisible(false);
			comboBoxAtributo1.setVisible(false);
			componenetesVentaLibreNV();
			break;
		case "Bocina":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasBocinas());
			Atributo1.setText("Conectividad:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
			componenetesVentaLibreNV();
			break;
		case "Monitor":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasPantalla());
			Atributo1.setText("Resolución:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.resolucionVideo());
			componenetesVentaLibreNV();
			break;
		case "Ratón":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasRaton());
			Atributo1.setText("No. de Botones:");
			componenetesVentaLibreNV();
			comboBoxAtributo1.setVisible(false);
			break;
		case "Memoria RAM":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMemoriasRAM());
			Atributo1.setText("Espacio:");
			componenetesVentaLibreV();
			break;
		case "Chasis":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasChasis());
			Atributo1.setText("Material:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.materialesChasis());
			componenetesVentaLibreNV();
			break;		
		case "Disco Duro":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasDiscoD());
			Atributo1.setText("Capacidad:");
			llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.capacidadesDisco());
			componenetesVentaLibreV();
			break;
		case "Fuente":
			llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasFuente());
			Atributo1.setText("Potencia:");
			componenetesVentaLibreNV();
			break;
		default:
			llenarComboBox(comboBoxMarca, new ArrayList<>());
			break;
		}
	}
	public void limpiarComboBox() {
		comboBoxMarca.removeAllItems();
	}
	public void iniciar() {
		comboBoxMarca.setSelectedItem(0);
	}
}
