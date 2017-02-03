import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.*;

/**
 * ListenerModel
 * Spielfeld wird neu gezeichnet
 * Figuren geloescht
 * Schuesse werden dargestellt
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerModel implements DionaRapListener {
	
	private Hauptfenster hf;
	
	public ListenerModel(Hauptfenster hf) { 
		this.hf = hf;                        //Hauptfensterreferenz
	}

	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {
		hf.getSpielfeld().deletePawns();  	//Figur loeschen
		hf.redrawSpielfeld();				//Spielfeld neu zeichnen
		hf.getSpielfeld().drawShoots(hf.getDionaRapModel());  //Schuesse zeichnen
	}

	@Override
	public void statusChanged(GameStatusEvent arg0) {
		// Spiel gewonnen verloren, Ausgabe Konsole
		hf.gameStatusChanged();	
		System.out.println(arg0);

		
		
	}	
}
