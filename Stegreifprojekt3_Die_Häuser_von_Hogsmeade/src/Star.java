import java.awt.Color;
import java.awt.Graphics;

/**
 * Sterne sind die Objekte, welche am Nachthimmel gezeichnet werden.
 * 
 * Jeder Stern wird an einer zufälligen Position im Himmel generiert
 * 
 * Für jeden Stern werden Getter für die ihn ausmachenden Objekt-Felder angeboten.
 * 
 * @author david, powder, marjan
 *
 */

public class Star {

	// Attribute
	private int x;
	private int y;
	
	private int radius;
	
	private Color color = Color.WHITE;
	
	/**
	 * Generiert einen Stern mit zufälliger Position und Größe
	 * 
	 * @param number wird modulo 5 gerechnet, damit Varianz in die Größe der Sterne kommt
	 */
	public Star(int number) {
		this.x = (int)(Math.random() * HogsmeadeApp.getWidth());
		this.y= (int)(Math.random() * HogsmeadeApp.getHeight() - Hogsmeade.getStreetHeight());
		this.radius = (number % 5) + 1;
	}
	
	/**
	 * Zeichnet an jeder Position einen Stern mit der jeweiligen Größen in der Sternen-Farbe
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}
	
	// Getter und Setter
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRadius() {
		return radius;
	}
}