package umu.pds.interfaz;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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

  }
