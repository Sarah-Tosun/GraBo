import java.awt.*;
import javax.swing.*;

public class Navigator extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tastatur tastatur = new Tastatur();
	
	// Position und Groessee des JFrames ermitteln mit getWidth() und getHeight() oder getBounds()
	// Kindfester von Hauptfenster erzeugen
	// Navigator erhaellt Hauptfenster
	public Navigator(Hauptfenster hauptfenster)
	{	    
		// Hauptfenster JWindow uebergeben
		super(hauptfenster);
		
		//Navigatorposition: Postition des Hauptfensters ermitteln x-Koord. + Breite des Hauptfensters + 10 (Abstand) und y-Koord.
		this.setLocation(hauptfenster.getX() + hauptfenster.getWidth() + 10, hauptfenster.getY());
		
		//Tastatur einfuegen
		this.add(tastatur);
		
		this.pack();
		this.setVisible(true);
		
		
	}
	
	
}
