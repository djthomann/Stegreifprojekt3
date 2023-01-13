import java.awt.Color;
import java.awt.Graphics;

public class Star {

	// Attribute
	private int x;
	private int y;
	
	private int radius;
	
	private Color color = Color.WHITE;
	
	// Konstruktor
	public Star(int number) {
		this.x = (int)(Math.random() * HogsmeadeApp.getWidth());
		this.y= (int)(Math.random() * HogsmeadeApp.getHeight() - Hogsmeade.streetHeight);
		this.radius = (number % 5) + 1;
		
	}
	
	// Objekt-Methoden
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