import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.*;

/**
 * ListenerModel
 * Spielfeld wird neu gezeichnet, Figuren geloescht, Schuesse werden dargestellt
 * Punktestand wird aus dem Model ausgelesen und der Toolbar uebergeben (setScore gesetzt)
 * Die Gegner-Anzahlt wird aus dem Model ausgelesen und der Toolbar ubergeben (setProgress gesetzt)
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerModel implements DionaRapListener {
	
	private Hauptfenster hf;
	
	public ListenerModel(Hauptfenster hf) { 
		this.hf = hf;                        //Hauptfensterreferenz
	}

	/**
	 * Wird ein modelChanged ausgeloest, werden die Figuren geloescht und neugezeichnet, Schuesse werden auch neugezeichnet, 
	 * Punktestand wird vom Model ermittelt und der Toolbar uebergeben/gesetzt, 
	 * OpponentCount vom Model wird geholt und der Toolbar uebergeben/gesetzt
	 * @param arg0 - DionaRapChangedEvent
	 */
	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {
		hf.getSpielfeld().deletePawns();  	//Figur loeschen
		hf.redrawSpielfeld();				//Spielfeld neu zeichnen
		hf.getSpielfeld().drawShoots(hf.getDionaRapModel());  //Schuesse zeichnen
		// Punktestand ToolBar
		int i= (hf.getDionaRapModel().getScore());
		hf.getToolbar().setScore(i);
		//Fortschritt (Gegner) Toolbar
		hf.getToolbar().setProgress(hf.getDionaRapModel().getOpponentCount());
		
	}

	@Override
	/**
	 * Hat sich der Status geaendert (Spiel gewonnen oder verloren) 
	 * OptionPane Ausgabe mit Button Neues Spiel oder Abbrechen
	 */
	public void statusChanged(GameStatusEvent arg0) {
		// Spiel gewonnen verloren, Ausgabe Konsole
		String text ="";
		ImageIcon icon =new ImageIcon();
		
		if(arg0.isGameOver()){
			text="Verloren";
			
			icon = new ImageIcon( System.getProperty("user.dir")+File.separator+"images"+File.separator+hf.getSpielfeld().getThemeName()+File.separator+"loss.gif" );
		}
		else if(arg0.isGameWon()){
			text="Gewonnen";
			icon = new ImageIcon( System.getProperty("user.dir")+File.separator+"images"+File.separator+hf.getSpielfeld().getThemeName()+File.separator+"win.gif" );
		}
		
		int option = JOptionPane.showOptionDialog(hf, text, "Game-Status", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, new String[]{"Neues Spiel","Abbrechen"}, null);
		//Neues Spiel Button gedrueckt
		if(option==0){
			hf.newGame();
		}else if(option==1){
		//Abbrechen gedrueckt - Button-Toolbar Neues Spiel aktiviert
		hf.getToolbar().getNewGame().addActionListener(new ListenerNew(hf));
		hf.getToolbar().getButtonSettings().addActionListener(new ListenerSettings(hf));
		hf.getToolbar().getNewGame().setEnabled(true);
		}
	}	
}
