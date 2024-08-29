package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import logica.Adaptador;
import logica.Bocina;
import logica.Chasis;
import logica.ComponenteOrdenador;
import logica.DiscoDuro;
import logica.Factura;
import logica.Fuente;
import logica.MemoriaRam;
import logica.Microprocesador;
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
import java.awt.Container;

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
import javax.print.attribute.DateTimeSyntax;
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
	private JComboBox<String> comboBoxAtributo1;
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
	private JTextField MarcaEncontText;
	ArrayList <Integer> extraida; 
	ArrayList<ComponenteOrdenador> componentes;
	ArrayList <ComponenteOrdenador> tablaC;
	ArrayList<Integer> cant;
	private Factura factura;
	private int cantidad;
	private JSpinner spinnerAtributo2;
	private JSpinner spinnerAtributo2_1;
	private JTextField txtatributo2;
	JPanel panelEnsamblado;
	private JTextField totalFactura;
	private Factura f;
	@SuppressWarnings("unchecked")

	public VenderPieza(Principal principal, TiendaDeComputadoras tienda, String nombreDeComponente) {
		nombre = nombreDeComponente;
		setTitle("Vender Piezas");
		p = principal;
		tiendaC = tienda;
		modelo = new ArrayList<>();
		cant = new ArrayList<Integer>();
		setBounds(100, 100, 906, 760);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			extraida  =new ArrayList<Integer>();
			componentes = new ArrayList<>();
			tablaC = new ArrayList<ComponenteOrdenador>();
			ArrayList<String> itemExp = new ArrayList<String>();
			JPanel FiltradodeProducto = new JPanel();
			FiltradodeProducto.setBackground(Color.WHITE);
			FiltradodeProducto.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
			FiltradodeProducto.setBounds(0, 0, 466, 437);
			contentPanel.add(FiltradodeProducto);
			FiltradodeProducto.setLayout(null);
			SpinnerNumberModel modelo = new SpinnerNumberModel(0,0,100,1);


			Atributo1 = new JTextField();
			Atributo1.setEditable(false);
			Atributo1.setText("Atributo1");
			Atributo1.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo1.setBorder(null);
			Atributo1.setBounds(12, 130, 162, 20);
			FiltradodeProducto.add(Atributo1);
			Atributo1.setColumns(10);

			Atributo2 = new JTextField();
			Atributo2.setEditable(false);
			Atributo2.setBorder(null);
			Atributo2.setFont(new Font("Tahoma", Font.BOLD, 15));
			Atributo2.setText("Atributo2");
			Atributo2.setColumns(10);
			Atributo2.setBounds(12, 157, 162, 20);
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
			comboBoxComponenetes.setBounds(177, 77, 276, 20);
			FiltradodeProducto.add(comboBoxComponenetes);

			comboBoxMarca = new JComboBox();
			comboBoxMarca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	

				}
			});
			comboBoxMarca.setBounds(177, 105, 276, 20);
			FiltradodeProducto.add(comboBoxMarca);

			JLabel lblMarca = new JLabel("Marca:");
			lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblMarca.setBounds(12, 106, 162, 16);
			FiltradodeProducto.add(lblMarca);

			ensamblarCheckBox = new JCheckBox("Ensamblar");
			ensamblarCheckBox.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(comboBoxComponenetes.getSelectedItem().equals("Tarjeta Madre")) {
						panelEnsamblado.setVisible(true);
					}else {
						panelEnsamblado.setVisible(false);
					}
				}
			});
			ensamblarCheckBox.setFont(new Font("Tahoma", Font.BOLD, 15));
			ensamblarCheckBox.setBackground(Color.WHITE);
			ensamblarCheckBox.setBounds(350, 49, 115, 20);
			FiltradodeProducto.add(ensamblarCheckBox);

			spinnerAtributo2_1 = new JSpinner(new SpinnerNumberModel(1,1,64,0.5));
			spinnerAtributo2_1.setBounds(176, 157, 221, 20);
			FiltradodeProducto.add(spinnerAtributo2_1);
			SpinnerNumberModel modelo1 = new SpinnerNumberModel(1,1,64,0.5);
			spinnerAtributo2_1.setModel(modelo1);

			txtatributo2 = new JTextField();
			txtatributo2.setEditable(false);
			txtatributo2.setBorder(null);
			txtatributo2.setBounds(409, 157, 44, 20);
			FiltradodeProducto.add(txtatributo2);
			txtatributo2.setBackground(Color.WHITE);
			txtatributo2.setText("t");
			txtatributo2.setFont(new Font("Tahoma", Font.BOLD, 15));
			txtatributo2.setColumns(10);

			comboBoxModelo = new JComboBox();
			comboBoxModelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarDatos();
					actualizarComponente((String) comboBoxModelo.getSelectedItem(), (String)comboBoxComponenetes.getSelectedItem());
				}
			});

			comboBoxModelo.setBounds(196, 393, 257, 20);
			FiltradodeProducto.add(comboBoxModelo);
			if (nombre == null) {
				llenarComboBox(comboBoxComponenetes, inicializaciones.InicializacionDeDatos.nameComponente());
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTeclado());
				Atributo1.setText("Retroiluminacion:");
				componenetesVentaLibreNV();
				spinnerAtributo2_1.setVisible(false);
				ensamblarCheckBox.setVisible(false);
				Atributo2.setVisible(false);
				txtatributo2.setVisible(false);
			} else {
				List<String> itemExp1 = inicializaciones.InicializacionDeDatos.nameComponente();
				itemExp1.remove(nombreDeComponente);
				itemExp1.add(0, nombreDeComponente);
				llenarComboBox(comboBoxComponenetes, (ArrayList<String>) itemExp1);
				componenetesVentaLibreNV();
			}


			JLabel lblNewLabel = new JLabel("Buscar Componente");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 12, 332, 51);
			FiltradodeProducto.add(lblNewLabel);

			comboBoxAtributo1 = new JComboBox();
			comboBoxAtributo1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			comboBoxAtributo1.setBounds(177, 130, 276, 20);



			FiltradodeProducto.add(comboBoxAtributo1);

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
						actualizarModeloMicroprocesador((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), (Double) spinnerAtributo2_1.getValue());
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
						actualizarModeloMemoriaRAM((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), (Double) spinnerAtributo2_1.getValue());
					case "Chasis":
						actualizarModeloChasis((String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem());
						break;		
					case "Disco Duro":
						actualizarModeloDiscoDuro((String) comboBoxMarca.getSelectedItem(), (Double) spinnerAtributo2_1.getValue(), (String) comboBoxAtributo1.getSelectedItem());
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



			btnNewButton.setBounds(27, 392, 126, 20);
			FiltradodeProducto.add(btnNewButton);


			panelEnsamblado = new JPanel();
			panelEnsamblado.setBorder(new LineBorder(UIManager.getColor("Button.frame")));
			panelEnsamblado.setBackground(Color.WHITE);
			panelEnsamblado.setBounds(12, 200, 441, 168);
			FiltradodeProducto.add(panelEnsamblado);
			panelEnsamblado.setLayout(null);
			panelEnsamblado.setVisible(false);

			JLabel AtributoDiscoDuro = new JLabel("Tipo de RAM:");
			AtributoDiscoDuro.setBounds(12, 100, 147, 20);
			panelEnsamblado.add(AtributoDiscoDuro);
			AtributoDiscoDuro.setFont(new Font("Tahoma", Font.BOLD, 15));

			JLabel AtributoMicro = new JLabel("Lista de Conexiones:");
			AtributoMicro.setBounds(12, 132, 163, 20);
			panelEnsamblado.add(AtributoMicro);
			AtributoMicro.setFont(new Font("Tahoma", Font.BOLD, 15));

			JLabel AtributoRAM = new JLabel("Tipo de Conector:");
			AtributoRAM.setBounds(12, 68, 147, 20);
			panelEnsamblado.add(AtributoRAM);
			AtributoRAM.setFont(new Font("Tahoma", Font.BOLD, 15));

			JLabel lblProcesoDeEnsamblado = new JLabel("Proceso de Ensamblado");
			lblProcesoDeEnsamblado.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblProcesoDeEnsamblado.setBounds(12, 0, 410, 30);
			panelEnsamblado.add(lblProcesoDeEnsamblado);


			primeraVisualizacionComponenetes();

		}
		{
			SpinnerNumberModel modelo = new SpinnerNumberModel(0,0,100,1);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 902, 702);
			panel.setBackground(UIManager.getColor("Button.disabledShadow"));
			contentPanel.add(panel);
			panel.setLayout(null);
			JPanel ProductosAgregados = new JPanel();
			ProductosAgregados.setBounds(0, 481, 902, 184);
			ProductosAgregados.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			ProductosAgregados.setBackground(UIManager.getColor("Button.disabledShadow"));
			panel.add(ProductosAgregados);
			ProductosAgregados.setLayout(new BorderLayout(0, 0));

			tableModel = new DefaultTableModel();
			tableModel.addColumn("Componenete");
			tableModel.addColumn("Marca");
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
			panel_1.setBounds(0, 438, 902, 45);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JButton okButton = new JButton("Borrar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int pos = table.getSelectedRow();
					ComponenteOrdenador c = null;
					if (pos != -1) {
						c = componentes.get(pos);
						int num = extraida.get(pos);;
						((DefaultTableModel) table.getModel()).removeRow(pos);
						if(componentes.get(pos) != null) {	
							tiendaC.actualizarNo(c, num);
						}
					} else {
						JOptionPane.showMessageDialog(VenderPieza.this, "Antes de eliminar debe de seleccionar un componente de la tabla");
					}

				}
			});
			okButton.setActionCommand("OK");
			okButton.setBounds(803, 12, 76, 22);
			panel_1.add(okButton);
			JPanel ProductoEncontrado = new JPanel();
			ProductoEncontrado.setBounds(468, 0, 434, 437);
			panel.add(ProductoEncontrado);
			ProductoEncontrado.setBackground(Color.WHITE);
			ProductoEncontrado.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
			ProductoEncontrado.setLayout(null);

			JLabel lblComponenteEncontrado = new JLabel("Componente Encontrado");
			lblComponenteEncontrado.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblComponenteEncontrado.setBounds(41, 12, 402, 51);
			ProductoEncontrado.add(lblComponenteEncontrado);

			nombretext = new JTextField();
			nombretext.setEditable(false);
			nombretext.setBounds(175, 75, 220, 20);
			ProductoEncontrado.add(nombretext);
			nombretext.setColumns(10);

			modelotext = new JTextField();
			modelotext.setEditable(false);
			modelotext.setColumns(10);
			modelotext.setBounds(175, 136, 220, 20);
			ProductoEncontrado.add(modelotext);

			notext = new JTextField();
			notext.setEditable(false);
			notext.setColumns(10);
			notext.setBounds(175, 168, 220, 20);
			ProductoEncontrado.add(notext);

			preciotext = new JTextField();
			preciotext.setEditable(false);
			preciotext.setColumns(10);
			preciotext.setBounds(175, 200, 220, 20);
			ProductoEncontrado.add(preciotext);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBounds(41, 75, 141, 16);
			ProductoEncontrado.add(lblNombre);

			JLabel lblModelo = new JLabel("Modelo:");
			lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblModelo.setBounds(41, 137, 141, 16);
			ProductoEncontrado.add(lblModelo);

			JLabel lblNoDeSerie = new JLabel("No. de Serie:");
			lblNoDeSerie.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNoDeSerie.setBounds(41, 169, 141, 16);
			ProductoEncontrado.add(lblNoDeSerie);

			JLabel lblPrecio = new JLabel("Precio:");
			lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPrecio.setBounds(41, 201, 141, 16);
			ProductoEncontrado.add(lblPrecio);

			JLabel lblCantidadExistente = new JLabel("Cantidad:");
			lblCantidadExistente.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCantidadExistente.setBounds(41, 233, 141, 16);
			ProductoEncontrado.add(lblCantidadExistente);

			cantidadtxt = new JTextField();
			cantidadtxt.setEditable(false);
			cantidadtxt.setColumns(10);
			cantidadtxt.setBounds(175, 232, 220, 20);
			ProductoEncontrado.add(cantidadtxt);

			JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
			spinner.setBounds(133, 396, 63, 22);
			ProductoEncontrado.add(spinner);
			SpinnerNumberModel modelo = new SpinnerNumberModel(0,0,10,1);
			spinner.setModel(modelo);


			JLabel lblCantidadExistente_1 = new JLabel("Cantidad:");
			lblCantidadExistente_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCantidadExistente_1.setBounds(41, 398, 101, 16);
			ProductoEncontrado.add(lblCantidadExistente_1);

			JLabel lblCantidadExistente_1_1 = new JLabel("A\u00F1adir al carrito");
			lblCantidadExistente_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCantidadExistente_1_1.setBounds(235, 398, 141, 16);
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
						ComponenteOrdenador c = null;
						String numSerie = notext.getText();
						float precio = Float.parseFloat(preciotext.getText());
						cantidad = (int) spinner.getValue();
						float result = precio * cantidad;
						String coste = String.valueOf(result);
						c = tienda.buscarComponente(numSerie);
						tableModel.addRow(new Object[]{nombretext.getText(), MarcaEncontText.getText(), modelotext.getText(), spinner.getValue(), coste});
						componentes.add(c);
						extraida.add(c.getCantDisponible());
						tiendaC.actualizarCantidadComponente(c, cantidad);
						cantidadtxt.setText(String.valueOf(c.getCantDisponible()));
						cant.add(cantidad);
						Object totalF = 0;
						float t = 0;
						for(int i = 0; i < tableModel.getRowCount(); i++) {
							totalF = tableModel.getValueAt(i, 4);
							t += Float.valueOf((String) totalF);
						}
						totalFactura.setText(String.valueOf(t));
					}
				}
			});

			btnCarrito.setBackground(Color.WHITE);
			btnCarrito.setIcon(new ImageIcon(VenderPieza.class.getResource("/gui/icons/carrito.png")));
			btnCarrito.setBounds(364, 390, 42, 36);
			ProductoEncontrado.add(btnCarrito);

			JLabel lblMarca_1 = new JLabel("Marca:");
			lblMarca_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblMarca_1.setBounds(41, 107, 141, 16);
			ProductoEncontrado.add(lblMarca_1);

			MarcaEncontText = new JTextField();
			MarcaEncontText.setEditable(false);
			MarcaEncontText.setColumns(10);
			MarcaEncontText.setBounds(175, 107, 220, 20);
			ProductoEncontrado.add(MarcaEncontText);

			totalFactura = new JTextField();
			totalFactura.setFont(new Font("Tahoma", Font.BOLD, 15));
			totalFactura.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
			totalFactura.setEditable(false);
			totalFactura.setEnabled(false);
			totalFactura.setBounds(786, 664, 116, 38);
			panel.add(totalFactura);
			totalFactura.setColumns(10);

			JLabel lblNewLabel_1 = new JLabel("Total:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_1.setBounds(725, 674, 50, 16);
			panel.add(lblNewLabel_1);

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(actualizarLista() == true) {
							JOptionPane.showMessageDialog(VenderPieza.this, "Cambios guardados satisfactoriamente");
							setVisible(false);
						}
					}

				});
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
	private boolean actualizarLista() {
		boolean act = false;
		obtenerObjeto();
		if (!componentes.isEmpty() ) {
			f = new Factura();
			f.setCantidadXPieza(cant);
			f.setCom(componentes);
			tiendaC.agregarFactura(f);
			act = true;
		} else {
			JOptionPane.showMessageDialog(VenderPieza.this, "No se ha realizado ningún cambio o las listas no tienen el mismo tamaño.");
		}
		return act;
	}
	private void primeraVisualizacionComponenetes() {
		if (nombre != null) {
			if (nombre.equals("Tarjeta Madre") || nombre.equals("Microprocesador") || nombre.equals("Disco Duro") || nombre.equals("Memoria RAM")) {
				if (spinnerAtributo2  != null) spinnerAtributo2.setVisible(true);
				ensamblarCheckBox.setEnabled(true);


			} else {
				if (spinnerAtributo2  != null) spinnerAtributo2.setVisible(false);

			}
		} else {
			System.out.println("Error: nombre es null");
		}
	}
	private void primeraVisualizacionAtributo() {
		if (nombre != null) {
			if (nombre.equals("Tarjeta Madre") || nombre.equals("Microprocesador") || nombre.equals("Disco Duro") || nombre.equals("Memoria RAM")) {
				if (Atributo2 != null) Atributo2.setVisible(true);
			} else {
				if (Atributo2 != null) Atributo2.setVisible(false);
			}
		} else {
			System.out.println("Error: nombre es null");
		}
	}
	private void componenetesVentaLibreNV() {
		if (Atributo2 != null) Atributo2.setVisible(false);
		if (spinnerAtributo2_1 != null) spinnerAtributo2_1.setVisible(false);
		if(ensamblarCheckBox != null) ensamblarCheckBox.setVisible(false);
		txtatributo2.setVisible(false);
	}

	private void componenetesVentaLibreV() {
		if (Atributo2 != null) Atributo2.setVisible(true);
		if (spinnerAtributo2  != null) spinnerAtributo2.setVisible(true);

		repaint();
		validate();

	}
	private void componenetesVentaLibreMicro() {
		if (Atributo2 != null) Atributo2.setVisible(true);
		if (spinnerAtributo2  != null) spinnerAtributo2.setVisible(true);

		repaint();
		validate();

	}
	private void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		comboBox.removeAllItems(); // Limpia el comboBox antes de llenarlo
		for (String item : items) {
			comboBox.addItem(item);
		}
	}

	/*****************Funcion para elegir las marcas************************************/
	@SuppressWarnings({ "unchecked", "deprecation" })
	private void elegirMarca(Object seleccionado) {
		if (comboBoxMarca != null && comboBoxAtributo1 != null) {
			comboBoxAtributo1.setVisible(true); 
			String nombreC = seleccionado.toString();
			ArrayList<String> Decision = new ArrayList<>();
			Decision.add("Sí");
			Decision.add("No");
			TarjetaMadre t;
			switch (nombreC) {
			case "Teclado":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTeclado());
				Atributo1.setText("Retroiluminacion:");
				llenarComboBox(comboBoxAtributo1, Decision);
				componenetesVentaLibreNV();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Tarjeta de Video":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaVideos());
				Atributo1.setText("Refrigeracion:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.refrigeracion());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Tarjeta Madre":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasTarjetaMadre());
				Atributo1.setText("Conector:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectores());
				componenetesVentaLibreMicro();
				Atributo2.setVisible(false);
				//llenarComboBox(comboBoxAtributo2, t.getMemoriasR());
				//llenarComboBox(comboBoxAtributo3, );
				ensamblarCheckBox.setVisible(true);
				comboBoxModelo.removeAllItems();
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Microprocesador":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMicroProcesadores());
				if(comboBoxMarca.getSelectedItem().equals("AMD")) {
					llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesAMD());
				}
				else {
					llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesIntel());	
				}
				componenetesVentaLibreV();
				Atributo1.setText("Conexión:");
				comboBoxModelo.removeAllItems();
				Atributo2.setText("Procesamiento:");
				spinnerAtributo2_1.setVisible(true);
				txtatributo2.setText("MHz");
				txtatributo2.setVisible(true);
				break;
			case "Adaptador":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasExtension());
				Atributo1.setVisible(false);
				comboBoxAtributo1.setVisible(false);
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Bocina":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasBocinas());
				Atributo1.setText("Conectividad:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Monitor":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasPantalla());
				Atributo1.setText("Resolución:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.resolucionVideo());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Ratón":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasRaton());
				Atributo1.setText("No. de Botones:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conectividad());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;
			case "Memoria RAM":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasMemoriasRAM());
				Atributo2.setText("Espacio:");
				componenetesVentaLibreV();
				Atributo1.setText("Tipo de Memoria:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.tiposDeMemoriaRAM());
				comboBoxModelo.removeAllItems();
				Atributo2.setText("Espacio:");
				spinnerAtributo2_1.setVisible(true);
				txtatributo2.setVisible(false);
				break;
			case "Chasis":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasChasis());
				Atributo1.setText("Material:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.materialesChasis());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
				break;		
			case "Disco Duro":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasDiscoD());
				Atributo2.setText("Capacidad:");
				Atributo1.setText("Conexiones:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.conexionesDiscoDuro());
				componenetesVentaLibreV();
				comboBoxModelo.removeAllItems();
				spinnerAtributo2_1.setVisible(true);
				txtatributo2.setVisible(true);
				txtatributo2.setText("TB");
				break;
			case "Fuente":
				llenarComboBox(comboBoxMarca, inicializaciones.InicializacionDeDatos.marcasFuente());
				Atributo1.setText("Eficiencia:");
				llenarComboBox(comboBoxAtributo1, inicializaciones.InicializacionDeDatos.eficiencia());
				componenetesVentaLibreNV();
				comboBoxModelo.removeAllItems();
				ensamblarCheckBox.setVisible(false);
				spinnerAtributo2_1.setVisible(false);
				txtatributo2.setVisible(false);
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
	/***********************************Microprocesador*******************************************/
	private void actualizarModeloMicroprocesador(String combo1, String combo2, Double combo3) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloMicroprocesador("Microprocesador", combo1, combo2,  combo3);
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
	/***********************************DiscoDuro*******************************************/
	private void actualizarModeloDiscoDuro(String combo1, Double combo3, String combo2) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloDiscoDuro("DiscoDuro", combo1, combo3, combo2);
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
	/*************************************Memoria RAM*********************************************/
	public void actualizarModeloMemoriaRAM(String combo1, String combo2, Double combo3) {
		try {
			modelo.clear();
			comboBoxModelo.removeAllItems();
			modelo = tiendaC.modeloMemoriaRAM("MemoriaRam", combo1, combo3, combo2);
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
					MarcaEncontText.setText(t.getMarca());
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
					MarcaEncontText.setText(a.getMarca());
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
					MarcaEncontText.setText(b.getMarca());
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
					MarcaEncontText.setText(c.getMarca());
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
					MarcaEncontText.setText(m.getMarca());
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
					MarcaEncontText.setText(t.getMarca());
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
					MarcaEncontText.setText(b.getMarca());
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
					MarcaEncontText.setText(f.getMarca());
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
		case "Microprocesador":
			try {
				Microprocesador m = tiendaC.encontMicro("Microprocesador", (String) comboBoxMarca.getSelectedItem(), (String) comboBoxAtributo1.getSelectedItem(), (Double)spinnerAtributo2_1.getValue(),modelo);
				if (m != null) {
					nombretext.setText("Microprocesador");
					MarcaEncontText.setText(m.getMarca());
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
		case "Disco Duro":
			try {
				DiscoDuro d = tiendaC.encontDiscoDuro("DiscoDuro", (String) comboBoxMarca.getSelectedItem(), (Double)spinnerAtributo2_1.getValue(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (d != null) {
					nombretext.setText("Disco Duro");
					MarcaEncontText.setText(d.getMarca());
					modelotext.setText(d.getModelo());
					notext.setText(d.getNumSerie());
					preciotext.setText(String.valueOf(d.getPrecio()));
					cantidadtxt.setText(String.valueOf(d.getCantDisponible()));
				} else {
					limpiarDatos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VenderPieza.this, "Error al actualizar el componente: " + e.getMessage());
			}
			break;
		case "Memoria RAM":
			try {
				MemoriaRam m = tiendaC.encontMemoriaRAM("MemoriaRam", (String) comboBoxMarca.getSelectedItem(), (Double)spinnerAtributo2_1.getValue(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (m != null) {
					nombretext.setText("Memoria RAM");
					MarcaEncontText.setText(m.getMarca());
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
			/*case "Tarjeta Madre":
			try {
				TarjetaMadre m = tiendaC.encontTarjetaMadre("Tarjeta Madre", (String) comboBoxMarca.getSelectedItem(), (Double)spinnerAtributo2_1.getValue(), (String) comboBoxAtributo1.getSelectedItem(), modelo);
				if (m != null) {
					nombretext.setText("Memoria RAM");
					MarcaEncontText.setText(m.getMarca());
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
			break;*/
		default:
			break;
		}
	}



	public void obtenerObjeto() {
		for(int j = 0; j < tableModel.getRowCount(); j++) {
			Object o = tableModel.getValueAt(j, 4);
			cant.add(Integer.getInteger((String) o));
		}
	}
	private void limpiarDatos() {
		nombretext.setText("");
		modelotext.setText("");
		MarcaEncontText.setText("");
		notext.setText("");
		preciotext.setText("");
		cantidadtxt.setText("");

	}
}