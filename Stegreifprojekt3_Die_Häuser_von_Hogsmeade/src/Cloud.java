import java.awt.Color;
import java.awt.Graphics;

public class Cloud {

	// Attributes
	private int x;
	private int y;
	
	private int radius = 30;
	
	private Color color = Color.WHITE;
	
	// Konstruktor
	public Cloud() {
		this.x = (int)(Math.random() * HogsmeadeApp.getWidth());
		this.y = (int)(Math.random() * (HogsmeadeApp.getHeight() - Hogsmeade.streetHeight) / 3);
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
	
}
