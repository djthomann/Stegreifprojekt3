import java.awt.Graphics;

public class StarrySky {
	
	// Attribute
	int amount;
	Star[] stars;
	Cloud[] clouds;
	
	// Konstruktor
	public StarrySky(int amount) {
		this.amount = amount;
		this.stars = new Star[amount];
		for(int i = 0; i < amount; i++) {
			this.stars[i] = new Star(i);
		}
		this.clouds = new Cloud[amount / 10];
		for(int i = 0; i < clouds.length; i++) {
			this.clouds[i] = new Cloud();
		}
	}
	
	// Objekt-Methoden	
	public void drawClouds(Graphics g) {
		for(int i = 0; i < clouds.length; i++) {
			clouds[i].draw(g);
		}
	}
	
	public void drawStars(Graphics g) {
		for(int i = 0; i < stars.length; i++) {
			stars[i].draw(g);
		}
	}
	
}
