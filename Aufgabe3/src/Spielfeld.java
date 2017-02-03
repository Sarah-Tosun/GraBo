import java.awt.*;
import java.io.File;

import javax.swing.*;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.*;

/**
 * Die Eigenschaften des Spielfeldes werden gesetzt
 * Labels enthalten
 * Methoden: Spielfeld zeichnen - Schuesse zeichnen - Figuren loeschen
 * @author Sarah Tosun
 * @version DionaRap
 */

public class Spielfeld extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel felder[][];
	private ImageIcon iconDestruction;
	private int lastMoveDir;

	public Spielfeld()
	{
		int zeile = 10;
		int spalte = 10;
		int groesse = 50;
		
		//Fenstergroesse festlegen
		this.setPreferredSize(new Dimension(groesse*zeile, groesse*spalte));
		
		//GridLayout (10x10 Felder)
		this.setLayout(new GridLayout(zeile,spalte));
				
		// Labels erstellen, zweidim. Array von JLabels
		this.felder = new JLabel[zeile][spalte];
	    
		//Zweidimens. Array fuellen
		for(int i=0; i<felder.length; i++)
		{
	    	for(int j=0; j<felder.length; j++)
	    	{   
				felder[j][i] = new JLabel();
				//sichtbar machen
				felder[j][i].setOpaque(true);
				//Modulo fuer Gerade/Ungerade
				int x = j%2;
				int y = i%2;
				//Wenn genau eines (XOR) keine Restwert besitz nach Modulo-Operation
					if(y == 0 ^ x == 0){
						//Hintergrund dieses Feldes weiss setzen
						felder[j][i].setBackground(Color.WHITE);
					}
					else{
						//sonst Hintergrund schwarz setzen
						felder[j][i].setBackground(Color.BLACK);				
					}
				felder[j][i].setForeground(inverse(felder[j][i].getBackground()));
				felder[j][i].addMouseListener(new ListenerMouse(j, i));
				//Felder einfuegen	
	    		this.add(felder[j][i]);	
			}
	    }	
		// Fenster sichtbar machen
	    this.setVisible(true);	

	    this.lastMoveDir = 0;
	    
	}
	/**Methode Spielfeld neu zeichen*/
	public void drawPawn(AbstractPawn pawn){		
			if (pawn.getX()<0 || pawn.getY()<0){
				return;
			}
			//Wenn X UND Y groesser 0, JLabel erzeugen, X Und Y Koord.(dann setze Pawns)
			JLabel field = this.felder[pawn.getX()][pawn.getY()];
			
			ImageIcon iconObstacle = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"squarehead"+File.separator+"obstacle.gif");
			ImageIcon iconOpponent = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"squarehead"+File.separator+"opponent.gif");
			ImageIcon iconVortex = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"squarehead"+File.separator+"vortex.gif");
			iconDestruction = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"squarehead"+File.separator+"destruction.gif");
			//Typ Player pruefen
			if(pawn instanceof Player){
				//figur.setText("  S");
				
				ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"squarehead"+File.separator+"player"+this.lastMoveDir+".gif");
				field.setIcon(icon);

				return;
			}	
			//Typ Obstacle pruefen
			if(pawn instanceof Obstacle){
				//figur.setText("H");
				field.setIcon(iconObstacle);
				return;
			}	
			//Typ Vortex pruefen
			if(pawn instanceof Vortex){
				//figur.setBackground(Color.RED);
				field.setIcon(iconVortex);
				return;
			}
			//Typ Opponent pruefen
			if(pawn instanceof Opponent){
				//figur.setText("G");
				field.setIcon(iconOpponent);
				return;
			}
			//Typ Destriction pruefen
			if(pawn instanceof Destruction){
				//figur.setText("*");				
				//System.out.println("*");
				field.setIcon(iconDestruction);
				return;
			}		
		}
	/**Pawns loeschen nach Veraenderung*/
	public void deletePawns(){
		for(int i=0; i<felder.length; i++)
		{
	    	for(int j=0; j<felder.length; j++)
	    	{   
				felder[i][j].setIcon(null);
			}
		}
	}
	/**Schuesse zeichnen */
	public void drawShoots(DionaRapModel model){
		int x;
		int y;
		for(int i=0;i<model.getActiveShoots().size();i++){
			x=model.getActiveShoots().get(i).getShot().getX();
			y=model.getActiveShoots().get(i).getShot().getY();
			//felder[x][y].setText("*");
			felder[x][y].setIcon(iconDestruction);
		}		
	}
	
	public Color inverse(Color oldColor){
		return new Color(255-oldColor.getRed(), 255-oldColor.getGreen(), 255-oldColor.getBlue());
	}
	
	public void setLastMoveDir(int dir){
		this.lastMoveDir = dir;
	}
	
}



