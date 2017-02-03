import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ListenerMenuToolbarOben setzt die Toolbar nach oben
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerMenuToolbarOben implements ActionListener {
	private Hauptfenster hf;

	public ListenerMenuToolbarOben(Hauptfenster hf) {
		this.hf = hf;
	}

	/**
	 * Die Toolbar wird erst vom Hauptfenster entfernt und dann neu hinzugefuegt und nach oben gesetzt
	 * @param e - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e){ 	
		hf.remove(hf.getToolbar());
		hf.add(hf.getToolbar(), BorderLayout.PAGE_START);
		hf.validate();
		//hf.getToolbar().setVisible(true);

	}
	
}

