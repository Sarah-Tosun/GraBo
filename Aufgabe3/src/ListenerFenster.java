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
		navigator.setLocation(e.getComponent().getX() + e.getComponent().getWidth() + 5, e.getComponent().getY());
		navigator.pack();
	}

	@Override
	public void componentResized(ComponentEvent e) {	
	}

	@Override
	public void componentShown(ComponentEvent e) {	
	}
}
