package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.pds.controlador.Controlador;
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;

public class Preguntas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador = Controlador.getUnicaInstancia();


	/**
	 * Create the frame.
	 */
	public Preguntas() {
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
	
		
		
		//HACER IMPLEMENTACIION
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		
		JLabel icono = new JLabel("Duopingo");
		icono.setForeground(new Color(0, 255, 0));
		icono.setFont(new Font("Arial", Font.BOLD, 28));
		try {
	            URL imgURL = Preguntas.class.getResource("/umu/pds/resources/musculitos.png");
	            BufferedImage img = ImageIO.read(imgURL);
	            icono.setIcon(new ImageIcon(img.getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
	        } catch (IOException | NullPointerException e) {
	            icono.setText("Imagen no encontrada");
	        }
		icono.setBackground(Color.WHITE);
		panel.add(icono);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setBackground(Color.WHITE);
		
		JButton continuar = new JButton("Continuar");
		continuar.setBackground(new Color(0, 255, 0));
		continuar.setForeground(new Color(255, 255, 255));
		continuar.setFont(new Font("Arial", Font.BOLD, 18));
		continuar.addActionListener(e -> {
			// ocultar la ventana de selección de curso
			   this.setVisible(false);
			// abrir ventana de curso de fútbol
			// conseguir respuesta seleccionada
			   int lastPregunta = controlador.getLastPregunta();
			   Pregunta p = controlador.getSiguientePregunta();
			   JFrame sig = UIutils.creaPreguntaUI(p, lastPregunta);
			   sig.setVisible(true);
		});
		panel_1.add(continuar);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setBackground(Color.WHITE);
		
		Component verticalStrut = Box.createVerticalStrut(70);
		panel_3.add(verticalStrut);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setBackground(Color.WHITE);
		
		JLabel texto = new JLabel("Hay 12 preguntas para responder, las cuales pueden ser de varios tipos.");			
		texto.setFont(new Font("Arial", Font.BOLD, 19));
		texto.setForeground(Color.BLACK);
		JLabel texto1 = new JLabel("Responder correctamente a una pregunta te otorgará 10 punto.");			
		texto1.setFont(new Font("Arial", Font.BOLD, 19));
		texto1.setForeground(Color.BLACK);
		JLabel texto2 = new JLabel("Responder incorrectamente te restará 5 punto,");			
		texto2.setFont(new Font("Arial", Font.BOLD, 19));
		texto2.setForeground(Color.BLACK);
		JLabel texto22 = new JLabel("pero podras volver a contestarla al final.");			
		texto22.setFont(new Font("Arial", Font.BOLD, 19));
		texto22.setForeground(Color.BLACK);
		JLabel texto3 = new JLabel("Realizar el test cada dia aumentara tu racha de dias consecutivos,");			
		texto3.setFont(new Font("Arial", Font.BOLD, 19));
		texto3.setForeground(Color.BLACK);
		JLabel texto33 = new JLabel("                   lo que te otorgara un bonus de puntos.");			
		texto33.setFont(new Font("Arial", Font.BOLD, 19));
		texto33.setForeground(Color.BLACK);
		JLabel texto4 = new JLabel("                       ");			
		texto4.setFont(new Font("Arial", Font.BOLD, 19));
		texto4.setForeground(Color.BLACK);
		JLabel texto5 = new JLabel("Buena suerte! ;)");			
		texto5.setFont(new Font("Arial", Font.BOLD, 19));
		texto5.setForeground(Color.BLACK);
		panel_4.add(texto);
		panel_4.add(texto1);
		panel_4.add(texto2);
		panel_4.add(texto22);
		panel_4.add(texto3);
		panel_4.add(texto33);
		panel_4.add(texto4);
		panel_4.add(texto5);
		
		JLabel labelNewLine = new JLabel("                                                                                                      ");
		labelNewLine.setForeground(Color.BLACK);
		labelNewLine.setFont(new Font("Dialog", Font.BOLD, 19));
		panel_4.add(labelNewLine);
		
		this.setVisible(true);
	}

}
