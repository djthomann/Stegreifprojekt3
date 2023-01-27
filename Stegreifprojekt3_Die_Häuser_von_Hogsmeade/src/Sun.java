import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Die Sonne bzw. in der Nacht der Mond verwaltet die Tageszeit und verarbeitet dafür ein Maus-Klick-Event
 * Die Position und Größe ist wählbar, genau wie der Durchmesser
 * 
 * Es werden Getter für die Position und Dimension der Sonne und die aktuelle Tageszeit angeboten
 * Außerdem wird es anderen Klassen erlaubt, die Tageszeit abzuändern
 * 
 * @author david, powder, marjan
 *
 */
public class Sun {

	// Position
	private int x;
	private int y;
	
	// Durchmesser
	private int diameter;
	
	// Tageszeit
	private boolean dayTime = true;
	
	// Farben
	private Color dayColor = new Color(237, 255, 78);
	private Color dayColorFrame = new Color(244, 111, 82);
	private Color nightColor = Color.WHITE;
	
	/**
	 * Konstruiert eine Sonne/einen Mond
	 * 
	 * @param x,y werden gesetzt
	 * @param diameter wird gesetzt
	 */
	public Sun(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	}
	
	/**
	 * Verarbeitet ein Klick-Event. Dazu wird geschaut, ob der Abstand der Maus zum Mittelpunkt der Sonne kleiner oder gleich
	 * dem Radius der Sonne ist. Dafür wird auf die Defintion eines Kreise (sqrt(x^2 + y^2 = r) zurückgegriffen
	 * 
	 * Wenn er kleiner ist, wurde die Sonne getroffen, ist er größer, wurde die Sonne nicht getroffen.
	 * 
	 * @param x,y sind die Koordinaten der Maus
	 * @return ob die Sonne getroffen wurde oder nicht
	 */
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
	
	/**
	 * Zeichnet die Maus an der jeweiligen Position mit der jeweiligen Farbe für Sonne oder Mond, 
	 * außerdem erhält die Sonne an ihrer Oberfläche eine sogenannte Korona 
	 * 
	 * @param g ist der mitgelieferte Grafik-Kontext
	 */
	public void draw(Graphics2D g) {
		if(dayTime) {
			g.setColor(dayColorFrame);
			g.fillOval(x-5, y-5, diameter+10, diameter+10);
			g.setColor(dayColor);
			g.fillOval(x, y, diameter, diameter);
		} else {
			g.setColor(nightColor);
			g.setColor(nightColor);
			g.fillOval(x, y, diameter, diameter);
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
		return diameter;
	}
	
	public boolean getDayTime() {
		return dayTime;
	}
	
	public void setDayTime(boolean time) {
		dayTime = time;
	}
	
}
