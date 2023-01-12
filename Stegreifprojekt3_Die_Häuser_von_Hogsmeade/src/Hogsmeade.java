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
public class Hogsmeade extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private final static Color nightSky = Color.BLACK;
	private final static Color daySky = new Color(50, 100, 200);
	public final static int streetHeight = 100;
	
	private static House house = new House(50, HogsmeadeApp.getHeight() - streetHeight - 200, 150, 200, 3, 3,Color.GREEN);
	private static House house2 = new House(250, HogsmeadeApp.getHeight() - streetHeight - 150, 200, 150, 5, 2,Color.MAGENTA);
	private static House house3 = new House(500, HogsmeadeApp.getHeight() - streetHeight - 250, 150, 250, 4, 5, Color.BLUE);
	Tree tree = new Tree(450, HogsmeadeApp.getHeight() - streetHeight - 100, 50, 100);
	Tree tree2 = new Tree(200, HogsmeadeApp.getHeight() - streetHeight - 100, 50, 100);
	StreetLamp lamp = new StreetLamp(20, HogsmeadeApp.getHeight() - streetHeight - 100, 100);
	StreetLamp lamp2 = new StreetLamp(660, HogsmeadeApp.getHeight() - streetHeight - 100, 100);
	Sun sun = new Sun(HogsmeadeApp.getWidth() / 2, 50, 50);

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
		
		// Beispiel fuer das Zeichnen des Himmels
		if(sun.getDayTime()) {
			g2d.setColor(daySky);	
		} else {
			g2d.setColor(nightSky);
		}
		g2d.fillRect(0, 0, getWidth(), getHeight());
		//hier wird alles gezeichnet ...
		
		// Sterne und Sonne/Mond
		sun.draw(g2d);
		
		// Straße
		g.setColor(Color.GRAY);
		g2d.fillRect(0, getHeight() - streetHeight, getWidth(), getHeight());
		
		// Häuser
		house.draw(g2d);
		house2.draw(g2d);
		house3.draw(g2d);
		
		// Bäume
		tree.draw(g2d);
		tree2.draw(g2d);
		
		// Lanternen
		lamp.draw(g2d, sun.getDayTime());
		lamp2.draw(g2d, sun.getDayTime());
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
		
		sun.setDayTime(sun.switchTime(x, y));
		house.setLight(house.switchLight(x, y));
		house2.setLight(house2.switchLight(x, y));
		house3.setLight(house3.switchLight(x, y));
		
		// hier sollte dann der Maus-Event entsprechend verarbeitet werden
		
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
}
