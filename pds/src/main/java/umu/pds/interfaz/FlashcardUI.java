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
	private Controlador controlador = Controlador.getUnicaInstancia();

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



		// On close stop app
		
		setBounds(420, 160, 732, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pregunta " + numPregunta);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));

		cardLayout = new CardLayout();
		panelTarjeta = new JPanel(cardLayout);
		panelTarjeta.setPreferredSize(new Dimension(500, 300));
		panelTarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		panelTarjeta.setBackground(Color.WHITE);

		// Panel de pregunta
		JPanel panelPregunta = new JPanel();
		panelPregunta.setBackground(Color.WHITE);
		panelPregunta.setLayout(new GridBagLayout());
		JLabel lblPregunta = new JLabel(
				"<html><div style='text-align: center; width: 400px;'>" + pregunta.getEnunciado() + "</div></html>",
				SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblPregunta.setForeground(Color.BLACK);
		panelPregunta.add(lblPregunta);

		// Panel de respuesta
		JPanel panelRespuesta = new JPanel();
		panelRespuesta.setBackground(Color.BLACK);
		panelRespuesta.setLayout(new GridBagLayout());
		JLabel lblRespuesta = new JLabel(
				"<html><div style='text-align: center; width: 400px;'>" + pregunta.getRespuesta() + "</div></html>",
				SwingConstants.CENTER);
		lblRespuesta.setFont(new Font("Arial", Font.BOLD, 16));
		lblRespuesta.setForeground(Color.WHITE);
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

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.rowHeights = new int[]{0, 0, 0};
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 1.0, 1.0};
		gbl_panelPrincipal.columnWeights = new double[]{0.0, 1.0};
		panelPrincipal.setLayout(gbl_panelPrincipal);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(20, 0, 20, 0);
		panelPrincipal.add(panelTarjeta, gbc);

		getContentPane().add(panelPrincipal);
				
				JPanel panelBtns = new JPanel();
				panelBtns.setBackground(Color.WHITE);
				GridBagConstraints gbc_panelBtns = new GridBagConstraints();
				gbc_panelBtns.fill = GridBagConstraints.BOTH;
				gbc_panelBtns.gridx = 1;
				gbc_panelBtns.gridy = 2;
				panelPrincipal.add(panelBtns, gbc_panelBtns);
				panelBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
						JButton btnBien = new JButton("Bien");
						panelBtns.add(btnBien);
						btnBien.setFont(new Font("Arial", Font.BOLD, 14));
						btnBien.setBackground(new Color(0, 200, 0));
						btnBien.setForeground(Color.WHITE);
						btnBien.setBorderPainted(false);
						btnBien.setFocusPainted(false);
						btnBien.setMaximumSize(new Dimension(200, 200));
						
						Component horizontalStrut = Box.createHorizontalStrut(20);
						panelBtns.add(horizontalStrut);
						
						JButton btnMal = new JButton("Mal");
						btnMal.setFont(new Font("Dialog", Font.BOLD, 14));
						btnMal.setForeground(new Color(255, 255, 255));
						btnMal.setBorderPainted(false);
						btnMal.setBackground(new Color(224, 27, 36));
						panelBtns.add(btnMal);
						btnBien.addActionListener(e -> manejarRespuesta(1, numPregunta));
						btnMal.addActionListener(e -> manejarRespuesta(0, numPregunta));
						setVisible(true);
	}
	
	private void manejarRespuesta(int respuesta,int numPregunta) {
		if (!volteada) {
			UIutils.showErrorDialog("Debes voltear la flashcard antes de continuar.");
			return;
		}
		this.setVisible(false);
		controlador.responderPregunta(respuesta);
		Pregunta p = controlador.getSiguientePregunta();
		if (p == null) {
			Puntos puntos = new Puntos();
			puntos.setVisible(true);
			return;
		}
		JFrame sig = UIutils.creaPreguntaUI(p, numPregunta + 1);
		sig.setVisible(true);
	}

}
