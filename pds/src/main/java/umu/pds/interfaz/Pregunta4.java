package umu.pds.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Pregunta4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pregunta4 frame = new Pregunta4();
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
	public Pregunta4() {
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
		setTitle("Pregunta 4");
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
        JLabel lblPregunta = new JLabel("Completa la frase de Luis Aragones : ");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setForeground(Color.BLACK);

        // Frase con hueco para completar
        JLabel lblFrase = new JLabel("Ganar, ganar y volver a ganar, y ganar y ganar, y ganar, y eso _______");
        lblFrase.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFrase.setForeground(Color.BLACK);
        
        // Panel para opciones
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(3, 1, 5, 5));
        panelOpciones.setBackground(Color.WHITE);

        // Botones de opción (más pequeños)
        JButton opcion1 = new JButton("1. es el futbol, señores");
        JButton opcion2 = new JButton("2. es la vida");
        JButton opcion3 = new JButton("3. es el futbol, chavales");

        // Ajustar tamaño de los botones
        Dimension botonSize = new Dimension(100, 30);
        opcion1.setPreferredSize(botonSize);
        opcion2.setPreferredSize(botonSize);
        opcion3.setPreferredSize(botonSize);

        // Acción para alternar selección
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botonPresionado = (JButton) e.getSource();

                // Alternar entre habilitar y deshabilitar los botones
                if (!botonPresionado.isEnabled()) {
                    opcion1.setEnabled(true);
                    opcion2.setEnabled(true);
                    opcion3.setEnabled(true);
                } else {
                    opcion1.setEnabled(botonPresionado == opcion1);
                    opcion2.setEnabled(botonPresionado == opcion2);
                    opcion3.setEnabled(botonPresionado == opcion3);
                }
            }
        };

        // Asignar acción a los botones
        opcion1.addActionListener(actionListener);
        opcion2.addActionListener(actionListener);
        opcion3.addActionListener(actionListener);

        // Estilo de los botones
        for (JButton boton : new JButton[]{opcion1, opcion2, opcion3}) {
            boton.setFont(new Font("Arial", Font.PLAIN, 14));
            boton.setBackground(Color.ORANGE);
            boton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
            boton.setFocusPainted(false);
            panelOpciones.add(boton);
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
        	// ocultar la ventana de registro
        	this.setVisible(false);
			// abrir la ventana principal
        	Pregunta5 p5 = new Pregunta5();
			p5.setVisible(true);
		});

        // Agregar componentes al panel principal
        panelPrincipal.add(lblPregunta);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(lblFrase);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelOpciones);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(btnContinuar);

        // Agregar panel a la ventana
        this.add(panelPrincipal);
		this.setVisible(true);
	}

}
