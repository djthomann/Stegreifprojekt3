import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Häuser sind einer der Hauptbestandteile der Straße von Hogsmeade.
 * 
 * Viele Aspekte werden vom Konstruktor zufällig gesetzt
 * 
 * Es werden Getter für die Position, die Dimension, die Hausfarbe, der aktuelle Lichtzustand, die Anzahl an Fenstern und Stockwerken angeboten
 * Es wird außerdem ein Setter für den Lichtzustand angeboten, auf den andere Objekte zugreifen können
 * 
 * @author david, powder, marjan
 * 
 */
public class House {

	// Position
	private int x;
	private int y;
	
	// Dimensionen
	private int width;
	private int height;
	
	// Stockwerke und Dach
	private int stories;
	private int storyHeight = 60;
	private static int roofHeight = 20;
	
	// Felder für Fenster(rahmen) und Türen
	private int windows;
	private final int windowSize;
	private final int WINDOW_FRAME_SIZE = 4; 
	private int doorSlot;
	
	// Felder für Schornstein
	private int chimneyPosition;
	private final int chimneyOffsetY = roofHeight / 2; // Schornstein steht mittig (y-Richtung) auf dem Dach
	private int CHIMNEY_WIDTH = 20;
	private int CHIMNEY_HEIGHT = 20 - chimneyOffsetY;
	
	// Farben
	private Color color;
	private final Color windowColorOff = new Color(168, 199, 233);
	private final Color windowColorOn = Color.YELLOW;
	private final Color doorColor = new Color(112, 79, 19);
	private final Color frameColor = Color.BLACK;
	private final Color chimneyColor = new Color(129, 99, 45);
	private final Color roofColor = Color.GRAY;
	
	// Ist das Licht an oder aus?
	private boolean lightOn;
	
	/**
	 * Konstruiert ein Haus
	 * Die Anzahl Stockwerke wird zufällig zwischen 2 und 5 gewählt
	 * Auch die Farbe wird zufällig generiert (gesamter RGB-Farbraum ist möglich)
	 * Da die Farben des Hauses im Laufe der Stockwerke immer dunkler wird, wird die Hausfarbe nach der Generierung nochmal aufgehellt (lighter() Funktion)
	 * 
	 * Die Höhe des Hauses ergibt sich aus der Zahl der Stockwerke mal der Stockwerk-Höhe
	 * 
	 * Die Anzahl der Fenster wird zufällig zwischen 2 und 4 generiert
	 * Aus Breite des Hauses und Anzahl Fenster wird eine passende Fenstergröße, die Abstände zwischen den Fenster erlaubt, errechnet.
	 * 
	 * Die Position der Tür wird zufällig im Intervall der Anzahl an Fenstern gewählt
	 * 
	 * Der Schornstein wird zufällig auf der Breite des Hauses platziert
	 * 
	 * Die Möglichkeit die Stockwerke (also indirekt die Höhe) und die Hausfarbe zu setzten wurden auskommentiert, aber im Programm behalten
	 * 
	 * @param x,y setzt die Position des Hauses
	 * @param width setzt die Breite
	 * 
	 * Optional parameters
	 * @param stories ist die Anzahl der Stockwerke
	 * @param color 
	 */
	public House(int x, int y, int width) {
		// random stories
		this.stories = (int)(Math.random() * 4 + 2);
		// optional parameter for manual number of stories: this.stories = stories;
		
		this.height = this.stories * storyHeight;
		this.width = width;
		
		this.x = x;
		this.y = y - this.height - roofHeight;
		
		// random color
		Random rand = new Random();
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		Color randomColor = new Color(r, g, b);
		randomColor = randomColor.brighter();
		this.color = randomColor;
		// optional parameter for manual color: this.color = color;
		
		// Test random windows
		this.windows = (int)(Math.random() * 2 + 3);
		this.windowSize = width / (windows+2);
		
		// door and chimney
		this.doorSlot = (int)(Math.random() * windows);
		
		this.chimneyPosition = (int)(Math.random() * (width - CHIMNEY_WIDTH + 1));
	}
	
