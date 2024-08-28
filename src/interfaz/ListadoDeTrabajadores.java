package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.Gerente;
import logica.TiendaDeComputadoras;
import logica.Trabajador;
@SuppressWarnings("unused")
public class ListadoDeTrabajadores extends JDialog {

	private static final long serialVersionUID = 1L;
	private static TiendaDeComputadoras tienda;
	private Principal p;
	private JButton btnBorrar;
	private JButton btnAtras;
	private static JTable tableTrabajadores;
	private static JTable tableGerentes;
	private DefaultTableModel model;
	private JButton btnAceptar;
	private boolean cambios = false;
	private static ArrayList <String> trabAElim = new ArrayList<>();
	private static int count;

	public ListadoDeTrabajadores(Principal principal, TiendaDeComputadoras t) {
		super(principal, true);
		p = principal; 
		tienda = t;
		setTitle("Gerentes y Trabajadores");
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
				if(cambios) {
					int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir? No se guardarán los cambios realizados", "", 0, 3);
					if(i==0) {
						setVisible(false);
						trabAElim.clear();
					}
				}
				else
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
				if(cambios) {
					int count = tienda.eliminarTrabajadores(trabAElim);
					JOptionPane.showMessageDialog(ListadoDeTrabajadores.this, "Cambios guardados satisfactoriamente. Se eliminaron " + count + " trabajadores");		
					setVisible(false);
					trabAElim.clear();
				}
				else
					JOptionPane.showMessageDialog(ListadoDeTrabajadores.this, "No ha realizado ningún cambio");	
			}
		});
		panelBotones.add(btnAceptar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar al trabajador seleccionado?", "", 0, 3);
				if(i==0) {	
					cambios = true;
					int pos = tableTrabajadores.getSelectedRow();
					int pos1 = tableGerentes.getSelectedRow();
					if (pos != -1) {
						String ID = (String) tableTrabajadores.getValueAt(pos, 3);
						trabAElim.add(ID);
						((DefaultTableModel) tableTrabajadores.getModel()).removeRow(pos);
						limpiarTrabajadores();
						llenarTablaTrabajadores(modelTrabajadores);
					} else if (pos1 != -1) {
						if(tienda.hallarGerentes(trabAElim) > 1) {
							String ID = (String) tableGerentes.getValueAt(pos1, 3);
							trabAElim.add(ID);
							((DefaultTableModel) tableGerentes.getModel()).removeRow(pos1);
							limpiarGerentes();
							llenarTablaGerentes(modelGerentes);
						}
						else
							JOptionPane.showMessageDialog(ListadoDeTrabajadores.this, "Error: Al menos debe haber un gerente en la empresa");
					} else {
						JOptionPane.showMessageDialog(ListadoDeTrabajadores.this, "Antes de eliminar debe de seleccionar un trabajador de la tabla");
					}
				}
			}
		});
		panelBotones.add(btnBorrar);
		
		llenarTablaGerentes(modelGerentes);
		llenarTablaTrabajadores(modelTrabajadores);
	}

	private static void llenarTablaTrabajadores(DefaultTableModel model) {
		count = 1;
		for (Trabajador t : tienda.getTrabajadores()) {
			if (!t.getCargo().equals("Gerente"))
				if(!trabAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < trabAElim.size(); i++) {
						if(t.getCI().equals(trabAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, t.getNombre(), t.getApellidos(), t.getCI(), t.getSalarioBasico(), t.getNivelEscolar(), t.getCargo()});
				}
				else
					model.addRow(new Object[]{count++, t.getNombre(), t.getApellidos(), t.getCI(), t.getSalarioBasico(), t.getNivelEscolar(), t.getCargo()});
		}
	}

	private static void llenarTablaGerentes(DefaultTableModel model) {
		count = 1;
		for (Trabajador t : tienda.getGerentes()) {
			Gerente g = (Gerente) t;
			SimpleDateFormat formFecha = new SimpleDateFormat("dd/mm/yyyy");
			String fecha = formFecha.format((Date) g.getFechaOcupCargo());
			if(!trabAElim.isEmpty()) {
				boolean encontrado = false;
				for(int i = 0; i < trabAElim.size(); i++) {
					if(g.getCI().equals(trabAElim.get(i))) {
						encontrado = true;
					}
				}
				if(!encontrado)
					model.addRow(new Object[]{count++, g.getNombre(), g.getApellidos(), g.getCI(), g.getSalarioBasico(), g.getNivelEscolar(), g.getCargo(), fecha});
			}
			else
				model.addRow(new Object[]{count++, g.getNombre(), g.getApellidos(), g.getCI(), g.getSalarioBasico(), g.getNivelEscolar(), g.getCargo(), fecha});
		}
	}

		private static void limpiarTrabajadores() {
			while(((DefaultTableModel) tableTrabajadores.getModel()).getRowCount() > 0)
				((DefaultTableModel) tableTrabajadores.getModel()).removeRow(0);
		}

		private static void limpiarGerentes() {
			while(((DefaultTableModel) tableGerentes.getModel()).getRowCount() > 0)
				((DefaultTableModel) tableGerentes.getModel()).removeRow(0);
		}

	}
