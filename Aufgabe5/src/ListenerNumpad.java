import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ListenerNumpad
 * Nummernblock - Figur bewegen
 * @author Sarah Tosun
 * @version DionaRap
 */

public class ListenerNumpad implements KeyListener {

	/**
	 * Nummerblock nutzbar um die Spielfigur zu bewegen oder zu schiessen
	 * gleiches Prinzig wie über Navigator und per Maus
	 * @param arg0 - KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0);
		int direction = new Integer(String.valueOf(arg0.getKeyChar())).intValue();
		if(direction>0 && direction<10 && direction!=5){
			((Hauptfenster) arg0.getSource()).getController().movePlayer(direction);
			((Hauptfenster) arg0.getSource()).getSpielfeld().setLastMoveDir(direction);
		} // Sind Schuesse vorhanden, darf noch geschossen werden
		else if(direction == 5 && (((Hauptfenster) arg0.getSource()).getToolbar().setShoot() == true)){
			((Hauptfenster) arg0.getSource()).getController().shoot();
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
