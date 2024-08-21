package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.TiendaDeComputadoras;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class VenderPieza extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private TiendaDeComputadoras tiendaC;
	private Principal p;
	private JComboBox comboBoxComponenetes;
	private JComboBox comboBoxMOdelo;
	private JComboBox comboBoxMarca;
	private String nombreC;
	
	public VenderPieza(Principal principal, TiendaDeComputadoras tienda, String nombreDeComponente) {
		nombreC = nombreDeComponente;
		setTitle("Vender Piezas");
		p = principal;
		tiendaC = tienda;
		setBounds(100, 100, 900, 746);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			ArrayList<String> itemExp = new ArrayList<String>();
			JPanel FiltradodeProducto = new JPanel();
			FiltradodeProducto.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
			FiltradodeProducto.setBounds(0, 0, 438, 535);
			contentPanel.add(FiltradodeProducto);
			FiltradodeProducto.setLayout(null);

			JLabel Componenetelabel = new JLabel("Componenetes:");
			Componenetelabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			Componenetelabel.setBounds(12, 78, 141, 16);
			FiltradodeProducto.add(Componenetelabel);

			comboBoxComponenetes = new JComboBox();
			comboBoxComponenetes.setBounds(157, 77, 257, 20);
			FiltradodeProducto.add(comboBoxComponenetes);


			JLabel lblModelo = new JLabel("Modelo:");
			lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblModelo.setBounds(12, 106, 141, 16);
			FiltradodeProducto.add(lblModelo);

			comboBoxMOdelo = new JComboBox();
			comboBoxMOdelo.setBounds(157, 105, 257, 20);
			FiltradodeProducto.add(comboBoxMOdelo);

			JLabel lblMarca = new JLabel("Marca:");
			lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblMarca.setBounds(12, 134, 141, 16);
			FiltradodeProducto.add(lblMarca);

			comboBoxMarca = new JComboBox();
			comboBoxMarca.setBounds(157, 133, 257, 20);
			FiltradodeProducto.add(comboBoxMarca);
			if(nombreC == null) {
				llenarComboBox(comboBoxComponenetes, inicializaciones.InicializacionDeDatos.nameComponenete());
				System.out.println("");
			}
			else {
				itemExp = inicializaciones.InicializacionDeDatos.nameComponenete();
				itemExp.remove(nombreDeComponente);
				itemExp.add(0,nombreDeComponente);
				
				llenarComboBox(comboBoxComponenetes, itemExp);
			}

			JLabel lblNewLabel = new JLabel("Buscar Componente");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 12, 402, 51);
			FiltradodeProducto.add(lblNewLabel);
		}
		{
			JPanel ProductoEncontrado = new JPanel();
			ProductoEncontrado.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
			ProductoEncontrado.setBounds(438, 0, 452, 535);
			contentPanel.add(ProductoEncontrado);
			ProductoEncontrado.setLayout(null);

			JLabel lblComponenteEncontrado = new JLabel("Componente Encontrado");
			lblComponenteEncontrado.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblComponenteEncontrado.setBounds(12, 12, 402, 51);
			ProductoEncontrado.add(lblComponenteEncontrado);
		}
		{
			JPanel ProductoAgregado = new JPanel();
			ProductoAgregado.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			ProductoAgregado.setBounds(0, 536, 890, 148);
			contentPanel.add(ProductoAgregado);
			ProductoAgregado.setLayout(null);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void llenarComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		for (String item : items) {
			comboBox.addItem(item);
		}
	}
}
