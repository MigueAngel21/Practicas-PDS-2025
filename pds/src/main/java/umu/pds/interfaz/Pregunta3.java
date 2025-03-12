package umu.pds.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
import javax.swing.border.EmptyBorder;

public class Pregunta3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pregunta3 frame = new Pregunta3();
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
	public Pregunta3() {
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
		setTitle("Pregunta 3");
		setResizable(false);
		//cambiar icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));
		
		
		
		
		//HACER IMPLEMENTACIION
		/*
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.WHITE);
		
		
		JButton continuar = new JButton("Continuar");
		continuar.setBackground(new Color(0, 255, 0));
		continuar.setForeground(new Color(255, 255, 255));
		continuar.setFont(new Font("Arial", Font.BOLD, 18));
		continuar.addActionListener(e -> {
			// ocultar la ventana de selección de curso
			   this.setVisible(false);
			// abrir ventana de curso de fútbol
			   Puntos p = new Puntos();
			   p.setVisible(true);
		});
		panel.add(continuar);
		*/
		// Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Pregunta en negrita
        JLabel lblPregunta = new JLabel("¿Cuál es el escudo del real madrid?");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPregunta.setForeground(Color.BLACK);

        // Panel de opciones con imágenes
        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 10, 10));
        panelOpciones.setBackground(Color.WHITE);


        // Crear botones con imágenes
        JButton btnMadrid = new JButton();
        try {
            URL imgURL = Preguntas.class.getResource("/umu/pds/resources/escudoRealMadrid.png");
            BufferedImage img = ImageIO.read(imgURL);
            btnMadrid.setIcon(new ImageIcon(img.getScaledInstance(100, 80, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            btnMadrid.setText("Imagen no encontrada");
        }
       
        JButton btnAtleti = new JButton();
        try {
            URL imgURL = Preguntas.class.getResource("/umu/pds/resources/escudoAtleticoMadrid.jpg");
            BufferedImage img = ImageIO.read(imgURL);
            btnAtleti.setIcon(new ImageIcon(img.getScaledInstance(100, 80, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            btnAtleti.setText("Imagen no encontrada");
        }
       
        JButton btnRayo = new JButton();
        try {
            URL imgURL = Preguntas.class.getResource("/umu/pds/resources/escudoRayoVall.jpg");
            BufferedImage img = ImageIO.read(imgURL);
            btnRayo.setIcon(new ImageIcon(img.getScaledInstance(100, 90, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            btnRayo.setText("Imagen no encontrada");
        }

        
        // Quitar bordes y fondo
        for (JButton btn : new JButton[]{btnMadrid, btnAtleti, btnRayo}) {
            btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
        }

        // Acción para manejar la selección de botones
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton selectedButton = (JButton) e.getSource();

                // Alternar selección
                if (!selectedButton.isEnabled()) {
                    btnMadrid.setEnabled(true);
                    btnAtleti.setEnabled(true);
                    btnRayo.setEnabled(true);
                } else {
                    btnMadrid.setEnabled(selectedButton == btnMadrid);
                    btnAtleti.setEnabled(selectedButton == btnAtleti);
                    btnRayo.setEnabled(selectedButton == btnRayo);
                }
            }
        };

        // Asignar acción a los botones
        btnMadrid.addActionListener(actionListener);
        btnAtleti.addActionListener(actionListener);
        btnRayo.addActionListener(actionListener);

        // Agregar botones al panel de opciones
        panelOpciones.add(btnMadrid);
        panelOpciones.add(btnAtleti);
        panelOpciones.add(btnRayo);

        // Botón "Continuar"
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
        btnContinuar.setBackground(new Color(0, 200, 0));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorderPainted(false);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setMaximumSize(new Dimension(120, 35));
        btnContinuar.addActionListener(e -> {
        	// ocultar la ventana de registro
        	this.setVisible(false);
			// abrir la ventana principal
        	Pregunta4 p4 = new Pregunta4();
			p4.setVisible(true);
		});

        // Agregar componentes al panel principal
        panelPrincipal.add(lblPregunta);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(panelOpciones);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(btnContinuar);

        // Agregar panel a la ventana
        this.add(panelPrincipal);
		this.setVisible(true);
	}

}
