package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import inicializaciones.InicializacionDeDatos;
import logica.ComponenteOrdenador;
import logica.TiendaDeComputadoras;

public class AgregarProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private TiendaDeComputadoras tienda;
	private JPanel contentPanel = new JPanel();
	private JTextField txtNoSerie;
	private JTextField txtPrecio;
	private JSpinner spinner;
	private JComboBox<String> comboBoxMarca;
	private JComboBox<String> comboBoxComponente;
	private JComboBox<String> comboBoxModelo;
	private JButton btnEliminar;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnGuardar;
	private ComponenteOrdenador componente;
	private JPanel panelSecundario;
	private JButton btnAtrs;
	private ArrayList<ComponenteOrdenador> piezasAgreg;
	private static float costo;
	private static int cantidad;
	private static String pieza;
	private static String marca;
	private static String modelo;
	private static String noSerie;
	private static Object marcaSeleccionada = null;
	private static Object compSeleccionado = null;


	public AgregarProducto(Principal principal, TiendaDeComputadoras tiendaC) {
		super(principal, true);
		piezasAgreg = new ArrayList<ComponenteOrdenador>();
		tienda = tiendaC;

		setTitle("Manejo de trabajadores");
		setBounds(100, 100, 900, 746);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 890, 682);
		panelPrincipal.setBackground(UIManager.getColor("Button.disabledShadow"));
		contentPanel.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		JPanel panelAgregarPiezas = new JPanel();
		panelAgregarPiezas.setBounds(29, 87, 837, 296);
		panelAgregarPiezas.setForeground(UIManager.getColor("Button.frame"));
		panelAgregarPiezas.setBorder(new TitledBorder(new LineBorder(new Color(0, 88, 168)), "Agregar Pieza", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAgregarPiezas.setBackground(UIManager.getColor("Button.light"));
		panelPrincipal.add(panelAgregarPiezas);
		panelAgregarPiezas.setLayout(null);

		JLabel lblComponente = new JLabel("Componente:");
		lblComponente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblComponente.setBounds(12, 44, 128, 16);
		panelAgregarPiezas.add(lblComponente);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMarca.setBounds(12, 71, 128, 16);
		panelAgregarPiezas.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setBounds(12, 98, 128, 16);
		panelAgregarPiezas.add(lblModelo);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setBounds(12, 125, 128, 16);
		panelAgregarPiezas.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad.setBounds(12, 179, 128, 16);
		panelAgregarPiezas.add(lblCantidad);

		JLabel lblNoSerie = new JLabel("No. Serie:");
		lblNoSerie.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNoSerie.setBounds(12, 152, 128, 16);
		panelAgregarPiezas.add(lblNoSerie);

		spinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		spinner.setBounds(247, 178, 56, 22);
		SpinnerNumberModel model = new SpinnerNumberModel(0,0,100,1);
		spinner.setModel(model);
		panelAgregarPiezas.add(spinner);

		txtNoSerie = new JTextField();
		txtNoSerie.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNoSerie.setForeground(UIManager.getColor("Button.foreground"));
		txtNoSerie.setBounds(247, 151, 560, 20);
		panelAgregarPiezas.add(txtNoSerie);
		txtNoSerie.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(247, 124, 560, 20);
		panelAgregarPiezas.add(txtPrecio);

		comboBoxComponente = new JComboBox<>();
		llenarComboBox(comboBoxComponente, InicializacionDeDatos.nameComponente());
		comboBoxMarca = new JComboBox<>();
		llenarComboBox(comboBoxMarca, InicializacionDeDatos.marcasTeclado());
		comboBoxModelo = new JComboBox<>();
		llenarComboBox(comboBoxModelo, InicializacionDeDatos.tecladosHyperX());

		comboBoxMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxMarca != null) {
				compSeleccionado = comboBoxComponente.getSelectedItem();
				elegirModelo(compSeleccionado, marcaSeleccionada);
				}
				else
					System.out.println("estas maja");
			}
		});
		comboBoxMarca.setBounds(247, 71, 560, 20);
		panelAgregarPiezas.add(comboBoxMarca);

		comboBoxComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compSeleccionado = comboBoxComponente.getSelectedItem();
				comboBoxMarca.removeAllItems();
				elegirMarca(compSeleccionado);
			}
		});
		comboBoxComponente.setBounds(247, 44, 560, 20);
		panelAgregarPiezas.add(comboBoxComponente);

		comboBoxModelo.setBounds(247, 98, 560, 20);
		panelAgregarPiezas.add(comboBoxModelo);

		JButton btnBorrar = new JButton("Limpiar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarDatos();
			}
		});
		btnBorrar.setBounds(737, 263, 70, 22);
		panelAgregarPiezas.add(btnBorrar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datoIncorrecto = false;
				boolean cantidadCero = false;
				noSerie = txtNoSerie.getText();
				cantidad = (int) spinner.getValue();
				String precio = txtPrecio.getText();
				pieza = (String) comboBoxComponente.getSelectedItem();
				marca = (String) comboBoxMarca.getSelectedItem();
				modelo = (String) comboBoxModelo.getSelectedItem();

				if (noSerie.trim().isEmpty() || !noSerie.matches("^[a-zA-Z0-9]+$")) {
					lblNoSerie.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblNoSerie.setForeground(Color.BLACK);
				}
				if (cantidad == 0) 
					cantidadCero = true;
				if (precio.trim().isEmpty()) {
					lblPrecio.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblPrecio.setForeground(Color.BLACK);
				}

				if (datoIncorrecto && cantidadCero) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "No pueden estar vacíos los campos marcados en rojo y la cantidad de piezas a agregar no puede ser 0.");
				} 
				else if (datoIncorrecto) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "No pueden estar vacíos los campos marcados en rojo.");
				}
				else if (cantidadCero) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "La cantidad de piezas a agregar no puede ser 0");
				}
				else {
					try {
						costo = Float.parseFloat(precio);
						ComponenteOrdenador comp = new ComponenteOrdenador(cantidad, noSerie, marca, modelo, costo);
						componente = comp;
						piezasAgreg.add(componente);
						JOptionPane.showMessageDialog(AgregarProducto.this, "Pieza agregada a la tabla de manera satisfactoria");
						tableModel.addRow(new Object[]{pieza, marca, modelo, costo, cantidad, noSerie});
						iniciarDatos();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(AgregarProducto.this, "El salario debe ser un número válido.");
					}
				}
			}

		});
		btnAgregar.setBounds(656, 263, 70, 22);
		panelAgregarPiezas.add(btnAgregar);

		JPanel panelPiezasAgregadas = new JPanel();
		panelPiezasAgregadas.setBounds(29, 399, 837, 237);
		panelPiezasAgregadas.setBorder(new TitledBorder(null, "Piezas agregadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPiezasAgregadas.setBackground(UIManager.getColor("Button.disabledShadow"));
		panelPrincipal.add(panelPiezasAgregadas);
		panelPiezasAgregadas.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Componente");
		tableModel.addColumn("Marca");
		tableModel.addColumn("Modelo");
		tableModel.addColumn("Precio");
		tableModel.addColumn("Cantidad");
		tableModel.addColumn("No. Serie");

		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		panelPiezasAgregadas.add(scrollPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 12, 76, 60);
		lblNewLabel.setIcon(new ImageIcon(AgregarTrabajador.class.getResource("/gui/icons/logoPeque\u00F1o1.jpg")));
		panelPrincipal.add(lblNewLabel);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(797, 647, 70, 22);
		panelPrincipal.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la pieza seleccionada?", "", 0, 3);
				if(i==0) {		
					int pos = table.getSelectedRow();
					if (pos != -1) {
						piezasAgreg.remove(pos);
						((DefaultTableModel) table.getModel()).removeRow(pos);
					} else {
						JOptionPane.showMessageDialog(AgregarProducto.this, "Antes de eliminar debe de seleccionar una pieza de la tabla");
					}
				}
			}
		});

		panelSecundario = new JPanel();
		panelSecundario.setBackground(Color.WHITE);
		panelSecundario.setBounds(0, 683, 890, 33);
		contentPanel.add(panelSecundario);
		panelSecundario.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelSecundario.add(btnAtrs);

		btnGuardar = new JButton("Aceptar");
		panelSecundario.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actualizarLista() == true) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "Cambios guardados satisfactoriamente");
					dispose();
				}
			}
		});
		iniciarDatos();

	}

	private void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		if(comboBox != null) {
			comboBox.removeAllItems();
			for (String item : items) {
				comboBox.addItem(item);
			}
		}
		else 
			System.out.println("ComboBox es null en llenarComboBox.");

	}

	private boolean actualizarLista() {
		boolean act = false;
		if(!piezasAgreg.isEmpty()) {
			tienda.agregarP(piezasAgreg);
			act = true;
		}
		else {
			JOptionPane.showMessageDialog(AgregarProducto.this, "No se ha realizado ningún cambio");
		}
		return act;
	}
	private void iniciarDatos() {
		txtNoSerie.setText("");
		txtPrecio.setText("");
		spinner.setValue(0);
	}

	private void elegirMarca(Object compSeleccionado) {
		if (comboBoxMarca != null) {
			String comp = compSeleccionado.toString();
			switch (comp) {
			case "Teclado":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTeclado());
				break;
			case "Tarjeta de Video":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaVideos());
				break;
			case "Tarjeta Madre":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaMadre());
				break;
			case "Microprocesador":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMicroProcesadores());
				break;
			case "Adaptador":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasExtension());
				break;
			case "Bocina":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasBocinas());
				break;
			case "Monitor":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasPantalla());
				break;
			case "Ratón":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasRaton());
				break;
			case "Memoria RAM":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMemoriasRAM());
				break;
			case "Chasis":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasChasis());
				break;		
			case "Disco Duro":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasDiscoD());
				break;
			case "Fuente":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasFuente());
				break;
			default:
				llenarComboBox(comboBoxMarca, new ArrayList<>());
				break;
			}
		} else {
			System.out.println("Uno o más comboBox son null");
		}
		marcaSeleccionada = comboBoxMarca.getSelectedItem();
		elegirModelo(compSeleccionado, marcaSeleccionada);
	}

	private void elegirModelo(Object compSeleccionado, Object marcaSeleccionada) {
		if (comboBoxMarca != null && comboBoxModelo != null) {
			String comp = compSeleccionado.toString();
			String marca = marcaSeleccionada.toString();
			comboBoxModelo.removeAllItems();
			switch (comp) {
			case "Teclado":
				if(marca == "HyperX") 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosHyperX());
				else if(marca == "Corsair") 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosCorsair());
				else if(marca == "Logitech") 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosLogitech());
				else if(marca == "Razer") 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosRazer());
				else 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosSteelSeries());
				break;
			case "Tarjeta de Video":
				if(marca == "AMD")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoAMD());
				else if(marca == "ASUS")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoASUS());
				else if(marca == "Gigabyte")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoGigabyte());
				else if(marca == "MSI")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoMSI());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoNVIDIA());
				break;
			case "Tarjeta Madre":
				if(marca == "ASRock")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreASRock());
				else if(marca == "ASUS")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreASUS());
				else if(marca == "EVGA")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreEVGA());
				else if(marca == "MSI")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreMSI());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreGigabyte());
				break;
			case "Microprocesador":
				if(marca == "AMD")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.microprocesadoresAMD());
				else 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.microprocesadoresIntel());
				break;
			case "Adaptador": 
				if(marca == "Amazon" || marca == "Apple") {
					comboBoxModelo.removeAll();
					comboBoxModelo.addItem("Canvio Basics");
				}
				else if(marca == "ViTech") {
					comboBoxModelo.removeAll();
					comboBoxModelo.addItem("Canvio Basics 5A");
				}
				else if(marca == "Mercado Libre") {
					comboBoxModelo.removeAll();
					comboBoxModelo.addItem("Canvio Basics 2B");
				}
				else {
					comboBoxModelo.removeAll();
					comboBoxModelo.addItem("Canvio Basics 8C");
				}
				break; 
			case "Bocina":
				if(marca == "Bose")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasBose());
				else if(marca == "JBL")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasJBL());
				else if(marca == "Sony")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasSony());
				else if(marca == "Logitech")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasLogitech());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasSennheiser());
				break;
			case "Monitor":
				if(marca == "Acer")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasAcer());
				else if(marca == "ASUS")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasASUS());
				else if(marca == "Dell")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasDell());
				else if(marca == "LG")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasLG());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasSamsung());
				break;
			case "Ratón":
				if(marca == "Corsair")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseCorsair());
				else if(marca == "HyperX")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseHyperX());
				else if(marca == "Logitech")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseLogitech());
				else if(marca == "Razer")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseRazer());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseSteelSeries());
				break;
			case "Memoria RAM":
				if(marca == "Corsair")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasCorsair());
				else if(marca == "Crucial")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasCrucial());
				else if(marca == "GSkill")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasGSkill());
				else if(marca == "HyperX")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasHyperX());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasKingston());
				break;
			case "Chasis":
				if(marca == "NZXT")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisNZXT());
				else if(marca == "Thermaltake")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisThermaltake());
				else if(marca == "Cooler Master")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisCoolerMaster());
				else if(marca == "Corsair")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisCorsair());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisFractalDesign());
				break;		
			case "Disco Duro":
				if(marca == "Crucial")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosCrucial());
				else if(marca == "Samsung")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosSamsung());
				else if(marca == "Seagate")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosSeagate());
				else if(marca == "WD")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosWD());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosToshiba());
				break;
			case "Fuente":
				if(marca == "Be Quiet!")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesBeQuiet());
				else if(marca == "Corsair")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesCorsair());
				else if(marca == "EVGA")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesEVGA());
				else if(marca == "Seasonic")
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesSeasonic());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesThermaltake());
				break;
			default:
				llenarComboBox(comboBoxModelo, new ArrayList<>());
				break;
			}
		} else {
			System.out.println("Uno o más comboBox son null");
		}
	}

}


