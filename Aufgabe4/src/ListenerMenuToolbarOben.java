import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ListenerMenuToolbarOben implements ActionListener {
	private Hauptfenster hf;

	public ListenerMenuToolbarOben(Hauptfenster hf) {
		this.hf = hf;
	}

	@Override
	public void actionPerformed(ActionEvent e){ 	
		hf.remove(hf.getToolbar());
		hf.add(hf.getToolbar(), BorderLayout.PAGE_START);
		hf.validate();
		//hf.getToolbar().setVisible(true);

	}
	
}

