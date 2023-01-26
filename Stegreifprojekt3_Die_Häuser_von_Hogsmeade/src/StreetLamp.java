import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class StreetLamp {

	// Attributes
	private int x;
	private int y;
	
	private int height = 70;
	private int width = 6;
	private int lanternRadius = 10;
	private int lightRadius = 20;
	
	private Color postColor = Color.DARK_GRAY;
	private Color lightColor = Color.ORANGE;
	
	// Konstruktor
	public StreetLamp(int x, int y) {
		this.x = x;
		this.y = y - this.height;
	}
	
	// Objekt-Methoden
	public void draw(Graphics2D g, boolean dayTime) {
		g.setColor(postColor);
		g.fillRect(x, y, width, height);
		AlphaComposite ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
		g.setComposite(ac1);
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(x - lightRadius / 2 + width / 2, y - lightRadius / 2, lightRadius, lightRadius);
		if(!dayTime) {
			g.setColor(lightColor);
			g.fillArc(x - lightRadius / 2 + width / 2 , y - lightRadius / 2, lightRadius, lightRadius, 180, 180);
		}
		ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g.setComposite(ac1);
		g.setColor(postColor);
		g.fillArc(x - lightRadius / 2 + width / 2 , y - lightRadius / 2, lightRadius, lightRadius, 0, 180);
	}
	
}
