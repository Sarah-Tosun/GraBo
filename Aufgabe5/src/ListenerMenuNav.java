import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
/**
 * ListenerMenuNav blendet den Navigator ein (und aus)
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerMenuNav implements ActionListener {
	private Hauptfenster hf;
	
	ListenerMenuNav(Hauptfenster hf){
		this.hf = hf;
	}

	/**
	 * Navigator -  ist die Checkbox nicht gewaehlt, ausblenden,
	 * wird sie gewaehlt einblenden
	 * @param e - ActionEvent
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		hf.getNavigator().setVisible(((JCheckBoxMenuItem) e.getSource()).isSelected());
		
		
	}

}
