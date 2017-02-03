import java.awt.*;
import java.io.File;
import javax.swing.*;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.*;

/**
 * Die Eigenschaften des Spielfeldes werden gesetzt
 * Spielfeld, Schuesse, Figuren zeichnen, loeschen
 * @author Sarah Tosun
 * @version DionaRap
 */

public class Spielfeld extends JPanel {

	private static final long serialVersionUID = 1L;
	private DionaRapModel model;
	private JLabel felder[][];
	private ImageIcon iconDestruction;
	private int lastMoveDir;
	private String name;
	private int zeilen;
	private int spalten;
	
	public Spielfeld()
	{
		this(12,12,"Vader");
	}
	public Spielfeld(int spalten, int zeilen, String name){
		this.zeilen = zeilen;
		this.spalten = spalten;
		this.name = name;

		int groesse = 50;
		
		//Fenstergroesse festlegen
		this.setPreferredSize(new Dimension(groesse*spalten, groesse*zeilen));
		
		//GridLayout (10x10 Felder)
		this.setLayout(new GridLayout(zeilen,spalten));
				
		// Labels erstellen, zweidim. Array von JLabels
		this.felder = new JLabel[zeilen][spalten];
	    
		//Zweidimens. Array fuellen
		for(int i=0; i<zeilen; i++)
		{
	    	for(int j=0; j<spalten; j++)
	    	{   
				felder[i][j] = new JLabel();
				//sichtbar machen
				felder[i][j].setOpaque(true);
				//Modulo fuer Gerade/Ungerade
				int x = j%2;
				int y = i%2;
				//Wenn genau eines (XOR) keine Restwert besitz nach Modulo-Operation
					if(y == 0 ^ x == 0){
						//Hintergrund dieses Feldes weiss setzen
						felder[i][j].setBackground(Color.WHITE);
					}
					else{
						//sonst Hintergrund schwarz setzen
						felder[i][j].setBackground(Color.BLACK);				
					}
				felder[i][j].setForeground(inverse(felder[i][j].getBackground()));
				//MouseListener registrieren
				felder[i][j].addMouseListener(new ListenerMouse(i, j));

				//Felder einfuegen	
	    		this.add(felder[i][j]);	
			}
	    }	
		// Fenster sichtbar machen
	    this.setVisible(true);	
		
	    this.lastMoveDir = 1;
	}

	/**
	 * Methode Spielfeld neu zeichen
	 * @param pawn (Figuren vom Hauptfenster(redrawSpielfeld()) gesetzt)
	 */
	public void drawPawn(AbstractPawn pawn){		
			if (pawn.getX()<0 || pawn.getY()<0){
				return;
			}
			//Wenn X ODER(oder beides) Y groesser 0, JLabel erzeugen, X Und Y Koord.(dann setze Pawns)
			JLabel field = this.felder[pawn.getY()][pawn.getX()];
			ImageIcon iconAmmo = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+this.getThemeName()+File.separator+"ammo.png");
			ImageIcon iconObstacle = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+this.getThemeName()+File.separator+"obstacle.gif");
			ImageIcon iconOpponent = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+this.getThemeName()+File.separator+"opponent.gif");
			ImageIcon iconVortex = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+this.getThemeName()+File.separator+"vortex.gif");
			iconDestruction = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+this.getThemeName()+File.separator+"destruction.gif");
			//Typ Player pruefen
			if(pawn instanceof Player){
				//figur.setText("  S");	
				ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+this.getThemeName()+File.separator+"player"+this.lastMoveDir+".gif");
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
			if(pawn instanceof Ammo){
				//figur.setText("*");				
				//System.out.println("*");
				field.setIcon(iconAmmo);
				return;
			}
			
		}
	/**
	 * Pawns loeschen nach Veraenderung
	 */
	public void deletePawns(){
		for(int i=0; i<zeilen; i++)
		{
	    	for(int j=0; j<spalten; j++)
	    	{   
				felder[i][j].setIcon(null);
			}
		}
	}
	/**
	 * Schuesse zeichnen 
	 * @param model DionaRapModel - Schuesse zeichnen
	 */
	public void drawShoots(DionaRapModel model){
		int x;
		int y;
		for(int i=0;i<model.getActiveShoots().size();i++){
			x=model.getActiveShoots().get(i).getShot().getX();
			y=model.getActiveShoots().get(i).getShot().getY();
			//felder[x][y].setText("*");
			felder[y][x].setIcon(iconDestruction);
		}		
	}
		
	public Color inverse(Color oldColor){
		return new Color(255-oldColor.getRed(), 255-oldColor.getGreen(), 255-oldColor.getBlue());
	}
	
	public void setLastMoveDir(int dir){
		this.lastMoveDir = dir;
	}
	/**
	 * Theme-Name zurueckgeben
	 * @return Theme-Name 
	 */
	public String getThemeName(){
		return name;
	}
	/**
	 * Theme-Name setzen (Voreinstellung setzen)
	 * @param name_ Theme-Name setzen
	 */
	public void setThemeName(String name_){
		name = name_;	
	}
	public int getZeilen(){
		return zeilen;
	}
	public void setZeilen(int x){
		this.zeilen = x;
	}
	public int getSpalten(){
		return spalten;
	}
	public void setSpalten(int y){
		this.spalten = y;
	}
	public DionaRapModel getModel(){
		return model;
	}
	public void setModel(DionaRapModel model) {
		this.model = model;
	}

	
}



