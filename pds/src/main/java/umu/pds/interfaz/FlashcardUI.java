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

import umu.pds.controlador.Controlador;
import umu.pds.dominio.Flashcard;
import umu.pds.dominio.MultipleChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private boolean volteada = false;
    private JPanel panelTarjeta;
    private CardLayout cardLayout;

    public FlashcardUI(Flashcard pregunta, int numPregunta) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBounds(420, 160, 732, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pregunta " + numPregunta);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/umu/pds/resources/musculitos.png"));

        cardLayout = new CardLayout();
        panelTarjeta = new JPanel(cardLayout);
        panelTarjeta.setPreferredSize(new Dimension(700, 400));

        // Panel de pregunta
        JPanel panelPregunta = new JPanel();
        panelPregunta.setBackground(Color.WHITE);
        JLabel lblPregunta = new JLabel(pregunta.getEnunciado(), SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        panelPregunta.add(lblPregunta);
        
        // Panel de respuesta
        JPanel panelRespuesta = new JPanel();
        panelRespuesta.setBackground(Color.LIGHT_GRAY);
        JLabel lblRespuesta = new JLabel(pregunta.getRespuesta(), SwingConstants.CENTER);
        lblRespuesta.setFont(new Font("Arial", Font.BOLD, 16));
        panelRespuesta.add(lblRespuesta);

        panelTarjeta.add(panelPregunta, "pregunta");
        panelTarjeta.add(panelRespuesta, "respuesta");

        panelTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cardLayout.next(panelTarjeta);
                volteada = !volteada;
            }
        });

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
        btnContinuar.setBackground(new Color(0, 200, 0));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorderPainted(false);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setMaximumSize(new Dimension(120, 35));
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!volteada) {
                    JOptionPane.showMessageDialog(null, "Debes ver la respuesta antes de continuar", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                setVisible(false);
                // Aquí iría la lógica para pasar a la siguiente pregunta
            }
        });

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelTarjeta, BorderLayout.CENTER);
        panelPrincipal.add(btnContinuar, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }
}
