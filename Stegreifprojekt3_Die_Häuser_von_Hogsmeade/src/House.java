import java.awt.Color;
import java.awt.Graphics;

public class House {

	// Attributes
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private final int windowSize;
	private final int frameSize = 4; 
	
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
	private int storyHeight;
	private static int roofHeight = 20;
	private int windows;
	
	// Constructor
	public House(int x, int y, int width, int height, int windows, int stories, Color color) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.color = color;
		this.windows = windows;
		this.windowSize = width / (windows+3);
		
		this.stories = stories;
		this.storyHeight = (height - roofHeight) / stories;
		
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
			g.fillRect(x, y  + + roofHeight + storyHeight * j, width, storyHeight);
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
			if(i == 0) {
				windowX = x + a / 2;
			} else {
				windowX = x + a * (i+1) - a / 2;
			}
			g.setColor(frameColor);
			g.fillRect(windowX  + windowSize * i - frameSize / 2, y + storyHeight * (currentStory + 1) - windowSize - frameSize / 2, windowSize + frameSize, windowSize + frameSize);
			if(lightOn) {
				g.setColor(windowColorOn);
			} else {
				g.setColor(windowColorOff);
			}
			g.fillRect(windowX  + windowSize * i, y + (storyHeight) * (currentStory + 1) - windowSize, windowSize, windowSize);
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
