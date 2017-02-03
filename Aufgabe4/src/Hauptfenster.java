import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.controller.*;

/**
 * Das Hauptfenster wird erstellt
 * Spielfeld eingefuegt, Navigator eingefuegt, Hauptfenster wird Navigator uebergeben
 * MenuBar eingefuegt Hauptfenster wird uebergeben, Toolbar eingefuegt
 * Popup eingefuegt Hauptfenster wird uebergeben
 * Model Instanz erzeugt, Controller Model uebergeben
 * Methoden: Neues Spiel erzeugen, Controller get-Methode, Spielfeld neuzeichnen, Toolbar get-Methode,
 * Model get-Method, Popup get-Methode, MenuBar get-Methode, updateLookandFeel
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
	// Array fuer Spiel-Figuren
	private AbstractPawn[] pawns;
	private MenuBar mb;
	private Toolbar toolbar = new Toolbar();
	private Popup pop;
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
 	
		mb = new MenuBar(this);
		//MenuBar hinzufuegen
		this.setJMenuBar(mb);
		
		//toolbar einfuegen
		this.add(toolbar, BorderLayout.PAGE_START);
		
		this.pack();
		
		//Position Bildschirm-Mitte setLocationRelativeTo()
		this.setLocationRelativeTo(null);
	    
		// Fenster sichtbar machen
		this.setVisible(true);

		//Hauptfenster der Navogatorinstanz uebergeben
		navigator = new Navigator(this);
		pop = new Popup(this);
	    //ComponentListener beim Hauptfenster registrieren, ListenerFenster-Objekt uebergeben
	    this.addComponentListener(new ListenerFenster(navigator));
		
		//DionaRapModel Instanz
		model = new DionaRapModel();
		// Fortschritt (Gegner) Toolbar
		this.getToolbar().setStartGegner(model.getOpponentCount());
	    
	    //Array wieviele Spielfiguren auf dem Spielfeld
	    pawns = model.getAllPawns();
		
	    //Aufruf Spielfeld neu zeichnen
	    redrawSpielfeld();
	    
	    //Neue ListenerModel Insatanz Parameter Hauptfenster
	    model.addModelChangedEventListener(new ListenerModel(this));
	    
	    //Model dem Controller uebergeben
	    controller = new DionaRapController(model);
	    
	    //Neue Instanz von ListenerNumpad Parameter Hauptfenster
	    //Listener Numpad KeyListener uebergeben
	    this.addKeyListener(new ListenerNumpad());

	    //Listener Fokus zurueckholen
	    this.requestFocus();	    
	}
		

	public static void main(String[] args)
	{   // Instantz der Anwendungsklasse erzeugen
		new Hauptfenster();
		
	}
	/**Neues Spiel erzeugen*/
	public void newGame(){
		//DionaRapModel Instanz
		model = new DionaRapModel();
		// Fortschritt (Gegner) Toolbar
		this.getToolbar().setStartGegner(model.getOpponentCount());
		this.getToolbar().setScore(0);
		this.getToolbar().setProgress(model.getOpponentCount());
		controller = new DionaRapController(model);
		model.addModelChangedEventListener(new ListenerModel(this));
		pawns=model.getAllPawns();
		redrawSpielfeld();
	}
	
	/**Controller mit get-Methode holen*/
	public DionaRapController getController(){
		return this.controller;
	}
	/**Spielfeld neu zeichnen*/
	public void redrawSpielfeld(){
		this.spielfeld.deletePawns();
		for(int i=0; i<pawns.length; i++){
			//Spielfeld Pawns uebergeben
			this.spielfeld.drawPawn(pawns[i]);
	    }
	}
	/**Spielfeld mit get-Methode holen*/
	public Spielfeld getSpielfeld(){
		return spielfeld;
	}
	/**Toolbar holen*/
	public Toolbar getToolbar(){
		//toolbar.setVisible(false);
		return toolbar;
	}
	/**Navigator holen*/
	public Navigator getNavigator(){
		return navigator;
	}
	
	/**Model mit get-Methode holen*/
	public DionaRapModel getDionaRapModel(){
		return model;
	}
	/**Popup holen*/
	public Popup getPopup(){
		return pop; 
	}
	/**MenuBar holen*/
	public MenuBar getMenu(){
		return mb;
	}

	/**Methode updateLookandFeel*/
	public void updateLookandFeel(String name){
		LookAndFeelInfo[] lafi = UIManager.getInstalledLookAndFeels();
		for(int i=0; i < lafi.length; i++){
			lafi[i].toString();
			if(lafi[i].getName().equals(name)){
				try {
					UIManager.setLookAndFeel(lafi[i].getClassName());
					SwingUtilities.updateComponentTreeUI(this);
					this.pack();
					SwingUtilities.updateComponentTreeUI(navigator);
					navigator.pack();
					navigator.updatePosition();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}
}
