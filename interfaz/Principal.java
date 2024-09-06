package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import inicializaciones.InicializacionDeDatos;
import logica.ManejoDeSesion;
import logica.TiendaDeComputadoras;
import login.Login;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
@SuppressWarnings("serial")

public class Principal extends JFrame {
	private TiendaDeComputadoras tiendaC;
	private ManejoDeSesion manejo;

	private JPanel contentPane;
	private JButton btnButtonTarjetaMadre;
	private JButton btnButtonMicroprocesador;
	private JButton btnButtonmemoriasRAM;
	private JButton btnButtonTarjetasVideo;
	private JButton btnButtonDiscosDuros;
	private JButton btnButtonFuente;
	private JButton btnButtonMonitores;
	private JButton btnButtonTeclado;
	private JButton btnButtonRaton;
	private JButton btnButtonBocina;
	private JButton btnButtonChasis;
	private JButton btnButtonOtro;
	private String nombredeComponente;
	private JMenuItem CerrarSesion;
	private JMenuItem CerrarPrograma;
	private JMenuItem Informacion;
	private JMenuItem VenderProductos;
	private JMenuItem AgregarProductos;
	private JMenuItem ListaDeProductos;
	private JMenuItem Facturas;
	private JMenuItem DineroR;
	private JMenuItem PagoTrab;
	private JMenuItem ListaDeTrabajadores;
	private JMenuItem AgregarTrabajador;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param manejoDeSesion 
	 * @param password1 
	 * @param user 
	 */
	public Principal(TiendaDeComputadoras tienda, ManejoDeSesion manejoDeSesion) {
		manejo = manejoDeSesion;
		tiendaC = tienda;
		setTitle("S.A.D PC Store");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/gui/icons/logoPeque\u00F1o1.jpg")));
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 746);
		inicializarDatos();


		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		JMenu MenuInicio = new JMenu("Inicio");
		MenuInicio.setBackground(Color.WHITE);
		menuBar.add(MenuInicio);

