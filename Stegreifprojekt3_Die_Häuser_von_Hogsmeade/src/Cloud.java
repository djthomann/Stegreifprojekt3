import java.awt.Color;
import java.awt.Graphics;

/**
 * Wolken sind Elemente, die am Tag am Himmel gezeichnet werden.
 * Jede Wolke ist etwas unterschiedlich groß und besteht aus kleineren "Teil-Wolken" die zufäälig um die Hauptwolke verteilt werden
 * --> dafür existiert das Feld random
 * 
 * Die Größe errechnet sind aus der Position der Wolke im Array der Sky Klasse mal einer maximal Größe
 * 
 * Es werden Getter für die Position und die Dimension der Wolke angeboten
 * 
 * @author david, powder, marjan
 *
 */
public class Cloud {

	// Position
	private int x;
	private int y;
	
	// Dimension
	private int radius = 30;
	private double random;
	
	// Farben
	private Color color = Color.WHITE;
	
	/**
	 * Konstruiert eine Wolke, alle Felder werden mit einer gewissen Varianz/Zufall erzeugt
	 * 
	 * @param number wird modulo 5 gerechnet, damit Varianz in die Größe der Wolken kommt
	 */
	public Cloud(int number) {
		this.x = (int)(Math.random() * HogsmeadeApp.getWidth());
		this.y = (int)(Math.random() * (HogsmeadeApp.getHeight() - Hogsmeade.getStreetHeight()) / 3);
		this.radius = ((number % 3) + 1) * radius;
		this.random = Math.random() + 0.5;
	}
	
	/**
	 * Zeichnet die Wolke und ihre Bestandteile mit der jeweiligen skalierten Größe und Position
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext mit Transparenz
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y,  radius, radius);
		g.fillOval(x + radius/2, y + (int)((radius / 2) * random),  (int)(radius * 0.66), (int)(radius * 0.66));
		g.fillOval(x - radius / 4, y + radius - (int)((radius / 2) * random),  (int)(radius * 0.66), (int)(radius * 0.66));
		g.fillOval(x + (int)(radius * 0.12), y + radius / 2,  (int)(radius * 0.66), (int)(radius * 0.66));		
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
