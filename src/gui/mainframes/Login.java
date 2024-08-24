package gui.mainframes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import componentesVisuales.AvatarCircular;
import componentesVisuales.BotonAnimacion;
import componentesVisuales.Linea;

public class Login extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel panelGeneral;
	private JPasswordField passwordField;
	private JTextField userField;
	private static String usuario = "informatica2024";
	private static String pass = "1234";
	private static boolean visible = false;
	final interfaz.Principal principal = new interfaz.Principal(null, null);
	
	public Login() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 536);
		panelGeneral = new JPanel();
		panelGeneral.setBackground(Color.WHITE);
		panelGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelGeneral);
		panelGeneral.setLayout(null);

		AvatarCircular avatarCircular = new AvatarCircular();
		avatarCircular.setAvatar(new ImageIcon(Login.class.getResource("/gui/icons/usuario.jpg")));
		avatarCircular.setBounds(159, 44, 156, 133);
		panelGeneral.add(avatarCircular);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(75, 351, 285, 16);
		panelGeneral.add(passwordField);

		final BotonAnimacion btnIniciar = new BotonAnimacion();
		btnIniciar.setFocusable(false);
		btnIniciar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnIniciar.setBackground(new Color(30, 144, 255));
			}
			public void mouseExited(MouseEvent e) {
				btnIniciar.setBackground(new Color(135, 206, 250));
			}
		});
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userField.getText().isEmpty() && !passwordField.getText().isEmpty()){
					if(usuario.equals(userField.getText()) && pass.equals(passwordField.getText())){
							setVisible(false);
							principal.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrecta.", "ERROR", 0);
				}
				else
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "ERROR", 2);
			}
		});
		btnIniciar.setBackground(new Color(135, 206, 250));
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIniciar.setText("Iniciar Sesi\u00F3n");
		btnIniciar.setColorEfecto(new Color(0, 255, 255));
		btnIniciar.setBounds(159, 443, 156, 34);
		panelGeneral.add(btnIniciar);

		Linea lineaPass = new Linea(0, 0);
		lineaPass.setGrosor(5);
		lineaPass.setBorder(new LineBorder(new Color(0, 0, 0)));
		lineaPass.setBackground(new Color(0, 0, 0));
		lineaPass.setBounds(75, 371, 339, 1);
		panelGeneral.add(lineaPass);

		Linea lineaUser = new Linea(0, 0);
		lineaUser.setGrosor(5);
		lineaUser.setBorder(new LineBorder(new Color(0, 0, 0)));
		lineaUser.setBackground(Color.BLACK);
		lineaUser.setBounds(75, 291, 339, 1);
		panelGeneral.add(lineaUser);

		JLabel lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(130, 317, 129, 23);
		panelGeneral.add(lblPass);

		JLabel labelUser = new JLabel("Nombre de Usuario:");
		labelUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelUser.setBounds(130, 237, 162, 23);
		panelGeneral.add(labelUser);

		userField = new JTextField();
		userField.setBackground(new Color(255, 255, 255));
		userField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		userField.setBorder(null);
		userField.setBounds(75, 271, 285, 16);
		panelGeneral.add(userField);
		userField.setColumns(10);

		JLabel iconUser = new JLabel("");
		iconUser.setIcon(new ImageIcon(Login.class.getResource("/gui/icons/usuarios3.png")));
		iconUser.setBounds(73, 233, 47, 27);
		panelGeneral.add(iconUser);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/gui/icons/pass2.png")));
		label.setBounds(85, 313, 40, 27);
		panelGeneral.add(label);
		
		final JButton btnHacerVisible = new JButton("");
		btnHacerVisible.setBorder(null);
		btnHacerVisible.setFocusable(false);
		btnHacerVisible.setIcon(new ImageIcon(Login.class.getResource("/gui/icons/mostrar pass.png")));
		btnHacerVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!visible){
				passwordField.setEchoChar((char)0);
				visible = true;
				btnHacerVisible.setIcon(new ImageIcon(Login.class.getResource("/gui/icons/ocultar pass.png")));
				}
				else {
					passwordField.setEchoChar('*');
					visible = false;
					btnHacerVisible.setIcon(new ImageIcon(Login.class.getResource("/gui/icons/mostrar pass.png")));
				}
			}
			
		});

		btnHacerVisible.setBackground(new Color(255, 255, 255));
		btnHacerVisible.setBounds(385, 344, 29, 23);
		panelGeneral.add(btnHacerVisible);

		setLocationRelativeTo(null);
	}
}
