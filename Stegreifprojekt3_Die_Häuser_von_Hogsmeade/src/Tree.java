import java.awt.Color;
import java.awt.Graphics;

/**
 * Bäume sind ein wesentliches Dekorations-Element der Szene
 * Höhe und Breite sind frei wählbar, die Größe der Baumkrone und der Blätter ist vorgegeben
 * 
 * Es werden Getter für die Position und die Dimension des jeweiligen Baums angeboten
 * 
 * @author THO
 *
 */
public class Tree {

	// Koordinaten
	private int x;
	private int y;
	
	// Dimension
	private int width;
	private int height;
	private int trunkWidth;
	
	private final int RADIUS = 80;
	private final int LEAF_RADIUS = 70;
	
	// Farben
	private Color lightLeafColor = new Color(0, 220, 0);
	private Color leafColor = new Color(0, 200, 0);
	private Color darkLeafColor = new Color(0, 150, 0);
	private Color trunkColor = new Color(168, 125, 47);
	
	/**
	 * Konstruiert einen Baum an einer bestimmten Position und in einer bestimmten Größe
	 * Die Breite des Baumstamms leitet sich direkt aus der Breite des Baums ab
	 * 
	 * @param x,y sind die Koordinaten des Baums
	 * @param width, height werden gesetzt
	 * 
	 */
	public Tree(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		
		this.x = x;
		this.y = y - this.height;
		
		
		trunkWidth = width / 3;
	}
	
	/**
	 * Zeichnet den Baum und die Baumkrone
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 */
	public void draw(Graphics g) {
		// Zeichnen des Stamms
		g.setColor(trunkColor);
		g.fillRect(x, y, trunkWidth, height);
		
		// Zeichnen der Krone aus einzelnen Blattgruppen mit verschiedenen Farben
		g.setColor(darkLeafColor);
		g.fillOval(x + trunkWidth / 2 - (int)(LEAF_RADIUS * 0.12), y + RADIUS / 6, (int)(LEAF_RADIUS * 0.75), (int)(LEAF_RADIUS * 0.75));
		g.setColor(lightLeafColor);
		g.fillOval(x - LEAF_RADIUS / 2 + trunkWidth / 2, y - LEAF_RADIUS / 2 + trunkWidth / 2, LEAF_RADIUS, LEAF_RADIUS);
		g.setColor(leafColor);
		g.fillOval(x + trunkWidth / 2 - (int)(LEAF_RADIUS * 0.66), y + RADIUS / 6, (int)(LEAF_RADIUS * 0.75), (int)(LEAF_RADIUS * 0.75));		
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
