package umu.pds.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class SeleccionCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionCurso frame = new SeleccionCurso();
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
	public SeleccionCurso() {
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
		
		// Título
        JLabel titulo = new JLabel("Quiero aprender:", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.BLACK);

        // Panel para cursos
        JPanel panelCursos = new JPanel(new GridLayout(1, 2, 10, 10));

        // Crear primer curso (Inglés)
        JPanel curso1 = new JPanel(new BorderLayout());
        curso1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        JLabel img1 = new JLabel();
        img1.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            URL imgURL = SeleccionCurso.class.getResource("/umu/pds/resources/banderaUSA.png");
            BufferedImage img = ImageIO.read(imgURL);
            img1.setIcon(new ImageIcon(img.getScaledInstance(50, 30, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            img1.setText("Imagen no encontrada");
        }

        JLabel texto1 = new JLabel("Inglés", SwingConstants.CENTER);
        JButton btn1 = new JButton("Elegir");
        btn1.addActionListener(e -> System.out.println("Seleccionaste: Inglés"));

        curso1.add(img1, BorderLayout.NORTH);
        curso1.add(texto1, BorderLayout.CENTER);
        curso1.add(btn1, BorderLayout.SOUTH);

        // Crear segundo curso (Francés)
        JPanel curso2 = new JPanel(new BorderLayout());
        curso2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel img2 = new JLabel();
        img2.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            URL imgURL = SeleccionCurso.class.getResource("/umu/pds/resources/banderaESP.png");
            BufferedImage img = ImageIO.read(imgURL);
            img2.setIcon(new ImageIcon(img.getScaledInstance(50, 30, Image.SCALE_SMOOTH)));
        } catch (IOException | NullPointerException e) {
            img2.setText("Imagen no encontrada");
        }

        JLabel texto2 = new JLabel("Español", SwingConstants.CENTER);
        JButton btn2 = new JButton("Elegir");
        btn2.addActionListener(e -> System.out.println("Seleccionaste: Español"));

        curso2.add(img2, BorderLayout.NORTH);
        curso2.add(texto2, BorderLayout.CENTER);
        curso2.add(btn2, BorderLayout.SOUTH);

        // Agregar cursos al panel
        panelCursos.add(curso1);
        panelCursos.add(curso2);

        // Agregar todo a la ventana
        this.add(titulo, BorderLayout.NORTH);
        this.add(panelCursos, BorderLayout.CENTER);

	    
		this.setVisible(true);
	}

}
