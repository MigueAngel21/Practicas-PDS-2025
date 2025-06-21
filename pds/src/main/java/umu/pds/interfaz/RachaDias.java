package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class RachaDias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//variable para la racha de dias
	private int rachaDias = 0; // Empieza en 0, se incrementa cada vez que el usuario completa una tarea diaria
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RachaDias frame = new RachaDias();
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
	public RachaDias() {
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
			   VentanaPrincipal vp = new VentanaPrincipal();
			   vp.setVisible(true);
		});
		panel_1.add(continuar);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setBackground(Color.WHITE);
		
		Component verticalStrut = Box.createVerticalStrut(50);
		panel_3.add(verticalStrut);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setBackground(Color.WHITE);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{251, 216, 0};
		gbl_panel_4.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel texto = new JLabel(" Racha de Días : ");			
		texto.setFont(new Font("Arial", Font.BOLD, 25));
		texto.setForeground(new Color(255, 128, 0));
		texto.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_texto = new GridBagConstraints();
		gbc_texto.insets = new Insets(0, 0, 5, 0);
		gbc_texto.gridx = 1;
		gbc_texto.gridy = 0;
		panel_4.add(texto, gbc_texto);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 1;
		panel_4.add(verticalStrut_1, gbc_verticalStrut_1);
		
		ImageIcon imagenMascota = new ImageIcon(getClass().getResource("/umu/pds/resources/DuolingoRachaVictorias.jpg"));
        Image img = imagenMascota.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        
        
		//variable para el numero de dias de la racha
		JLabel lblNewLabel_1 = new JLabel(" " + rachaDias+1 + " Días ");
		rachaDias++; // Incrementar la racha de días
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setForeground(new Color(255, 128, 0));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_4.add(lblNewLabel_1, gbc_lblNewLabel_1);
		JLabel lblNewLabel = new JLabel(new ImageIcon(img));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel_4.add(lblNewLabel, gbc_lblNewLabel);
		
		Component verticalStrut_2 = Box.createVerticalStrut(30);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 5;
		panel_4.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lblNewLabel_2 = new JLabel("Trabajaste duro y alcanzate tu meta diaria.\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		panel_4.add(lblNewLabel_2, gbc_lblNewLabel_2);
		

		this.setVisible(true);
	}

}
