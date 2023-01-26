import java.awt.Color;
import java.awt.Graphics;

public class Tree {

	// Attributes
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private int radius = 80;
	private int leafRadius = 70;
	
	private Color lightLeafColor = new Color(0, 220, 0);
	private Color leafColor = new Color(0, 200, 0);
	private Color darkLeafColor = new Color(0, 150, 0);
	private Color trunkColor = new Color(168, 125, 47);
	private int trunkWidth;
	
	// Konstruktor
	public Tree(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		
		this.x = x;
		this.y = y - this.height;
		
		
		trunkWidth = width / 3;
	}
	
	// Objekt-Methoden
	public void draw(Graphics g) {
		g.setColor(trunkColor);
		g.fillRect(x, y, trunkWidth, height);
		
		// Blätter
		g.setColor(darkLeafColor);
		g.fillOval(x + trunkWidth / 2 - (int)(leafRadius * 0.12), y + radius / 6, (int)(leafRadius * 0.75), (int)(leafRadius * 0.75));
		g.setColor(lightLeafColor);
		g.fillOval(x - leafRadius / 2 + trunkWidth / 2, y - leafRadius / 2 + trunkWidth / 2, leafRadius, leafRadius);
		g.setColor(leafColor);
		g.fillOval(x + trunkWidth / 2 - (int)(leafRadius * 0.66), y + radius / 6, (int)(leafRadius * 0.75), (int)(leafRadius * 0.75));		
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
