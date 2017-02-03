import java.awt.*;
import javax.swing.*;

/**
 * Navigator ist das Kindfenster vom Hauptfenster
 * Tastatur wird in das Kindfenster gesetzt.
 * Postition des Kindfensters wird ermittelt
 * @author Sarah Tosun
 * @version DionaRap
 */

public class Navigator extends JWindow {

	private static final long serialVersionUID = 1L;
	private Tastatur tastatur;
	
	// Kindfester von Hauptfenster erzeugen
	// Navigator erhaellt Hauptfenster
	public Navigator(Hauptfenster hauptfenster)
	{	    
		// Hauptfenster JWindow uebergeben
		super(hauptfenster);
		
		//Navigatorposition: Postition des Hauptfensters ermitteln x-Koord. + Breite des Hauptfensters + 10 (Abstand) und y-Koord.
		this.setLocation(hauptfenster.getX() + hauptfenster.getWidth() + 5, hauptfenster.getY());
		
		Polygon p = new Polygon();
		
		p.addPoint(60,0);
		p.addPoint(120,0);
		p.addPoint(180,60);
		p.addPoint(180,120);
		p.addPoint(120,180);
		p.addPoint(60,180);
		p.addPoint(0, 120);
		p.addPoint(0, 60);
		
		this.setShape(p);	
		
		
		//Tastaturinstanz erzeugen uebergabe Parameter Hauptfenster
		tastatur = new Tastatur(hauptfenster); 
		//Tastatur einfuegen
		this.add(tastatur);
		this.pack();
		this.setVisible(true);
		
	}	
}
