package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Gerente;
import logica.TiendaDeComputadoras;
import logica.Trabajador;

public class ListadoDeProductos extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private TiendaDeComputadoras tienda;
	private Principal p;
	private JButton btnBorrar;
	private JButton btnAtras;
	private JTable tableTrabajadores;
	private JTable tableGerentes;
	private DefaultTableModel model;
	private JButton btnAceptar;

	public ListadoDeProductos(Principal principal, TiendaDeComputadoras t) {
		super(principal, true);
		p = principal;
		tienda = t;
		setTitle("Listado de Productos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(900, 746);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panelGerentes = new JPanel(new BorderLayout());
		String[] columnNamesGerentes = {"No.", "Nombre", "Apellidos", "CI", "Salario", "Educación", "Cargo", "Fecha de Ingreso"};
		DefaultTableModel modelGerentes = new DefaultTableModel(columnNamesGerentes, 0);
		tableGerentes = new JTable(modelGerentes);
		panelGerentes.add(new JScrollPane(tableGerentes), BorderLayout.CENTER);

		JPanel panelTrabajadores = new JPanel(new BorderLayout());
		String[] columnNamesTrabajadores = {"No.", "Nombre", "Apellidos", "CI", "Salario", "Educación", "Cargo"};
		DefaultTableModel modelTrabajadores = new DefaultTableModel(columnNamesTrabajadores, 0);
		tableTrabajadores = new JTable(modelTrabajadores);
		panelTrabajadores.add(new JScrollPane(tableTrabajadores), BorderLayout.CENTER);

		tabbedPane.addTab("Trabajadores", panelTrabajadores);
		tabbedPane.addTab("Gerentes", panelGerentes);

		JPanel panelBotones = new JPanel();
		btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBotones.add(btnAtras);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelBotones.add(btnAceptar);
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = tableTrabajadores.getSelectedRow();
				int pos1 = tableGerentes.getSelectedRow();
				if (pos != -1) {
					Object t = tableTrabajadores.getValueAt(pos, 0);
					tienda.eliminarTrabajador1(pos);
					((DefaultTableModel) tableTrabajadores.getModel()).removeRow(pos);
				} else if (pos1 != -1) {
					tienda.eliminarTrabajador1(pos1);
					((DefaultTableModel) tableGerentes.getModel()).removeRow(pos1);
				} else {
					JOptionPane.showMessageDialog(ListadoDeProductos.this, "Antes de eliminar debe de seleccionar un trabajador de la tabla");
				}
			}
		});
		panelBotones.add(btnBorrar);
		inicializaciones.InicializacionDeDatos.crearGerentes(tienda);
		inicializaciones.InicializacionDeDatos.crearTrabajadores(tienda);

		llenarTablaGerentes(tienda, modelGerentes);
		llenarTablaTrabajadores(tienda, modelTrabajadores);

	}

	private static void llenarTablaTrabajadores(TiendaDeComputadoras tienda, DefaultTableModel model) {
		for (Trabajador t : tienda.getTrabajadores()) {
			if (!t.getCargo().equals("Gerente"))
				model.addRow(new Object[]{t.getNumero(), t.getNombre(), t.getApellidos(), t.getCI(), t.getSalarioBasico(), t.getNivelEscolar(), t.getCargo()});
		}
	}

	private static void llenarTablaGerentes(TiendaDeComputadoras tienda, DefaultTableModel model) {
		for (Trabajador t : tienda.getGerentes()) {
			Gerente g = (Gerente) t;
			SimpleDateFormat formFecha = new SimpleDateFormat("dd/mm/yyyy");
			String fecha = formFecha.format((Date) g.getFechaOcupCargo());
			model.addRow(new Object[]{g.getNumero(), g.getNombre(), g.getApellidos(), g.getCI(), g.getSalarioBasico(), g.getNivelEscolar(), g.getCargo(), fecha});
		}
	}
}

