import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerMouseMenu implements MouseListener {
	private Hauptfenster hf;
	public ListenerMouseMenu(Hauptfenster hf_) {
		hf = hf_;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 3){	
			hf.getPopup().setVisible(true);	
			hf.getPopup().setLocation(e.getLocationOnScreen());	
		}
		//Links- Klick Menöffnet sich
		if(e.getButton() == 1){
			hf.getPopup().setVisible(false);	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
