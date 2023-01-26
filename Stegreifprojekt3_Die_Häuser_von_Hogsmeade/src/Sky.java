import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Sky {
	
	// Attribute
	int amount;
	Star[] stars;
	Cloud[] clouds;
	
	// Konstruktor
	public Sky(int amount) {
		this.amount = amount;
		this.stars = new Star[amount];
		for(int i = 0; i < amount; i++) {
			this.stars[i] = new Star(i);
		}
		this.clouds = new Cloud[amount / 5];
		for(int i = 0; i < clouds.length; i++) {
			this.clouds[i] = new Cloud(i);
		}
	}
	
	public void drawSky(Graphics2D g, Color sky) {
		g.setColor(sky);
		g.fillRect(0, 0, HogsmeadeApp.getWidth(), HogsmeadeApp.getHeight());
	}
	
	// Objekt-Methoden	
	public void drawClouds(Graphics2D g) {
		AlphaComposite ac1 = AlphaComposite.getInstance(
		AlphaComposite.SRC_OVER, 0.6f);
		g.setComposite(ac1);
		for(int i = 0; i < clouds.length; i++) {
			clouds[i].draw(g);
		}
		ac1 = AlphaComposite.getInstance(
		AlphaComposite.SRC_OVER, 1f);
		g.setComposite(ac1);
	}
	
	public void drawStars(Graphics g) {
		for(int i = 0; i < stars.length; i++) {
			stars[i].draw(g);
		}
	}
	
}
