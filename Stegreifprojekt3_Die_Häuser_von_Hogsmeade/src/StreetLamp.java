import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Straßenlaternen sind eher kleine Elemente, auf die beim Erstellen nur wenig eingewirkt werden kann.
 * 
 * Sie haben eine fixe Größe und ein fixes Aussehen
 * 
 * Es werden Getter für die Koordinaten angeboten
 * 
 * @author THO
 *
 */
public class StreetLamp {

	// Attributes
	private int x;
	private int y;
	
	private static final int HEIGHT = 70;
	private static final int WIDTH = 6;
	private int lightRadius = 20;
	
	private static Color POST_COLOR = Color.DARK_GRAY;
	private static Color LIGHT_COLOR = Color.ORANGE;
	
	/**
	 * Konstruiert das Objekt und setzt die Koordinaten
	 * 
	 * @param x,y Koordinaten an denen die Laterne gezeichnet werden soll
	 * 
	 */
	public StreetLamp(int x, int y) {
		this.x = x;
		this.y = y - StreetLamp.HEIGHT;
	}
	
	/**
	 * Zeichnet aus geometrischen Formen eine Straßenlaterne, die Laterne an sich wird mit Transparenz gezeichnet 
	 * um einen Glas-Effekt zu simulieren
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 * @param dayTime wird genutzt um je nach Tageszeit eine leuchtende oder nicht leuchtende Laterne zu zeichnen
	 */
	public void draw(Graphics2D g, boolean dayTime) {
		// Zeichnet des Pfostens
		g.setColor(POST_COLOR);
		g.fillRect(x, y, WIDTH, HEIGHT);
		
		// Einstellen der Transparenz
		AlphaComposite ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
		g.setComposite(ac1);
		
		// Zeichnen der Laterne
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(x - lightRadius / 2 + WIDTH / 2, y - lightRadius / 2, lightRadius, lightRadius);
		if(!dayTime) {
			g.setColor(LIGHT_COLOR);
			g.fillArc(x - lightRadius / 2 + WIDTH / 2 , y - lightRadius / 2, lightRadius, lightRadius, 180, 180);
		}
		
		// Zurücksetzen der Transparenz
		ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g.setComposite(ac1);
		
		// Zeichnen des Laternengehäuses
		g.setColor(POST_COLOR);
		g.fillArc(x - lightRadius / 2 + WIDTH / 2 , y - lightRadius / 2, lightRadius, lightRadius, 0, 180);
	}
	
	// Getter und Setter
	public int getHeight() {
		return HEIGHT;
	}
	
	public int getWIDTH() {
		return WIDTH;
	}
	
}
