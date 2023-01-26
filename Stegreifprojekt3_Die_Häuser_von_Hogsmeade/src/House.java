import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class House {

	// Attributes
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private final int windowSize;
	private final int frameSize = 4; 
	private int doorSlot;
	
	private int chimneyPosition;
	private final int chimneyOffsetY = roofHeight / 2;
	private int chimneyWidth = 20;
	private int chimneyHeight = 20 - chimneyOffsetY;
	
	
	private Color color;
	private final Color windowColorOff = new Color(168, 199, 233);
	private final Color windowColorOn = Color.YELLOW;
	private final Color doorColor = new Color(112, 79, 19);
	private final Color frameColor = Color.BLACK;
	private final Color chimneyColor = new Color(129, 99, 45);
	private final Color roofColor = Color.GRAY;
	
	private boolean lightOn;
	
	private int stories;
	private int storyHeight = 60;
	private static int roofHeight = 20;
	private int windows;
	
	// Constructor
	public House(int x, int y, int width, int stories, Color color) {
		// random stories
		this.stories = (int)(Math.random() * 3 + 2);
		
		// --> Story-Parameter entfernen
		
		// this.stories = stories;
		this.height = this.stories * storyHeight;
		this.width = width;
		
		this.x = x;
		this.y = y - this.height - roofHeight;
		
		// Test random colors
		
		Random rand = new Random();
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		Color randomColor = new Color(r, g, b);
		
		randomColor = randomColor.brighter();
		
		// Test random windows
		this.windows = (int)(Math.random() * 2 + 3);
		
		this.color = randomColor;
		this.windowSize = width / (windows+2);
		this.doorSlot = (int)(Math.random() * windows);
		
		this.chimneyPosition = (int)(Math.random() * (width - chimneyWidth + 1));
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
		// Haus + Fenster
		g.setColor(color);
		drawStories(g);
		
		// Dach
		g.setColor(roofColor);
		g.fillRect(x, y, width, roofHeight);
		
		// Schornstein
		g.setColor(chimneyColor);
		g.fillRect(x + chimneyPosition, y - chimneyHeight, chimneyWidth, chimneyHeight + chimneyOffsetY);
		g.setColor(roofColor);
		g.fillRect(x + chimneyPosition, y - chimneyHeight - 10, chimneyWidth, 10);
	}
	
	public void drawStories(Graphics g) {
		Color newColor = color;
		for(int j = 0; j < stories; j++) {
			
			g.setColor(newColor);
			g.fillRect(x, y + roofHeight + storyHeight * j, width, storyHeight);
			newColor = newColor.darker();
			
			drawWindows(g, j);
		}
	}
	
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
				g.fillRect(windowX - frameSize / 2, windowY - frameSize / 2, windowSize + frameSize, windowSize + frameSize);
				
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
				g.fillRect(windowX - frameSize / 2, windowY - frameSize / 2, windowSize + frameSize,  windowSize + b + frameSize / 2);
				
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
