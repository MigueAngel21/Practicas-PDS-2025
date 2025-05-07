package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.pds.controlador.Controlador;
import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.LibreriaCursos;

public class SeleccionCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Controlador controlador = Controlador.getUnicaInstancia();
	private String SelectedEstrategia = "Secuencial";

	/**
	 * Create the frame.
	 */
	public SeleccionCurso(LibreriaCursos libreriaCursos) {
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
		
	    
		// 🔹 Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout((LayoutManager) new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);

        // 🔹 Panel superior con logo y nombre de la app
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.WHITE);

        JLabel logo = new JLabel();
        try {
            URL imgURL = SeleccionCurso.class.getResource("/umu/pds/resources/musculitos.png");
            BufferedImage img = ImageIO.read(imgURL);
            logo.setIcon(new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            logo.setText("LOGO");
        }

        JLabel nombreApp = new JLabel("Duopingo");
        nombreApp.setFont(new Font("Arial", Font.BOLD, 20));
        nombreApp.setForeground(new Color(0, 150, 0)); // Verde Duolingo
        panelSuperior.add(logo);
        panelSuperior.add(nombreApp);

        // 🔹 Texto centrado "Quiero aprender:"
        JLabel titulo = new JLabel("Quiero aprender sobre ...  :");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(Color.BLACK);

        // 🔹 Panel de cursos (centrado con GridLayout)
        JPanel panelCursos = new JPanel(new GridLayout(1, 2, 20, 10));
        panelCursos.setBackground(Color.WHITE);

        // por cada curso en la libreria de cursos
       
        // 🟢 Primer marco: Imagen + Nombre del curso + Botón
        /*JPanel curso1 = new JPanel();
        curso1.setLayout(new BorderLayout());
        curso1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        curso1.setBackground(Color.WHITE);

        JLabel imgLabel1 = new JLabel("", SwingConstants.CENTER);
        try {
            URL imgURL = SeleccionCurso.class.getResource("/umu/pds/resources/furbo.jpg");
            BufferedImage img = ImageIO.read(imgURL);
            imgLabel1.setIcon(new ImageIcon(img.getScaledInstance(320, 200, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            imgLabel1.setText("Imagen no encontrada");
        }

        JLabel texto1 = new JLabel("Fútbol", SwingConstants.CENTER);
        texto1.setFont(new Font("Arial", Font.BOLD, 20));
        texto1.setForeground(Color.BLACK);
        JButton boton1 = new JButton("Seleccionar Curso");
        boton1.setPreferredSize(new Dimension(60, 40));
        boton1.setBackground(new Color(30, 144, 255));
        boton1.setForeground(Color.WHITE);
        boton1.setFocusPainted(false);
		boton1.addActionListener(e -> {
			// ocultar la ventana de selección de curso
			   this.setVisible(false);
			// abrir ventana de curso de fútbol
			   VentanaPrincipal vp = new VentanaPrincipal();
			   vp.setVisible(true);
			
		});

        JPanel panelBoton1 = new JPanel(new BorderLayout());
        panelBoton1.setBackground(Color.WHITE);
    //    panelBoton1.add(texto1, BorderLayout.NORTH);
        panelBoton1.add(boton1, BorderLayout.SOUTH);*/

    //    curso1.add(imgLabel1, BorderLayout.CENTER);
    //    curso1.add(panelBoton1, BorderLayout.SOUTH);

        for (EspecificacionCurso curso : libreriaCursos.getCursos()) {
        	JButton boton = new JButton("Seleccionar Curso");
        	boton.setPreferredSize(new Dimension(60, 40));
        	boton.setBackground(new Color(30, 144, 255));
        	boton.setForeground(Color.WHITE);
        	boton.setFocusPainted(false);
			boton.addActionListener(e -> {
				// ocultar la ventana de selección de curso
				this.setVisible(false);
				controlador.setCursoActual(curso,SelectedEstrategia);
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);

			});
        	
        	JPanel cursoPanel = new JPanel();
        	cursoPanel.setLayout(new BorderLayout());
        	cursoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        	cursoPanel.setBackground(Color.WHITE);
        	
        	JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
			try {
				//URL imgURL = SeleccionCurso.class.getResource("/umu/pds/resources/furbo.jpg");
				System.out.println(curso.getImagen());
				URL imgURL = SeleccionCurso.class.getResource(curso.getImagen());
				BufferedImage img = ImageIO.read(imgURL);
				imgLabel.setIcon(new ImageIcon(img.getScaledInstance(320, 200, Image.SCALE_SMOOTH)));
			} catch (IOException | NullPointerException e) {
				imgLabel.setText("Imagen no encontrada");
			}
			
			JLabel texto = new JLabel(curso.getNombre(), SwingConstants.CENTER);
			texto.setFont(new Font("Arial", Font.BOLD, 20));
			texto.setForeground(Color.BLACK);
			
			JPanel panelBoton = new JPanel(new BorderLayout());
			panelBoton.setBackground(Color.WHITE);
			
			panelBoton.add(texto, BorderLayout.NORTH);
			panelBoton.add(boton);
			cursoPanel.add(imgLabel, BorderLayout.CENTER);
			cursoPanel.add(panelBoton, BorderLayout.SOUTH);
			panelCursos.add(cursoPanel);
		}
			
 
        // 🔵 Segundo marco: Imagen + Nombre del curso + Botón
        JPanel curso2 = new JPanel();
        curso2.setLayout(new BorderLayout());
        curso2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        curso2.setBackground(Color.WHITE);

        JLabel imgLabel2 = new JLabel("", SwingConstants.CENTER);
        try {
            URL imgURL = SeleccionCurso.class.getResource("/umu/pds/resources/simbolo +.png");
            BufferedImage img = ImageIO.read(imgURL);
            imgLabel2.setIcon(new ImageIcon(img.getScaledInstance(90, 70, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            imgLabel2.setText("Imagen no encontrada");
        }

        JLabel texto2 = new JLabel("Crear Nuevo Curso", SwingConstants.CENTER);
        texto2.setFont(new Font("Arial", Font.BOLD, 20));
        texto2.setForeground(Color.BLACK);
        JButton boton2 = new JButton("Crear");
        boton2.setPreferredSize(new Dimension(60, 40));
        boton2.setBackground(new Color(30, 144, 255));
        boton2.setForeground(Color.WHITE);
        boton2.setFocusPainted(false);

        JPanel panelBoton2 = new JPanel(new BorderLayout());
        panelBoton2.setBackground(Color.WHITE);
        panelBoton2.add(texto2, BorderLayout.NORTH);
        panelBoton2.add(boton2, BorderLayout.SOUTH);

        curso2.add(imgLabel2, BorderLayout.CENTER);
        curso2.add(panelBoton2, BorderLayout.SOUTH);

        // Agregar cursos al panel de cursos
        panelCursos.add(curso2);

        // Agregar elementos al panel principal
        panelPrincipal.add(panelSuperior);
        
        JLabel lblNewLabel = new JLabel("                                                                                                                                                                                                                                  ");
        panelSuperior.add(lblNewLabel);
        
        JLabel lblEstrategiaDeAprendizaje = new JLabel("Estrategia de Aprendizaje:");
		lblEstrategiaDeAprendizaje.setForeground(Color.BLACK);
		lblEstrategiaDeAprendizaje.setFont(new Font("Dialog", Font.BOLD, 19));
        panelSuperior.add(lblEstrategiaDeAprendizaje);
        
        JComboBox<String> comboBoxEstrategia = new JComboBox();
		comboBoxEstrategia.setBackground(new Color(255, 255, 255));
        panelSuperior.add(comboBoxEstrategia);
		comboBoxEstrategia.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		comboBoxEstrategia.setForeground(new Color(0, 0, 0));
        panelPrincipal.add(Box.createVerticalStrut(10)); // Espacio
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createVerticalStrut(10)); // Espacio
        panelPrincipal.add(panelCursos);
        
       controlador.getEstrategiasAprendizaje().forEach(e -> {
			comboBoxEstrategia.addItem(e);
		});
		
		comboBoxEstrategia.addActionListener(ev -> {
			String estrategia = (String) comboBoxEstrategia.getSelectedItem();
			if (!estrategia.isBlank()) {
				SelectedEstrategia = estrategia;
			} 
		});

        // Agregar todo a la ventana
        getContentPane().add(panelPrincipal);

        
		this.setVisible(true);
	}

}
