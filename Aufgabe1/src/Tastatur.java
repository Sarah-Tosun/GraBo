import java.awt.*;
import javax.swing.*;

public class Tastatur extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Buttons 
	// GridLayout, Tastatur mit 9 Buttons
	public Tastatur()
	{
		//Rand um die Tastatur (Rot, staerke 2)
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		//GridLayout anlegen 9 Felder
		this.setLayout(new GridLayout(3,3,0,0));
		//Gesamtes Tastenfenster Groesse festlegen
		this.setPreferredSize(new Dimension(180,180));
		//Schrift erzeugen, festlegen
		Font f = new Font("Arial", Font.BOLD, 16);
		// Array, dass 9 Tasten aufnehmen kann
		JButton[] tasten = new JButton[10];
		
		// 9 Tasten erzeugen, im Array speichern und im Grid platzieren
		for ( int i=9; i>=1; i-- ){
			// Taste erzeugen. Beschriftung ist der Wert von i
			tasten[i]=new JButton(Integer.toString(i)); 
			// Schrift setzen
			tasten[i].setFont(f);
			// Taste ins Grid legen 
			this.add(tasten[i]);
			//Zahlen umgedreht anordnen
			this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}  
	}
}
