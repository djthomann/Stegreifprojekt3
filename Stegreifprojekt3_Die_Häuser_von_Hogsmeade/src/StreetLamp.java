import java.awt.Color;
import java.awt.Graphics;

public class StreetLamp {

	// Attributes
	private int x;
	private int y;
	
	private int height;
	private int width = 6;
	private int lanternRadius = 10;
	private int lightRadius = 20;
	
	private Color postColor = Color.DARK_GRAY;
	private Color lightColor = Color.ORANGE;
	
	// Konstruktor
	public StreetLamp(int x, int y, int height) {
		this.x = x;
		this.y = y;
		
		this.height = height;
	}
	
	// Objekt-Methoden
	public void draw(Graphics g, boolean dayTime) {
		g.setColor(postColor);
		g.fillRect(x, y, width, height);
		
		if(!dayTime) {
			g.setColor(lightColor);
			g.fillOval(x - lightRadius / 2 + width / 2 , y - lightRadius / 2, lightRadius, lightRadius);
		}
		
		g.setColor(Color.GRAY);
		g.fillOval(x - lanternRadius / 2 + width / 2 , y - lanternRadius / 2, lanternRadius, lanternRadius);
	}
	
}
