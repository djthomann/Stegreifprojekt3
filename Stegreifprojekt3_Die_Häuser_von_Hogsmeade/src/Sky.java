import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Der Himmel verwaltet die Stern- und Wolken-Objekte und verwaltet diese in Arrays
 * 
 * Bietet draw Methoden für den Himmel, die Sterne und die Wolken an
 * 
 * @author david, powder, marjan
 *
 */
public class Sky {
	
	// Attribute
	int amount;
	Star[] stars;
	Cloud[] clouds;
	
	/**
	 * Instanziiert die Himmel und Stern Arrays
	 * 
	 * @param amount ist die Menge an Sternen und Wolken die generiert wird
	 */
	public Sky(int amount) {
		this.amount = amount;
		this.stars = new Star[amount];
		for(int i = 0; i < amount; i++) {
			this.stars[i] = new Star(i);
		}
		this.clouds = new Cloud[amount / 5]; // Es gibt fünfmal weniger Wolken als Sterne 
		for(int i = 0; i < clouds.length; i++) {
			this.clouds[i] = new Cloud(i);
		}
	}
	
	/**
	 * Zeichnet den Himmel als Hintergrund der Szene
	 * 
	 * @param g der von der Klasse Hogsmeade überlieferte Grafik-Kontext
	 * @param skyColor die jeweilige Farbe des Himmels, je nach Tageszeit
	 */
	public void drawSky(Graphics2D g, Color skyColor) {
		g.setColor(skyColor);
		g.fillRect(0, 0, HogsmeadeApp.getWidth(), HogsmeadeApp.getHeight());
	}
	
	/**
	 * Zeichnet über den Himmel die Wolken
	 * 
	 * Nutzt AlphaComposite um die Wolken transparent zu machen
	 * 	
	 * @param g
	 */
	public void drawClouds(Graphics2D g) {
		
		// Ändern der Transparenz
		AlphaComposite ac1 = AlphaComposite.getInstance(
		AlphaComposite.SRC_OVER, 0.6f);
		g.setComposite(ac1);
		
		// Loop über das Array
		for(int i = 0; i < clouds.length; i++) {
			clouds[i].draw(g);
		}
		
		// Zurücksetzen des Transparenzwerts
		ac1 = AlphaComposite.getInstance(
		AlphaComposite.SRC_OVER, 1f);
		g.setComposite(ac1);
	}
	
	/**
	 * Zeichnet über den Himmel die Sterne
	 * 
	 * @param g Grafik-Kontext
	 */
	public void drawStars(Graphics g) {
		
		// Loop über das Array
		for(int i = 0; i < stars.length; i++) {
			stars[i].draw(g);
		}
	}
	
}
