package umu.pds.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;

public class MultipleChoiceUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador = Controlador.getUnicaInstancia();
	private int respuestaSeleccionada = -1;

	/**
	 * Create the frame.
	 */
	public MultipleChoiceUI(MultipleChoice pregunta, int numPregunta) {

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


        // Configuración de la ventana
		setBounds(420, 160, 732, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pregunta " + numPregunta);
		setResizable(false);
		// cambiar icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));

		// HACER IMPLEMENTACIION
		/*
		 * JPanel panel = new JPanel(); getContentPane().add(panel,
		 * BorderLayout.CENTER); panel.setBackground(Color.WHITE);
		 * 
		 * 
		 * JButton continuar = new JButton("Continuar"); continuar.setBackground(new
		 * Color(0, 255, 0)); continuar.setForeground(new Color(255, 255, 255));
		 * continuar.setFont(new Font("Arial", Font.BOLD, 18));
		 * continuar.addActionListener(e -> { // ocultar la ventana de selección de
		 * curso this.setVisible(false); // abrir ventana de curso de fútbol Puntos p =
		 * new Puntos(); p.setVisible(true); }); panel.add(continuar);
		 */
		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		// Pregunta en negrita
		JLabel lblPregunta = new JLabel(pregunta.getEnunciado());
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		if(pregunta.contieneImagen()) {
	        lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		lblPregunta.setForeground(Color.BLACK);

		// Frase con hueco para completar
		JLabel lblFrase = new JLabel(pregunta.getPregunta());
		lblFrase.setFont(new Font("Arial", Font.PLAIN, 14));
		lblFrase.setForeground(Color.BLACK);

		// Panel para opciones
		JPanel panelOpciones = new JPanel();
		if (!pregunta.contieneImagen()) {
			panelOpciones.setLayout(new GridLayout(3, 1, 5, 5));
			panelOpciones.setBackground(Color.WHITE);
		}else {
			panelOpciones.setLayout(new GridLayout(1, 3, 10, 10));
			panelOpciones.setBackground(Color.WHITE);
		}

		JButton opciones[] = new JButton[pregunta.getNumOpciones()];
		if (!pregunta.contieneImagen()) {
			Dimension botonSize = new Dimension(100, 30);
			// Botones de opción (más pequeños)
			for (int i = 0; i < pregunta.getNumOpciones(); i++) {
				opciones[i] = new JButton((i + 1) + ". " + pregunta.getOpciones().get(i));
				opciones[i].setPreferredSize(botonSize);
				panelOpciones.add(opciones[i]);
				// Estilo
				opciones[i].setFont(new Font("Arial", Font.PLAIN, 14));
				opciones[i].setBackground(Color.ORANGE);
				opciones[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
				opciones[i].setFocusPainted(false);
				panelOpciones.add(opciones[i]);
			}
		} else {
			for (int i = 0; i < pregunta.getNumOpciones(); i++) {
				try {
					URL imgURL = Preguntas.class.getResource(pregunta.getOpciones().get(i));
					// Si no se encuentra la imagen en el recurso, intentar cargarla desde una URL
					if (imgURL == null) {
						imgURL = new URL(pregunta.getOpciones().get(i));
					}
					BufferedImage img = ImageIO.read(imgURL);
					opciones[i] = new JButton();
					opciones[i].setIcon(new ImageIcon(img.getScaledInstance(160, 150, Image.SCALE_SMOOTH)));
				} catch (Exception e) {
					opciones[i] = new JButton("Imagen no encontrada");
				}
				// Estilo
				opciones[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
				opciones[i].setBackground(Color.WHITE);
				opciones[i].setFocusPainted(false);
				panelOpciones.add(opciones[i]);
			}
		}

		// Acción para alternar selección
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton botonPresionado = (JButton) e.getSource();

				// Guardar la respuesta seleccionada
				for (int i = 0; i < opciones.length; i++) {
					if (opciones[i] == botonPresionado) {
						respuestaSeleccionada = i;
						break;
					}
				}
				// Alternar entre habilitar y deshabilitar los botones
				for (JButton boton : opciones) {
					boton.setEnabled(boton == botonPresionado);
				}
			}
		};

		for (JButton boton : opciones) {
			boton.addActionListener(actionListener);
		}

		// Botón de continuar
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
		btnContinuar.setBackground(new Color(0, 200, 0));
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setBorderPainted(false);
		btnContinuar.setFocusPainted(false);
		btnContinuar.setMaximumSize(new Dimension(120, 35));
		btnContinuar.addActionListener(e -> {
			if (respuestaSeleccionada == -1) {
				UIutils.showErrorDialog("Debes seleccionar una respuesta");
				return;
			}
			this.setVisible(false);
			// conseguir respuesta seleccion
			controlador.responderPregunta(respuestaSeleccionada);
			Pregunta p = controlador.getSiguientePregunta();
			if (p == null) {
				// Abrir ventana de puntuación
				Puntos puntos = new Puntos();
				puntos.setVisible(true);
				return;
			}
			JFrame sig = UIutils.creaPreguntaUI(p, numPregunta + 1);
			sig.setVisible(true);
		});

		if (controlador.getCursoActual().getEstrategia().getClass().getSimpleName().equals("Multijugador")) {
		    // Crear un panel para las puntuaciones
		    JPanel panelPuntuaciones = new JPanel();
		    panelPuntuaciones.setLayout(new GridLayout(1, 2, 10, 0)); // Espaciado entre los JLabels
		    panelPuntuaciones.setBackground(Color.WHITE);
		    panelPuntuaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		    // Crear y configurar los JLabels para las puntuaciones
		    JLabel lblPuntuacionA = new JLabel("Jugador A: " + controlador.getCursoActual().getProgreso().getPuntosA());
		    lblPuntuacionA.setFont(new Font("Arial", Font.BOLD, 14));
		    lblPuntuacionA.setForeground(Color.BLACK);

		    JLabel lblPuntuacionB = new JLabel("Jugador B: " + controlador.getCursoActual().getProgreso().getPuntosB());
		    lblPuntuacionB.setFont(new Font("Arial", Font.BOLD, 14));
		    lblPuntuacionB.setForeground(Color.BLACK);

		    // Agregar los JLabels al panel de puntuaciones
		    panelPuntuaciones.add(lblPuntuacionA);
		    panelPuntuaciones.add(lblPuntuacionB);
		    panelPrincipal.add(panelPuntuaciones); // Agregar el panel de puntuaciones al panel principal
		   }

		    // Agregar el panel de puntuaciones al contenedor principal, justo encima de panelPrincipal
		
		// Agregar componentes al panel principal
		if (!pregunta.contieneImagen()) {
			panelPrincipal.add(lblPregunta);
			panelPrincipal.add(Box.createVerticalStrut(10));
			panelPrincipal.add(lblFrase);
			panelPrincipal.add(Box.createVerticalStrut(10));
			panelPrincipal.add(panelOpciones);
			panelPrincipal.add(Box.createVerticalStrut(15));
			panelPrincipal.add(btnContinuar);
		} else {
			panelPrincipal.add(lblPregunta);
			panelPrincipal.add(Box.createVerticalStrut(15));
			panelPrincipal.add(panelOpciones);
			panelPrincipal.add(Box.createVerticalStrut(20));
			panelPrincipal.add(btnContinuar);
		}

		// Agregar panel a la ventana
		this.add(panelPrincipal);
		this.setVisible(true);
	}


}
