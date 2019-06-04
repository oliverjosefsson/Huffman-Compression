package u5;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;

public class CompressImage {
	private int width, height, colors;
	private HuffmanCompression huff = new HuffmanCompression();
	
	private int[] charFreqs = new int[1024];

	public void compress(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		WritableRaster inraster = image.getRaster();

		colors = 3; // RGB
		try {
			inraster.getSample(0, 0, 2);
		} catch (Exception e) {
			colors = 1; // Monochrome
		}

		Object[] res = loadValues(inraster); 
		System.out.println("Image compressed");
		colors = (int) res[0];
		setFreq();
	}

	private Object[] loadValues(WritableRaster inraster) { 
		HashMap<String, Integer> nbrOfColor = new HashMap<String, Integer>();
		int r = 0, g = 0, b = 0, sample;

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				sample = 0;
				for (int c = 0; c < colors; c++) {
					sample += inraster.getSample(i, j, c);
					if (c == 0)
						r = sample;
					else if (c == 1)
						g = sample;
					else if (c == 2)
						b = sample;
				}
				nbrOfColor.put(r + " " + g + " " + b, 1);
				
				charFreqs[sample]++;   
			}
		Object[] o = {nbrOfColor.size()};
		return o;
	}

	private void setFreq() {
		HuffmanTree tree = huff.buildTree(charFreqs);
		printSize(tree);
	}

	private void printSize(HuffmanTree tree) {
		huff.printResults(tree);
		int mod = 0;
		while (Math.pow(2, mod) < colors) {
			mod++;
		}
		System.out.println("Number of different colors: " + colors);
		System.out.println("In binary code: " + (int) Math.ceil((Math.pow(2, mod) * width * height) / 8.0) + " bytes");
	}

	public static void main(String[] args) {
		ImageReader iReader = new ImageReader();
		while (iReader.getImage() == null) {
			iReader.selectImage();
		}
		CompressImage compress = new CompressImage();
		compress.compress(iReader.getImage());
	}
}
