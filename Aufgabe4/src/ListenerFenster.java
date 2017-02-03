import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * ListenerFenster
 * Navogator bleibt beim Hauptfenster
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerFenster implements ComponentListener {
	
	private Navigator navigator;

	public ListenerFenster(Navigator navigator) {
		this.navigator=navigator;
	}

	@Override
	public void componentHidden(ComponentEvent e) {	
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		//Wenn HF bewegt wird Navigator mit bewegen
		navigator.updatePosition();
	}

	@Override
	public void componentResized(ComponentEvent e) {	
	}

	@Override
	public void componentShown(ComponentEvent e) {	
	}
}
