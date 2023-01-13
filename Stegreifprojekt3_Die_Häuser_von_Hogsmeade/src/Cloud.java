import java.awt.Color;
import java.awt.Graphics;

public class Cloud {

	// Attributes
	private int x;
	private int y;
	
	private int radius = 30;
	private double random;
	
	private Color color = Color.WHITE;
	
	// Konstruktor
	public Cloud(int number) {
		this.x = (int)(Math.random() * HogsmeadeApp.getWidth());
		this.y = (int)(Math.random() * (HogsmeadeApp.getHeight() - Hogsmeade.streetHeight) / 3);
		this.radius = ((number % 3) + 1) * radius;
		this.random = Math.random() + 0.5;
		
	}
	
	// Objekt-Methoden
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
	
}
