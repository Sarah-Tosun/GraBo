import java.awt.*;
import javax.swing.*;

public class Spielfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Spielfeld()
	{
		int zeile = 10;
		int spalte = 10;
		
		//Fenstergroesse festlegen
		this.setPreferredSize(new Dimension(600, 600));
		
		//GridLayout (10x10 Felder)
		this.setLayout(new GridLayout(zeile,spalte));
				
		
		
		// Labels erstellen, zweidim. Array von JLabels
		JLabel felder[][] = new JLabel[zeile][spalte];
	    
		//Zweidimens. Array füllen
		for(int i=0; i<felder.length; i++)
		{
	    	for(int j=0; j<felder.length; j++)
	    	{   
				felder[i][j] = new JLabel();
				//sichtbar machen
				felder[i][j].setOpaque(true);
				//Modulo für Gerade/Ungerade
				int x = i%2;
				int y = j%2;
				//Wenn x nach Modulo-Operation keinen Restwert hat (Gerade)
				if(x == 0){
					//Wenn y nach Modulo-Operation keinen Restwert hat (Gerade)
					if(y == 0){
						//dann setze den Hintergrund dieses Feldes schwarz
						felder[i][j].setBackground(Color.BLACK);
					}
					else{
						//sonst setze den Hintergrund des ungeraden y Feldes weiß
						felder[i][j].setBackground(Color.WHITE);
					}
				}else{ // sonst x hat Restwert
					//Wenn y gerade
				 	if(y == 0){
				 		//dann setze den Hintergrund dieses Feldes weiß
						felder[i][j].setBackground(Color.WHITE);
				 	}else{
				 		//sonst setze den Hintergrund dieses Feldes schwarz
						felder[i][j].setBackground(Color.BLACK);
					}
				}
				//Felder einfügen	
	    		this.add(felder[i][j]);	
			}
	    }
		// Fenster sichtbar machen
	    this.setVisible(true);					
	}
}
