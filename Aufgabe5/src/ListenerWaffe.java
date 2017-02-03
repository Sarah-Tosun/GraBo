import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ListenerWaffe
 * Taste 5 Navigator - Schiessen
 * @author Sarah Tosun
 * @version DionaRap
 */

// Reagiert auf Events des Buttons 5
public class ListenerWaffe implements ActionListener {

	/**
	 * Sind noch Schuesse vorhanden, kann geschossen werden
	 * ansonsten blinkt die Munitionsanzeige in der Toolbar
	 * @param arg0 - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton tmp = (JButton)arg0.getSource();
		Hauptfenster htmp = (Hauptfenster) tmp.getTopLevelAncestor().getParent(); //Typecast, Hauptfenster beschaffen
		
		// Sind Schuesse vorhanden, darf noch geschossen werden
		if(htmp.getToolbar().setShoot() == true){
			htmp.getController().shoot();
		}
		//Version2: ((Hauptfenster) ((JButton)e.getSource()).getTopLevelAncestor().getParent()).getController().shoot();
		htmp.requestFocus();
	}
}
