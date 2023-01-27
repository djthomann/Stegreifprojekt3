import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * Basis-Panel stellt Grundfunktionen fuer den Aufbau interaktiver Anwendungen zur
 * Verfuegung.
 *  
 * Alle Mausereignisse koennen in einzelnen Methoden verarbeitet werden. 
 *  
 * @author Joerg Berdux
 * @version 1.0
 * 
 * Es werden Farben und Objekt-Array-Längen initialisiert und Objekte deklariert und gezeichnet
 * 
 * Zudem werden für die Konstanten, die die Szene gestalten Getter() angeboten.
 * 
 * @author david, powder, marjan
 * @version 2.0
 * 
 */

public class Hogsmeade extends JPanel implements MouseListener {

	// Class fields
	private static final long serialVersionUID = 1L;
	
	// Scene fields
	private final static Color NIGHT_SKY_COLOR = Color.BLACK;
	private final static Color DAY_SKY_COLOR = new Color(50, 100, 200);
	private final static int STREET_HEIGHT = 100;
	
	// lengths of object arrays
	private final static int NUMBER_OF_HOUSES = 5;
	private final static int NUMBER_OF_TREES = 3;
	private final static int NUMBER_OF_LAMPS = 3;
	
	// Instance fields
	// Objects in the scene
	private House[] houses;
	private Tree[] trees;
	private StreetLamp[] lamps;
	private Sun sun;
	private Sky sky;
	
	/**
	 * Initialisierung des Panels und setzen des MouseListerns
	 * fuer die Verwendung von Maus-Ereignissen
	 * 
	 * Initialisieren der Arrays und instanziieren
	 */
	public Hogsmeade(){
		
		/* registriert Panel als MouseListener, so dass die jeweilige spezialisierte 
		 * Methode aufgerufen wird, wenn ein Mausereignis innerhalb des Panels ausgeloest 
		 * wird
		 */
		this.addMouseListener(this);
		
		// Initialisiere der Szenen-Objekte
		houses = new House[NUMBER_OF_HOUSES];
		houses[0] = new House(50, HogsmeadeApp.getHeight() - STREET_HEIGHT , 150);
		houses[1] = new House(250, HogsmeadeApp.getHeight() - STREET_HEIGHT, 200);
		houses[2] = new House(500, HogsmeadeApp.getHeight() - STREET_HEIGHT, 150);
		houses[3] = new House(670, HogsmeadeApp.getHeight() - STREET_HEIGHT, 150);
		houses[4] = new House(880, HogsmeadeApp.getHeight() - STREET_HEIGHT, 150);
		
		trees = new Tree[NUMBER_OF_TREES];
		trees[0] = new Tree(215, HogsmeadeApp.getHeight() - STREET_HEIGHT, 50, 80);
		trees[1] = new Tree(460, HogsmeadeApp.getHeight() - STREET_HEIGHT, 50, 100);
		trees[2] = new Tree(840, HogsmeadeApp.getHeight() - STREET_HEIGHT, 50, 80);
		
		lamps = new StreetLamp[NUMBER_OF_LAMPS];
		lamps[0] = new StreetLamp(20, HogsmeadeApp.getHeight() - STREET_HEIGHT);
		lamps[1] = new StreetLamp(660, HogsmeadeApp.getHeight() - STREET_HEIGHT);
		lamps[2] = new StreetLamp(1050, HogsmeadeApp.getHeight() - STREET_HEIGHT);
		
		sky = new Sky(100);
		
		sun = new Sun(HogsmeadeApp.getWidth() / 2, 50, 50);
	}
	
	/** 
	 * Zeichnen der Strasse.
	 * 
	 * Nacheinander zeichnen des Himmels, der Sonne/Mond, Wolken und Sterne sowie der Straße und Szenen-Objekte
	 * Für die Szenen-Objekte wird einfach über das verwaltende Array geloopt und die jeweilige draw() methode aufgerufen
	 * 
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 * 
	 * @param g Graphik-Kontext, auf dem die Landschaft gezeichnet wird
	 */
	public void paint(Graphics g){
		// Setzen des Grafik-Kontexts
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paint(g);
		
		// Himmel
		if(sun.getDayTime()) {
			sky.drawSky(g2d, DAY_SKY_COLOR);
		} else {
			sky.drawSky(g2d, NIGHT_SKY_COLOR);
		}
		
		// Sonne/Mond
		sun.draw(g2d);
		
		// Wolken und Sterne
		if(sun.getDayTime()) {
			sky.drawClouds(g2d);
		} else {
			sky.drawStars(g2d);
		}
		
		// Straße
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, getHeight() - STREET_HEIGHT, getWidth(), STREET_HEIGHT);
		g2d.setColor(Color.GRAY.darker().darker());
		g2d.fillRect(0, getHeight() - STREET_HEIGHT / 2, getWidth(), STREET_HEIGHT / 2);
		g2d.setColor(Color.GRAY.darker());
		g2d.fillRect(0, getHeight() - STREET_HEIGHT / 2 - 20, getWidth(), 30);
		
		// Häuser
		for(int i = 0; i < NUMBER_OF_HOUSES; i++) {
			houses[i].draw(g2d);
		}
		
		// Bäume
		for(int i = 0; i < trees.length; i++) {
			trees[i].draw(g2d);
		}
		
		// Lanternen
		for(int i = 0; i < lamps.length; i++) {
			lamps[i].draw(g2d, sun.getDayTime());
		}
	}
	
	
	/** 
	 * Aufloesung der x, y-Position, an der Mausbutton betaetigt wurde.
	 * 
	 * Das Ereignis wird von jedem Objekt, welches klickbar ist (Sonne und Häuser) einzeln verarbeitet.
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * 
	 * @param e Maus-Ereignis, das ausgeloest wurde 
	 */
	public void mouseClicked(MouseEvent e){
		int x, y;
		
		x = e.getX(); // x-Koordinate, an der Mausereignis stattgefunden hat
		y = e.getY(); // y-Koordinate, an der Mausereignis stattgefunden hat
		
		// Das Maus-Ereignis wird von den entsprechenden klickbaren Objekten verarbeitet
		
		sun.setDayTime(sun.switchTime(x, y));
		
		for(int i = 0; i < NUMBER_OF_HOUSES; i++) {
			houses[i].setLight(houses[i].switchLight(x, y));
		}
		
		// nach jeder Veraenderung soll der Graphik-Kontext neu gezeichnet werden
		repaint();
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e){
		// diese Methode bleibt einfach leer
	}

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
	
	// Getter und Setter
	public static Color getNightSkyColor() {
		return NIGHT_SKY_COLOR;
	}
	
	public static Color getDaySkyColor() {
		return DAY_SKY_COLOR;
	}
	
	public static int getStreetHeight() {
		return STREET_HEIGHT;
	}
	
	public static int getNumberOfHouses() {
		return NUMBER_OF_HOUSES;
	}
	
	public static int getNumberOfTrees() {
		return NUMBER_OF_TREES;
	}
	
	public static int getNumberOfLamps() {
		return NUMBER_OF_LAMPS;
	}
}
