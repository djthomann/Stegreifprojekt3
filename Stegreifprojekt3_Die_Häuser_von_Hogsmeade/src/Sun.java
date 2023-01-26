import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Sun {

	// Attribute/Felder
	private int x;
	private int y;
	
	private int sunRadius;
	
	private boolean dayTime = true;
	
	private Color dayColor = new Color(237, 255, 78);
	private Color dayColorFrame = new Color(244, 111, 82);
	private Color nightColor = Color.WHITE;
	
	// Konstruktor
	public Sun(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.sunRadius = radius;
	}
	
	// Objekt-Methoden 
	public boolean switchTime(int x, int y) {
		// Mittelpunkt der Sonne: getX() + getRadius() / 2 | getY() + getRadius() / 2
		int centerX = getX() + getRadius() / 2; 
		int centerY = getY() + getRadius() / 2; 
		
		// Abstand von Maus zu Sonnenmittelpunkt: 
		double r = Math.sqrt(Math.pow((centerX) - x, 2) + Math.pow((centerY) - y, 2));
		
		if(r <= getRadius() / 2) {
			// getroffen
			return !dayTime;
		} else {
			// nicht getroffen
			return dayTime;
		}
		
	}
	
	public void draw(Graphics2D g) {
		if(dayTime) {
			g.setColor(dayColorFrame);
			g.fillOval(x-5, y-5, sunRadius+10, sunRadius+10);
			g.setColor(dayColor);
			g.fillOval(x, y, sunRadius, sunRadius);
		} else {
			g.setColor(nightColor);
			g.setColor(nightColor);
			g.fillOval(x, y, sunRadius, sunRadius);
		}
	}
	
	// Getter und Setter
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRadius() {
		return sunRadius;
	}
	
	public boolean getDayTime() {
		return dayTime;
	}
	
	public void setDayTime(boolean time) {
		dayTime = time;
	}
	
}
