import java.awt.*;
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

	public Spielfeld()
	{
		int zeile = 10;
		int spalte = 10;
		
		//Fenstergroesse festlegen
		this.setPreferredSize(new Dimension(50*zeile, 50*spalte));
		
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
				//Felder einfuegen	
	    		this.add(felder[j][i]);	
			}
	    }	
		// Fenster sichtbar machen
	    this.setVisible(true);	
	    
	}
	/**Methode Spielfeld neu zeichen*/
	public void drawPawn(AbstractPawn pawns){		
			if (pawns.getX()<0 || pawns.getY()<0){
				return;
			}
			//Wenn X UND Y groesser 0, JLabel erzeugen, X Und Y Koord.(dann setze Pawns)
			JLabel figur = this.felder[pawns.getX()][pawns.getY()];
			
			//Typ Player pruefen
			if(pawns instanceof Player){
				figur.setText("  S");
				return;
			}	
			//Typ Obstacle pruefen
			if(pawns instanceof Obstacle){
				figur.setText("H");
				return;
			}	
			//Typ Vortex pruefen
			if(pawns instanceof Vortex){
				figur.setBackground(Color.RED);
				return;
			}
			//Typ Opponent pruefen
			if(pawns instanceof Opponent){
				figur.setText("G");
				return;
			}
			//Typ Destriction pruefen
			if(pawns instanceof Destruction){
				figur.setText("*");				
				System.out.println("*");
				return;
			}		
		}
	/**Pawns loeschen nach Veraenderung*/
	public void deletePawn(){
		for(int i=0; i<felder.length; i++)
		{
	    	for(int j=0; j<felder.length; j++)
	    	{   
				felder[i][j].setText("");
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
			felder[x][y].setText("*");
		}		
	}
	
	public Color inverse(Color oldColor){
		return new Color(255-oldColor.getRed(), 255-oldColor.getGreen(), 255-oldColor.getBlue());
	}
	
}



