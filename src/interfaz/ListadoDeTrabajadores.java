package interfaz;

import logica.Gerente;

import logica.TiendaDeComputadoras;



import logica.Trabajador;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import inicializaciones.*;
//import com.sun.glass.events.WindowEvent;


import javax.swing.*;
import inicializaciones.InicializacionDeDatos;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
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
	private static boolean tablasLlenas = false;

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
		String[] columnNamesGerentes = {"No.", "Nombre", "Apellidos", "CI", "Salario", "Educaci�n", "Cargo", "Fecha de Ingreso"};
		DefaultTableModel modelGerentes = new DefaultTableModel(columnNamesGerentes, 0);
		tableGerentes = new JTable(modelGerentes);
		panelGerentes.add(new JScrollPane(tableGerentes), BorderLayout.CENTER);

		JPanel panelTrabajadores = new JPanel(new BorderLayout());
		String[] columnNamesTrabajadores = {"No.", "Nombre", "Apellidos", "CI", "Salario", "Educaci�n", "Cargo"};
		DefaultTableModel modelTrabajadores = new DefaultTableModel(columnNamesTrabajadores, 0);
		tableTrabajadores = new JTable(modelTrabajadores);
		panelTrabajadores.add(new JScrollPane(tableTrabajadores), BorderLayout.CENTER);

		tabbedPane.addTab("Trabajadores", panelTrabajadores);
		tabbedPane.addTab("Gerentes", panelGerentes);

		JPanel panelBotones = new JPanel();
		btnAtras = new JButton("Atr�s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cambios) {
					int i = JOptionPane.showConfirmDialog(null, "�Seguro que desea salir? No se guardar�n los cambios realizados", "", 0, 3);
					if(i==0)
						setVisible(false);
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
					JOptionPane.showMessageDialog(ListadoDeTrabajadores.this, "Cambios guardados satisfactoriamente");
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(ListadoDeTrabajadores.this, "No ha realizado ning�n cambio");	
			}
		});
		panelBotones.add(btnAceptar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "�Seguro que desea borrar al trabajador seleccionado?", "", 0, 3);
				if(i==0) {	
					int pos = tableTrabajadores.getSelectedRow();
					int pos1 = tableGerentes.getSelectedRow();
					if (pos != -1) {
						String ID = (String) tableTrabajadores.getValueAt(pos, 3);
						((DefaultTableModel) tableTrabajadores.getModel()).removeRow(pos);
						tienda.eliminarTrabajador1(ID);
						cambios = true;
						limpiarTrabajadores();
						llenarTablaTrabajadores(modelTrabajadores);
					} else if (pos1 != -1) {
						if(tienda.hallarGerentes() > 1) {
							String ID = (String) tableGerentes.getValueAt(pos1, 3);
							((DefaultTableModel) tableGerentes.getModel()).removeRow(pos1);
							tienda.eliminarTrabajador1(ID);
							cambios = true;
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

		if(!tablasLlenas) 
			inicializar();
		llenarTablaGerentes(modelGerentes);
		llenarTablaTrabajadores(modelTrabajadores);
	}

	private static void llenarTablaTrabajadores(DefaultTableModel model) {
		int i = 1;
		for (Trabajador t : tienda.getTrabajadores()) {
			if (!t.getCargo().equals("Gerente"))
				model.addRow(new Object[]{i++, t.getNombre(), t.getApellidos(), t.getCI(), t.getSalarioBasico(), t.getNivelEscolar(), t.getCargo()});
		}
	}

	private static void llenarTablaGerentes(DefaultTableModel model) {
		int i = 1;
		for (Trabajador t : tienda.getGerentes()) {
			Gerente g = (Gerente) t;
			SimpleDateFormat formFecha = new SimpleDateFormat("dd/mm/yyyy");
			String fecha = formFecha.format((Date) g.getFechaOcupCargo());
			model.addRow(new Object[]{i++, g.getNombre(), g.getApellidos(), g.getCI(), g.getSalarioBasico(), g.getNivelEscolar(), g.getCargo(), fecha});
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
	
	private static void inicializar() {
		inicializaciones.InicializacionDeDatos.crearGerentes(tienda);
		inicializaciones.InicializacionDeDatos.crearTrabajadores(tienda);
		tablasLlenas = true;
	}


}
