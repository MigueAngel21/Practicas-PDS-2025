package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.pds.controlador.Controlador;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador = Controlador.getUnicaInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
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
		//getContentPane().setLayout(new BorderLayout(0, 0));
		
		//HACER IMPLEMENTACION
		// 🔹 Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // 🔸 Panel Izquierdo (Logo + Botones)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(150, this.getHeight()));
        panelIzquierdo.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Duopingo");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.GREEN);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnCursos = new JButton(" Cursos");
        btnCursos.setForeground(Color.BLACK);
        btnCursos.setBackground(Color.WHITE);
        btnCursos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCursos.addActionListener(e -> {
			// ocultar la ventana de selección de curso
			   this.setVisible(false);
			// abrir ventana de curso de fútbol
			   SeleccionCurso sc = new SeleccionCurso(controlador.getLibreriaCursos());
			   sc.setVisible(true);
			
		});
        JButton btnPerfil = new JButton(" Perfil");
        btnPerfil.setForeground(Color.BLACK);
        btnPerfil.setBackground(Color.WHITE);
        
        btnPerfil.addActionListener(e -> {
        	            // ocultar la ventana de selección de curso
        	              this.setVisible(false);
        	              // abrir ventana de perfil
        	              Perfil perfil = new Perfil(controlador.getEstadisticas(), controlador.getUsername(), this);
        	              perfil.setVisible(true);
          });
        
        btnPerfil.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JButton btnMas = new JButton(" Más");
        btnMas.setForeground(Color.BLACK);
        btnMas.setBackground(Color.WHITE);
        btnMas.setFont(new Font("Tahoma", Font.PLAIN, 18));

        btnCursos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/libros.png")));
        btnPerfil.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/iconoPerfil.png")));
        btnMas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/tres-puntos.png")));

        btnCursos.setHorizontalAlignment(SwingConstants.LEFT);
        btnPerfil.setHorizontalAlignment(SwingConstants.LEFT);
        btnMas.setHorizontalAlignment(SwingConstants.LEFT);

        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(titulo);
        panelIzquierdo.add(Box.createVerticalStrut(80));
        panelIzquierdo.add(btnCursos);
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setFont(new Font("Dialog", Font.PLAIN, 30));
        panelIzquierdo.add(verticalStrut);
        panelIzquierdo.add(btnPerfil);
        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(btnMas);

        // 🔸 Panel Central (Texto + Botón)
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);

        JLabel labelTexto = new JLabel("Comenzar Curso ...");
        labelTexto.setFont(new Font("Arial", Font.BOLD, 30));
        labelTexto.setForeground(Color.BLACK);
        labelTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnEmpezar = new JButton();
        btnEmpezar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/flecha-correcta.png")));
        btnEmpezar.setBorderPainted(false);
        btnEmpezar.setContentAreaFilled(false);
        btnEmpezar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEmpezar.addActionListener(e -> {
			// ocultar la ventana de selección de curso
			   this.setVisible(false);
			// abrir ventana de curso de fútbol
			   Preguntas p = new Preguntas();
			   p.setVisible(true);
		});

        panelCentral.add(Box.createVerticalStrut(120));
        panelCentral.add(labelTexto);
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(btnEmpezar);

        // 🔸 Panel Derecho (Iconos + Números)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setPreferredSize(new Dimension(100, this.getHeight()));
        panelDerecho.setBackground(Color.WHITE);

        JLabel puntos = new JLabel(" 200");
        puntos.setFont(new Font("Arial", Font.BOLD, 20));
        puntos.setForeground(Color.BLUE);
        puntos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/joya-puntos.png")));

        JLabel fuego = new JLabel(" 2");
        fuego.setFont(new Font("Arial", Font.BOLD, 20));
        fuego.setForeground(Color.ORANGE);
        fuego.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/fuego.png")));

        JLabel corazones = new JLabel(" ∞");
        corazones.setFont(new Font("Arial", Font.BOLD, 30));
        corazones.setForeground(Color.RED);
        corazones.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/pds/resources/corazon.png")));

        panelDerecho.add(Box.createVerticalStrut(140));
        panelDerecho.add(puntos);
        panelDerecho.add(Box.createVerticalStrut(20));
        panelDerecho.add(fuego);
        panelDerecho.add(Box.createVerticalStrut(20));
        panelDerecho.add(corazones);

        // 🔹 Agregar paneles al Frame
        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        getContentPane().add(panelPrincipal);
        
		this.setVisible(true);
	}

}
