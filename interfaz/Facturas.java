package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Factura;
import logica.TiendaDeComputadoras;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Facturas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private Principal p;
	private TiendaDeComputadoras tienda;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textTotalRecaudado;

	public Facturas(Principal principal, TiendaDeComputadoras tiendaC) {
		super(principal, true);
		setResizable(false);
		setTitle("Facturas");
		tienda = tiendaC;
		p = principal;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 894, 643);
		contentPanel.add(scrollPane);

		JPanel panel = new JPanel(new BorderLayout());
		String[] columnNames = {"Nombre", "Marca", "Modelo", "Cantidad", "Costo Total"};
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 645, 890, 39);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Total Recaudado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(591, 12, 134, 16);
		panel_1.add(lblNewLabel);

		textTotalRecaudado = new JTextField();
		textTotalRecaudado.setFont(new Font("Tahoma", Font.BOLD, 15));
		textTotalRecaudado.setEnabled(false);
		textTotalRecaudado.setEditable(false);
		textTotalRecaudado.setBounds(744, 11, 104, 19);
		panel_1.add(textTotalRecaudado);
		textTotalRecaudado.setColumns(10);
		textTotalRecaudado.setText(String.valueOf(tienda.calcularTotalFactura()));
		setSize(900, 746);

		actualizarTabla();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton cancelButton = new JButton("Atrás");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private void actualizarTabla() {
		// Limpiar el modelo antes de agregar nuevas filas
		model.setRowCount(0);

		for (Factura f : tienda.getFacturas()) {
			System.out.println("Factura: " + f);
			for (int i = 0; i < f.getCom().size(); i++) {
				Factura fac = f;
				System.out.println("Componente: " + fac.getCom().get(i));
				model.addRow(new Object[]{
						fac.getCom().get(i).getClass().getSimpleName(),
						fac.getCom().get(i).getMarca(),
						fac.getCom().get(i).getModelo(),
						fac.getCantidadXPieza().get(i),
						fac.calcularMontoXPieza(fac.getCom().get(i))
				});
			}
		}

		// Notificar al modelo que los datos han cambiado
		model.fireTableDataChanged();
		table.revalidate();
		table.repaint();
	}

}

