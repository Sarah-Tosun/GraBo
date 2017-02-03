import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class ListenerMouse implements MouseListener {

	private int j;
	private int i;
	
	ListenerMouse(int j, int i){
		this.j = j;
		this.i = i;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Wenn Spieler-Position NEBEN Klick-Position, dann bewege Spieler auf Klick-Position
		System.out.println(j +","+ i);
		System.out.println(((JLabel) e.getSource()).getTopLevelAncestor().toString());
		Hauptfenster hf = (Hauptfenster) ((JLabel) e.getSource()).getTopLevelAncestor();
		int px = hf.getDionaRapModel().getPlayer().getX();
		int py = hf.getDionaRapModel().getPlayer().getY();
		/*
		e.getSource()
		((Hauptfenster) e.getSource()).getController().movePlayer(i);
		
		if(((Hauptfenster) e.getSource())){
			
		}
		*/
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
