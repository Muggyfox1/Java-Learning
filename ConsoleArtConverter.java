import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class ConsoleArtConverter {

	static Color[] CColors = {
			new Color(12,12,12),
			new Color(0, 55, 218),
			new Color(19, 161, 14),
			new Color(58, 150, 221),
			new Color(197, 15, 31),
			new Color(136, 23, 152),
			new Color(193, 156, 0),
			new Color(204, 204, 204),
			new Color(118, 118, 118),
			new Color(59, 120, 255),
			new Color(22, 198, 12),
			new Color(97, 214, 214),
			new Color(231, 72, 86),
			new Color(180, 0, 158),
			new Color(249, 241, 165),
			new Color(255, 255, 255)		
	};
	
	static String s = "";
	
	public static void main(String[] args) {
		String[] paths = {"Picture.png",
		                  "Result.png",
		                  "Result.txt"};
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(paths[0]));
		} catch (IOException e) {
			System.out.println(e);
		}
		
		Graphics g = img.createGraphics();
		int Height = img.getHeight();
		int Width = img.getWidth();
		
		System.out.println("Redrawing!");
		for(int i = 0; i < Height; i++) {
			for(int j = 0; j < Width; j++) {
				Color c = CalculateClosestColor(new Color(img.getRGB(j,i)));
				System.out.println(i + " " + j);
				g.setColor(c);
				g.fillRect(j, i, 1, 1);
			}
			s += '\n';
		}
			 	
		try {
		    BufferedImage bi = img;
		    File outputfile = new File(paths[1]);
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		try {
			FileWriter FW = new FileWriter(paths[2]);
			FW.write(s);
			FW.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Done!");
	}
	
	public static Color CalculateClosestColor(Color c){
		int Closest = 0;
		double CDist = 5000;
		
		for(int k = 0; k < CColors.length; k++){

			double Dist = Math.abs(Math.sqrt(Math.pow(c.getRed() - CColors[k].getRed(),2) + 
					Math.pow(c.getBlue() - CColors[k].getBlue(),2) + 
					Math.pow(c.getGreen() - CColors[k].getGreen(),2)));
			
			if(Dist < CDist) {
				Closest = k;
				CDist = Dist;
			}
			
		}
		String CString = "buecdmkazlgxrvyw";
		s += CString.charAt(Closest);
		return CColors[Closest];
	}
}