package u5;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Lets the user select a image and store it so that it can be retrieved by others.
 * @author Lucas Borg
 *
 */
public class ImageReader {
	private BufferedImage image;

	/**
	 * Lets the user select a monochrome image which is then stored.
	 */
	public void selectImage() {
		JFileChooser chooser = new JFileChooser();
//		chooser.setCurrentDirectory(
//				new File("C:\\Users\\zbn\\eclipse-workspace\\Text & bild\\images"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			try {
				image = ImageIO.read(file);
			} catch (Exception e) {
				image = null;
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the image which has been selected with selectImage();
	 * 
	 * @return The selected image.
	 */
	public BufferedImage getImage() {
		return this.image;
	}
}
