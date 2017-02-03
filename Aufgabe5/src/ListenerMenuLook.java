import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ListenerMenuLook waehlt das Look and Feel aus
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerMenuLook implements ActionListener {
	private Hauptfenster hf;
	private String name;
	
	ListenerMenuLook(Hauptfenster hf, String name){
		this.hf = hf;
		this.name = name;
	}

	/**
	 * Look and Feel wird veraendert
	 * @param e - ActionEvent
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		hf.updateLookandFeel(name);
		
	}

}
