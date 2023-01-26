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
 */

// TODO: CHANGE NAME FOR CONSTANTS 
// TODO: CONSTRUCT EMPTY HOUSES 

public class Hogsmeade extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	final Color nightSky;
	final Color daySky;
	final static int streetHeight = 100;
	final int numberOfHouses = 5;
	
	// Objects in the scene
	private House[] houses;
	private Tree[] trees;
	private StreetLamp[] lamps;
	private Sun sun;
	private Sky sky;
	
	/**
	 * Initialisierung des Panels und setzen des MouseListerns
	 * fuer die Verwendung von Maus-Ereignissen
	 */
	public Hogsmeade(){
		
		/* registriert Panel als MouseListener, so dass die jeweilige spezialisierte 
		 * Methode aufgerufen wird, wenn ein Mausereignis innerhalb des Panels ausgeloest 
		 * wird
		 */
		this.addMouseListener(this);
		
		// Initialisiere Haeuser, Baeume, Sonne ...
		nightSky = Color.BLACK;
		daySky = new Color(50, 100, 200);
		
		houses = new House[numberOfHouses];
		houses[0] = new House(50, HogsmeadeApp.getHeight() - streetHeight , 150, 3,Color.GREEN);
		houses[1] = new House(250, HogsmeadeApp.getHeight() - streetHeight, 200, 2,Color.MAGENTA);
		houses[2] = new House(500, HogsmeadeApp.getHeight() - streetHeight, 150, 4, Color.BLUE);
		houses[3] = new House(670, HogsmeadeApp.getHeight() - streetHeight, 150, 2, Color.RED);
		houses[4] = new House(880, HogsmeadeApp.getHeight() - streetHeight, 150, 5, Color.YELLOW);
		
		trees = new Tree[3];
		trees[0] = new Tree(215, HogsmeadeApp.getHeight() - streetHeight, 50, 80);
		trees[1] = new Tree(460, HogsmeadeApp.getHeight() - streetHeight, 50, 100);
		trees[2] = new Tree(840, HogsmeadeApp.getHeight() - streetHeight, 50, 80);
		
		lamps = new StreetLamp[3];
		lamps[0] = new StreetLamp(20, HogsmeadeApp.getHeight() - streetHeight);
		lamps[1] = new StreetLamp(660, HogsmeadeApp.getHeight() - streetHeight);
		lamps[2] = new StreetLamp(1050, HogsmeadeApp.getHeight() - streetHeight);
		
		sky = new Sky(100);
		
		sun = new Sun(HogsmeadeApp.getWidth() / 2, 50, 50);
	}
	
	/** 
	 * Zeichnen der Strasse.
	 * 
	 * Umsetzung der Methode
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 * 
	 * @param g Graphik-Kontext, auf dem die Landschaft gezeichnet wird
	 */
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paint(g);
		
		// Himmel
		if(sun.getDayTime()) {
			sky.drawSky(g2d, daySky);
		} else {
			sky.drawSky(g2d, nightSky);
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
		g2d.fillRect(0, getHeight() - streetHeight, getWidth(), streetHeight);
		g2d.setColor(Color.GRAY.darker().darker());
		g2d.fillRect(0, getHeight() - streetHeight / 2, getWidth(), streetHeight / 2);
		g2d.setColor(Color.GRAY.darker());
		g2d.fillRect(0, getHeight() - streetHeight / 2 - 20, getWidth(), 30);
		
		// Häuser
		for(int i = 0; i < numberOfHouses; i++) {
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
	 * Umsetzung der Methode
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
		
		for(int i = 0; i < numberOfHouses; i++) {
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
	public static int getStreetHeight() {
		return streetHeight;
	}
}
