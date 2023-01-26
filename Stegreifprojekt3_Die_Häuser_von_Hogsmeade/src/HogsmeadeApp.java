import javax.swing.JFrame;

/**
 * Starter der Hogsmaede-Applikation.
 * 
 * Applikation wird in einem Standard-Fenster der Groesse 700 x 500 angezeigt. --> Abgeändert thomann
 *
 * @author berdux
 *
 */
public class HogsmeadeApp {
	/**
	 * Starten der Applikation und Anzeige des Fensters
	 * 
	 * @param args wird nicht interpretiert
	 */
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 500;
	
	// Getter und Setter
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
	public static void main(String[] args) {
		// Initialisierung des Frames
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        
        
        /* Instanziierung der eigentlichen Anzeige, 
         * die in in der Klasse Hogsmaede definiert ist.
         * Hogsmaede ist als JPanel eine Darstellungsflaeche,
         * in der die Landschaft gezeichnet wird.
         */
		Hogsmeade myDisplay = new Hogsmeade();
        jFrame.add(myDisplay);
        jFrame.setVisible(true);
	}
}
