import java.awt.Graphics;

public class StarrySky {
	
	// Attribute
	int amount;
	Star[] stars;
	
	// Konstruktor
	public StarrySky(int amount) {
		this.amount = amount;
		this.stars = new Star[amount];
		for(int i = 0; i < amount; i++) {
			this.stars[i] = new Star(i);
		}
	}
	
	// Objekt-Methoden
	public void draw(Graphics g) {
		for(int i = 0; i < amount; i++) {
			stars[i].draw(g);
		}
	}
	
}
