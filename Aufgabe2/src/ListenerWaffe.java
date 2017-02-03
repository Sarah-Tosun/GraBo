import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ListenerWaffe
 * Taste 5 - Schiessen
 * @author Sarah Tosun
 * @version DionaRap
 */

// Reagiert auf Events des Buttons 5
public class ListenerWaffe implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton)e.getSource();
		Hauptfenster htmp = (Hauptfenster) tmp.getTopLevelAncestor().getParent(); //Typecast, Hauptfenster beschaffen
		htmp.getController().shoot();
		//Version2: ((Hauptfenster) ((JButton)e.getSource()).getTopLevelAncestor().getParent()).getController().shoot();
		htmp.requestFocus();
	}
}
