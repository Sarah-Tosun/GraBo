import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * ListenerFenster
 * Navogator bleibt beim Hauptfenster, Position wird upgedatet
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

	/**
	 * Wird das Hauptfenster verschoben, wird die Position des Navigators neuberechnet/-gesetzt
	 * @param e - ComponentEvent
	 */
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
