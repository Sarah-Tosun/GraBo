import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * ListenerBewegung 
 * fuer Taste 1-4 und 6-9
 * Spieler bewegen
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerBewegung implements ActionListener{
	private int taste;
	private Hauptfenster hf;

	public ListenerBewegung(int i, Hauptfenster hf) {
		this.taste = i;
		this.hf = hf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//ueber Referenz zum HF ueber getController Methode zum Spieler bewegen aufrufen
		hf.getController().movePlayer(taste);
		hf.requestFocus();
	}

}
