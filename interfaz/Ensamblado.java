package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.ComponenteOrdenador;
import logica.Factura;
import logica.TarjetaMadre;
import logica.TiendaDeComputadoras;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Ensamblado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textmarca1;
	private JTextField textmodelo1;
	private JTextField textNo1;
	private JTextField textPrecio1;
	private JTextField textmarca2;
	private JTextField textmodelo2;
	private JTextField textNo2;
	private JTextField textPrecio2;
	private JTextField textmarca3;
	private JTextField textmodelo3;
	private JTextField textNo3;
	private JTextField textPrecio3;
	private JTable table;
	private TarjetaMadre TM;
	private DefaultTableModel tableModel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_2;
	private JButton btnNewButton;
	private JSpinner spinner1_1;
	private JSpinner spinner1;
	private boolean RAM;
	private boolean Disco;
	private int contDisco = 0;
	private int contRAM = 0;
	private JLabel costoEnsamblado;
	private JLabel costoTotal;
	@SuppressWarnings("unused")
	private Principal principal;
	private TiendaDeComputadoras tienda;
	@SuppressWarnings("unused")
	private VenderPieza venderP;
	private ArrayList<ComponenteOrdenador> componentes;
	private Factura f;
	private ArrayList<Integer> cant;
	private ArrayList<Boolean> ensamblado;
	private float t = 20;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Ensamblado(TarjetaMadre m, VenderPieza venderPieza, Principal p, TiendaDeComputadoras tiendaC) {
		super(venderPieza, true);
		setLocationRelativeTo(null);
		setTitle("Proceso de Ensamblado");
		setResizable(false);
		setBackground(UIManager.getColor("Button.disabledShadow"));
		principal = p;
		venderP = venderPieza;
		tienda = tiendaC;
		TM = m;
		cant = new ArrayList<Integer>();
		componentes = new ArrayList<ComponenteOrdenador>();
		ensamblado = new ArrayList<Boolean>();
		setBounds(100, 100, 906, 783);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) UIManager.getColor("Button.frame")));
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 12, 900, 36);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Zona de Ensamblado:");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblNewLabel.setBounds(23, -11, 528, 47);
				panel.add(lblNewLabel);
			}
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Microprocesador:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_2.setBounds(57, 86, 191, 36);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Disco Duro:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_2.setBounds(373, 86, 170, 36);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("RAM:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_2.setBounds(708, 86, 152, 36);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JPanel panel1 = new JPanel();
			panel1.setBorder(new LineBorder(UIManager.getColor("Button.frame")));
			panel1.setBackground(UIManager.getColor("Button.highlight"));
			panel1.setBounds(29, 166, 255, 310);
			contentPanel.add(panel1);
			panel1.setLayout(null);
			{
				JLabel lblNewLabel_2 = new JLabel("Modelo:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel_2.setBounds(12, 60, 103, 36);
				panel1.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("No. de Serie:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel_2.setBounds(12, 108, 103, 36);
				panel1.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Marca:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel_2.setBounds(12, 12, 103, 36);
				panel1.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Precio:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel_2.setBounds(12, 156, 103, 36);
				panel1.add(lblNewLabel_2);
			}

			textmarca1 = new JTextField();
			textmarca1.setEditable(false);
			textmarca1.setColumns(10);
			textmarca1.setBounds(116, 21, 127, 20);
			panel1.add(textmarca1);
			textmarca1.setText(TM.getMicro().getMarca());

			textmodelo1 = new JTextField();
			textmodelo1.setEditable(false);
			textmodelo1.setColumns(10);
			textmodelo1.setBounds(116, 69, 127, 20);
			panel1.add(textmodelo1);
			textmodelo1.setText(TM.getMicro().getModelo());

			textNo1 = new JTextField();
			textNo1.setEditable(false);
			textNo1.setColumns(10);
			textNo1.setBounds(116, 120, 127, 20);
			panel1.add(textNo1);
			textNo1.setText(TM.getMicro().getNumSerie());

			textPrecio1 = new JTextField();
			textPrecio1.setEditable(false);
			textPrecio1.setColumns(10);
			textPrecio1.setBounds(116, 165, 127, 20);
			panel1.add(textPrecio1);
			textPrecio1.setText(String.valueOf(TM.getMicro().getPrecio()));


		}
		componentes.add(TM);
		componentes.add(TM.getMicro());
		cant.add(1);
		cant.add(1);
		ensamblado.add(true);
		ensamblado.add(true);
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBorder(new LineBorder(UIManager.getColor("Button.frame")));
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(322, 166, 255, 310);
		contentPanel.add(panel2);

		textmarca2 = new JTextField();
		textmarca2.setEditable(false);
		textmarca2.setColumns(10);
		textmarca2.setBounds(116, 21, 127, 20);
		panel2.add(textmarca2);


		textmodelo2 = new JTextField();
		textmodelo2.setEditable(false);
		textmodelo2.setColumns(10);
		textmodelo2.setBounds(116, 69, 127, 20);
		panel2.add(textmodelo2);


		textNo2 = new JTextField();
		textNo2.setEditable(false);
		textNo2.setColumns(10);
		textNo2.setBounds(116, 117, 127, 20);
		panel2.add(textNo2);

		textPrecio2 = new JTextField();
		textPrecio2.setEditable(false);
		textPrecio2.setColumns(10);
		textPrecio2.setBounds(116, 165, 127, 20);
		panel2.add(textPrecio2);


		JLabel lblNewLabel_2 = new JLabel("Modelo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(12, 60, 103, 36);
		panel2.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("No. de Serie:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(12, 108, 103, 36);
		panel2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Marca:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(12, 12, 103, 36);
		panel2.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Precio:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(12, 156, 103, 36);
		panel2.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Cantidad:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_4.setBounds(12, 204, 103, 36);
		panel2.add(lblNewLabel_2_4);


		spinner1 = new JSpinner();
		spinner1 = new JSpinner(new SpinnerNumberModel(1,1,4,1));
		spinner1.setBounds(116, 212, 127, 22);
		panel2.add(spinner1);
		SpinnerNumberModel modelo = new SpinnerNumberModel(1,1,4,1);
		spinner1.setModel(modelo);

		btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.setBounds(174, 277, 70, 22);
		panel2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contDisco += (Integer)spinner1.getValue();
				if(contDisco <= 4) {
					ComponenteOrdenador c = null;
					c = tienda.buscarComponente(textNo2.getText());
					if(c.getCantDisponible() > 0) {
						componentes.add(c);
						actualizarTotal();
						Disco = true;
						float precio = Float.parseFloat(textPrecio2.getText());
						int cantidad = (int) spinner1.getValue();
						float result = precio * cantidad;
						String coste = String.valueOf(result);
						cant.add((Integer) spinner1.getValue());
						ensamblado.add(true);
						tableModel.addRow(new Object[]{"Disco Duro", textmarca2.getText(), textmodelo2.getText(), spinner1.getValue(), coste});
						actualizarCostoE();
					}
					else {
						JOptionPane.showMessageDialog(Ensamblado.this, "La cantidad de Discos Duros para el ensamblado no puede ser mayor que 4");
					}
				}
				else {
					JOptionPane.showMessageDialog(Ensamblado.this, "La cantidad de Discos Duros no está disponible");
				}
			}
		});

		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBorder(new LineBorder(UIManager.getColor("Button.frame")));
		panel3.setBackground(Color.WHITE);
		panel3.setBounds(624, 166, 255, 310);
		contentPanel.add(panel3);


		textmarca3 = new JTextField();
		textmarca3.setEditable(false);
		textmarca3.setColumns(10);
		textmarca3.setBounds(114, 21, 129, 20);
		panel3.add(textmarca3);

		textmodelo3 = new JTextField();
		textmodelo3.setEditable(false);
		textmodelo3.setColumns(10);
		textmodelo3.setBounds(114, 69, 127, 20);
		panel3.add(textmodelo3);

		textNo3 = new JTextField();
		textNo3.setEditable(false);
		textNo3.setColumns(10);
		textNo3.setBounds(116, 117, 127, 20);
		panel3.add(textNo3);

		textPrecio3 = new JTextField();
		textPrecio3.setEditable(false);
		textPrecio3.setColumns(10);
		textPrecio3.setBounds(116, 165, 127, 20);
		panel3.add(textPrecio3);


		JLabel lblNewLabel_2_5 = new JLabel("Modelo:");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_5.setBounds(12, 60, 103, 36);
		panel3.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_6 = new JLabel("No. de Serie:");
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_6.setBounds(12, 108, 103, 36);
		panel3.add(lblNewLabel_2_6);

		JLabel lblNewLabel_2_7 = new JLabel("Marca:");
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_7.setBounds(12, 12, 103, 36);
		panel3.add(lblNewLabel_2_7);

		JLabel lblNewLabel_2_8 = new JLabel("Precio:");
		lblNewLabel_2_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_8.setBounds(12, 156, 103, 36);
		panel3.add(lblNewLabel_2_8);

		JLabel lblNewLabel_2_9 = new JLabel("Cantidad:");
		lblNewLabel_2_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_9.setBounds(12, 204, 103, 36);
		panel3.add(lblNewLabel_2_9);


		spinner1_1 = new JSpinner();
		spinner1_1 = new JSpinner(new SpinnerNumberModel(1,1,6,1));
		spinner1_1.setBounds(116, 212, 127, 22);
		panel3.add(spinner1_1);
		SpinnerNumberModel modelo1 = new SpinnerNumberModel(1,1,6,1);
		spinner1_1.setModel(modelo1);

		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contRAM += (Integer)spinner1_1.getValue();
				if(contRAM <= 6) {
					ComponenteOrdenador c = null;
					c = tienda.buscarComponente(textNo3.getText());
					if(c.getCantDisponible() > 0) {
						componentes.add(c);
						actualizarTotal();
						RAM = true;
						float precio = Float.parseFloat(textPrecio3.getText());
						int cantidad = (int) spinner1_1.getValue();
						float result = precio * cantidad;
						String coste = String.valueOf(result);
						ensamblado.add(true);
						cant.add((Integer) spinner1_1.getValue());
						tableModel.addRow(new Object[]{"RAM", textmarca3.getText(), textmodelo3.getText(), spinner1_1.getValue(), coste});
						actualizarCostoE();
					}
					else {
						JOptionPane.showMessageDialog(Ensamblado.this, "La cantidad de RAM para el ensamblado no puede ser mayor que 6");
					}
				}
				else {
					JOptionPane.showMessageDialog(Ensamblado.this, "La cantidad de RAM para el ensamblado no está disponible");
				}
			}
		});
		btnAadir.setBounds(174, 277, 70, 22);
		panel3.add(btnAadir);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDiscoDuro(comboBox.getSelectedIndex());
			}
		});
		comboBox.setBounds(322, 134, 255, 20);
		contentPanel.add(comboBox);

		comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarRAM(comboBox_2.getSelectedIndex());
			}
		});
		comboBox_2.setBounds(624, 134, 255, 20);
		contentPanel.add(comboBox_2);
		llenarcombo(comboBox, comboBox_2);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(UIManager.getColor("Button.highlight"));
		scrollPane.setBounds(29, 527, 850, 152);
		contentPanel.add(scrollPane);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Marca");
		tableModel.addColumn("Modelo");
		tableModel.addColumn("Cantidad");
		tableModel.addColumn("Precio");

		table = new JTable(tableModel);
		scrollPane.setBorder(new TitledBorder(null, "Piezas Ensambladas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(table);

		tableModel.addRow(new Object[]{"Tarjeta Madre", TM.getMarca(), TM.getModelo(), "1",String.valueOf(TM.getPrecio())});
		tableModel.addRow(new Object[]{"Microprocesador", textmarca1.getText(), textmodelo1.getText(), "1", textPrecio1.getText()});
		JButton btnNewButton_1 = new JButton("Ensamblar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Disco == true && RAM == true) {
					if (!componentes.isEmpty() ) {
						LocalDate fecha = LocalDate.now();
						f = new Factura(fecha);
						f.setCantidadXPieza(cant);
						f.setCom(componentes);
						f.setEnsamblado(ensamblado);
						f.setPrecioEnsamblado(Float.parseFloat(costoEnsamblado.getText()));
						tienda.agregarFactura(f);
						boolean hecho = tienda.eliminarCantPiezas(f);
						if(hecho == true) {
							JOptionPane.showMessageDialog(Ensamblado.this, "Proceso de ensamblado realizado exitosamente");
							dispose();
						}
					}
				}
				else if(Disco == false && RAM == true){
					JOptionPane.showMessageDialog(Ensamblado.this, "Antes de ensamblar debe de agregar un Disco Duro");
				}
				else if(Disco == true && RAM == false) {
					JOptionPane.showMessageDialog(Ensamblado.this, "Antes de ensamblar debe de agregar una Memoria RAM");
				}
				else {
					JOptionPane.showMessageDialog(Ensamblado.this, "Antes de ensamblar debe de agregar una Memoria RAM y un Disco Duro");
				}

			}
		});
		btnNewButton_1.setBounds(800, 501, 70, 22);
		contentPanel.add(btnNewButton_1);


		JLabel lblNewLabel_3 = new JLabel("Costo de Ensamblado:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(29, 678, 191, 44);
		contentPanel.add(lblNewLabel_3);

		costoEnsamblado = new JLabel("New label");
		costoEnsamblado.setFont(new Font("Tahoma", Font.BOLD, 15));
		costoEnsamblado.setEnabled(false);
		costoEnsamblado.setBounds(205, 686, 89, 29);
		contentPanel.add(costoEnsamblado);
		float t = tableModel.getRowCount() * 10;
		costoEnsamblado.setText(String.valueOf(t));

		costoTotal = new JLabel("New label");
		costoTotal.setEnabled(false);
		costoTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		costoTotal.setBounds(745, 685, 129, 30);
		contentPanel.add(costoTotal);;
		costoTotal.setText(costoEnsamblado.getText());
		actualizarTotal();
		JLabel lblNewLabel_3_1 = new JLabel("Costo Total:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(645, 678, 106, 44);
		contentPanel.add(lblNewLabel_3_1);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(2, 0, 0, 0, (Color) UIManager.getColor("Button.frame")));
			buttonPane.setBackground(UIManager.getColor("Button.highlight"));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Atr\u00E1s");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir? No se guardarán los cambios realizados", "", 0, 3);
						if(i == 0) {
							setVisible(false);
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void actualizarDiscoDuro(int selectedIndex) {
		textmarca2.setText(TM.getDiscos().get(comboBox.getSelectedIndex()).getMarca());
		textmodelo2.setText(TM.getDiscos().get(comboBox.getSelectedIndex()).getModelo());
		textNo2.setText(TM.getDiscos().get(comboBox.getSelectedIndex()).getNumSerie());
		textPrecio2.setText(String.valueOf(TM.getDiscos().get(comboBox.getSelectedIndex()).getPrecio()));

	}
	protected void actualizarRAM(int selectedIndex) {
		textmarca3.setText(TM.getMemoriasR().get(comboBox_2.getSelectedIndex()).getMarca());
		textmodelo3.setText(TM.getMemoriasR().get(comboBox_2.getSelectedIndex()).getModelo());
		textNo3.setText(TM.getMemoriasR().get(comboBox_2.getSelectedIndex()).getNumSerie());
		textPrecio3.setText(String.valueOf(TM.getMemoriasR().get(comboBox_2.getSelectedIndex()).getPrecio()));

	}

	private void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		comboBox.removeAllItems(); 
		for (String item : items) {
			comboBox.addItem(item);
		}
	}
	public void llenarcombo(JComboBox<String> comboBox, JComboBox<String> comboBox_1) {
		ArrayList<String> ramList = new ArrayList<String>();
		ArrayList<String> discoList = new ArrayList<String>();
		ArrayList<String> microList = new ArrayList<String>();
		for(int i = 0; i < TM.getMemoriasR().size(); i++) {
			ramList.add(i, TM.getMemoriasR().get(i).getMarca() + "   " +  TM.getMemoriasR().get(i).getModelo());
		}
		if(ramList != null) {
			llenarComboBox(comboBox_1, ramList);
		}

		for(int i = 0; i < TM.getDiscos().size(); i++) {
			discoList.add(i, TM.getDiscos().get(i).getMarca() + "   " +  TM.getDiscos().get(i).getModelo());
		}
		if(discoList != null) {
			llenarComboBox(comboBox, discoList);
		}
		microList.add(TM.getMicro().getMarca() + "   " +  TM.getMicro().getModelo());
	}
	private void actualizarTotal() {
		Object totalF = 0;
		float t = 0;
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			totalF = tableModel.getValueAt(i, 4);
			t += Float.valueOf((String) totalF);
		}
		t += Float.parseFloat(costoEnsamblado.getText());
		costoTotal.setText(String.valueOf(t));
	}
	private void actualizarCostoE() {
		t += 10;
		costoEnsamblado.setText(String.valueOf(t));
	}
}
