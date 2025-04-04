package umu.pds.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.pds.controlador.Controlador;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador = Controlador.getUnicaInstancia();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setBounds(420, 160, 732, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DUOPINGO");
		setResizable(false);
		// cambiar icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));

		// Crear panel principal con BoxLayout
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);

		// Estilo de los campos de entrada
		Dimension fieldSize = new Dimension(300, 40);

		// Título
		JLabel titulo = new JLabel("Ingresar");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		titulo.setForeground(Color.BLACK);
		panel.add(Box.createVerticalStrut(40));
		panel.add(titulo);

		// Campo de usuario
		JTextField txtUsuario = new JTextField("  Correo");
		txtUsuario.setPreferredSize(fieldSize);
		txtUsuario.setMaximumSize(fieldSize);
		txtUsuario.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		panel.add(Box.createVerticalStrut(15));
		panel.add(txtUsuario);

		// Campo de contraseña
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(fieldSize);
		txtPassword.setMaximumSize(fieldSize);
		txtPassword.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		panel.add(Box.createVerticalStrut(10));
		panel.add(txtPassword);

		// Botón de Ingresar
		JButton btnLogin = new JButton("INGRESAR");
		btnLogin.setBackground(new Color(30, 144, 255)); // Azul brillante
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFocusPainted(false);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setMaximumSize(fieldSize);
		panel.add(Box.createVerticalStrut(20));

		btnLogin.addActionListener(e -> {
			String correo = txtUsuario.getText();
			String contrasena = new String(txtPassword.getPassword());
			if (UIutils.isAnyFieldBlank(correo, contrasena)) {
				UIutils.showErrorDialog("Por favor, rellene todos los campos");
				return;
			}
			boolean exito = controlador.InciarSesion(correo, contrasena);
			if (exito) {
				// ocultar la ventana de registro
				this.setVisible(false);
				// abrir la ventana principal
				SeleccionCurso window = new SeleccionCurso();
				window.setVisible(true);
			}else {
                UIutils.showErrorDialog("Usuario o contraseña incorrecto");
			}
		});

		panel.add(btnLogin);

		// Separador con línea
		panel.add(Box.createVerticalStrut(20));
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setMaximumSize(new Dimension(300, 10));
		panel.add(separator);

		// Botón GOOGLE
		JButton btnGoogle = new JButton(" GOOGLE");
		btnGoogle.setBackground(Color.ORANGE);
		btnGoogle.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnGoogle.setFocusPainted(false);
		btnGoogle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGoogle.setMaximumSize(fieldSize);
		panel.add(Box.createVerticalStrut(15));
		panel.add(btnGoogle);

		// Botón Vovler
		JButton btnVolver = new JButton(" VOLVER");
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnVolver.setFocusPainted(false);
		btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnVolver.setMaximumSize(fieldSize);
		panel.add(Box.createVerticalStrut(10));
		btnVolver.addActionListener(e -> {
			// ocultar la ventana de registro
			this.setVisible(false);
			// abrir la ventana principal
			InicioApp window = new InicioApp();
			window.frame.setVisible(true);

		});
		panel.add(btnVolver);

		// Agregar el panel a la ventana
		this.add(panel);

		this.setVisible(true);
	}

}
