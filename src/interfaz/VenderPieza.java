package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Adaptador;
import logica.Bocina;
import logica.Chasis;
import logica.Fuente;
import logica.MemoriaRam;
import logica.Monitor;
import logica.Mouse;
import logica.TarjetaDeVideo;
import logica.TarjetaMadre;
import logica.Teclado;
import logica.TiendaDeComputadoras;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;

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
	private JComboBox<String> comboBoxAtributo1;
	private JComboBox<String> comboBoxAtributo2;
	private JComboBox<String> comboBoxAtributo3;
	private ArrayList<String> modelo; 
	private String selec;
	private String selec1;
	private JTextField nombretext;
	private JTextField modelotext;
	private JTextField notext;
	private JTextField preciotext;
	private int cont = 0;
	private JTextField cantidadtxt;
	private JTable table;
	private JCheckBox ensamblarCheckBox;
	private DefaultTableModel tableModel;
	private JSpinner spinnerAtributo1;
	@SuppressWarnings("unchecked")
	public VenderPieza(Principal principal, TiendaDeComputadoras tienda, String nombreDeComponente) {
		nombre = nombreDeComponente;
		setTitle("Vender Piezas");
		p = principal;
		tiendaC = tienda;
		modelo = new ArrayList<>();
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
			FiltradodeProducto.setBounds(0, 0, 438, 419);
			contentPanel.add(FiltradodeProducto);
			FiltradodeProducto.setLayout(null);
			SpinnerNumberModel modelo = new SpinnerNumberModel(0,0,100,1);


			Atributo1 = new JTextField();
			Atributo1.setEditable(false);
			Atributo1.setText("Atributo1");
			Atributo1.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo1.setBorder(null);
			Atributo1.setBounds(12, 130, 141, 20);
			FiltradodeProducto.add(Atributo1);
			Atributo1.setColumns(10);

			Atributo2 = new JTextField();
			Atributo2.setEditable(false);
			Atributo2.setBorder(null);
			Atributo2.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo2.setText("Atributo2");
			Atributo2.setColumns(10);
			Atributo2.setBounds(12, 161, 141, 20);


			Atributo3 = new JTextField();
			Atributo3.setEditable(false);
			Atributo3.setBorder(null);
			Atributo3.setText("Atributo3");
			Atributo3.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo3.setColumns(10);
			Atributo3.setBounds(12, 193, 141, 20);
			FiltradodeProducto.add(Atributo3);
			FiltradodeProducto.add(Atributo2);

			primeraVisualizacionAtributo();

			JLabel Componentelabel = new JLabel("Componentes:");
			Componentelabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			Componentelabel.setBounds(12, 78, 141, 16);
			FiltradodeProducto.add(Componentelabel);

			comboBoxComponenetes = new JComboBox();
			comboBoxComponenetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object seleccionado = comboBoxComponenetes.getSelectedItem();
					limpiarComboBox();
					elegirMarca(seleccionado);
				}
			});
			comboBoxComponenetes.setBounds(165, 77, 249, 20);
			FiltradodeProducto.add(comboBoxComponenetes);

			comboBoxMarca = new JComboBox();
			comboBoxMarca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	

				}
			});
			comboBoxMarca.setBounds(165, 105, 249, 20);
			FiltradodeProducto.add(comboBoxMarca);

			JLabel lblMarca = new JLabel("Marca:");
			lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblMarca.setBounds(12, 106, 141, 16);
			FiltradodeProducto.add(lblMarca);


			comboBoxModelo = new JComboBox();
			comboBoxModelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarDatos();
					actualizarComponente((String) comboBoxModelo.getSelectedItem(), (String)comboBoxComponenetes.getSelectedItem());
				}
			});

			comboBoxModelo.setBounds(157, 375, 257, 20);
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
			comboBoxAtributo1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			comboBoxAtributo1.setBounds(165, 133, 249, 20);


			comboBoxAtributo2 = new JComboBox();
			comboBoxAtributo2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			comboBoxAtributo2.setBounds(165, 161, 249, 20);


			comboBoxAtributo3 = new JComboBox<String>();
			comboBoxAtributo3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			comboBoxAtributo3.setBounds(165, 193, 249, 20);



			FiltradodeProducto.add(comboBoxAtributo1);
			FiltradodeProducto.add(comboBoxAtributo2);
			FiltradodeProducto.add(comboBoxAtributo3);

			JButton btnNewButton = new JButton("Buscar");
			btnNewButton.setBorder(UIManager.getBorder("Button.border"));
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setIcon(new ImageIcon(VenderPieza.class.getResource("/gui/icons/lupa.png")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String desicion = (String) comboBoxComponenetes.getSelectedItem();
					switch (desicion) {
					case "Teclado":
						actualizarModeloTeclado((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;
					case "Tarjeta de Video":
						actualizarModeloTarjetaVideo((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;
					case "Tarjeta Madre":

						break;
					case "Microprocesador":
						break;
					case "Adaptador":
						actualizarModeloAdaptador((String) comboBoxMarca.getSelectedItem());
						break;
					case "Bocina":
						actualizarModeloBocina((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;
					case "Monitor":
						actualizarModeloMonitor((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;
					case "Ratón":
						actualizarModeloRaton((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;
					case "Memoria RAM":

					case "Chasis":
						actualizarModeloChasis((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;		
					case "Disco Duro":

						break;
					case "Fuente":
						actualizarModeloFuente((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;
					default:
						llenarComboBox(comboBoxMarca, new ArrayList<>());
						break;
					}
				}
			});



			btnNewButton.setBounds(26, 375, 111, 20);
			FiltradodeProducto.add(btnNewButton);

			ensamblarCheckBox = new JCheckBox("Ensamblar");
			ensamblarCheckBox.setFont(new Font("Tahoma", Font.BOLD, 15));
			ensamblarCheckBox.setBackground(Color.WHITE);
			ensamblarCheckBox.setBounds(284, 343, 130, 24);
			FiltradodeProducto.add(ensamblarCheckBox);
			ensamblarCheckBox.setEnabled(false);



			primeraVisualizacionComponenetes();

		}
		{
			JPanel ProductoEncontrado = new JPanel();
			ProductoEncontrado.setBackground(Color.WHITE);
			ProductoEncontrado.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
			ProductoEncontrado.setBounds(438, 0, 452, 419);
			contentPanel.add(ProductoEncontrado);
			ProductoEncontrado.setLayout(null);

			JLabel lblComponenteEncontrado = new JLabel("Componente Encontrado");
			lblComponenteEncontrado.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblComponenteEncontrado.setBounds(12, 12, 402, 51);
			ProductoEncontrado.add(lblComponenteEncontrado);

			nombretext = new JTextField();
			nombretext.setEditable(false);
			nombretext.setBounds(155, 75, 220, 20);
			ProductoEncontrado.add(nombretext);
			nombretext.setColumns(10);

			modelotext = new JTextField();
			modelotext.setEditable(false);
			modelotext.setColumns(10);
			modelotext.setBounds(155, 107, 220, 20);
			ProductoEncontrado.add(modelotext);

			notext = new JTextField();
			notext.setEditable(false);
			notext.setColumns(10);
			notext.setBounds(155, 139, 220, 20);
			ProductoEncontrado.add(notext);

			preciotext = new JTextField();
			preciotext.setEditable(false);
			preciotext.setColumns(10);
			preciotext.setBounds(155, 172, 220, 20);
			ProductoEncontrado.add(preciotext);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBounds(43, 75, 141, 16);
			ProductoEncontrado.add(lblNombre);

			JLabel lblModelo = new JLabel("Modelo:");
			lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblModelo.setBounds(43, 108, 141, 16);
			ProductoEncontrado.add(lblModelo);

			JLabel lblNoDeSerie = new JLabel("No. de Serie:");
			lblNoDeSerie.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNoDeSerie.setBounds(43, 140, 141, 16);
			ProductoEncontrado.add(lblNoDeSerie);

			JLabel lblPrecio = new JLabel("Precio:");
			lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPrecio.setBounds(43, 173, 141, 16);
			ProductoEncontrado.add(lblPrecio);

			JLabel lblCantidadExistente = new JLabel("Cantidad:");
			lblCantidadExistente.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCantidadExistente.setBounds(43, 204, 141, 16);
			ProductoEncontrado.add(lblCantidadExistente);

			cantidadtxt = new JTextField();
			cantidadtxt.setEditable(false);
			cantidadtxt.setColumns(10);
			cantidadtxt.setBounds(155, 204, 220, 20);
			ProductoEncontrado.add(cantidadtxt);

			JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
			spinner.setBounds(121, 372, 63, 22);
			ProductoEncontrado.add(spinner);
			SpinnerNumberModel modelo = new SpinnerNumberModel(0,0,100,1);
			spinner.setModel(modelo);


			JLabel lblCantidadExistente_1 = new JLabel("Cantidad:");
			lblCantidadExistente_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCantidadExistente_1.setBounds(12, 374, 101, 16);
			ProductoEncontrado.add(lblCantidadExistente_1);

			JLabel lblCantidadExistente_1_1 = new JLabel("A\u00F1adir al carrito");
			lblCantidadExistente_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCantidadExistente_1_1.setBounds(221, 374, 141, 16);
			ProductoEncontrado.add(lblCantidadExistente_1_1);

			JButton btnCarrito = new JButton("");
			btnCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int num = 0;
					String numero = cantidadtxt.getText();
					int cantidadPieza = Integer.parseInt(numero);
					int sp = (int) spinner.getValue();
					if(nombretext.getText().isEmpty() || spinner.getValue().equals(num)){
						JOptionPane.showMessageDialog(VenderPieza.this, "Error. Debe de agregar algún producto al carrito");  
					}	
					else if(cantidadPieza < sp) {
						JOptionPane.showMessageDialog(VenderPieza.this, "Error. La cantidad deseada no esta disponible");  
					}
					else {
						float precio = Float.parseFloat(preciotext.getText());
						int cantidad = (int) spinner.getValue();
						float result = precio * cantidad;
						String coste = String.valueOf(result);
						tableModel.addRow(new Object[]{nombretext.getText(), modelotext.getText(), spinner.getValue(), coste});
					}
				}
			});

			btnCarrito.setBackground(Color.WHITE);
			btnCarrito.setIcon(new ImageIcon(VenderPieza.class.getResource("/gui/icons/carrito.png")));
			btnCarrito.setBounds(365, 365, 38, 36);
			ProductoEncontrado.add(btnCarrito);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 890, 682);
			panel.setBackground(UIManager.getColor("Button.disabledShadow"));
			contentPanel.add(panel);
			panel.setLayout(null);
			JPanel ProductosAgregados = new JPanel();
			ProductosAgregados.setBounds(0, 464, 890, 218);
			ProductosAgregados.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			ProductosAgregados.setBackground(UIManager.getColor("Button.disabledShadow"));
			panel.add(ProductosAgregados);
			ProductosAgregados.setLayout(new BorderLayout(0, 0));

			tableModel = new DefaultTableModel();
			tableModel.addColumn("Componenete");
			tableModel.addColumn("Modelo");
			tableModel.addColumn("Cantidad");
			tableModel.addColumn("Precio");

			table = new JTable(tableModel);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			ProductosAgregados.add(scrollPane, BorderLayout.CENTER);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new MatteBorder(2, 2, 1, 2, (Color) new Color(0, 0, 0)));
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(0, 419, 890, 45);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JButton okButton = new JButton("Borrar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int pos = table.getSelectedRow();
					if (pos != -1) {
						((DefaultTableModel) table.getModel()).removeRow(pos);			
					} else {
						JOptionPane.showMessageDialog(VenderPieza.this, "Antes de eliminar debe de seleccionar un componente de la tabla");
					}

				}
			});
			okButton.setActionCommand("OK");
			okButton.setBounds(803, 12, 76, 22);
			panel_1.add(okButton);

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Atr\u00E1s");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
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
				ensamblarCheckBox.setEnabled(true);


			} else {
				if (comboBoxAtributo2 != null) comboBoxAtributo2.setVisible(false);
				if (comboBoxAtributo3 != null) comboBoxAtributo3.setVisible(false);

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
			} else {
				if (Atributo2 != null) Atributo2.setVisible(false);
				if (Atributo3 != null) Atributo3.setVisible(false);
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
	

	}

	private void componenetesVentaLibreV() {
		if (Atributo2 != null) Atributo2.setVisible(true);
		if (comboBoxAtributo2 != null) comboBoxAtributo2.setVisible(true);
		if (Atributo3 != null) Atributo3.setVisible(true);
		if (comboBoxAtributo3 != null) comboBoxAtributo3.setVisible(true);

		repaint();
		validate();

	}
	private void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		comboBox.removeAllItems(); // Limpia el comboBox antes de llenarlo
		for (String item : items) {
			comboBox.addItem(item);
		}
		System.out.println("llenarComboBox ejecutado correctamente con " + items.size() + " elementos.");
	}

	/*****************Funcion para elegir las marcas************************************/
	@SuppressWarnings("unchecked")
	private void elegirMarca(Object seleccionado) {
		if (comboBoxMarca != null && comboBoxAtributo1 != null) {
			comboBoxAtributo1.setVisible(true); // Asegúrate de que el comboBox sea visible
			String nombreC = seleccionado.toString();
			ArrayList<String> Desicion = new ArrayList<>();
			Desicion.add("Sí");
			Desicion.add("No");
			TarjetaMadre t;
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
				comboBoxModelo.removeAllItems();
				break;
			case "Tarjeta Madre":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaMadre());
				Atributo1.setText("Tipo de Conector:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectores());
				componenetesVentaLibreV();
				Atributo2.setText("Tipo de RAM");
				//llenarComboBox(comboBoxAtributo2, t.getMemoriasR());
				comboBoxModelo.removeAllItems();
				break;
			case "Microprocesador":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMicroProcesadores());
				Atributo1.setText("Tipo de Conexion:");
				if(comboBoxMarca.getSelectedItem().equals("AMD")) {
					llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesAMD());
				}
				else {
					llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesIntel());	
				}
				componenetesVentaLibreV();
				comboBoxModelo.removeAllItems();
				Atributo2.setText("Velocidad de Procesamiento");
				//llenarComboBox(comboBoxAtributo2, inicializaciones.InicializacionDeDatos.);
				break;
			case "Adaptador":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasExtension());
				Atributo1.setVisible(false);
				comboBoxAtributo1.setVisible(false);
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				break;
			case "Bocina":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasBocinas());
				Atributo1.setText("Conectividad:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				break;
			case "Monitor":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasPantalla());
				Atributo1.setText("Resolución:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.resolucionVideo());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				break;
			case "Ratón":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasRaton());
				Atributo1.setText("No. de Botones:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				break;
			case "Memoria RAM":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMemoriasRAM());
				Atributo1.setText("Espacio:");
				componenetesVentaLibreV();
				Atributo2.setText("Tipo de Memoria:");
				llenarComboBox(comboBoxAtributo2, inicializaciones.InicializacionDeDatos.tiposDeMemoriaRAM());
				comboBoxModelo.removeAllItems();
				break;
			case "Chasis":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasChasis());
				Atributo1.setText("Material:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.materialesChasis());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				break;		
			case "Disco Duro":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasDiscoD());
				Atributo1.setText("Capacidad:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.capacidadesDisco());
				componenetesVentaLibreV();
				comboBoxModelo.removeAllItems();
				break;
			case "Fuente":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasFuente());
				Atributo1.setText("Eficiencia:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.eficiencia());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				break;
			default:
				llenarComboBox(comboBoxMarca, new ArrayList<>());
				break;
			}
		} else {
			System.out.println("Uno o más comboBox son null");
		}
	}
	/*******************************Teclado**********************************/
	private void actualizarModeloTeclado(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modelo("Teclado", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}
	}
	/*******************************Adaptador**********************************/
	private void actualizarModeloAdaptador(String combo1) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloAdaptador("Adaptador", combo1);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}
	}
	/*******************************Bocina**********************************/
	private void actualizarModeloBocina(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloBocina("Bocina", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}
	}
	/*******************************Adaptador**********************************/
	private void actualizarModeloChasis(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloChasis("Chasis", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}	
	}
	/*******************************Monitor**********************************/
	private void actualizarModeloMonitor(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloMonitor("Monitor", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}
	}

	/*******************************Tarjeta de Video **********************************/
	private void actualizarModeloTarjetaVideo(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloTarjetaVideo("TarjetaDeVideo", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}

	}
	/***************************************Raton****************************************/
	private void actualizarModeloRaton(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloMouse("Mouse", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}
	}
	/*****************************************Fuente************************************************/

	private void actualizarModeloFuente(String combo1, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloFuente("Mouse", combo1, combo2);
			if (modelo != null && !modelo.isEmpty()) {
				llenarComboBox(comboBoxModelo, modelo);
				comboBoxModelo.setEnabled(true);
			} else {
				limpiarDatos();
				comboBoxModelo.setEnabled(false);
				JOptionPane.showMessageDialog(VenderPieza.this, "Componente no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el modelo: " + e.getMessage());
		}
	}

	private void limpiarComboBox() {
		comboBoxMarca.removeAllItems();
	}

	private void limpiarComboBoxModelo() {
		comboBoxModelo.removeAllItems();
	}

	private void iniciar() {
		comboBoxMarca.setSelectedIndex(0);
	}

	private void actualizarComponente(String modelo, String componente) {
		String nombreC = componente;
		switch (nombreC) {
		case "Teclado":
			try {	
				Teclado t = tiendaC.encontTeclado("Teclado", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (t != null) {
					nombretext.setText("Teclado");
					modelotext.setText(t.getModelo());
					notext.setText(t.getNumSerie());
					preciotext.setText(String.valueOf(t.getPrecio()));
					cantidadtxt.setText(String.valueOf(t.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Adaptador":
			try {
				Adaptador a = tiendaC.encontAdaptador("Teclado", (String) comboBoxMarca.getSelectedItem(), modelo);
				if (a != null) {
					nombretext.setText("Adaptador");
					modelotext.setText(a.getModelo());
					notext.setText(a.getNumSerie());
					preciotext.setText(String.valueOf(a.getPrecio()));
					cantidadtxt.setText(String.valueOf(a.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Bocina":
			try {
				Bocina b = tiendaC.encontBocina("Bocina", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (b != null) {
					nombretext.setText("Bocina");
					modelotext.setText(b.getModelo());
					notext.setText(b.getNumSerie());
					preciotext.setText(String.valueOf(b.getPrecio()));
					cantidadtxt.setText(String.valueOf(b.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Chasis":
			try {
				Chasis c = tiendaC.encontChasis("Chasis", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (c != null) {
					nombretext.setText("Chasis");
					modelotext.setText(c.getModelo());
					notext.setText(c.getNumSerie());
					preciotext.setText(String.valueOf(c.getPrecio()));
					cantidadtxt.setText(String.valueOf(c.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Monitor":
			try {
				Monitor m = tiendaC.encontMonitor("Monitor", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (m != null) {
					nombretext.setText("Monitor");
					modelotext.setText(m.getModelo());
					notext.setText(m.getNumSerie());
					preciotext.setText(String.valueOf(m.getPrecio()));
					cantidadtxt.setText(String.valueOf(m.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Tarjeta de Video":
			try {
				TarjetaDeVideo t = tiendaC.encontTarjetaVideo("TarjetaDeVideo", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (t != null) {
					nombretext.setText("Tarjeta de Video");
					modelotext.setText(t.getModelo());
					notext.setText(t.getNumSerie());
					preciotext.setText(String.valueOf(t.getPrecio()));
					cantidadtxt.setText(String.valueOf(t.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Ratón":
			try {
				Mouse b = tiendaC.encontMouse("Mouse", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (b != null) {
					nombretext.setText("Ratón");
					modelotext.setText(b.getModelo());
					notext.setText(b.getNumSerie());
					preciotext.setText(String.valueOf(b.getPrecio()));
					cantidadtxt.setText(String.valueOf(b.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Fuente":
			try {
				Fuente f = tiendaC.encontFuente("Fuente", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (f != null) {
					nombretext.setText("Fuente");
					modelotext.setText(f.getModelo());
					notext.setText(f.getNumSerie());
					preciotext.setText(String.valueOf(f.getPrecio()));
					cantidadtxt.setText(String.valueOf(f.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		}

	}

	private void limpiarDatos() {
		nombretext.setText("");
		modelotext.setText("");
		notext.setText("");
		preciotext.setText("");
		cantidadtxt.setText("");

	}
}