		CerrarSesion = new JMenuItem("Cerrar Sesi\u00F3n");
		CerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManejoDeSesion manejo = ManejoDeSesion.getInstance();
					if (manejo != null) {
						manejo.cerrarSesion();
						dispose();
						Login frame = new Login();
						frame.setVisible(true);
					} else {
						System.out.println("ManejoDeSesion es null");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cerrar sesión.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		CerrarSesion.setBackground(Color.WHITE);
		MenuInicio.add(CerrarSesion);

		CerrarPrograma = new JMenuItem("Cerrar Programa");
		CerrarPrograma.setBackground(Color.WHITE);
		CerrarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		MenuInicio.add(CerrarPrograma);

		Informacion = new JMenuItem("Informaci\u00F3n");
		Informacion.setBackground(Color.WHITE);
		Informacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Informacion dialog = new Informacion(Principal.this, tiendaC);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		});
		MenuInicio.add(Informacion);

		JMenu MenuProductos = new JMenu("Productos");
		MenuProductos.setBackground(Color.WHITE);
		menuBar.add(MenuProductos);

		VenderProductos = new JMenuItem("Vender Productos");
		VenderProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tiendaC != null) {
						VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, null);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					} else {
						System.out.println("Error: tiendaC es null");
					}
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		VenderProductos.setBackground(Color.WHITE);
		MenuProductos.add(VenderProductos);

		AgregarProductos = new JMenuItem("Agregar Productos ");
		AgregarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AgregarProducto dialog = new AgregarProducto(Principal.this, tiendaC);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e5) {
					e5.printStackTrace();
				}
			}
		});
		AgregarProductos.setBackground(Color.WHITE);
		MenuProductos.add(AgregarProductos);

		ListaDeProductos = new JMenuItem("Listado de Productos");
		ListaDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListadoDeProductos dialog = new ListadoDeProductos(Principal.this, tiendaC);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e6) {
					e6.printStackTrace();
				}
			}
		});
		ListaDeProductos.setBackground(Color.WHITE);
		MenuProductos.add(ListaDeProductos);
		
		Facturas = new JMenuItem("Facturas");
		Facturas.setBackground(Color.WHITE);
		Facturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Facturas dialog = new Facturas(Principal.this, tiendaC);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					} catch (Exception e8) {
						e8.printStackTrace();
					}
				}
		});
		MenuProductos.add(Facturas);

		JMenu MenuReportes = new JMenu("Reportes");
		MenuReportes.setBackground(Color.WHITE);
		menuBar.add(MenuReportes);

		DineroR = new JMenuItem("Dinero Recaudado");
		DineroR.setBackground(Color.WHITE);
		MenuReportes.add(DineroR);

		PagoTrab = new JMenuItem("Pago de los Trabajadores");
		PagoTrab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			}
		});
		PagoTrab.setBackground(Color.WHITE);
		MenuReportes.add(PagoTrab);

		JMenu MenuTrabajadores = new JMenu("Trabajadores");
		MenuTrabajadores.setBackground(Color.WHITE);
		menuBar.add(MenuTrabajadores);

		ListaDeTrabajadores = new JMenuItem("Listado");
		ListaDeTrabajadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = ManejoDeSesion.getInstance().getUsername();
				if (user.equals("gerente2024")) {
					try {
						ListadoDeTrabajadores dialog = new ListadoDeTrabajadores(Principal.this, tiendaC);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setSize(900, 746); 
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(Principal.this, "Error. A este campo solo puede acceder el gerente");
				}
			}
		});
		ListaDeTrabajadores.setBackground(Color.WHITE);
		MenuTrabajadores.add(ListaDeTrabajadores);

		AgregarTrabajador = new JMenuItem("Agregar Trabajador");
		AgregarTrabajador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = ManejoDeSesion.getInstance().getUsername();
				if (user.equals("gerente2024")) {
					try {
						AgregarTrabajador dialog = new AgregarTrabajador(Principal.this, tiendaC);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setSize(900, 746); 
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(Principal.this, "Error. A este campo solo puede acceder el gerente");
				}
			}
		});
		AgregarTrabajador.setBackground(Color.WHITE);
		MenuTrabajadores.add(AgregarTrabajador);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 892, 696);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 64, 866, 149);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		btnButtonTarjetaMadre = new JButton("New button");
		btnButtonTarjetaMadre.setBorder(null);
		btnButtonTarjetaMadre.setFocusable(false);
		btnButtonTarjetaMadre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonTarjetaMadre.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonTarjetaMadre.setBorder(null);
			}
		});
		btnButtonTarjetaMadre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nombredeComponente = "Tarjeta Madre";
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}


			}
		});
		btnButtonTarjetaMadre.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Motherboards/motherboard2.jpg")));
		panel_1.add(btnButtonTarjetaMadre);

		btnButtonMicroprocesador = new JButton("New button");
		btnButtonMicroprocesador.setBorder(null);
		btnButtonMicroprocesador.setFocusable(false);
		btnButtonMicroprocesador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonMicroprocesador.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonMicroprocesador.setBorder(null);


			}
		});
		btnButtonMicroprocesador.setBackground(Color.WHITE);
		btnButtonMicroprocesador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nombredeComponente = "Microprocesador";
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC,  nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonMicroprocesador.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Micro/micro1.jpg")));
		panel_1.add(btnButtonMicroprocesador);

		btnButtonmemoriasRAM = new JButton("New button");
		btnButtonmemoriasRAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Memoria RAM";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC,  nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}

			}
		});
		btnButtonmemoriasRAM.setBorder(null);
		btnButtonmemoriasRAM.setFocusable(false);
		btnButtonmemoriasRAM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonmemoriasRAM.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonmemoriasRAM.setBorder(null);

			}
		});
		btnButtonmemoriasRAM.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/RAM/ram9.jpg")));
		panel_1.add(btnButtonmemoriasRAM);

		btnButtonTarjetasVideo = new JButton("New button");
		btnButtonTarjetasVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Tarjeta de Video";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC,  nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonTarjetasVideo.setBorder(null);
		btnButtonTarjetasVideo.setFocusable(false);
		btnButtonTarjetasVideo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonTarjetasVideo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonTarjetasVideo.setBorder(null);

			}
		});
		btnButtonTarjetasVideo.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Tarjetas/tarjetas3.jpg")));
		panel_1.add(btnButtonTarjetasVideo);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(10, 266, 866, 149);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new GridLayout(0, 4, 0, 0));

		btnButtonDiscosDuros = new JButton("New button");
		btnButtonDiscosDuros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Disco Duro";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC,  nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonDiscosDuros.setBorder(null);
		btnButtonDiscosDuros.setFocusable(false);
		btnButtonDiscosDuros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonDiscosDuros.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonDiscosDuros.setBorder(null);

			}
		});
		btnButtonDiscosDuros.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Discos/discos3.jpg")));
		panel_1_1.add(btnButtonDiscosDuros);

		btnButtonFuente = new JButton("New button");
		btnButtonFuente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Fuente";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC,  nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonFuente.setBorder(null);
		btnButtonFuente.setFocusable(false);
		btnButtonFuente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonFuente.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonFuente.setBorder(null);
			}
		});
		btnButtonFuente.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Fuentes/fuente4.jpg")));
		panel_1_1.add(btnButtonFuente);

		btnButtonMonitores = new JButton("New button");
		btnButtonMonitores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Monitor";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonMonitores.setBorder(null);
		btnButtonMonitores.setFocusable(false);
		btnButtonMonitores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonMonitores.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonMonitores.setBorder(null);
			}
		});
		btnButtonMonitores.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Monitores/monitor7.jpg")));
		panel_1_1.add(btnButtonMonitores);

		btnButtonTeclado = new JButton("New button");
		btnButtonTeclado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Teclado";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC,  nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonTeclado.setBorder(null);
		btnButtonTeclado.setFocusable(false);
		btnButtonTeclado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonTeclado.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonTeclado.setBorder(null);
			}
		});
		btnButtonTeclado.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Teclados/teclados4.jpg")));
		panel_1_1.add(btnButtonTeclado);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(Color.WHITE);
		panel_1_2.setBounds(10, 468, 866, 149);
		panel.add(panel_1_2);
		panel_1_2.setLayout(new GridLayout(0, 4, 0, 0));

		btnButtonRaton = new JButton("New button");
		btnButtonRaton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Ratón";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonRaton.setBorder(null);
		btnButtonRaton.setFocusable(false);
		btnButtonRaton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonRaton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonRaton.setBorder(null);
			}
		});
		btnButtonRaton.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Mouse/mouse6.jpg")));
		panel_1_2.add(btnButtonRaton);

		btnButtonBocina = new JButton("New button");
		btnButtonBocina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Bocina";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonBocina.setBorder(null);
		btnButtonBocina.setFocusable(false);
		btnButtonBocina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonBocina.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonBocina.setBorder(null);
			}
		});
		btnButtonBocina.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Bocinas/bocinas13.jpg")));
		panel_1_2.add(btnButtonBocina);

		btnButtonChasis = new JButton("New button");
		btnButtonChasis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Chasis";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonChasis.setBorder(null);
		btnButtonChasis.setFocusable(false);
		btnButtonChasis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonChasis.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonChasis.setBorder(null);
			}
		});
		btnButtonChasis.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Chasis/chasis3.jpg")));
		panel_1_2.add(btnButtonChasis);

		btnButtonOtro = new JButton("New button");
		btnButtonOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombredeComponente = "Adaptador";
				try {
					VenderPieza dialog = new VenderPieza(Principal.this, tiendaC, nombredeComponente);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Throwable e3) {
					e3.printStackTrace();
				}
			}
		});
		btnButtonOtro.setBorder(null);
		btnButtonOtro.setFocusable(false);
		btnButtonOtro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnButtonOtro.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 235)));
			}
			public void mouseExited(MouseEvent e) {
				btnButtonOtro.setBorder(null);
			}
		});
		btnButtonOtro.setIcon(new ImageIcon(Principal.class.getResource("/gui/images/Otros/otros5.jpg")));
		panel_1_2.add(btnButtonOtro);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 223, 866, 33);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Tarjetas Madres");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Microprocesadores");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Memorias RAM");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Tarjetas de Video");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1_3);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(10, 425, 866, 33);
		panel.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Discos Duros");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Fuentes");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Monitores");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Teclados");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_1.add(lblNewLabel_2_3);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(Color.WHITE);
		panel_2_2.setBounds(10, 627, 866, 33);
		panel.add(panel_2_2);
		panel_2_2.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblNewLabel_2_4 = new JLabel("Ratones");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_5 = new JLabel("Bocinas");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_6 = new JLabel("Chasis");
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2.add(lblNewLabel_2_6);

		JLabel lblNewLabel_2_7 = new JLabel("Otros");
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_2.add(lblNewLabel_2_7);

		JLabel lblNewLabel = new JLabel("Productos en Venta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(10, 10, 293, 44);
		panel.add(lblNewLabel);
	}

	private void inicializarDatos() {
		InicializacionDeDatos.crearTrabajadores(tiendaC);
		InicializacionDeDatos.crearGerentes(tiendaC);
		inicializaciones.InicializacionDeDatos.llamarInicializaciones(tiendaC);
	}
}
