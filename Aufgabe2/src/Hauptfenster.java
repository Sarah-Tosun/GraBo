import java.awt.*;
import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.controller.*;

/**
 * Das Hauptfenster wird erstellt
 * Spielfeld wird hinzugefuegt, Navigator wird hinzugefuegt
 * das Hauptfenster wird Navigator uebergeben
 * Methoden (Spielfeld/Model)
 * @author Sarah Tosun
 * @version DionaRap
 */

public class Hauptfenster extends JFrame {

	// Diese Variable wird benoetigt, da JFrame das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	// erstellt Instanz von Spielfeld
	private Spielfeld spielfeld = new Spielfeld();
	// erstellt Instanz der Navigation und uebergib Hauptfenster an Navigator
	private Navigator navigator;
	//Spielinstanz
	private DionaRapModel model;
	//Controllerinstanz
	private DionaRapController controller;
	//ListenerFensterinstanz
	private ListenerFenster listFenster;
	//ListenerModelinstanz
	private ListenerModel listModel;
	// Array fuer Spiel-Figuren
	private AbstractPawn[] pawns;
		
	public Hauptfenster ()
	{   //Fenstertitel wird uebergeben
		super("DionaRap");
				
	    // ContentPane erzeugen fuer BorderLayout
	    Container content = this.getContentPane();
	    
	    //BorderLayout
		content.setLayout(new BorderLayout());
				
		//Spielfeld einfuegen
		this.add(spielfeld);   	
	    
		// Fenster schliessen X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 			   
		this.pack();
		
		//Position Bildschirm-Mitte setLocationRelativeTo()
		this.setLocationRelativeTo(null);
	    
		// Fenster sichtbar machen
		this.setVisible(true);
		
		//Hauptfenster der Navogatorinstanz uebergeben
		navigator = new Navigator(this);
		
	    //ComponentListener beim Hauptfenster registrieren, ListenerFenster-Objekt uebergeben
	    this.addComponentListener(listFenster = new ListenerFenster(navigator));
		
		//DionaRapModel Instanz
		model = new DionaRapModel();
		
		//Spieler bekommen
		//model.getPlayer();
	    
	    //Array wieviele Spielfiguren auf dem Spielfeld
	    pawns = model.getAllPawns();
		
	    //Aufruf Spielfeld neu zeichnen
	    redrawSpielfeld();
	    
	    //Neue ListenerModel Insatanz Parameter Hauptfenster
	    model.addModelChangedEventListener(listModel = new ListenerModel(this));
	    
	    //Model dem Controller übergeben
	    controller = new DionaRapController(model);
	    
	    //Neue Instanz von ListenerNumpad Parameter Hauptfenster
	    //Listener Numpad KeyListener uebergeben
	    this.addKeyListener(new ListenerNumpad());
	    this.requestFocus();
	}
		
	public static void main(String[] args)
	{   // Instantz der Anwendungsklasse erzeugen
		new Hauptfenster();
		
	}
	/**Controller mit get-Methode holen*/
	public DionaRapController getController(){
		return this.controller;
	}
	/**Spielfeld neu zeichnen*/
	public void redrawSpielfeld(){
		for(int i=0; i<pawns.length; i++){
	    	//Spielfeld Pawns uebergeben
			this.spielfeld.drawPawn(pawns[i]);
	    }
	}
	/**Spielfeld mit get-Methode holen*/
	public Spielfeld getSpielfeld(){
		return spielfeld;
	}
	/**Model mit get-Methode holen*/
	public DionaRapModel getDionaRapModel(){
		return model;
	}

}
