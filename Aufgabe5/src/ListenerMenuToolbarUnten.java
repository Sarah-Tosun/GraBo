import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ListenerMenuToolbarUnten setzt die Toolbar nach unten
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerMenuToolbarUnten implements ActionListener {

	private Hauptfenster hf;
	
	ListenerMenuToolbarUnten(Hauptfenster hf){
		this.hf = hf;
	}

	/**
	 * Die Toolbar wird erst vom Hauptfenster entfernt und dann neu hinzugefuegt und nach unten gesetzt
	 * @param e - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		hf.remove(hf.getToolbar());
		hf.add(hf.getToolbar(), BorderLayout.PAGE_END);
		hf.validate();
		//hf.getToolbar().setVisible(true);
		
	}

}
