package umu.pds.interfaz;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.pds.controlador.Controlador;



public class InicioApp {

	JFrame frame;
	Controlador controlador = Controlador.getUnicaInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioApp window = new InicioApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 */
	public InicioApp() {
		controlador.cargarCursosJSON("cursos");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		
		frame = new JFrame();
		frame.setBounds(420, 160, 732, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("DUOPINGO");
		frame.setResizable(false);
		//cambiar icono de la ventana
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));
		
		 
		 // Crear el panel principal sin fondo blanco
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("¡La forma divertida, efectiva y gratis de aprender!");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(Color.BLACK);
        panel.add(Box.createVerticalStrut(20));
        panel.add(titulo);

        // Cargar imagen desde resources y redimensionarla
        ImageIcon imagenMascota = new ImageIcon(getClass().getResource("/umu/pds/resources/mascota.jpg"));
        Image img = imagenMascota.getImage().getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JLabel labelImagen = new JLabel(new ImageIcon(img));
        labelImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(labelImagen);

        // Botón "EMPEZAR AHORA"
        JButton btnEmpezar = new JButton("EMPEZAR AHORA");
        btnEmpezar.setBackground(new Color(76, 175, 80)); // Verde
        btnEmpezar.setForeground(Color.WHITE);
        btnEmpezar.setFocusPainted(false);
        btnEmpezar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEmpezar.setMaximumSize(new Dimension(180, 40));
        btnEmpezar.addActionListener(e -> {
			// abrir la ventana principal
			 Register register = new Register();
			 register.setVisible(true);
			// ocultar la ventana de login
			frame.setVisible(false);
		});
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnEmpezar);

        // Botón "YA TENGO UNA CUENTA"
        JButton btnCuenta = new JButton("YA TENGO UNA CUENTA");
        btnCuenta.setBackground(Color.GRAY);
        btnCuenta.setForeground(Color.WHITE);
        btnCuenta.setFocusPainted(false);
        btnCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCuenta.setMaximumSize(new Dimension(180, 40));
        btnCuenta.addActionListener(e -> {
			// abrir la ventana principal
			 Login login = new Login();
			 login.setVisible(true);
			// ocultar la ventana de login
			frame.setVisible(false);
		});
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnCuenta);

        // Agregar el panel a la ventana
        frame.add(panel);
        frame.setVisible(true);
	}

}
