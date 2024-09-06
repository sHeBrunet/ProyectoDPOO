package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import componentesVisuales.JTextFieldModificado;
import logica.ComponenteOrdenador;
import logica.TiendaDeComputadoras;

public class AgregarProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private TiendaDeComputadoras tienda;
	private JPanel contentPanel = new JPanel();
	private JTextField txtNoSerieFijo;
	private JTextField txtPrecio;
	private JTextFieldModificado txtNoSerieMovible;
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
	private static int cantidad;
	private static float precio;
	private static String pieza;
	private static String marca;
	private static String modelo;
	private static String noSerie;
	private static Object compSeleccionado = null;
	private static JLabel lblNoSerie;
	private static JLabel lblCantidad;
	private static JLabel lblPrecio;
	private static JLabel Atributo1;
	private static JLabel Atributo2;
	private JComboBox<String> comboBoxAtributo1;
	private JSpinner spinnerAtributo2;
	private JLabel lblAtributo2;

	public AgregarProducto(Principal principal, TiendaDeComputadoras tiendaC) {
		super(principal, true);
		piezasAgreg = new ArrayList<ComponenteOrdenador>();
		tienda = tiendaC;

		setTitle("Manejo de productos");
		setBounds(100, 100, 900, 746);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 890, 672);
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

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setBounds(12, 213, 128, 16);
		panelAgregarPiezas.add(lblPrecio);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad.setBounds(12, 186, 128, 16);
		panelAgregarPiezas.add(lblCantidad);

		lblNoSerie = new JLabel("No. Serie:");
		lblNoSerie.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNoSerie.setBounds(12, 155, 162, 20);
		panelAgregarPiezas.add(lblNoSerie);

		spinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		spinner.setFont(new Font("Arial", Font.PLAIN, 15));
		spinner.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		spinner.setBounds(247, 184, 70, 22);
		SpinnerNumberModel model = new SpinnerNumberModel(0,0,100,1);
		spinner.setModel(model);
		panelAgregarPiezas.add(spinner);

		txtNoSerieMovible = new JTextFieldModificado();
		txtNoSerieMovible.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t = e.getKeyChar();
				if ((t < '0' || t > '9') && (t < 'A' || t > 'Z') && (t < 'a' || t > 'z') && t != 8) {
					JOptionPane.showMessageDialog(null, "En este campo no se pueden introducir caracteres especiales", "Datos erróneos", JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
		txtNoSerieMovible.setDisabledTextColor(Color.WHITE);
		txtNoSerieMovible.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(0, 0, 0)));
		txtNoSerieMovible.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNoSerieMovible.setForeground(UIManager.getColor("Button.foreground"));
		txtNoSerieMovible.setBounds(269, 157, 48, 20);
		panelAgregarPiezas.add(txtNoSerieMovible);
		txtNoSerieMovible.setColumns(10);
		txtNoSerieMovible.setLimite(5);

		txtNoSerieFijo = new JTextField();
		txtNoSerieFijo.setEditable(false);
		txtNoSerieFijo.setDisabledTextColor(Color.WHITE);
		txtNoSerieFijo.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		txtNoSerieFijo.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNoSerieFijo.setForeground(UIManager.getColor("Button.foreground"));
		txtNoSerieFijo.setBounds(247, 157, 25, 20);
		panelAgregarPiezas.add(txtNoSerieFijo);
		txtNoSerieFijo.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setDisabledTextColor(Color.WHITE);
		txtPrecio.setEditable(false);
		txtPrecio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(247, 212, 70, 20);
		panelAgregarPiezas.add(txtPrecio);

		Atributo1 = new JLabel();
		Atributo1.setText("Atributo1");
		Atributo1.setFont(new Font("Tahoma", Font.BOLD, 15));
		Atributo1.setBorder(null);
		Atributo1.setBounds(12, 126, 162, 20);
		panelAgregarPiezas.add(Atributo1);

		lblAtributo2 = new JLabel();
		lblAtributo2.setBorder(null);
		lblAtributo2.setBackground(Color.WHITE);
		lblAtributo2.setText("t");
		lblAtributo2.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelAgregarPiezas.add(lblAtributo2);
		lblAtributo2.setBounds(327, 155, 37, 19);

		Atributo2 = new JLabel();
		Atributo2.setVisible(false);
		Atributo2.setBorder(null);
		Atributo2.setFont(new Font("Tahoma", Font.BOLD, 15));
		Atributo2.setText("Atributo2");
		Atributo2.setBounds(12, 155, 162, 20);
		panelAgregarPiezas.add(Atributo2);

		comboBoxModelo = new JComboBox<String>();	
		comboBoxModelo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBoxModelo.setBounds(247, 98, 560, 20);
		panelAgregarPiezas.add(comboBoxModelo);

		comboBoxComponente = new JComboBox<String>();
		comboBoxComponente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBoxComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compSeleccionado = comboBoxComponente.getSelectedItem();
				comboBoxMarca.removeAllItems();
				elegirMarca(compSeleccionado);
				elegirModelo(compSeleccionado, comboBoxMarca.getSelectedItem());
				txtPrecio.setText(Float.toString(precio));
			}
		});
		comboBoxComponente.setBounds(247, 44, 560, 20);
		panelAgregarPiezas.add(comboBoxComponente);

		comboBoxMarca = new JComboBox<String>();
		comboBoxMarca.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBoxMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(compSeleccionado != null) {
					elegirModelo(compSeleccionado, comboBoxMarca.getSelectedItem());
					txtPrecio.setText(Float.toString(precio));
				}

			}
		});
		comboBoxMarca.setBounds(247, 71, 560, 20);
		panelAgregarPiezas.add(comboBoxMarca);

		comboBoxAtributo1 = new JComboBox<String>();
		comboBoxAtributo1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBoxAtributo1.setBounds(247, 128, 560, 20);
		panelAgregarPiezas.add(comboBoxAtributo1);

		spinnerAtributo2 = new JSpinner(new SpinnerNumberModel(1,1,100,.5));
		spinnerAtributo2.setVisible(false);
		spinnerAtributo2.setFont(new Font("Arial", Font.PLAIN, 15));
		spinnerAtributo2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		spinnerAtributo2.setBounds(247, 155, 70, 22);
		spinnerAtributo2.setModel(new SpinnerNumberModel(1, 1, 100, .5));
		panelAgregarPiezas.add(spinnerAtributo2);

		llenarComboBox(comboBoxComponente, inicializaciones.InicializacionDeDatos.nameComponente());
		llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasAdaptadores());
		precio = 12;
		txtPrecio.setText(Float.toString(precio));

		JButton btnBorrar = new JButton("Limpiar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});
		btnBorrar.setBounds(737, 263, 70, 22);
		panelAgregarPiezas.add(btnBorrar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datoIncorrecto = false;
				boolean cantidadCero = false;
				boolean IDRepetido = false;
				noSerie = txtNoSerieFijo.getText() + txtNoSerieMovible.getText();
				cantidad = (int) spinner.getValue();
				pieza = (String) comboBoxComponente.getSelectedItem();
				marca = (String) comboBoxMarca.getSelectedItem();
				modelo = (String) comboBoxModelo.getSelectedItem();

				if (txtNoSerieMovible.getText().length() == 0) {
					lblNoSerie.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblNoSerie.setForeground(Color.BLACK);
					boolean stop = false;
					for(int i = 0; i < tienda.getComponentes().size() && !stop; i++) {
						if(tienda.getComponentes().get(i).getNumSerie().equalsIgnoreCase(noSerie)
								&& (!(tienda.getComponentes().get(i).getMarca().equalsIgnoreCase(marca))
										|| (!(tienda.getComponentes().get(i).getModelo().equalsIgnoreCase(modelo))))) {
							stop = true;
							IDRepetido = true;
						}
					}
				}
				if (cantidad == 0) 
					cantidadCero = true;

				if (datoIncorrecto && cantidadCero) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "No pueden estar vacíos los campos marcados en rojo y la cantidad de piezas a agregar no puede ser 0.");
				} 
				else if (datoIncorrecto) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "No pueden estar vacíos los campos marcados en rojo.");
				}
				else if (cantidadCero) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "La cantidad de piezas a agregar no puede ser 0");
				}
				else if (IDRepetido) {
					JOptionPane.showMessageDialog(AgregarProducto.this, "Ya existe una pieza distinta en el almacen con ese No de Serie.");
				}
				else {
					ComponenteOrdenador comp = new ComponenteOrdenador(cantidad, noSerie, marca, modelo, precio);
					componente = comp;
					piezasAgreg.add(componente);
					JOptionPane.showMessageDialog(AgregarProducto.this, "Pieza agregada a la tabla de manera satisfactoria");
					tableModel.addRow(new Object[]{pieza, marca, modelo, precio, cantidad, noSerie});
				}
				limpiarDatos();
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

		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("Componente");
		tableModel.addColumn("Marca");
		tableModel.addColumn("Modelo");
		tableModel.addColumn("Precio");
		tableModel.addColumn("Cantidad");
		tableModel.addColumn("No. Serie");

		table = new JTable(tableModel);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
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
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		panelPiezasAgregadas.add(scrollPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 12, 76, 60);
		lblNewLabel.setIcon(new ImageIcon(AgregarProducto.class.getResource("/gui/icons/logoPeque\u00F1o1.jpg")));
		panelPrincipal.add(lblNewLabel);

		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(AgregarProducto.class.getResource("/gui/icons/basura.png")));
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
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setBorder(null);
		btnEliminar.setFocusable(false);
		btnEliminar.setActionCommand("OK");
		btnEliminar.setBounds(813, 636, 41, 35);	
		panelPrincipal.add(btnEliminar);

		panelSecundario = new JPanel();
		panelSecundario.setBackground(Color.WHITE);
		panelSecundario.setBounds(0, 676, 890, 40);
		contentPanel.add(panelSecundario);
		panelSecundario.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actualizarLista() == true) {
					int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir? Los cambios realizados no serán guardados", "", 0, 3);
					if(i==0) 
						dispose();
				}
				else
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
					limpiarDatos();
				}
				limpiarDatos();
			}
		});
	}

	private void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		if(comboBox != null) {
			comboBox.removeAllItems();
			for (String item : items) {
				comboBox.addItem(item);
			}
		}
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

	private void limpiarDatos() {
		spinner.setValue(0);
		spinnerAtributo2.setValue(0);
		txtNoSerieMovible.setText("");
	}

	private void elegirMarca(Object compSeleccionado) {
		if (comboBoxMarca != null) {	
			String comp = compSeleccionado.toString();
			ArrayList <String> decision = new ArrayList<>();
			decision.add("Sí");
			decision.add("No");
			switch (comp) {
			case "Teclado":
				precio = 25;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTeclado());
				organizarLabelsUnAtrib();
				txtNoSerieFijo.setText("TE");	
				Atributo1.setText("Retroiluminación");
				llenarComboBox(comboBoxAtributo1, decision);
				break;
			case "Tarjeta de Video":	
				precio = 100;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaVideos());
				txtNoSerieFijo.setText("TT");
				organizarLabelsUnAtrib();
				Atributo1.setText("Refrigeración");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.refrigeracion());
				break;
			case "Tarjeta Madre":
				precio = 150;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaMadre());
				txtNoSerieFijo.setText("TM");
				organizarLabelsUnAtrib();
				Atributo1.setText("Conector");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectores());
				break;
			case "Microprocesador":
				precio = 75;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMicroProcesadores());
				txtNoSerieFijo.setText("MP");
				organizarLabelsDosAtrib();
				Atributo1.setText("Conexión");
				Atributo2.setText("Procesamiento");
				lblAtributo2.setText("GHz");
				spinnerAtributo2.setModel(new SpinnerNumberModel(1, 1, 100, .5));
				break;
			case "Adaptador":
				precio = 12;
				Atributo1.setVisible(false);
				Atributo2.setVisible(false);
				comboBoxAtributo1.setVisible(false);
				spinnerAtributo2.setVisible(false);
				lblNoSerie.setBounds(12, 125, 128, 16);
				lblCantidad.setBounds(12, 152, 128, 16);
				lblPrecio.setBounds(12, 179, 128, 16);
				lblAtributo2.setVisible(false);
				txtNoSerieFijo.setBounds(247, 124, 24, 20);
				spinner.setBounds(247, 151, 70, 22);
				txtPrecio.setBounds(247, 178, 70, 20);
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasAdaptadores());
				txtNoSerieFijo.setText("A");
				txtNoSerieMovible.setBounds(267, 124, 50, 20);
				break;
			case "Bocina":	
				precio = 25;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasBocinas());
				txtNoSerieFijo.setText("B");
				organizarLabelsUnAtrib();	
				Atributo1.setText("Conectividad");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
				break;
			case "Monitor":
				precio = 55;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasPantalla());
				txtNoSerieFijo.setText("MN");
				organizarLabelsUnAtrib();
				Atributo1.setText("Resolución");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.resolucionVideo());
				break;
			case "Ratón":
				precio = 25;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasRaton());
				txtNoSerieFijo.setText("R");
				organizarLabelsUnAtrib();
				Atributo1.setText("Conectividad");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
				break;
			case "Memoria RAM":
				precio = 45;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMemoriasRAM());
				txtNoSerieFijo.setText("MR");
				organizarLabelsDosAtrib();
				Atributo1.setText("Tipo de Memoria");
				Atributo2.setText("Espacio");
				lblAtributo2.setText("Gb");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.tiposDeMemoriaRAM());
				spinnerAtributo2.setModel(new SpinnerNumberModel(1, null, 8, 1) {
					private static final long serialVersionUID = 1L;
					@Override
					public Object getNextValue() {                
						Object nextValue = super.getValue();
						int x = Integer.valueOf(nextValue.toString())*2;
						return x;
					}
				});
				break;
			case "Chasis":
				precio = 100;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasChasis());
				txtNoSerieFijo.setText("C");
				organizarLabelsUnAtrib();
				Atributo1.setText("Material");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.materialesChasis());
				break;		
			case "Disco Duro":
				precio = 40;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasDiscoD());
				txtNoSerieFijo.setText("DD");
				organizarLabelsDosAtrib();
				Atributo1.setText("Conexión");
				Atributo2.setText("Capacidad");
				lblAtributo2.setText("Tb");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesDiscoDuro());
				break;
			case "Fuente":	
				precio = 45;
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasFuente());
				txtNoSerieFijo.setText("F");
				organizarLabelsUnAtrib();
				Atributo1.setText("Eficiencia");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.eficiencia());
				break;
			default:
			}
		}
		elegirModelo(compSeleccionado, comboBoxMarca.getSelectedItem());
	}

	private void elegirModelo(Object compSeleccionado, Object marcaSeleccionada) {
		if (marcaSeleccionada != null && compSeleccionado!= null) {
			String comp = compSeleccionado.toString();
			String marca = marcaSeleccionada.toString();
			comboBoxModelo.removeAllItems();
			switch (comp) {
			case "Teclado":
				if(marca.equalsIgnoreCase("HyperX")) 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosHyperX());			
				else if(marca.equalsIgnoreCase("Corsair"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosCorsair());
				else if(marca.equalsIgnoreCase("Logitech"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosLogitech());
				else if(marca.equalsIgnoreCase("Razer"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosRazer());
				else 
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tecladosSteelSeries());
				break;
			case "Tarjeta de Video":
				if(marca.equalsIgnoreCase("AMD"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoAMD());
				else if(marca.equalsIgnoreCase("ASUS"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoASUS());
				else if(marca.equalsIgnoreCase("Gigabyte"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoGigabyte());
				else if(marca.equalsIgnoreCase("MSI"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoMSI());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasVideoNVIDIA());
				break;
			case "Tarjeta Madre":
				if(marca.equalsIgnoreCase("ASRock"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreASRock());
				else if(marca.equalsIgnoreCase("ASUS"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreASUS());
				else if(marca.equalsIgnoreCase("EVGA"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreEVGA());
				else if(marca.equalsIgnoreCase("MSI"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreMSI());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.tarjetasMadreGigabyte());
				break;
			case "Microprocesador":
				if(marca.equalsIgnoreCase("AMD")) {
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.microprocesadoresAMD());
					llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesAMD());
				}
				else {
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.microprocesadoresIntel());
					llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesIntel());
				}
				break;
			case "Adaptador": 
				comboBoxModelo.removeAllItems();
				if(marca.equalsIgnoreCase("Anker")) {
					comboBoxModelo.addItem("Hub 7en1 Thunderbolt 3");
				}
				else if(marca.equalsIgnoreCase("Amazon Basics")) {					
					comboBoxModelo.addItem("USB-C a USB-A");
				}
				else if(marca.equalsIgnoreCase("Apple")) {
					comboBoxModelo.addItem("USB-C a Ethernet");
				}
				else if(marca.equalsIgnoreCase("Belkin")) {
					comboBoxModelo.addItem("USB-C a HDMI");
				}
				else {
					comboBoxModelo.addItem("HDMI a VGA");
				}
				break; 
			case "Bocina":
				if(marca.equalsIgnoreCase("Bose"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasBose());
				else if(marca.equalsIgnoreCase("JBL"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasJBL());
				else if(marca.equalsIgnoreCase("Sony"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasSony());
				else if(marca.equalsIgnoreCase("Logitech"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasLogitech());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.bocinasSennheiser());
				break;
			case "Monitor":
				if(marca.equalsIgnoreCase("Acer"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasAcer());
				else if(marca.equalsIgnoreCase("ASUS"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasASUS());
				else if(marca.equalsIgnoreCase("Dell"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasDell());
				else if(marca.equalsIgnoreCase("LG"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasLG());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.pantallasSamsung());
				break;
			case "Ratón":
				if(marca.equalsIgnoreCase("Corsair"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseCorsair());
				else if(marca.equalsIgnoreCase("HyperX"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseHyperX());
				else if(marca.equalsIgnoreCase("Logitech"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseLogitech());
				else if(marca.equalsIgnoreCase("Razer"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseRazer());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.mouseSteelSeries());
				break;
			case "Memoria RAM":
				if(marca.equalsIgnoreCase("Corsair"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasCorsair());
				else if(marca.equalsIgnoreCase("Crucial"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasCrucial());
				else if(marca.equalsIgnoreCase("GSkill"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasGSkill());
				else if(marca.equalsIgnoreCase("HyperX"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasHyperX());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.memoriasKingston());
				break;
			case "Chasis":
				if(marca.equalsIgnoreCase("NZXT"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisNZXT());
				else if(marca.equalsIgnoreCase("Thermaltake"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisThermaltake());
				else if(marca.equalsIgnoreCase("Cooler Master"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisCoolerMaster());
				else if(marca.equalsIgnoreCase("Corsair"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisCorsair());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.chasisFractalDesign());
				break;		
			case "Disco Duro":
				if(marca.equalsIgnoreCase("Crucial"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosCrucial());
				else if(marca.equalsIgnoreCase("Samsung"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosSamsung());
				else if(marca.equalsIgnoreCase("Seagate"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosSeagate());
				else if(marca.equalsIgnoreCase("WD"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosWD());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.discosToshiba());
				break;
			case "Fuente":
				if(marca.equalsIgnoreCase("Be Quiet!"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesBeQuiet());
				else if(marca.equalsIgnoreCase("Corsair"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesCorsair());
				else if(marca.equalsIgnoreCase("EVGA"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesEVGA());
				else if(marca.equalsIgnoreCase("Seasonic"))
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesSeasonic());
				else
					llenarComboBox(comboBoxModelo, inicializaciones.InicializacionDeDatos.fuentesThermaltake());
				break;
			}
		}
	}

	/*private float obtenerPrecioComp(Object marcaSeleccionada, Object modeloSeleccionado) {
		String marca = marcaSeleccionada.toString();
		String modelo = modeloSeleccionado.toString();
		float precio = 0;
		boolean stop = false;
		if(marca != "" && modelo != "") {
			for(int i = 0; i < tienda.getComponentes().size() && !stop; i++) {
				ComponenteOrdenador c = tienda.getComponentes().get(i);
				if(c.getMarca().equalsIgnoreCase(marca) && c.getModelo().equalsIgnoreCase(modelo)) {
					precio = c.getPrecio();
					stop = true;
				}
			}	
		}
		return precio;
	}*/

	private void organizarLabelsUnAtrib() {
		Atributo1.setVisible(true);	
		comboBoxAtributo1.setVisible(true);
		Atributo2.setVisible(false);		
		lblPrecio.setBounds(12, 213, 128, 16);
		txtPrecio.setBounds(247, 212, 70, 20);
		lblCantidad.setBounds(12, 186, 128, 16);
		spinner.setBounds(247, 184, 70, 22);
		lblNoSerie.setBounds(12, 155, 162, 20);
		txtNoSerieFijo.setBounds(247, 157, 24, 20);
		txtNoSerieMovible.setBounds(269, 157, 48, 20);
		spinnerAtributo2.setVisible(false);
		lblAtributo2.setVisible(false);
	}

	private void organizarLabelsDosAtrib() {
		Atributo1.setVisible(true);	
		comboBoxAtributo1.setVisible(true);
		Atributo2.setVisible(true);	
		spinnerAtributo2.setVisible(true);
		lblNoSerie.setBounds(12, 186, 128, 16);
		txtNoSerieFijo.setBounds(247, 185, 24, 20);
		lblCantidad.setBounds(12, 213, 128, 16);
		spinner.setBounds(247, 211, 70, 22);
		lblPrecio.setBounds(12, 240, 128, 16);
		txtPrecio.setBounds(247, 240, 70, 20);	
		txtNoSerieMovible.setBounds(269, 185, 48, 20);
		lblAtributo2.setVisible(true);
	}
}


