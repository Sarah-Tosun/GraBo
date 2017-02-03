import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * Die Eigenschaften der Tastatur werden gesetzt
 * Buttons enthalten
 * @author Sarah Tosun
 * @version DionaRap
 */

public class Tastatur extends JPanel{

	private static final long serialVersionUID = 1L;

	public Tastatur(Hauptfenster hf)
	{		
		//Rand um die Tastatur (Rot, staerke 2)
		//this.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		
		//GridLayout anlegen 9 Felder
		this.setLayout(new GridLayout(3,3,0,0));
		
		//Gesamtes Tastenfenster Groesse festlegen
		this.setPreferredSize(new Dimension(180,180));
		
		//Schrift erzeugen, festlegen
		Font f = new Font("Arial", Font.BOLD, 16);
		
		// Array, dass 9 Tasten aufnehmen kann
		JButton[] tasten = new JButton[10];
		
		//Zahlen umgedreht anordnen
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		//setMargin(new Insets(0,0,0,0));
		
		// 9 Tasten erzeugen, im Array speichern und im Grid platzieren
		for ( int i=9; i>=1; i-- ){
			ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"navigator"+File.separator+"taste"+i+".gif");
			//JButton Taste erzeugen. Beschriftung ist der Wert von i
			tasten[i] = new JButton(icon);
			
			if (i != 5) {
				tasten[i].addActionListener(new ListenerBewegung(i, hf));//Parameter:Zahl, Hauptfenster
			} else {
				tasten[i].addActionListener(new ListenerWaffe()); //Taste 5
			}
			// Taste ins Grid legen 
			this.add(tasten[i]);
			// Schrift setzen
			tasten[i].setFont(f);	
		}  
	}	
}
