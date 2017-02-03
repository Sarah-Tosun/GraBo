import java.awt.Color;
import javax.swing.JPanel;
/**
 * BlinkerThread
 * sind keine Schuesse vorhanden und es wird geschossen, 
 * blinkt der Hintergrund des Munitionspanels in der Toolbar
 * @author Sarah Tosun
 * @version DionaRap
 */
public class BlinkerThread extends Thread {

	private JPanel p;
	private Color c;

	public BlinkerThread(JPanel p) {
		this.p = p;
		//Background holen
		c = p.getBackground();
	}

	@Override
	public void run() {
		boolean red = false;
		for (int i = 0; i < 10; i++) {
			red = !red; // Negiert - Erster Durchlauf true
			if (red) {
				// red border;
				p.setBackground(Color.RED);
			} else { // Zweiter Durchlauf false, usw. (10x = 5x blinken)
				// remove border
				p.setBackground(c);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				break;
			}
		}
		// Alter Background setzten
		p.setBackground(c);
	}
}
