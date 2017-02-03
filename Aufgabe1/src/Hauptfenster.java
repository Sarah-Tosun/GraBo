import java.awt.*;
import javax.swing.*;

public class Hauptfenster extends JFrame {

	// Diese Variable wird benoetigt, da JFrame das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	// erstellt Instanz von Spielfeld
	private Spielfeld spielfeld = new Spielfeld();
	// erstellt Instanz der Navigation und übergib Hauptfenster an Navigator
	private Navigator navigator;
	
	public Hauptfenster ()
	{   //Fenstertitel wird uebergeben
		super("DionaRap");
				
	    // ContentPane erzeugen fuer BorderLayout
	    Container content = this.getContentPane();
	    
	    //BorderLayout
		content.setLayout(new BorderLayout());
				
		//Spielfeld einfuegen
		this.add(spielfeld);   	
	    
		// Klick auf X im Fensterbalken Anwendung mit exit(0) beenden
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 			   
		this.pack();
		
		//Position Bildschirm-Mitte setLocationRelativeTo()
		this.setLocationRelativeTo(null);
	    
		// Fenster sichtbar machen
		this.setVisible(true);
	    
		//Hauptfenster der Navogatorinstanz übergeben
		navigator = new Navigator(this);
				
	}
		
	public static void main(String[] args)
	{   // Instantz der Anwendungsklasse erzeugen
		new Hauptfenster();
	}
	
}
