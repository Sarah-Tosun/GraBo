import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerMenuLook implements ActionListener {
	private Hauptfenster hf;
	private String name;
	
	ListenerMenuLook(Hauptfenster hf, String name){
		this.hf = hf;
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		hf.updateLookandFeel(name);
		
	}

}
