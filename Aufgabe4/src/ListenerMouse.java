import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;


public class ListenerMouse implements MouseListener {
	
	private int zeile;
	private int spalte;
	private Hauptfenster hf;
	
	public ListenerMouse(int zeile, int spalte){
		this.zeile = zeile;
		this.spalte = spalte;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		hf = (Hauptfenster) ((JLabel) arg0.getSource()).getTopLevelAncestor();
		//Rechts-Klick Popup-Menu
		if(arg0.getButton() == 3){	
			hf.getPopup().setVisible(true);	
			hf.getPopup().setLocation(arg0.getLocationOnScreen());	
		}
		//Links- Klick Spieler bewegt sich
		if(arg0.getButton() == 1){
			hf.getPopup().setVisible(false);
			//Wenn Spieler-Position NEBEN Klick-Position, dann bewege Spieler auf Klick-Position
			int pspalte = hf.getDionaRapModel().getPlayer().getX();
			int pzeile = hf.getDionaRapModel().getPlayer().getY();
			int result = 10;
			int resspalte = spalte - pspalte;
			int reszeile = zeile - pzeile;
			switch(reszeile){
			case 0: 
				switch(resspalte){
				case 0: result = 5;
						break;
				case-1: result = 4;
						break;
				case 1: result = 6;
						break;
				}
			break;
			case-1: 
				switch(resspalte){
				case 0: result = 8;
						break;
				case-1: result = 7;
						break;
				case 1: result = 9;
						break;	
				}
			break;	
			case 1: 
				switch(resspalte){
				case 0: result = 2;
						break;
				case-1: result = 1;
						break;
				case 1: result = 3;
						break;	
				}
			break;
			}
			System.out.println(result);
			if(result == 5){
				hf.getController().shoot();
			}else{
				if(result < 10)
					hf.getController().movePlayer(result);
				else
					System.out.println("Ungültige Bewegung");
			}
			
		}
	}
				
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}
}
