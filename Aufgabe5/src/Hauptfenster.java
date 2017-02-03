import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.data.MTConfiguration;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Ammo;
import de.fhwgt.dionarap.controller.*;

/**
 * Das Hauptfenster wird erstellt
 * Spielfeld eingefuegt, Navigator eingefuegt, Hauptfenster wird Navigator uebergeben
 * MenuBar eingefuegt Hauptfenster wird uebergeben, Toolbar eingefuegt Spielfeld uebergeben
 * Popup eingefuegt Hauptfenster wird uebergeben
 * Model Instanz erzeugt, Controller Model uebergeben
 * Methoden: NewGame, Controller get-Methode, Spielfeld neuzeichnen, Toolbar get-Methode,
 * Model get-Method, Popup get-Methode, MenuBar get-Methode, MutliThread-Konfiguration, updateLookandFeel
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
	private Toolbar toolbar = new Toolbar(spielfeld);
	private Popup pop;
	private Settings settings;

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
		settings = new Settings(this);
	    //ComponentListener beim Hauptfenster registrieren, ListenerFenster-Objekt uebergeben
	    this.addComponentListener(new ListenerFenster(navigator));
	    
	    //Neue Instanz von ListenerNumpad Parameter Hauptfenster
	    //Listener Numpad KeyListener uebergeben
	    this.addKeyListener(new ListenerNumpad());

	    newGame();
	    
	    //Listener Fokus zurueckholen
	    this.requestFocus();	    
	}
		

	public static void main(String[] args)
	{   // Instantz der Anwendungsklasse erzeugen
		new Hauptfenster();	
	}
	/**
	 * Neues Spiel erzeugen
	 */
	public void newGame(){
		Ammo ammo = new Ammo();
		int opponent = this.getSettings().getHashmap().get("text4");
		int gridx = this.getSettings().getHashmap().get("text1");
		int gridy = this.getSettings().getHashmap().get("text2");
		int obstacle = this.getSettings().getHashmap().get("text3");
		model = new DionaRapModel(gridx, gridy, opponent, obstacle);
		
		//Eine Munition dem Spielfeld hinzufuegen
		model.addAmmo(ammo);

		// Fortschritt (Gegner) Toolbar
		this.getToolbar().setStartGegner(model.getOpponentCount());
		this.getToolbar().setScore(0);
		this.getToolbar().setProgress(model.getOpponentCount());
		
		//Schuesse zu Beginn auf 5 setzten und der Toolbar uebergeben
		model.setShootAmount(5);
		this.getToolbar().addMunition(model.getShootAmount());
		
		this.getToolbar().getButtonSettings().addActionListener(new ListenerSettings(this));
		pawns = model.getAllPawns();
		this.remove(spielfeld);
		this.spielfeld = new Spielfeld(model.getGrid().getGridSizeX(),model.getGrid().getGridSizeY(), this.getSpielfeld().getThemeName());
		this.add(spielfeld);
		redrawSpielfeld();
		
		controller = new DionaRapController(model);
		// Controller Konfigurationen uebergeben
		controller.setMultiThreaded(getDefaultConfiguration());
		model.addModelChangedEventListener(new ListenerModel(this));
		
	}

	/**
	 * Controller aus Hauptfenster-Instanz holen
	 * @return Controller zurueckgeben
	 */
	public DionaRapController getController(){
		return this.controller;
	}

	/** Spielfeld neu zeichnen */
	public void redrawSpielfeld(){
		this.spielfeld.deletePawns();
		for(int i=0; i<pawns.length; i++){
			//Spielfeld Pawns uebergeben
			this.spielfeld.drawPawn(pawns[i]);
	    }
		this.getToolbar().addMunition(model.getShootAmount());
		this.pack();
	}
	
	public Spielfeld getSpielfeld(){
		return spielfeld;
	}
	
	public Toolbar getToolbar(){
		return toolbar;
	}

	public Navigator getNavigator(){
		return navigator;
	}
	public DionaRapModel getDionaRapModel(){
		return model;
	}
	public Popup getPopup(){
		return pop; 
	}
	public MenuBar getMenu(){
		return mb;
	}
	public Settings getSettings(){
		return settings;
	}
	/**
	 * Spielfeld model uebergeben
	 * @param model - Spielfeld Model uebergeben
	 */
	public void setModel(DionaRapModel model){
		spielfeld.setModel(model);
	}

	/**
	 * Multithreading der Gegner, Einstellung ueber Settings(Hashmap)
	 * @return Multithreading-Konfiguration
	 */
	private MTConfiguration getDefaultConfiguration() {
		MTConfiguration conf = new MTConfiguration();
		conf.setAlgorithmAStarActive(true);
		int box1 = this.getSettings().getHashmap().get("box1");
		boolean b1;
		int box2 = this.getSettings().getHashmap().get("box2");
		boolean b2;
		int box3 = this.getSettings().getHashmap().get("box3");
		boolean b3;
		if (box1 == 1) {
			b1 = true;
		} else {
			b1 = false;
		}
		if (box2 == 1) {
			b2 = true;
		} else {
			b2 = false;
		}
		if (box3 == 1) {
			b3 = true;
		} else {
			b3 = false;
		}
		conf.setAvoidCollisionWithOpponent(b3);
		conf.setAvoidCollisionWithObstacles(b2);
		conf.setMinimumTime(800);
		conf.setShotGetsOwnThread(true);
		conf.setOpponentStartWaitTime(this.getSettings().getHashmap().get("slider1"));
		conf.setOpponentWaitTime(this.getSettings().getHashmap().get("slider3"));
		conf.setShotWaitTime(this.getSettings().getHashmap().get("slider2"));
		conf.setRandomOpponentWaitTime(b1);
		conf.setDynamicOpponentWaitTime(false);
		return conf;
	}

	/**
	 * Methode Aktuallisierung des LookandFeels
	 * @param name (JMenuBar LookandFeel-Name gesetzt und ListenerMenuLook uebergeben)
	 */
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
