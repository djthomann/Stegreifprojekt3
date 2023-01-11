import java.awt.Color;
import java.awt.Graphics;

public class Sun {

	// Attribute/Felder
	private int x;
	private int y;
	
	private int sunRadius;
	private int maxStarRadius = 5;
	
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
		if(x >= getX() && x <= getX() + getRadius() && y >= getY() && y <= getY() + getRadius()) {
			// x und y liegen in der Bounding Box der Sonne
			return !dayTime;
		} else {
			return dayTime;
		}
		
	}
	
	public void draw(Graphics g) {
		g.setColor(dayColorFrame);
		g.fillOval(x-5, y-5, sunRadius+10, sunRadius+10);
		if(dayTime) {
			g.setColor(dayColor);
		} else {
			g.setColor(nightColor);
			drawStars(g);
		}
		g.fillOval(x, y, sunRadius, sunRadius);
	}
	
	public void drawStars(Graphics g) {
		g.setColor(nightColor);
		for(int i = 0; i < 100; i++) {
			int posX = (int)(Math.random() * HogsmeadeApp.getWidth());
			int posY = (int)(Math.random() * HogsmeadeApp.getHeight() - Hogsmeade.streetHeight);
			g.fillOval(posX, posY, maxStarRadius / (i % 5 + 1), maxStarRadius / (i % 5 + 1));
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
