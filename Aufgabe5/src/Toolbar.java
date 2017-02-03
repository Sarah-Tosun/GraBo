import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Toolbar, Button Neues Spiel, Punktestand, Munition, Fortschrittm und Button Settings enthalten
 * @author Sarah Tosun
 * @version DionaRap
 */
public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JButton b_newGame;
	private JButton bSettings;
	private JTextField t_points;
	private int startGegner;
	private JProgressBar pr_bar;
	private JPanel p_mun;
	private Munition m1;
	private Munition m2;
	private Munition m3;
	private Spielfeld spielfeld;
	private BlinkerThread t;

	public Toolbar(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		// Panel fuer Button Neues Spiel - BorderLayout
		JPanel p_button1 = new JPanel();
		p_button1.setLayout(new BoxLayout(p_button1, BoxLayout.X_AXIS));
		b_newGame = new JButton("Neues Spiel");
		b_newGame.setEnabled(false);
		p_button1.add(b_newGame);


		// Panel Punktestand
		JPanel p_points = new JPanel();
		p_points.setBorder(BorderFactory.createTitledBorder("Punktestand"));
		// Textfeld - bei Start voreingestellt auf "0"
		t_points = new JTextField("0", 3);
		t_points.setBackground(Color.white);
		// Nicht editierbar
		t_points.setEditable(false);
		// Textfeld dem Panel hinzufuege
		p_points.add(t_points);

		// Panel Munition
		p_mun = new JPanel();
		// blinken wenn Schuss/keine Munition mehr
		p_mun.setBorder(BorderFactory.createTitledBorder("Munition"));
		
		//Thread blinken erzeugen
		t = new BlinkerThread(p_mun);

		m1 = new Munition(spielfeld.getThemeName());
		m2 = new Munition(spielfeld.getThemeName());
		m3 = new Munition(spielfeld.getThemeName());
		p_mun.add(m1);
		p_mun.add(m2);
		p_mun.add(m3);

		// Panel Spielfortschritt
		JPanel p_cont = new JPanel();
		p_cont.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
		// Fortschritt
		pr_bar = new JProgressBar();
		// ProgressBar bei Start voreingestellt auf 0 %
		pr_bar.setValue(0);
		pr_bar.setStringPainted(true);
		// ProgressBar dem Panel hinzufuegen
		p_cont.add(pr_bar);

		// Panel fuer Button Settings
		JPanel p_button2 = new JPanel();
		bSettings = new JButton("Settings");
		p_button2.setLayout(new BoxLayout(p_button2, BoxLayout.X_AXIS));
		bSettings.setEnabled(true);
		p_button2.add(bSettings);

		this.add(p_button1);
		this.add(p_points);
		this.add(p_mun);
		this.add(p_cont);
		this.add(p_button2);
		this.setVisible(true);
		// Toolbar kann nicht aus dem Fenster entnommen werden
		this.setFloatable(false);
	}

	/**
	 * Button - Neues Spiel
	 * @return Rueckgabe Button - Neues Spiel
	 */
	public JButton getNewGame() {
		return b_newGame;
	}
	/**
	 * Button Settings
	 * @return Rueckgabe Button - Settings
	 */
	public JButton getButtonSettings() {
		return bSettings;
	}

	/**
	 * Punktestand setzten
	 * @param i  (Punkte ueber ListenerModel - Model)
	 */
	public void setScore(int i) {
		String s = String.valueOf(i);
		t_points.setText(s);
	}

	public int getStartGegner() {
		return startGegner;
	}

	/**
	 * Gegner am Start setzten
	 * @param s Gegner am Start
	 */
	public void setStartGegner(int s) {
		startGegner = s;
	}

	/**
	 * Munition erzeugen und anhand der Anzahl der Schuesse (vom Model) in der Toolbar anzeigen
	 * @param schuesse  (Anzahl der Schuesse vom Model)
	 */
	public void addMunition(int schuesse) {
		p_mun.remove(m1);
		p_mun.remove(m2);
		p_mun.remove(m3);
		m1 = new Munition(spielfeld.getThemeName()); // Theme vom Spielfeld holen
		m2 = new Munition(spielfeld.getThemeName());
		m3 = new Munition(spielfeld.getThemeName());
		p_mun.add(m1);
		p_mun.add(m2);
		p_mun.add(m3);
		if(t.isAlive() == true && schuesse > 0){
			t.interrupt();
		}	// wenn Schuesse vorhanden dann bis zu drei hinzufuegen 
			if (schuesse <= 3 && schuesse > 0) {
				switch (schuesse) {
				case 1:
					m3.setAmmo();
					//p_mun.add(m1);
					//p_mun.add(Box.createHorizontalStrut(5));
					break;
				case 2:
					m3.setAmmo();
					m2.setAmmo();
					break;
				case 3:
					m3.setAmmo();
					m2.setAmmo();
					m1.setAmmo();
					break;
				}

			} // sonst, mehr als 3 Einheiten, nur 2 Anzeigen, eine Zahl!
			else if (schuesse > 3) {
				// Ubergabe an Munition Anzahl der Schuesse - 2 angezeigte Bilder-Muniton
				m1.setAmmo(schuesse -2);
				m2.setAmmo(); // Bild1-Munition
				m3.setAmmo(); // Bild2-Munition
			} 
	}
	
	/**
	 * Fortschritt setzten
	 * @param actuellGegner (ueber ListenerModel vom Model)
	 */
	public void setProgress(int actuellGegner) {
		double prozentsatz = 100 / startGegner;
		double result = (startGegner - actuellGegner) * prozentsatz;
		pr_bar.setValue((int) result);
	}
	/**
	 * Sind noch Schuesse vorhanden ja oder nein (ListenerNumpad/ListenerMouse/ListenerWaffe)
	 * @return true / false - Schuesse vorhanden?
	 */
	public boolean setShoot(){
		// Hauptfenster
		Hauptfenster hf = (Hauptfenster) this.getTopLevelAncestor();
		//Schuesse vorhanden gib true zurueck
		if(hf.getDionaRapModel().getShootAmount() > 0){
			return true;
		}else{ // Wenn der BlinkerThread NICHT am leben ist
			if(t.isAlive() == false){ 
				//erzeuge BlinkterThread und starte diesen
				t = new BlinkerThread(p_mun);
				t.start();
			}
			//ist der Thread am Leben, gib false zurueck, keine Schuesse mehr (BlinkerThread blinkt)
			return false;
		}
	}

}
