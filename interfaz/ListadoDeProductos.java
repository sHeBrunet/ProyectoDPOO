package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private String[] columnNames = {"No.", "Marca", "Modelo", "Precio", "Cantidad", "No. Serie"};
	private static JTable tableBocinas;
	private static JTable tableChasis;
	private static JTable tableDiscos;
	private static JTable tableFuentes;
	private static JTable tableMicros;
	private static JTable tableMonitores;
	private static JTable tableMotherboards;
	private static JTable tableMouses;
	private static JTable tableRAM;
	private static JTable tableTarjetas;
	private static JTable tableTeclados;
	private static JTable tableAdaptadores;
	private static JTable tableTotalPiezas;
	private boolean cambios = false;
	private static ArrayList <String> piezasAElim = new ArrayList <>();
	private static int count;

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
		JPanel panelTotalPiezas = new JPanel(new BorderLayout());

		DefaultTableModel modelChasis = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableChasis = new JTable(modelChasis);
		panelChasis.add(new JScrollPane(tableChasis), BorderLayout.CENTER);

		DefaultTableModel modelBocinas = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableBocinas = new JTable(modelBocinas);
		panelBocinas.add(new JScrollPane(tableBocinas), BorderLayout.CENTER);

		DefaultTableModel modelDiscos = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableDiscos = new JTable(modelDiscos);
		panelDiscos.add(new JScrollPane(tableDiscos), BorderLayout.CENTER);

		DefaultTableModel modelFuentes = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableFuentes = new JTable(modelFuentes);
		panelFuentes.add(new JScrollPane(tableFuentes), BorderLayout.CENTER);

		DefaultTableModel modelMicros = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableMicros = new JTable(modelMicros);
		panelMicros.add(new JScrollPane(tableMicros), BorderLayout.CENTER);

		DefaultTableModel modelMonitores = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableMonitores = new JTable(modelMonitores);
		panelMonitores.add(new JScrollPane(tableMonitores), BorderLayout.CENTER);

		DefaultTableModel modelMotherboards = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableMotherboards = new JTable(modelMotherboards);
		panelMotherboards.add(new JScrollPane(tableMotherboards), BorderLayout.CENTER);

		DefaultTableModel modelMouse = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableMouses = new JTable(modelMouse);
		panelMouse.add(new JScrollPane(tableMouses), BorderLayout.CENTER);

		DefaultTableModel modelRAM = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableRAM = new JTable(modelRAM);
		panelRAM.add(new JScrollPane(tableRAM), BorderLayout.CENTER);

		DefaultTableModel modelTarjetas = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableTarjetas = new JTable(modelTarjetas);
		panelTarjetas.add(new JScrollPane(tableTarjetas), BorderLayout.CENTER);

		DefaultTableModel modelTeclados = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableTeclados = new JTable(modelTeclados);
		panelTeclados.add(new JScrollPane(tableTeclados), BorderLayout.CENTER);

		DefaultTableModel modelAdaptadores = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableAdaptadores = new JTable(modelAdaptadores);
		panelAdaptadores.add(new JScrollPane(tableAdaptadores), BorderLayout.CENTER);

		DefaultTableModel modelTotalPiezas = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableTotalPiezas = new JTable(modelTotalPiezas);
		panelTotalPiezas.add(new JScrollPane(tableTotalPiezas), BorderLayout.CENTER);

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
		tabbedPane.addTab("General", panelTotalPiezas);

		JPanel panelBotones = new JPanel();
		btnAtras = new JButton("Atrás");
		btnAtras.setFocusable(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cambios) {
					int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir? No se guardarán los cambios realizados", "", 0, 3);
					if(i==0) {
						setVisible(false);
						piezasAElim.clear();
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
		btnAceptar.setFocusable(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cambios) {
					int count = tienda.eliminarPiezas(piezasAElim);
					JOptionPane.showMessageDialog(ListadoDeProductos.this, "Cambios guardados satisfactoriamente. Se eliminaron " + count + " piezas");		
					setVisible(false);
					cambios = false;
					piezasAElim.clear();
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
				int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la pieza seleccionada?", "", 0, 3);
				if(i==0) {		
					cambios = true;
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
					int posPT = tableTotalPiezas.getSelectedRow();
					if (posAd != -1) {
						String ID = (String) tableAdaptadores.getValueAt(posAd, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableAdaptadores.getModel()).removeRow(posAd);
						limpiarAdaptadores();
						limpiarTotalPiezas();
						llenarTablaAdaptadores(modelAdaptadores);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posB != -1) {
						String ID = (String) tableBocinas.getValueAt(posB, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableBocinas.getModel()).removeRow(posB);
						limpiarBocinas();
						limpiarTotalPiezas();
						llenarTablaBocinas(modelBocinas);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posC != -1) {
						String ID = (String) tableChasis.getValueAt(posC, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableChasis.getModel()).removeRow(posC);
						limpiarChasis();
						limpiarTotalPiezas();
						llenarTablaChasis(modelChasis);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posD != -1) {
						String ID = (String) tableDiscos.getValueAt(posD, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableDiscos.getModel()).removeRow(posD);
						limpiarDiscos();
						limpiarTotalPiezas();
						llenarTablaDiscos(modelDiscos);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posF != -1) {
						String ID = (String) tableFuentes.getValueAt(posF, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableFuentes.getModel()).removeRow(posF);
						limpiarFuentes();
						limpiarTotalPiezas();
						llenarTablaFuentes(modelFuentes);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posMic != -1) {
						String ID = (String) tableMicros.getValueAt(posMic, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableMicros.getModel()).removeRow(posMic);
						limpiarMicros();
						limpiarTotalPiezas();
						llenarTablaMicros(modelMicros);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posMon != -1) {
						String ID = (String) tableMonitores.getValueAt(posMon, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableMonitores.getModel()).removeRow(posMon);
						limpiarMonitores();
						limpiarTotalPiezas();
						llenarTablaMonitores(modelMonitores);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posMoth != -1) {
						String ID = (String) tableMotherboards.getValueAt(posMoth, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableMotherboards.getModel()).removeRow(posMoth);
						limpiarMotherboards();
						limpiarTotalPiezas();
						llenarTablaMotherboards(modelMotherboards);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posMous != -1) {
						String ID = (String) tableMouses.getValueAt(posMous, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableMouses.getModel()).removeRow(posMous);
						limpiarMouses();
						limpiarTotalPiezas();
						llenarTablaMouses(modelMouse);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posR != -1) {
						String ID = (String) tableRAM.getValueAt(posR, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableRAM.getModel()).removeRow(posR);
						limpiarRAM();
						limpiarTotalPiezas();
						llenarTablaRAM(modelRAM);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posTV != -1) {
						String ID = (String) tableTarjetas.getValueAt(posTV, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableTarjetas.getModel()).removeRow(posTV);
						limpiarTarjetas();
						limpiarTotalPiezas();
						llenarTablaTarjetaVideo(modelTarjetas);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posT != -1) {
						String ID = (String) tableTeclados.getValueAt(posT, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableTeclados.getModel()).removeRow(posT);		
						limpiarTeclados();
						limpiarTotalPiezas();
						llenarTablaTeclados(modelTeclados);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else if (posPT != -1) {
						String ID = (String) tableTotalPiezas.getValueAt(posPT, 5);
						piezasAElim.add(ID);
						((DefaultTableModel) tableTotalPiezas.getModel()).removeRow(posPT);		
						limpiarTodo();
						llenarTablaAdaptadores(modelAdaptadores);
						llenarTablaBocinas(modelBocinas);
						llenarTablaChasis(modelChasis);
						llenarTablaDiscos(modelDiscos);
						llenarTablaFuentes(modelFuentes);
						llenarTablaMicros(modelMicros);
						llenarTablaMonitores(modelMonitores);
						llenarTablaMotherboards(modelMotherboards);
						llenarTablaMouses(modelMouse);
						llenarTablaRAM(modelRAM);
						llenarTablaTarjetaVideo(modelTarjetas);
						llenarTablaTeclados(modelTeclados);
						llenarTablaTotalPiezas(modelTotalPiezas);
					} else {
						JOptionPane.showMessageDialog(ListadoDeProductos.this, "Antes de eliminar debe de seleccionar un componente de la tabla");
					}
				}
			}
		});
		panelBotones.add(btnBorrar);

		piezasAElim.clear();				
		llenarTablaAdaptadores(modelAdaptadores);
		llenarTablaBocinas(modelBocinas);
		llenarTablaChasis(modelChasis);
		llenarTablaDiscos(modelDiscos);
		llenarTablaFuentes(modelFuentes);
		llenarTablaMicros(modelMicros);
		llenarTablaMonitores(modelMonitores);
		llenarTablaMotherboards(modelMotherboards);
		llenarTablaMouses(modelMouse);
		llenarTablaRAM(modelRAM);
		llenarTablaTarjetaVideo(modelTarjetas);
		llenarTablaTeclados(modelTeclados);
		llenarTablaTotalPiezas(modelTotalPiezas);
	}

	private static void llenarTablaAdaptadores(DefaultTableModel model) {
		count = 1;             
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("A")) 
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaBocinas(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("B")) 
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaChasis(DefaultTableModel model) {
		count= 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("C"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaDiscos(DefaultTableModel model) {
		count= 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("DD"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaFuentes(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("F"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaMicros(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("MP"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaMonitores(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("MN"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaMotherboards(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("TM"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaMouses(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("R"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaRAM(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("MR"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaTarjetaVideo(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("TV"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaTeclados(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(c.getNumSerie().startsWith("TE"))
				if(!piezasAElim.isEmpty()) {
					boolean encontrado = false;
					for(int i = 0; i < piezasAElim.size(); i++) {
						if(c.getNumSerie().equals(piezasAElim.get(i))) {
							encontrado = true;
						}
					}
					if(!encontrado)
						model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
				else {
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
				}
		}
	}

	private static void llenarTablaTotalPiezas(DefaultTableModel model) {
		count = 1;
		for (ComponenteOrdenador c : tienda.getComponentes()) {
			if(!piezasAElim.isEmpty()) {
				boolean encontrado = false;
				for(int i = 0; i < piezasAElim.size(); i++) {
					if(c.getNumSerie().equals(piezasAElim.get(i))) {
						encontrado = true;
					}
				}
				if(!encontrado)
					model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
			}
			else {
				model.addRow(new Object[]{count++, c.getMarca(), c.getModelo(), c.getPrecio(), c.getCantDisponible(), c.getNumSerie()});
			}
		}
	}

	private static void limpiarAdaptadores() {
		while(((DefaultTableModel) tableAdaptadores.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableAdaptadores.getModel()).removeRow(0);
	}

	private static void limpiarBocinas() {
		while(((DefaultTableModel) tableBocinas.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableBocinas.getModel()).removeRow(0);
	}

	private static void limpiarChasis() {
		while(((DefaultTableModel) tableChasis.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableChasis.getModel()).removeRow(0);
	}

	private static void limpiarDiscos() {
		while(((DefaultTableModel) tableDiscos.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableDiscos.getModel()).removeRow(0);
	}

	private static void limpiarFuentes() {
		while(((DefaultTableModel) tableFuentes.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableFuentes.getModel()).removeRow(0);
	}

	private static void limpiarMicros() {
		while(((DefaultTableModel) tableMicros.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableMicros.getModel()).removeRow(0);
	}

	private static void limpiarMonitores() {
		while(((DefaultTableModel) tableMonitores.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableMonitores.getModel()).removeRow(0);
	}

	private static void limpiarMotherboards() {
		while(((DefaultTableModel) tableMotherboards.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableMotherboards.getModel()).removeRow(0);
	}

	private static void limpiarMouses() {
		while(((DefaultTableModel) tableMouses.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableMouses.getModel()).removeRow(0);
	}

	private static void limpiarRAM() {
		while(((DefaultTableModel) tableRAM.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableRAM.getModel()).removeRow(0);
	}

	private static void limpiarTarjetas() {
		while(((DefaultTableModel) tableTarjetas.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableTarjetas.getModel()).removeRow(0);
	}

	private static void limpiarTeclados() {
		while(((DefaultTableModel) tableTeclados.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableTeclados.getModel()).removeRow(0);
	}

	private static void limpiarTotalPiezas() {
		while(((DefaultTableModel) tableTotalPiezas.getModel()).getRowCount() > 0)
			((DefaultTableModel) tableTotalPiezas.getModel()).removeRow(0);
	}

	private static void limpiarTodo() {
		limpiarAdaptadores();
		limpiarBocinas();
		limpiarChasis();
		limpiarDiscos();
		limpiarFuentes();
		limpiarMicros();
		limpiarMonitores();
		limpiarMotherboards();
		limpiarMouses();
		limpiarRAM();
		limpiarTarjetas();
		limpiarTeclados();
		limpiarTotalPiezas();
	}
}

