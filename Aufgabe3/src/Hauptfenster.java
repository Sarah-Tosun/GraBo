import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.io.File;

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
	// Array fuer Spiel-Figuren
	private AbstractPawn[] pawns;
	
	private MenuBar mb;
	private Toolbar toolbar = new Toolbar();

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
		//MenuBar hinzugügen
		this.setJMenuBar(mb);
		
		//toolbar.setLayout(new BoxLayout());
		this.add(toolbar, BorderLayout.PAGE_START);

		this.pack();
		
		//Position Bildschirm-Mitte setLocationRelativeTo()
		this.setLocationRelativeTo(null);
	    
		// Fenster sichtbar machen
		this.setVisible(true);

		//Hauptfenster der Navogatorinstanz uebergeben
		navigator = new Navigator(this);
		
	    //ComponentListener beim Hauptfenster registrieren, ListenerFenster-Objekt uebergeben
	    this.addComponentListener(new ListenerFenster(navigator));
		
		//DionaRapModel Instanz
		model = new DionaRapModel();
		
		//Spieler bekommen
		//model.getPlayer();
	    
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
	public Toolbar getToolbar(){
		return toolbar;
	}
	public Navigator getNavigator(){
		return navigator;
	}
	
	/**Model mit get-Methode holen*/
	public DionaRapModel getDionaRapModel(){
		return model;
	}
	public void gameStatusChanged(){
		String options[] = {"neues Spiel","Cancel"};
		
		if(this.getDionaRapModel().isGameOver()){
			ImageIcon over = new ImageIcon( System.getProperty("user.dir")+File.separator+"images"+File.separator+"gameover.gif" );
			JOptionPane.showOptionDialog(this, "Verloren", "Game-Status", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, over, options, options[0]);
			this.dispose();
			new Hauptfenster();
		}
		if(this.getDionaRapModel().isGameWon()){
			ImageIcon win = new ImageIcon( System.getProperty("user.dir")+File.separator+"images"+File.separator+"gewonnen.gif" );
			JOptionPane.showOptionDialog(this, "Gewonnen", "Game-Status", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, win, options, options[1]);
			
		}
	}
	public void updateLookandFeel(String name){
		LookAndFeelInfo[] lafi = UIManager.getInstalledLookAndFeels();
		for(int i=0; i < lafi.length; i++){
			lafi[i].toString();
			if(lafi[i].getName().equals(name)){
				try {
					UIManager.setLookAndFeel(lafi[i].getClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
	}
}
