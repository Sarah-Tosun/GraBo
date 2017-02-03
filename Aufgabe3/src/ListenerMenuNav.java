import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

public class ListenerMenuNav implements ActionListener {
	private Hauptfenster hf;
	
	ListenerMenuNav(Hauptfenster hf){
		this.hf = hf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		hf.getNavigator().setVisible(((JCheckBoxMenuItem) e.getSource()).isSelected());
		
		
	}

}
