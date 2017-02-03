import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ListenerNew
 * Toolbar Button - Neues Spiel
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerNew implements ActionListener {

	private Hauptfenster hf;
	public ListenerNew(Hauptfenster hf) {
		this.hf = hf;
	}

	/**
	 * Bei Klick auf Neues Spiel in der Toolbar
	 * @param e - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		hf.newGame();
	}

}
