package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.Adaptador;
import logica.Bocina;
import logica.Chasis;
import logica.ComponenteOrdenador;
import logica.DiscoDuro;
import logica.Fuente;
import logica.MemoriaRam;
import logica.Microprocesador;
import logica.Monitor;
import logica.Mouse;
import logica.TarjetaDeVideo;
import logica.TarjetaMadre;
import logica.Teclado;
import logica.TiendaDeComputadoras;

public class ListadoDeProductos extends JDialog {

	private static final long serialVersionUID = 1L;

	private static TiendaDeComputadoras tienda;
	private JButton btnBorrar;
	private JButton btnAtras;
	private JButton btnAceptar;
	private String[] columnNames = {"No.", "Marca", "Modelo", "Precio", "Cantidad"};
	private JTable tableBocinas;
	private JTable tableChasis;
	private JTable tableDiscos;
	private JTable tableFuentes;
	private JTable tableMicros;
	private JTable tableMonitores;
	private JTable tableMotherboards;
	private JTable tableMouses;
	private JTable tableRAM;
	private JTable tableTarjetas;
	private JTable tableTeclados;
	private JTable tableAdaptadores;
	private boolean cambios = false;
	private static int i;

	public ListadoDeProductos(Principal principal, TiendaDeComputadoras t) {
		super(principal, true);
		tienda = t;
		setTitle("Listado de Productos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(900, 746);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panelChasis = new JPanel(new BorderLayout());
		JPanel panelBocinas = new JPanel(new BorderLayout());
		JPanel panelDiscos = new JPanel(new BorderLayout());
		JPanel panelFuentes = new JPanel(new BorderLayout());
		JPanel panelMicros = new JPanel(new BorderLayout());
		JPanel panelMonitores = new JPanel(new BorderLayout());
		JPanel panelMotherboards = new JPanel(new BorderLayout());
		JPanel panelMouse = new JPanel(new BorderLayout());
		JPanel panelAdaptadores = new JPanel(new BorderLayout());
		JPanel panelRAM = new JPanel(new BorderLayout());
		JPanel panelTarjetas = new JPanel(new BorderLayout());
		JPanel panelTeclados = new JPanel(new BorderLayout());

		DefaultTableModel modelChasis = new DefaultTableModel(columnNames, 0);
		tableChasis = new JTable(modelChasis);
		panelChasis.add(new JScrollPane(tableChasis), BorderLayout.CENTER);

		DefaultTableModel modelBocinas = new DefaultTableModel(columnNames, 0);
		tableBocinas = new JTable(modelBocinas);
		panelBocinas.add(new JScrollPane(tableBocinas), BorderLayout.CENTER);

		DefaultTableModel modelDiscos = new DefaultTableModel(columnNames, 0);
		tableDiscos = new JTable(modelDiscos);
		panelDiscos.add(new JScrollPane(tableDiscos), BorderLayout.CENTER);

		DefaultTableModel modelFuentes = new DefaultTableModel(columnNames, 0);
		tableFuentes = new JTable(modelFuentes);
		panelFuentes.add(new JScrollPane(tableFuentes), BorderLayout.CENTER);

		DefaultTableModel modelMicros = new DefaultTableModel(columnNames, 0);
		tableMicros = new JTable(modelMicros);
		panelMicros.add(new JScrollPane(tableMicros), BorderLayout.CENTER);

		DefaultTableModel modelMonitores = new DefaultTableModel(columnNames, 0);
		tableMonitores = new JTable(modelMonitores);
		panelMonitores.add(new JScrollPane(tableMonitores), BorderLayout.CENTER);

		DefaultTableModel modelMotherboards = new DefaultTableModel(columnNames, 0);
		tableMotherboards = new JTable(modelMotherboards);
		panelMotherboards.add(new JScrollPane(tableMotherboards), BorderLayout.CENTER);

		DefaultTableModel modelMouse = new DefaultTableModel(columnNames, 0);
		tableMouses = new JTable(modelMouse);
		panelMouse.add(new JScrollPane(tableMouses), BorderLayout.CENTER);

		DefaultTableModel modelRAM = new DefaultTableModel(columnNames, 0);
		tableRAM = new JTable(modelRAM);
		panelRAM.add(new JScrollPane(tableRAM), BorderLayout.CENTER);

		DefaultTableModel modelTarjetas = new DefaultTableModel(columnNames, 0);
		tableTarjetas = new JTable(modelTarjetas);
		panelTarjetas.add(new JScrollPane(tableTarjetas), BorderLayout.CENTER);

		DefaultTableModel modelTeclados = new DefaultTableModel(columnNames, 0);
		tableTeclados = new JTable(modelTeclados);
		panelTeclados.add(new JScrollPane(tableTeclados), BorderLayout.CENTER);

		DefaultTableModel modelAdaptadores = new DefaultTableModel(columnNames, 0);
		tableAdaptadores = new JTable(modelAdaptadores);
		panelAdaptadores.add(new JScrollPane(tableAdaptadores), BorderLayout.CENTER);

		tabbedPane.addTab("Adaptadores", panelAdaptadores);
		tabbedPane.addTab("Bocinas", panelBocinas);
		tabbedPane.addTab("Chasis", panelChasis);
		tabbedPane.addTab("Discos", panelDiscos);
		tabbedPane.addTab("Fuentes", panelFuentes);
		tabbedPane.addTab("Micros", panelMicros);
		tabbedPane.addTab("Monitores", panelMonitores);
		tabbedPane.addTab("Motherboards", panelMotherboards);
		tabbedPane.addTab("Mouses", panelMouse);
		tabbedPane.addTab("RAM", panelRAM);
		tabbedPane.addTab("Tarjetas de Video", panelTarjetas);
		tabbedPane.addTab("Teclados", panelTeclados);


		JPanel panelBotones = new JPanel();
		btnAtras = new JButton("Atrás");
		btnAtras.setFocusable(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cambios) {
					int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir? No se guardarán los cambios realizados", "", 0, 3);
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
		btnAceptar.setFocusable(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cambios) {
					JOptionPane.showMessageDialog(ListadoDeProductos.this, "Cambios guardados satisfactoriamente");		
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(ListadoDeProductos.this, "No ha realizado ningún cambio");	
			}
		});
		panelBotones.add(btnAceptar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setFocusable(false);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posAd = tableAdaptadores.getSelectedRow();
				int posB = tableBocinas.getSelectedRow(); 
				int posC = tableChasis.getSelectedRow();
				int posD = tableDiscos.getSelectedRow();
				int posF = tableFuentes.getSelectedRow();
				int posMic = tableMicros.getSelectedRow();
				int posMon = tableMonitores.getSelectedRow();
				int posMoth = tableMotherboards.getSelectedRow();
				int posMous = tableMouses.getSelectedRow();
				int posR = tableRAM.getSelectedRow();
				int posTV = tableTarjetas.getSelectedRow();
				int posT = tableTeclados.getSelectedRow();

				if (posAd != -1) {
					((DefaultTableModel) tableAdaptadores.getModel()).removeRow(posAd);
					tienda.eliminarAdaptadores(posAd);
					cambios = true;
				} else if (posB != -1) {
					((DefaultTableModel) tableBocinas.getModel()).removeRow(posB);
					tienda.eliminarBocinas(posB);
					cambios = true;
				} else if (posC != -1) {
					((DefaultTableModel) tableChasis.getModel()).removeRow(posC);
					tienda.eliminarChasis(posC);
					cambios = true;
				} else if (posD != -1) {
					((DefaultTableModel) tableDiscos.getModel()).removeRow(posD);
					tienda.eliminarDiscos(posD);
					cambios = true;
				} else if (posF != -1) {
					((DefaultTableModel) tableFuentes.getModel()).removeRow(posF);
					tienda.eliminarFuentes(posF);
					cambios = true;
				} else if (posMic != -1) {
					((DefaultTableModel) tableMicros.getModel()).removeRow(posMic);
					tienda.eliminarMicros(posMic);
					cambios = true;
				} else if (posMon != -1) {
					((DefaultTableModel) tableMonitores.getModel()).removeRow(posMon);
					tienda.eliminarMonitores(posMon);
					cambios = true;
				} else if (posMoth != -1) {
					((DefaultTableModel) tableMotherboards.getModel()).removeRow(posMoth);
					tienda.eliminarMotherboards(posMoth);
					cambios = true;
				} else if (posMous != -1) {
					((DefaultTableModel) tableMouses.getModel()).removeRow(posMous);
					tienda.eliminarMouses(posMous);
					cambios = true;
				} else if (posR != -1) {
					((DefaultTableModel) tableRAM.getModel()).removeRow(posR);
					tienda.eliminarRAM(posR);
					cambios = true;
				} else if (posTV != -1) {
					((DefaultTableModel) tableTarjetas.getModel()).removeRow(posTV);
					tienda.eliminarTarjetaVideo(posTV);
					cambios = true;
				} else if (posT != -1) {
					((DefaultTableModel) tableTeclados.getModel()).removeRow(posT);
					tienda.eliminarTeclados(posT);
					cambios = true;
				} else {
					JOptionPane.showMessageDialog(ListadoDeProductos.this, "Antes de eliminar debe de seleccionar un componente de la tabla");
				}
			}
		});
		panelBotones.add(btnBorrar);

		inicializar();
		llenarTablaAdaptadores(tienda, modelAdaptadores);
		llenarTablaBocinas(tienda, modelBocinas);
		llenarTablaChasis(tienda, modelChasis);
		llenarTablaDiscos(tienda, modelDiscos);
		llenarTablaFuentes(tienda, modelFuentes);
		llenarTablaMicros(tienda, modelMicros);
		llenarTablaMonitores(tienda, modelMonitores);
		llenarTablaMotherboards(tienda, modelMotherboards);
		llenarTablaMouses(tienda, modelMouse);
		llenarTablaRAM(tienda, modelRAM);
		llenarTablaTarjetaVideo(tienda, modelTarjetas);
		llenarTablaTeclados(tienda, modelTeclados);
	}

	private static void llenarTablaAdaptadores(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Adaptador) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaBocinas(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Bocina) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaChasis(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i= 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if (c instanceof Chasis)
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaDiscos(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i= 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof DiscoDuro) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaFuentes(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Fuente) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaMicros(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Microprocesador) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaMonitores(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Monitor) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaMotherboards(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof TarjetaMadre) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaMouses(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Mouse) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaRAM(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof MemoriaRam) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaTarjetaVideo(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof TarjetaDeVideo) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void llenarTablaTeclados(TiendaDeComputadoras tienda, DefaultTableModel model) {
		i = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c instanceof Teclado) 
				model.addRow(new Object[]{i++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible()});
		}
	}

	private static void inicializar() {
		inicializaciones.InicializacionDeDatos.inicializarBocinas(tienda);
		inicializaciones.InicializacionDeDatos.inicializarChasis(tienda);
		inicializaciones.InicializacionDeDatos.inicializarDiscosDuros(tienda);
		inicializaciones.InicializacionDeDatos.inicializarFuentes(tienda);
		inicializaciones.InicializacionDeDatos.inicializarMemoriasRAM(tienda);
		inicializaciones.InicializacionDeDatos.inicializarMicroprocesador(tienda);
		inicializaciones.InicializacionDeDatos.inicializarMonitores(tienda);
		inicializaciones.InicializacionDeDatos.inicializarMouses(tienda);
		inicializaciones.InicializacionDeDatos.inicializarTarjetasMadre(tienda);
		inicializaciones.InicializacionDeDatos.inicializarTarjetasVideo(tienda);
		inicializaciones.InicializacionDeDatos.inicializarTeclados(tienda);
		inicializaciones.InicializacionDeDatos.inicializarExtension(tienda);
	}

	private static void actualizarTablas(){

	}
}

