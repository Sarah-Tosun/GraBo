import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ListenerPopup
 * Theme auswaehlen, Spielfeld neuzeichnen
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerPopup implements ActionListener {

	private Hauptfenster hf;
	private String name;
	public ListenerPopup(Hauptfenster hf, String name) {
		this.hf = hf;
		this.name = name;
	}


	/**
	 * Theme wird gewaehlt und dem Spielfeld uebergeben
	 * das Spielfeld wird neugezeichnet und das Popup wieder ausgeblendet
	 * @param e - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		hf.getSpielfeld().setThemeName(name);
		hf.redrawSpielfeld();
		hf.getPopup().setVisible(false);
	}
}
