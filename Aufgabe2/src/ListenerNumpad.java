import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * ListenerNumpad
 * Nummernblock - Figur bewegen
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerNumpad implements KeyListener {

	
	public ListenerNumpad(){
		//System.out.println("Numpad");
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		int direction = new Integer(String.valueOf(arg0.getKeyChar())).intValue();
		//int direction = arg0.getKeyCode() - 96;
		if(direction>0 && direction<10 && direction!=5){
			((Hauptfenster) arg0.getSource()).getController().movePlayer(direction);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub	
	}

}
