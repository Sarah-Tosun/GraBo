import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerNew implements ActionListener {

	private Hauptfenster hf;
	public ListenerNew(Hauptfenster hf_) {
		hf = hf_;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		hf.newGame();
	}

}
