package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import umu.pds.controlador.Controlador;
import umu.pds.dominio.Flashcard;
import umu.pds.dominio.Pregunta;

import javax.swing.*;
import java.awt.*;

public class FlashcardUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean volteada = false;
	private JPanel panelTarjeta;
	private CardLayout cardLayout;
	private Controlador controlador = Controlador.INSTANCE;

	public FlashcardUI(Flashcard pregunta, int numPregunta) {
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
		setTitle("Pregunta " + numPregunta);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));

		cardLayout = new CardLayout();
		panelTarjeta = new JPanel(cardLayout);
		panelTarjeta.setPreferredSize(new Dimension(500, 300));
		panelTarjeta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

		// Panel de pregunta
		JPanel panelPregunta = new JPanel();
		panelPregunta.setBackground(Color.WHITE);
		panelPregunta.setLayout(new GridBagLayout());
		JLabel lblPregunta = new JLabel(
				"<html><div style='text-align: center; width: 400px;'>" + pregunta.getEnunciado() + "</div></html>",
				SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		panelPregunta.add(lblPregunta);

		// Panel de respuesta
		JPanel panelRespuesta = new JPanel();
		panelRespuesta.setBackground(Color.LIGHT_GRAY);
		panelRespuesta.setLayout(new GridBagLayout());
		JLabel lblRespuesta = new JLabel(
				"<html><div style='text-align: center; width: 400px;'>" + pregunta.getRespuesta() + "</div></html>",
				SwingConstants.CENTER);
		lblRespuesta.setFont(new Font("Arial", Font.BOLD, 16));
		panelRespuesta.add(lblRespuesta);

		panelTarjeta.add(panelPregunta, "pregunta");
		panelTarjeta.add(panelRespuesta, "respuesta");

		panelTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (!volteada) {
					cardLayout.next(panelTarjeta);
					volteada = !volteada;
				}
			}
		});

		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
		btnContinuar.setBackground(new Color(0, 200, 0));
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setBorderPainted(false);
		btnContinuar.setFocusPainted(false);
		btnContinuar.setMaximumSize(new Dimension(120, 35));
		btnContinuar.addActionListener(e -> {
			if (!volteada) {
				UIutils.showErrorDialog("Debes voltear la flashcard antes de continuar.");
				return;
			}
			this.setVisible(false);
			// conseguir respuesta seleccion
			controlador.responderPregunta(numPregunta);
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

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20, 0, 20, 0);
		panelPrincipal.add(panelTarjeta, gbc);

		gbc.gridy = 1;
		panelPrincipal.add(btnContinuar, gbc);

		add(panelPrincipal);
		setVisible(true);
	}
}
