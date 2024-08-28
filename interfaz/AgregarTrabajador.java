package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import inicializaciones.InicializacionDeDatos;
import logica.Gerente;
import logica.TiendaDeComputadoras;
import logica.Trabajador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import componentesVisuales.CampoCIValidado;
import logica.utilidades.logica.GeneradorCICubano;
import logica.utilidades.interfaz.ManejadorLookAndFeels;
public class AgregarTrabajador extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTextField numTrabajador;
	private JTextField nombreT;
	private JTextField apellidosT;
	private JTextField salarioT;
	private JComboBox<String> NivelE;
	private JComboBox<String> cargoT;
	private JSpinner spinnerFecha;
	private JButton btnEliminar;
	private JTable table;
	private DefaultTableModel tableModel;
	private TiendaDeComputadoras tienda;
	private JButton btnGuardar;
	private JLabel fechaOcupTextLabel;
	private Trabajador trab;
	private JPanel panelSecundario;
	private JButton btnAtrs;
	private int noTrabajador = 0;
	private int contador = 0;
	private ArrayList<Trabajador> trabaj;
	private JTextField ciT;


	public AgregarTrabajador(Principal principal, TiendaDeComputadoras tiendaC) {
		super(principal, true);
		setResizable(false);
		trabaj = new ArrayList<Trabajador>();
		tienda = tiendaC;

		setTitle("Manejo de trabajadores");
		setBounds(100, 100, 896, 746);
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

		JPanel panelAgregarTrabajadores = new JPanel();
		panelAgregarTrabajadores.setBounds(29, 87, 837, 296);
		panelAgregarTrabajadores.setForeground(UIManager.getColor("Button.frame"));
		panelAgregarTrabajadores.setBorder(new TitledBorder(new LineBorder(new Color(0, 88, 168)), "Agregar Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAgregarTrabajadores.setBackground(UIManager.getColor("Button.light"));
		panelPrincipal.add(panelAgregarTrabajadores);
		panelAgregarTrabajadores.setLayout(null);

		ciT = new JTextField();
		ciT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(ciT.getText().length() < 11) {
					char t = e.getKeyChar();
					if((t- '0' > 9 || t- '0' < 0) && t!= 8) {
						JOptionPane.showMessageDialog(null, "Solo se pueden introducir numeros en este campo", "Datos erróneos", JOptionPane.ERROR_MESSAGE);
						e.consume();
					}
				}
				else {
					e.consume();
				}
			}
		});
		ciT.setText("");
		ciT.setFont(new Font("Arial", Font.PLAIN, 15));
		ciT.setColumns(10);
		ciT.setBounds(247, 110, 560, 20);
		panelAgregarTrabajadores.add(ciT);


		JLabel lblnombreT = new JLabel("Nombre:");
		lblnombreT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombreT.setBounds(12, 55, 128, 16);
		panelAgregarTrabajadores.add(lblnombreT);

		JLabel lblApellidosT = new JLabel("Apellidos:");
		lblApellidosT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidosT.setBounds(12, 83, 128, 16);
		panelAgregarTrabajadores.add(lblApellidosT);

		JLabel lblCiT = new JLabel("CI:");
		lblCiT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCiT.setBounds(12, 111, 128, 16);
		panelAgregarTrabajadores.add(lblCiT);

		JLabel lblSalarioT = new JLabel("Salario Básico:");
		lblSalarioT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSalarioT.setBounds(12, 139, 128, 16);
		panelAgregarTrabajadores.add(lblSalarioT);

		JLabel lblNivelEsc = new JLabel("Nivel Escolar:");
		lblNivelEsc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNivelEsc.setBounds(12, 167, 128, 16);
		panelAgregarTrabajadores.add(lblNivelEsc);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCargo.setBounds(12, 195, 128, 16);
		panelAgregarTrabajadores.add(lblCargo);

		nombreT = new JTextField();
		nombreT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(nombreT.getText().length() < 20) {
					char nombre =e.getKeyChar();
					if(nombre- '0' <= 9 && nombre- '0' >= 0) {
						e.consume();
						JOptionPane.showMessageDialog(null, "Solo se pueden introducir letras en este campo","Datos erróneos", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					e.consume();
				}
			}
		});
		nombreT.setFont(new Font("Arial", Font.PLAIN, 15));
		nombreT.setForeground(UIManager.getColor("Button.foreground"));
		nombreT.setBounds(247, 54, 560, 20);
		panelAgregarTrabajadores.add(nombreT);
		nombreT.setColumns(10);

		apellidosT = new JTextField();
		apellidosT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(apellidosT.getText().length() < 20) {
					char nombre =e.getKeyChar();
					if(nombre- '0' <= 9 && nombre- '0' >= 0) {
						e.consume();
						JOptionPane.showMessageDialog(null, "Solo se pueden introducir letras en este campo","Datos erróneos", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					e.consume();
				}
			}
		});
		apellidosT.setFont(new Font("Arial", Font.PLAIN, 15));
		apellidosT.setColumns(10);
		apellidosT.setBounds(247, 82, 560, 20);
		panelAgregarTrabajadores.add(apellidosT);

		salarioT = new JTextField();
		salarioT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(salarioT.getText().length() < 15) {
					char t = e.getKeyChar();
					if((t- '0' > 9 || t- '0' < 0) && t!= 8) {
						JOptionPane.showMessageDialog(null, "Solo se pueden introducir numeros en este campo", "Datos erróneos", JOptionPane.ERROR_MESSAGE);
						e.consume();
					}
				}
				else {
					e.consume();
				}
			}
		});
		setFont(new Font("Arial", Font.PLAIN, 15));
		salarioT.setColumns(10);
		salarioT.setBounds(247, 138, 560, 20);
		panelAgregarTrabajadores.add(salarioT);

		NivelE =  new JComboBox<>();
		NivelE.setBounds(247, 166, 560, 20);
		panelAgregarTrabajadores.add(NivelE);

		cargoT = new JComboBox<>();
		cargoT.setBounds(247, 194, 560, 20);
		panelAgregarTrabajadores.add(cargoT);

		llenarComboBox(NivelE, InicializacionDeDatos.nivelesEscolar());
		llenarComboBox(cargoT, InicializacionDeDatos.cargos());

		fechaOcupTextLabel = new JLabel("Fecha Ocupación del cargo:");
		fechaOcupTextLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		fechaOcupTextLabel.setBounds(12, 223, 200, 16);
		panelAgregarTrabajadores.add(fechaOcupTextLabel).setVisible(false);

		spinnerFecha = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
		spinnerFecha.setEditor(dateEditor);
		spinnerFecha.setBounds(246, 221, 98, 22);
		panelAgregarTrabajadores.add(spinnerFecha);
		spinnerFecha.setVisible(false);

		JLabel lblNoTrabajador = new JLabel("No. trabajador:");
		lblNoTrabajador.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNoTrabajador.setBounds(12, 27, 128, 16);
		panelAgregarTrabajadores.add(lblNoTrabajador);

		numTrabajador = new JTextField();
		numTrabajador.setEditable(false);
		numTrabajador.setBounds(247, 26, 38, 20);
		panelAgregarTrabajadores.add(numTrabajador);
		numTrabajador.setColumns(10);
		contador = tienda.getCantTrabajadores();
		iniciarDatos();

		cargoT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cargoT.getSelectedItem().equals("Gerente")) {
					fechaOcupTextLabel.setVisible(true);
					spinnerFecha.setVisible(true);
				} else {
					spinnerFecha.setVisible(false);
					fechaOcupTextLabel.setVisible(false);
				}
			}
		});

		JButton btnBorrar = new JButton("Limpiar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarDatos();
			}
		});
		btnBorrar.setBounds(737, 263, 70, 22);
		panelAgregarTrabajadores.add(btnBorrar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datoIncorrecto = false;
				boolean ciIncorrecto = false;
				String nombre = nombreT.getText();
				String apellidos = apellidosT.getText();
				String ci = ciT.getText(); 
				String salarioB = salarioT.getText();
				String cargo = (String) cargoT.getSelectedItem();
				String nivelE = (String) NivelE.getSelectedItem();

				if (numTrabajador.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(AgregarTrabajador.this, "El número de trabajador no puede estar vacío.");
					datoIncorrecto = true;
				} else {
					try {
						noTrabajador = Integer.parseInt(numTrabajador.getText().trim());
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(AgregarTrabajador.this, "El número de trabajador debe ser un número válido.");
						datoIncorrecto = true;
					}
				}
				if (nombre.trim().isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
					lblnombreT.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblnombreT.setForeground(Color.BLACK);
				}

				if (apellidos.isEmpty() || !apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
					lblApellidosT.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblApellidosT.setForeground(Color.BLACK);
				}

				if (ci.trim().isEmpty()) {
					lblCiT.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblCiT.setForeground(Color.BLACK);
				}

				if (salarioB.trim().isEmpty()) {
					lblSalarioT.setForeground(Color.RED);
					datoIncorrecto = true;
				} else {
					lblSalarioT.setForeground(Color.BLACK);
				}
				if (datoIncorrecto) {
					JOptionPane.showMessageDialog(AgregarTrabajador.this, "No pueden estar vacíos los campos marcados en rojo.");
				} else if(!ciIncorrecto){
					try {
						if (ci.length() == 11) {
							int anioNacimiento = Integer.parseInt(ci.substring(0, 2)) + 1950;
							int mesNacimiento = Integer.parseInt(ci.substring(2, 4));
							int diaNacimiento = Integer.parseInt(ci.substring(4, 6));
							if (anioNacimiento >= 1950) { 
								if (mesNacimiento >= 1 || mesNacimiento <= 12) {
									if (tiendaC.getTrabajadores().get(0).validarDia(anioNacimiento, mesNacimiento, diaNacimiento)) {
										if(!tiendaC.encontCI(ci , trabaj)) {
											ciIncorrecto = true;
										}
										else {
											JOptionPane.showMessageDialog(AgregarTrabajador.this, "El carnet ya esta en el sistema");
										}
									}
									else {
										JOptionPane.showMessageDialog(AgregarTrabajador.this, "El día de nacimiento no es válido para el mes y año dados.");
									}
								}
								else {
									JOptionPane.showMessageDialog(AgregarTrabajador.this, "El mes de nacimiento debe estar entre 01 y 12.");

								}
							}
							else {
								JOptionPane.showMessageDialog(AgregarTrabajador.this, "El año de nacimiento debe ser a partir de 1950.");
							}
						}
						else {
							JOptionPane.showMessageDialog(AgregarTrabajador.this, "El carnet debe tener exactamente 11 dígitos numéricos.");
						}

					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(AgregarTrabajador.this, "El carnet debe de ser un número válido");
					}
					if(ciIncorrecto) {
						try {
							float salario = Float.parseFloat(salarioB);
							if(salario > 0 ) {
								Trabajador trabajador;
								if (cargo.equals("Gerente")) {
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									String fechaStr = sdf.format((Date) spinnerFecha.getValue());
									java.sql.Date fecha = null;
									try {
										fecha = new java.sql.Date(sdf.parse(fechaStr).getTime());
									} catch (ParseException ex) {
										JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + ex.getMessage());
										ex.printStackTrace();
									}
									trabajador = new Gerente(Integer.toString(noTrabajador), nombre, apellidos, ci, salario, nivelE, cargo, fecha);
								} else {
									trabajador = new Trabajador(Integer.toString(noTrabajador), nombre, apellidos, ci, salario, nivelE, cargo);
								}
								trab = trabajador;
								trabaj.add(trab);

								JOptionPane.showMessageDialog(AgregarTrabajador.this, "Trabajador agregado a la tabla de manera satisfactoria");

								if (cargo.equals("Gerente")) {
									SimpleDateFormat formFecha = new SimpleDateFormat("dd/mm/yyyy");
									String fecha = formFecha.format((Date) spinnerFecha.getValue());
									tableModel.addRow(new Object[]{noTrabajador, nombre, apellidos, ci, salario, nivelE, cargo, fecha});
								} else {
									tableModel.addRow(new Object[]{noTrabajador, nombre, apellidos, ci, salario, nivelE, cargo, ""});
								}

								iniciarDatos();
							}
							else {
								JOptionPane.showMessageDialog(AgregarTrabajador.this, "El salario básico debe ser mayor que cero");	
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(AgregarTrabajador.this, "El salario debe ser un número válido.");
						}
	
					}
				}
			}

		});
		btnAgregar.setBounds(656, 263, 70, 22);
		panelAgregarTrabajadores.add(btnAgregar);


		JPanel panelTrabajadoresAgregados = new JPanel();
		panelTrabajadoresAgregados.setBounds(29, 399, 837, 245);
		panelTrabajadoresAgregados.setBorder(new TitledBorder(null, "Trabajadores Agregados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrabajadoresAgregados.setBackground(UIManager.getColor("Button.disabledShadow"));
		panelPrincipal.add(panelTrabajadoresAgregados);
		panelTrabajadoresAgregados.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel();
		tableModel.addColumn("No.");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Apellidos");
		tableModel.addColumn("CI");
		tableModel.addColumn("Salario");
		tableModel.addColumn("Educación");
		tableModel.addColumn("Cargo");
		tableModel.addColumn("Fecha de Ingreso");

		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		panelTrabajadoresAgregados.add(scrollPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 12, 76, 60);
		lblNewLabel.setIcon(new ImageIcon(AgregarTrabajador.class.getResource("/gui/icons/logoPeque\u00F1o1.jpg")));
		panelPrincipal.add(lblNewLabel);

		btnEliminar = new JButton("Eliminar"); 
		btnEliminar.setBounds(797, 647, 70, 22);
		panelPrincipal.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el trabajador seleccionado?", "", 0, 3);
				if(i==0) {		
					int pos = table.getSelectedRow();
					if (pos != -1) {
						trabaj.remove(pos);		
						while(((DefaultTableModel) table.getModel()).getRowCount() > 0)
							((DefaultTableModel) table.getModel()).removeRow(0);
						agregarTabla();
						numTrabajador.setText(Integer.toString(--contador));
					} else {
						JOptionPane.showMessageDialog(AgregarTrabajador.this, "Antes de eliminar debe de seleccionar un trabajador de la tabla");
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
				boolean act = actualizarLista();
				if(act) {
					int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir? No se guardarán los cambios realizados", "", 0, 3);
					if(i==0) {
						setVisible(false);
					}
				}
				else
					setVisible(false);
			}
		});

		panelSecundario.add(btnAtrs);

		btnGuardar = new JButton("Aceptar");
		panelSecundario.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actualizarLista() == true) {
					JOptionPane.showMessageDialog(AgregarTrabajador.this, "Cambios guardados satisfactoriamente");
					setVisible(false);
				}
			}
		});
	}

	public void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		for (String item : items) {
			comboBox.addItem(item);
		}
	}
	private boolean actualizarLista() {
		boolean act = false;
		if(!trabaj.isEmpty()) {
			tienda.agregarT(trabaj);
			act = true;
		}
		else {
			JOptionPane.showMessageDialog(AgregarTrabajador.this, "No se ha realizado ningún cambio");
		}
		return act;
	}

	private void iniciarDatos() {
		numTrabajador.setText(Integer.toString(++contador));
		nombreT.setText("");
		apellidosT.setText("");
		ciT.setText("");
		salarioT.setText("");
		NivelE.setSelectedIndex(0);
		cargoT.setSelectedIndex(0);
		spinnerFecha.setValue(new Date());
		spinnerFecha.setVisible(false);
		fechaOcupTextLabel.setVisible(false);
	}


	/***************Otra validacion CI********************/
	public static boolean validarCarnet(String carnet) {
		boolean e = false;

		String regex = "^(\\d{2})(\\d{2})(\\d{2})(\\d{5})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(carnet);
		String dia = matcher.group(1);
		String mes = matcher.group(2);
		String anno = matcher.group(3);
		String resto = matcher.group(4);
		int year = Integer.parseInt(anno);
		year +=(year < 50) ? 2000: 1900;
		while(!e) {
			if(carnet.length() == 11) {
				if(matcher.matches()) {	
					if(year >= 1950) {
						e = true;
					}
				}
			}
			String fechaStr =  String.format("%02d-%02d-%04d", Integer.parseInt(dia), Integer.parseInt(mes), year);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			try {
				LocalDate fecha = LocalDate.parse(fechaStr, formatter);
			}
			catch(DateTimeException e1) {
				e = true;
			}
		}
		return e;
	}
	private void agregarTabla() {
		int count = tienda.getCantTrabajadores();
		for(Trabajador t: trabaj) {
			if (t.getCargo().equals("Gerente")) {
				SimpleDateFormat formFecha = new SimpleDateFormat("dd/mm/yyyy");
				String fecha = formFecha.format((Date) spinnerFecha.getValue());
				tableModel.addRow(new Object[]{count++, t.getNombre(), t.getApellidos(), t.getCI(), t.getSalarioBasico(), t.getNivelEscolar(), t.getCargo(), fecha});
			} else {
				tableModel.addRow(new Object[]{count++, t.getNombre(), t.getApellidos(), t.getCI(), t.getSalarioBasico(), t.getNivelEscolar(), t.getCargo(), ""});
			}
		}
	}
}

