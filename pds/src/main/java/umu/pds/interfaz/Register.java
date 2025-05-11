package umu.pds.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

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

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Controlador controlador = Controlador.getUnicaInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
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
		//cambiar icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));
		
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
		//cambiar icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));
		
		// Crear el panel principal con BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // Agregar el título
        JLabel titleLabel = new JLabel("Crea tu perfil");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        // Campo de edad
        JTextField ageField = new JTextField("Edad");
        ageField.setPreferredSize(new Dimension(250, 40));
        ageField.setMaximumSize(new Dimension(250, 40));
        panel.add(ageField);

        JLabel ageInfo = new JLabel("Agregar tu edad te garantiza una experiencia adaptada en Duopingo.");
        ageInfo.setFont(new Font("Arial", Font.PLAIN, 10));
        ageInfo.setForeground(Color.GRAY);
        ageInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(ageInfo);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado

        // Campo de nombre opcional
        JTextField nameField = new JTextField("Nombre");
        nameField.setPreferredSize(new Dimension(250, 40));
        nameField.setMaximumSize(new Dimension(250, 40));
        panel.add(nameField);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado

        // Campo de correo
        JTextField emailField = new JTextField("Correo");
        emailField.setPreferredSize(new Dimension(250, 40));
        emailField.setMaximumSize(new Dimension(250, 40));
        panel.add(emailField);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado

        // Campo de contraseña
        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(250, 40));
        passField.setMaximumSize(new Dimension(250, 40));
        panel.add(passField);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        // Botón de crear cuenta
        JButton registerButton = new JButton("CREAR CUENTA");
        registerButton.setBackground(new Color(33, 150, 243)); // Azul de Duolingo
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> {
        	// ocultar la ventana de registro
        	controlador.registrarUsuario(nameField.getText(), emailField.getText(), passField.getPassword(), ageField.getText());
        	this.setVisible(false);
			// abrir la ventana principal
        	InicioApp window = new InicioApp();
			window.frame.setVisible(true);
		});
        panel.add(registerButton);

        panel.add(Box.createRigidArea(new Dimension(20, 20))); // Espaciado

        // Línea separadora
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(250, 10));
        panel.add(separator);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        // Botón de Facebook
        JButton fbButton = new JButton("VOLVER");
        fbButton.setPreferredSize(new Dimension(70, 40));
        JPanel socialPanel = new JPanel();
        socialPanel.add(fbButton);
        socialPanel.setBackground(Color.WHITE);
        fbButton.addActionListener(e -> {
        	// ocultar la ventana de registro
        	this.setVisible(false);
			// abrir la ventana principal
        	InicioApp window = new InicioApp();
			window.frame.setVisible(true);
			
		});
        panel.add(socialPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        // Términos y condiciones
        JLabel termsLabel = new JLabel("Al registrarte en Duolingo, aceptas nuestros Términos y Política de privacidad.");
        termsLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        termsLabel.setForeground(Color.GRAY);
        termsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(termsLabel);

        this.add(panel);
		
		
		this.setVisible(true);
	}

}
