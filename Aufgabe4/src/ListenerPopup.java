import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerPopup implements ActionListener {

	private Hauptfenster hf;
	private String name;
	public ListenerPopup(Hauptfenster hf_, String name_) {
		hf = hf_;
		name = name_;
	}
	public ListenerPopup(){}

	@Override
	public void actionPerformed(ActionEvent e) {
		hf.getSpielfeld().setThemeName(name);
		hf.redrawSpielfeld();
	}
}
