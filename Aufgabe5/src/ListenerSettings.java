import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ListenerSettings
 * Toolbar Button Settings gedrueckt - Settings anzeigen
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerSettings implements ActionListener {
	private Hauptfenster hf;
	
	public ListenerSettings(Hauptfenster hf) {
		this.hf = hf;
	}

	/**
	 * Bei Klick auf Settings in der Toolbar, erscheint das Fenster mit den Einstellmoeglichkeiten
	 * @param arg0 - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		hf.getSettings().setVisible(true);;

	}

}
