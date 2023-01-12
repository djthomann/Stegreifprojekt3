import java.awt.Color;
import java.awt.Graphics;

public class House {

	// Attributes
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private final int windowSize;
	private final int frameSize = 2; 
	
	int chimneyPosition;
	private final int chimneyOffsetY = 10;
	private int chimneyWidth = 20;
	private int chimneyHeight = 40 - chimneyOffsetY;
	
	
	private Color color;
	private final Color windowColorOff = new Color(168, 199, 233);
	private final Color windowColorOn = Color.YELLOW;
	private final Color frameColor = Color.BLACK;
	private final Color chimneyColor = new Color(129, 99, 45);
	private final Color roofColor = Color.GRAY;
	
	private boolean lightOn;
	
	private int stories;
	private int windows;
	
	// Constructor
	public House(int x, int y, int width, int height, int windows, int stories, Color color) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.color = color;
		this.windows = windows;
		this.windowSize = width / windows - 20;
		
		this.stories = stories;
		
		chimneyPosition = (int)(Math.random() * (width - chimneyWidth + 1));
	}
	
	// Object Methods
	public boolean switchLight(int x, int y) {
		if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
			return !lightOn;
		} else {
			return lightOn;
		}
	}
	
	public void draw(Graphics g) {
		// Haus
		g.setColor(color);
		g.fillRect(x, y, width, height);
		Color darker = color.darker();
		g.setColor(darker);
		g.fillRect(x, y + (height - height / 4), width, height / 4);
		
		// Dach
		g.setColor(roofColor);
		g.fillRect(x, y, width, 20);
		
		
		// Fenster und Fensterrahme --> Methode drawWindows? --> evtl. in separate Funktionen aufteilen		
		int windowX = x;
		for(int j = 0; j < stories; j++) {
			for(int i = 0; i < windows; i++) {
				int a = (width - (windows*windowSize)) / windows;
					if(i == 0) {
						windowX = x + a / 2;
					} else {
						windowX = x + a * (i+1) - a / 2;
					}
					g.setColor(frameColor);
					g.fillRect(windowX  + windowSize * i - frameSize / 2, y + 40 * (j + 1) - frameSize / 2, windowSize + frameSize, windowSize + frameSize);
					if(lightOn) {
						g.setColor(windowColorOn);
					} else {
						g.setColor(windowColorOff);
					}
					g.fillRect(windowX  + windowSize * i, y + 40 * (j + 1), windowSize, windowSize);
			}
		}
		
		// Schornstein
		g.setColor(chimneyColor);
		g.fillRect(x + chimneyPosition, y - chimneyHeight, chimneyWidth, chimneyHeight + chimneyOffsetY);
		g.setColor(roofColor);
		g.fillRect(x + chimneyPosition, y - chimneyHeight - 10, chimneyWidth, 10);
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
