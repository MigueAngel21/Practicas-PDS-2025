package umu.pds.interfaz;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

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
import umu.pds.dominio.Curso;
import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.Estadistica;
import umu.pds.dominio.Flashcard;
import umu.pds.dominio.LibreriaCursos;
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;
import umu.pds.dominio.Usuario;



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

	
	private void añadirDatosDePrueba() {
		Pregunta p1 = new MultipleChoice("Completa la frase de Luis Rubiales : ","Ella me dijo, eres un crack, y yo le dije, _________?", 3, 1, "un abrazo", "un piquito", "un apreton de manos");
		Pregunta p2 = new MultipleChoice("Responde a la siguiente pregunta :","¿Cuántas champions league tiene el real madrid?", 3, 1, "15", "14", "13");
		Pregunta p3 = new MultipleChoice("Cuál es el escudo del real madrird", "", 3, 0, true, "/umu/pds/resources/escudoRealMadrid.png", "/umu/pds/resources/escudoAtleticoMadrid.jpg", "/umu/pds/resources/escudoRayoVall.jpg"); 
		Pregunta p4 = new MultipleChoice("Completa la frase de Luis Aragones :", "Ganar, ganar y volver a ganar, y ganar y ganar, y ganar, y eso _______", 3, 1, "es el futbol, señores", "es la vida", "es el futbol, chavales");
		Pregunta p5 = new MultipleChoice("Responde a la siguiente pregunta :","¿Quién es el jugador de la selección española con mas partidos jugados?:", 3, 1, "Iker Casillas", "Sergio Ramos", "Andres Iniesta");
		Pregunta p6 = new MultipleChoice("¿Cuál de los tres equipos pertenece a la comunidad valenciana?","", 3, 1, true, "/umu/pds/resources/escudoVillareal.png", "/umu/pds/resources/escudoGirona.png", "/umu/pds/resources/escudoMurcia.png");
		Pregunta p7 = new MultipleChoice("Completa la frase de Josep Mourinho : ", "Si te gusta la presión, estás en el club adecuado. Si no, vete _____", 3, 2, "a tomar viento", "al banquillo", "al circo");
		Pregunta p8 = new MultipleChoice("Responde a la siguiente pregunta :","¿Cuántas eurocopas ha ganado la selección española de futbol?", 3, 1, "3", "4", "5");
		Pregunta p9 = new MultipleChoice("¿Cuál de los siguientes estadios de futbol es el Santiago Bernabeu?","", 3, 2, true, "/umu/pds/resources/estadioDaLuz.jpg", "/umu/pds/resources/estadioWembley.jpg", "/umu/pds/resources/estadioBernabeu.jpg");
		Pregunta p10 = new MultipleChoice("Completa la frase de Vicente del Bosque :", "En el fútbol no siempre ganan los mejores, sino los que mejor _____", 3, 1, "compiten", "pierden", "ganan");
		Pregunta p11 = new MultipleChoice("Responde a la siguiente pregunta :","¿Cuáles de estos jugadores no ha ganado un balón de oro?", 3, 1, "Luka Modric", "Karim Benzema", "Dani Carvajal");
		Pregunta p12 = new MultipleChoice("¿Cuál de estos jugadores ha jugado en el Real Madrid?","", 3, 2, true, "/umu/pds/resources/Havertz.jpg", "/umu/pds/resources/Carvajal.jpg", "/umu/pds/resources/AlexanderArnold.jpg");
		Pregunta pX = new Flashcard("¿Quién es el jugador con más balones de oro?", "Lionel Messi");
		List<Pregunta> preguntas = new ArrayList<Pregunta>();
		preguntas.add(p1);
		preguntas.add(pX);
		preguntas.add(p2);
		preguntas.add(p3);
		preguntas.add(p4);
		preguntas.add(p5);
		preguntas.add(p6);
		preguntas.add(p7);
		preguntas.add(p8);
		preguntas.add(p9);
		preguntas.add(p10);
		preguntas.add(p11);
		preguntas.add(p12);
		EspecificacionCurso especificacion = new EspecificacionCurso("Curso de fútbol", "Aprende sobre fútbol", preguntas);
		LibreriaCursos.INSTANCE.addCurso(especificacion);
		
		Usuario paco = new Usuario("paco", "paco@duopingo.com", "1234", 18);
		controlador.setUsuarioActual(paco);
		
		Curso curso = new Curso(especificacion);
		controlador.setCursoActual(curso);
		
		Estadistica estadistica = new Estadistica(15,2);
		Progreso progreso = new Progreso(5,2,7,curso);
		
		estadistica.addProgreso(progreso);
		paco.updateEstadisticas(estadistica);
		
	}
	
	/**
	 * Create the application.
	 */
	public InicioApp() {
		añadirDatosDePrueba();
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