	/**
	 * Verarbeitet ein Klick-Event. Dazu wird überprüft, ob die Koordinaten der Maus in der Bounding Box des Hauses liegen.
	 * Somit kann das Licht nur aktiviert werden, wenn der eigentliche Haus-Körper geklickt wird
	 * 
	 * @param x,y Position/Koordinaten der Maus
	 * @return wurde das Haus getroffen oder nicht 
	 */
	public boolean switchLight(int x, int y) {
		if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
			// getroffen
			return !lightOn;
		} else {
			// nicht getroffen
			return lightOn;
		}
	}
	
	/**
	 * Zeichnet ein Haus mit allen seinen Bestandteilen
	 * 
	 * Dazu werden zuerst die einzelnen Stockwerke gezeichnet. Jedes Stockwerk zeichnet seine dazugehörigen Fenster direkt mit
	 * Darüber wird ein Dach gelegt und darüber wiederum der Schornstein
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 */
	public void draw(Graphics g) {
		// Haus + Fenster
		g.setColor(color);
		drawStories(g);
		
		// Dach
		g.setColor(roofColor);
		g.fillRect(x, y, width, roofHeight);
		
		// Schornstein
		g.setColor(chimneyColor);
		g.fillRect(x + chimneyPosition, y - CHIMNEY_HEIGHT, CHIMNEY_WIDTH, CHIMNEY_HEIGHT + chimneyOffsetY);
		g.setColor(roofColor);
		g.fillRect(x + chimneyPosition, y - CHIMNEY_HEIGHT - 10, CHIMNEY_WIDTH, 10);
	}
	
	/**
	 * Zeichnet alle Stockwerke des Hauses, dabei wird pro Stockwerk die Farbe der Wand immer dunkler.
	 * Jedes Stockwerk ruft einmal die drawWindows() Methode auf
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 */
	public void drawStories(Graphics g) {
		Color newColor = color;
		for(int j = 0; j < stories; j++) {
			
			g.setColor(newColor);
			g.fillRect(x, y + roofHeight + storyHeight * j, width, storyHeight);
			newColor = newColor.darker();
			
			drawWindows(g, j);
		}
	}
	
	/**
	 * Zeichnet in das aktuelle Stockwerk die für das Haus spezifische Anzahl an Fenstern mit dazugehörigem Rahmen
	 * Die Fenster werden vertikal mittig und horizontal gleichmäßig verteilt
	 * 
	 * Die Fenster werden je nach Lichtzustand des Hauses mit einer anderen Farbe gezeichnet
	 * 
	 * Im Erdgeschoss (currentStory == stories -1) wird ein Fenster mit der Tür "ausgetauscht"
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 * @param currentStory ist das aktuelle Stockwerk
	 */
	public void drawWindows(Graphics g, int currentStory) {
		int windowX = x;		
		for(int i = 0; i < windows; i++) {
			
			// Spacing between windows | a --> left and right, b --> up and down 
			int a = (width - (windows*windowSize)) / windows;
			int b = (storyHeight - windowSize) / 2;
			
			// spacing is different for first window
			if(i == 0) {
				windowX = x + a / 2;
			} else {
				windowX = x + a * (i+1) - a / 2;
			}
			
			// calculate current window coordinates
			windowX = windowX  + windowSize * i;
			int windowY = y + (storyHeight) * (currentStory + 1) - windowSize + roofHeight - b;
			
			// if not on ground level or not at door --> draw window
			if(currentStory != stories - 1 || i != doorSlot) {
				// draw frame
				g.setColor(frameColor);
				g.fillRect(windowX - WINDOW_FRAME_SIZE / 2, windowY - WINDOW_FRAME_SIZE / 2, windowSize + WINDOW_FRAME_SIZE, windowSize + WINDOW_FRAME_SIZE);
				
				// Set window Color
				if(lightOn) {
					g.setColor(windowColorOn);
				} else {
					g.setColor(windowColorOff);
				}
				
				// draw window
				g.fillRect(windowX, windowY, windowSize, windowSize);
			} else {
				// draw door frame
				g.setColor(frameColor);
				g.fillRect(windowX - WINDOW_FRAME_SIZE / 2, windowY - WINDOW_FRAME_SIZE / 2, windowSize + WINDOW_FRAME_SIZE,  windowSize + b + WINDOW_FRAME_SIZE / 2);
				
				// draw door
				g.setColor(doorColor);
				g.fillRect(windowX, windowY, windowSize, windowSize + b);
			}		
		}
	}
	
	// Getters and Setters
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
	
	public int getWindows() {
		return windows;
	}
	
	public int getStories() {
		return stories;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean getLight() {
		return lightOn;
	}
	
	public void setLight(boolean light) {
		lightOn = light;
	}	
}
