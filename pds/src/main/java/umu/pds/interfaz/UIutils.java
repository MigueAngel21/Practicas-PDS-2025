package umu.pds.interfaz;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import umu.pds.dominio.Flashcard;
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;

public class UIutils {

	public static boolean isAnyFieldBlank(Object... fields) {
		for (Object field : fields) {
			if (field == null || (field instanceof String && ((String) field).isEmpty())) {
				return true;
			}
		}
		return false;
	}

	public static void showErrorDialog(String message) {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Error");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}



	public static ImageIcon loadImage(URL url, int size) {
		try {
            return new ImageIcon(ImageIO.read(url).getScaledInstance(size, size, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static JFrame creaPreguntaUI(Pregunta pregunta, int numPregunta) {
		if (pregunta instanceof MultipleChoice) {
            return new MultipleChoiceUI((MultipleChoice) pregunta, numPregunta);
        } else if (pregunta instanceof Flashcard) {
            return new FlashcardUI((Flashcard) pregunta, numPregunta);
        } else {
            return null;
        }
	}

	public static void showInfoDialog(String string) {
		JOptionPane optionPane = new JOptionPane(string, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Info");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

  }
