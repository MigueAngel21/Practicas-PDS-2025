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
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.VerdaderoFalso;

public class VerdaderoFalsoUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador = Controlador.getUnicaInstancia();
	private int respuestaSeleccionada = -1;

	/**
	 * Create the frame.
	 */
	/**
	 * Create the frame.
	 */
	public VerdaderoFalsoUI(VerdaderoFalso pregunta, int numPregunta) {

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
		panelOpciones.setBackground(Color.WHITE);
		
		if (pregunta.contieneImagen()) {
		    panelOpciones.setLayout(new GridLayout(1, pregunta.getNumOpciones(), 10, 10));
		    
		    for (int i = 0; i < pregunta.getNumOpciones(); i++) {
		        try {
		            URL imgURL = Preguntas.class.getResource(pregunta.getOpciones().get(i));
		            if (imgURL == null) {
		                imgURL = new URL(pregunta.getOpciones().get(i));
		            }
		            BufferedImage img = ImageIO.read(imgURL);
		            JLabel imagenLabel = new JLabel(new ImageIcon(img.getScaledInstance(160, 150, Image.SCALE_SMOOTH)));
		            imagenLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		            panelOpciones.add(imagenLabel);
		        } catch (Exception e) {
		            JLabel errorLabel = new JLabel("Imagen no encontrada");
		            panelOpciones.add(errorLabel);
		        }
		    }
		}
		
		// Crear botones "Verdadero" y "Falso"
		JButton btnVerdadero = new JButton("Verdadero");
		JButton btnFalso = new JButton("Falso");

		btnVerdadero.setFont(new Font("Arial", Font.BOLD, 14));
		btnFalso.setFont(new Font("Arial", Font.BOLD, 14));
		btnVerdadero.setBackground(Color.GREEN);
		btnFalso.setBackground(Color.RED);
		btnVerdadero.setFocusPainted(false);
		btnFalso.setFocusPainted(false);
		btnVerdadero.setMaximumSize(new Dimension(120, 35));
		btnFalso.setMaximumSize(new Dimension(120, 35));
		
		
		btnVerdadero.addActionListener(e -> {
		    respuestaSeleccionada = 1;
		    btnVerdadero.setEnabled(false);
		    btnFalso.setEnabled(true);
		});

		btnFalso.addActionListener(e -> {
		    respuestaSeleccionada = 0;
		    btnFalso.setEnabled(false);
		    btnVerdadero.setEnabled(true);
		});

		
		// Panel para los botones de respuesta
		JPanel panelRespuestas = new JPanel();
		panelRespuestas.setBackground(Color.WHITE);
		panelRespuestas.add(btnVerdadero);
		panelRespuestas.add(Box.createHorizontalStrut(20));
		panelRespuestas.add(btnFalso);
		
		
		
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
		    // Configurar el borde del panel de puntuaciones de forma que lo que haya debajo esté justo debajo
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
		    panelPrincipal.add(panelPuntuaciones); // Agregar el panel de puntuaciones al contenedor principal, justo encima de panelPrincipal
		  }


		// Agregar componentes al panel principal
		panelPrincipal.add(lblPregunta);
		//añador espacio entre la pregunta y la imagen
		if (pregunta.contieneImagen()) {
			panelPrincipal.add(Box.createVerticalStrut(70));
			panelPrincipal.add(lblFrase);
		}
		if (!pregunta.contieneImagen()) {
		    panelPrincipal.add(Box.createVerticalStrut(30));
		    panelPrincipal.add(lblFrase);
		}
		panelPrincipal.add(Box.createVerticalStrut(30));
		panelPrincipal.add(panelOpciones);       // vacío o con imágenes
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(panelRespuestas);     // botones Verdadero/Falso
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(btnContinuar);

		// Agregar panel a la ventana
		this.add(panelPrincipal);
		this.setVisible(true);
	}


}