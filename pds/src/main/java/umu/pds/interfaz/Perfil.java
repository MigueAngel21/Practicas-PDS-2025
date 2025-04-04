package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.pds.controlador.Controlador;
import umu.pds.dominio.Estadistica;
import umu.pds.dominio.Progreso;
import javax.swing.JButton;

public class Perfil extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controlador controlador = Controlador.getUnicaInstancia();
	
	public Perfil(Estadistica estadistica, String username, JFrame previous) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}



		setBounds(420, 160, 732, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Perfil");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setText(username);
		lblUsuario.setFont(new Font("Libertinus Sans", Font.BOLD, 24));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblUsuario, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 229, 125, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblTiempoDeUso = new JLabel("Tiempo De Uso:");
		lblTiempoDeUso.setFont(new Font("Libertinus Sans", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTiempoDeUso = new GridBagConstraints();
		gbc_lblTiempoDeUso.insets = new Insets(0, 0, 5, 5);
		gbc_lblTiempoDeUso.gridx = 2;
		gbc_lblTiempoDeUso.gridy = 3;
		panel.add(lblTiempoDeUso, gbc_lblTiempoDeUso);
		
		JLabel lblNtiempo = new JLabel("Ntiempo");
		lblNtiempo.setText(estadistica.getTiempoDeUso() + " minutos");
		GridBagConstraints gbc_lblNtiempo = new GridBagConstraints();
		gbc_lblNtiempo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNtiempo.gridx = 3;
		gbc_lblNtiempo.gridy = 3;
		panel.add(lblNtiempo, gbc_lblNtiempo);
		
		JLabel lblRachaDeDas = new JLabel("Racha de días:");
		lblRachaDeDas.setFont(new Font("Libertinus Sans", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRachaDeDas = new GridBagConstraints();
		gbc_lblRachaDeDas.insets = new Insets(0, 0, 5, 5);
		gbc_lblRachaDeDas.gridx = 2;
		gbc_lblRachaDeDas.gridy = 4;
		panel.add(lblRachaDeDas, gbc_lblRachaDeDas);
		
		JLabel lblNracha = new JLabel("NRacha");
		lblNracha.setFont(new Font("Arial", Font.BOLD, 14));
		lblNracha.setForeground(new Color(255, 128, 0));
		lblNracha.setText(estadistica.getRachaDeDias() + " días");
		GridBagConstraints gbc_lblNracha = new GridBagConstraints();
		gbc_lblNracha.insets = new Insets(0, 0, 5, 5);
		gbc_lblNracha.gridx = 3;
		gbc_lblNracha.gridy = 4;
		panel.add(lblNracha, gbc_lblNracha);
		
		JComboBox comboBox = new JComboBox(estadistica.getProgresos().toArray());
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		panel.add(comboBox, gbc_comboBox);
		
		//Conseguir el curso seleccionado en la comboBox
		Progreso p = (Progreso) comboBox.getSelectedItem();
		
		JLabel lblRepuestasCorrectas = new JLabel("Repuestas Correctas: ");
		lblRepuestasCorrectas.setFont(new Font("Libertinus Sans", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRepuestasCorrectas = new GridBagConstraints();
		gbc_lblRepuestasCorrectas.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepuestasCorrectas.gridx = 2;
		gbc_lblRepuestasCorrectas.gridy = 6;
		panel.add(lblRepuestasCorrectas, gbc_lblRepuestasCorrectas);
		
		JLabel lblNcorrectas = new JLabel("Ncorrectas");
		lblNcorrectas.setText(String.valueOf(p.getRespuestasCorrectas()));
		GridBagConstraints gbc_lblNcorrectas = new GridBagConstraints();
		gbc_lblNcorrectas.insets = new Insets(0, 0, 5, 5);
		gbc_lblNcorrectas.gridx = 3;
		gbc_lblNcorrectas.gridy = 6;
		panel.add(lblNcorrectas, gbc_lblNcorrectas);
		
		JLabel lblRespuestasIncorrectas = new JLabel("Respuestas Incorrectas: ");
		lblRespuestasIncorrectas.setFont(new Font("Libertinus Sans", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRespuestasIncorrectas = new GridBagConstraints();
		gbc_lblRespuestasIncorrectas.insets = new Insets(0, 0, 5, 5);
		gbc_lblRespuestasIncorrectas.gridx = 2;
		gbc_lblRespuestasIncorrectas.gridy = 7;
		panel.add(lblRespuestasIncorrectas, gbc_lblRespuestasIncorrectas);
		
		JLabel lblNincorrectas = new JLabel("Nincorrectas");
		lblNincorrectas.setText(String.valueOf(p.getRespuestasIncorrectas()));
		GridBagConstraints gbc_lblNincorrectas = new GridBagConstraints();
		gbc_lblNincorrectas.insets = new Insets(0, 0, 5, 5);
		gbc_lblNincorrectas.gridx = 3;
		gbc_lblNincorrectas.gridy = 7;
		panel.add(lblNincorrectas, gbc_lblNincorrectas);
		
		JLabel lblCompletado = new JLabel("Completado: ");
		lblCompletado.setFont(new Font("Libertinus Sans", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCompletado = new GridBagConstraints();
		gbc_lblCompletado.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompletado.gridx = 2;
		gbc_lblCompletado.gridy = 8;
		panel.add(lblCompletado, gbc_lblCompletado);
		
		JLabel lblNcompletado = new JLabel("Ncompletado");
		lblNcompletado.setText(String.valueOf(p.getCompletitud()) + "%");
		GridBagConstraints gbc_lblNcompletado = new GridBagConstraints();
		gbc_lblNcompletado.insets = new Insets(0, 0, 5, 5);
		gbc_lblNcompletado.gridx = 3;
		gbc_lblNcompletado.gridy = 8;
		panel.add(lblNcompletado, gbc_lblNcompletado);
		
		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 8;
		gbc_btnSalir.gridy = 12;
		panel.add(btnSalir, gbc_btnSalir);
		
		btnSalir.addActionListener(e -> {
			   this.setVisible(false);
			   previous.setVisible(true);
		});
		

	}
	
}
