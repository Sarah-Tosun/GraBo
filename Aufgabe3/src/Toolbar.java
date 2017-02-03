import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class Toolbar extends JToolBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton b_newGame;
	
	Toolbar(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		//Abstand fester Groesse
		this.add(Box.createHorizontalStrut(20));
		
		b_newGame = new JButton("Neues Spiel");
		//Setze bevorzugte Groesse
		b_newGame.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		b_newGame.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		b_newGame.setPreferredSize(new Dimension(60,50));
		b_newGame.setEnabled(false);
		

		//Panel Punktestand	
		JPanel p_points = new JPanel();	
		p_points.setBorder(BorderFactory.createTitledBorder("Punktestand"));
		p_points.setPreferredSize(new Dimension(80,50));
		//Textfeld
		JTextField t_points = new JTextField("0", 3);
		t_points.setPreferredSize(new Dimension(60,15));
		t_points.setBackground(Color.white);
		//Nicht editierbar
		t_points.setEditable(false);
		//Textfeld dem Panel hinzufuegen
		p_points.add(t_points);

		//Panel Munition
		JPanel p_mun = new JPanel();	
		p_mun.setBorder(BorderFactory.createTitledBorder("Munition"));	
		p_mun.setPreferredSize(new Dimension(80,50));
		//Bilder			
		//Bilder dem Panel hinzufuegen
		
		
		//Panel Spielfortschritt
		JPanel p_cont = new JPanel();	
		p_cont.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
		p_cont.setPreferredSize(new Dimension(80,50));
		//Fortschritt
		JProgressBar pr_bar = new JProgressBar();
		pr_bar.setPreferredSize(new Dimension(80,15));
		pr_bar.setValue(30);
		pr_bar.setStringPainted(true);
		
		//ProgressBar dem Panel hinzufuegen
		p_cont.add(pr_bar);
		
		this.add(b_newGame);
		//Abstand dazwischen
		this.add(Box.createHorizontalStrut(20));
		this.add(p_points);
		this.add(Box.createHorizontalStrut(2));
		this.add(p_mun);
		this.add(Box.createHorizontalStrut(2));
		this.add(p_cont);
		this.add(Box.createHorizontalStrut(20));
		this.add(new JButton("Settings"));
		this.setPreferredSize(new Dimension(80,50));
		this.add(Box.createHorizontalStrut(20));
		this.setVisible(true);
		//Toolbar kann nicht aus dem Fenster entnommen werden
		this.setFloatable(false);
		panel.add(this);
		
		
	}

	
	//public void NewGame(){
	//	b_newGame.setEnabled(true);
	//}
}
