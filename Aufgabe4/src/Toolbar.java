import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class Toolbar extends JToolBar {
	
	/**
	 * Toolbar (oben / unten)
	 * Button Neues Spiel, Punktestand, Munition, Fortschritt und Settings enthalten
	 * Methoden: Rückgabe Button - Neues Spiel, Punktestand setzten von ListenerModel, Gegner am Start setzten von Hauptfenster
	 * Fortschritt setzten vom ListenerModel
	 * @author Sarah Tosun
	 * @version DionaRap
	 */
	private static final long serialVersionUID = 1L;
	private JButton b_newGame;
	private JButton bSettings;
	private JTextField t_points;
	private int startGegner;
	private JProgressBar pr_bar;
	
	public Toolbar(){

		this.setLayout(new GridLayout(0,5));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

//Panel fuer Button Neues Spiel - BorderLayout
		JPanel p_button1 = new JPanel();	
		p_button1.setLayout(new BoxLayout(p_button1, BoxLayout.X_AXIS));
		b_newGame = new JButton("Neues Spiel");
		b_newGame.setEnabled(false);
		b_newGame.setPreferredSize(new Dimension(80,30));
		p_button1.add(Box.createHorizontalStrut(5));
		p_button1.add(b_newGame);
		p_button1.add(Box.createHorizontalStrut(5));

//Panel Punktestand	
		JPanel p_points = new JPanel();	
		p_points.setBorder(BorderFactory.createTitledBorder("Punktestand"));
		//p_points.setMinimumSize(new Dimension(70,50));
		//Textfeld - bei Start voreingestellt auf "0"
		t_points = new JTextField("0",3);
		t_points.setMinimumSize(new Dimension(30,20));
		t_points.setBackground(Color.white);
		//Nicht editierbar
		t_points.setEditable(false);
		//Textfeld dem Panel hinzufuege
		p_points.add(t_points);

//Panel Munition
		JPanel p_mun = new JPanel();	
		p_mun.setBorder(BorderFactory.createTitledBorder("Munition"));	
		p_mun.setPreferredSize(new Dimension(60,40));
	
//Panel Spielfortschritt
		JPanel p_cont = new JPanel();	
		p_cont.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
		p_cont.setMinimumSize(new Dimension(70,50));
		//Fortschritt
		pr_bar = new JProgressBar();
		pr_bar.setPreferredSize(new Dimension(70,20));
		//ProgressBar bei Start voreingestellt auf 0 %
		pr_bar.setValue(0);
		pr_bar.setStringPainted(true);
		//ProgressBar dem Panel hinzufuegen
		p_cont.add(pr_bar);


//Panel fuer Button Settings
		JPanel p_button2 = new JPanel();	
		bSettings = new JButton("Settings");
		p_button2.setLayout(new BoxLayout(p_button2, BoxLayout.X_AXIS));
		bSettings.setEnabled(true);
		bSettings.setPreferredSize(new Dimension(90,30));
		p_button2.add(Box.createHorizontalStrut(5));
		p_button2.add(bSettings);
		p_button2.add(Box.createHorizontalStrut(5));

		this.add(p_button1);
		this.add(p_points);
		this.add(p_mun);
		this.add(p_cont);
		this.add(p_button2);
		//this.setPreferredSize(new Dimension(400,60));
		this.setVisible(true);
//Toolbar kann nicht aus dem Fenster entnommen werden
		this.setFloatable(false); 
	}
/** Rückgabe Button - Neues Spiel  */
	public JButton getNewGame(){
		return b_newGame;
	}
/** Punktestand setzten von ListenerModel  */
	public void setScore(int i){
		String s = String.valueOf(i);
		t_points.setText(s);
	}
/** Gegner am Start setzten von Hauptfenster  */
	public void setStartGegner(int s){
		startGegner = s;
	}
/** Fortschritt setzten vom ListenerModel */
	public void setProgress(int actuellGegner){
	double prozentsatz = 100/startGegner;
	double result = (startGegner-actuellGegner)*prozentsatz;
	pr_bar.setValue((int)result);
	}
	

}
