import java.awt.Color;
import java.awt.Graphics;

public class Tree {

	// Attributes
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private int radius = 80;
	
	private Color leafColor = new Color(0, 200, 0);
	private Color darkLeafColor = new Color(0, 150, 0);
	private Color trunkColor = new Color(168, 125, 47);
	private int trunkWidth;
	
	// Konstruktor
	public Tree(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		trunkWidth = width / 3;
	}
	
	// Objekt-Methoden
	public void draw(Graphics g) {
		g.setColor(trunkColor);
		g.fillRect(x + trunkWidth, y, trunkWidth, height);
		
		// Blätter
		g.setColor(darkLeafColor);
		g.fillOval((x + width / 2) - radius / 8, y + radius / 6, radius / 2, radius / 2);
		g.setColor(darkLeafColor);
		g.fillOval((x + width / 2) - radius / 3, y + radius / 6, radius / 2, radius / 2);
		g.setColor(leafColor);
		g.fillOval((x + width / 2) - radius / 2, y - radius / 2, radius, radius);
		
	}
	
	// Getter und Setter
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
