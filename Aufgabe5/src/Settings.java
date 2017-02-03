import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * Erstellen der Settings (Slider, Checkboxen, Textfelder)
 * Es koennen Einstellungen am Spiel vorgenommen werden
 * @author Sarah Tosun
 * @version DionaRap
 */

public class Settings extends JDialog {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> hm = new HashMap<String, Integer>();
	private JSlider slider1;
	private JSlider slider2;
	private JSlider slider3;
	private JCheckBox box1;
	private JCheckBox box2;
	private JCheckBox box3;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JButton take;
	private JButton cancel;
	int b1;
	int b2;
	int b3;
	int t1;
	int t2;
	int t3;
	int t4;


	public Settings(Hauptfenster hf) {
		super(hf);
		this.setPreferredSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(11, 2));
		// Zeile 1
		JLabel label1 = new JLabel("Wartezeit der Gegner:");
		slider1 = new JSlider();
		slider1.setMinimum(0);
		slider1.setMaximum(10000);
		// 10000/Major 2000 = 5 somit 5 Zahlen/Abstaende
		slider1.setMajorTickSpacing(2000);
		// Zwischenabstaende 2000 / Minor 500 = 4;
		slider1.setMinorTickSpacing(500);
		slider1.setPaintTicks(true);
		// Beschriftung
		slider1.createStandardLabels(1);
		slider1.setPaintLabels(true);
		// Voreinstellung wo steht der Regler
		slider1.setValue(6000);

		// Zeile 2
		JLabel label2 = new JLabel("Verzögerung eines Schusses:");
		slider2 = new JSlider();
		slider2.setMinimum(0);
		slider2.setMaximum(10000);
		// 10000/Major 2000 = 5 somit 5 Zahlen/Abstaende
		slider2.setMajorTickSpacing(2000);
		// Zwischenabstaende 2000 / Minor 500 = 4;
		slider2.setMinorTickSpacing(500);
		slider2.setPaintTicks(true);
		slider2.createStandardLabels(1);
		slider2.setPaintLabels(true);
		// Voreinstellung wo steht der Regler
		slider2.setValue(500);

		// Zeile 3
		JLabel label3 = new JLabel("Wartezeit eines Gegners vor jedem Schritt:");
		slider3 = new JSlider();
		slider3.setMinimum(0);
		slider3.setMaximum(10000);
		// 10000/Major 2000 = 5 somit 5 Zahlen/Abstaende
		slider3.setMajorTickSpacing(2000);
		// Zwischenabstaende 2000 / Minor 500 = 4;
		slider3.setMinorTickSpacing(500);
		slider3.setPaintTicks(true);
		slider3.createStandardLabels(1);
		slider3.setPaintLabels(true);
		// Voreinstellung wo steht der Regler
		slider3.setValue(4000);

		// Zeile 4
		JLabel label4 = new JLabel();
		box1 = new JCheckBox("Zufällige Wartezeiten der Gegner", false);

		// Zeile5
		JLabel label5 = new JLabel();
		box2 = new JCheckBox("Gegner meiden Kollission mit Hindernis", true);

		// Zeile6
		JLabel label6 = new JLabel();
		box3 = new JCheckBox("Gegner meiden Kollission mit anderen Gegnern", false);

		// Zeile7
		JLabel label7 = new JLabel("Anzahl Zeilen des Spielfeldes:");
		text1 = new JTextField("12", 2);
		text1.setEditable(true);

		// Zeile8
		JLabel label8 = new JLabel("Anzahl Spalten des Spielfeldes:");
		text2 = new JTextField("12", 2);
		text2.setEditable(true);

		// Zeile9
		JLabel label9 = new JLabel("Anzahl Hindernisse:");
		text3 = new JTextField("8", 1);
		text3.setEditable(true);

		// Zeile10
		JLabel label10 = new JLabel("Anzahl der Gegner:");
		text4 = new JTextField("5", 1);
		text4.setEditable(true);

		// Zeile11
		take = new JButton("Übernehmen");
		cancel = new JButton("Abbruch");
		take.addActionListener(new ListenerSettingsButton(hf));
		cancel.addActionListener(new ListenerSettingsButton(hf));

		this.add(label1);
		this.add(slider1);
		this.add(label2);
		this.add(slider2);
		this.add(label3);
		this.add(slider3);
		this.add(label4);
		this.add(box1);
		this.add(label5);
		this.add(box2);
		this.add(label6);
		this.add(box3);
		this.add(label7);
		this.add(text1);
		this.add(label8);
		this.add(text2);
		this.add(label9);
		this.add(text3);
		this.add(label10);
		this.add(text4);
		this.add(take);
		this.add(cancel);
		this.pack();
		this.setLocation(hf.getX() + 10, hf.getY() + 100);
		//Hashmap fuellen (und den Settings hinzugefuegt)
		this.setHashmap(hm);
	}

	public HashMap<String, Integer> getHashmap() {
		return hm;
	}
	/**
	 * Hashmap fuellen (Voreinstellungen)
	 * @param hm Haschmap Settings
	 */
	public void setHashmap(HashMap<String, Integer> hm) {
		// Hashmap
		t1 = Integer.parseInt(text1.getText());
		t2 = Integer.parseInt(text2.getText());
		t3 = Integer.parseInt(text3.getText());
		t4 = Integer.parseInt(text4.getText());
		if (box1.isSelected()) {
			b1 = 1;
		} else {
			b1 = 0;
		}
		if (box2.isSelected()) {
			b2 = 1;
		} else {
			b2 = 0;
		}
		if (box3.isSelected()) {
			b3 = 1;
		} else {
			b3 = 0;
		}
		hm.put("slider1", slider1.getValue());
		hm.put("slider2", slider2.getValue());
		hm.put("slider3", slider3.getValue());
		hm.put("box1", b1);
		hm.put("box2", b2);
		hm.put("box3", b3);
		hm.put("text1", t1);
		hm.put("text2", t2);
		hm.put("text3", t3);
		hm.put("text4", t4);
	}
	public JSlider getSlider1(){
		return slider1;
	}
	public JSlider getSlider2(){
		return slider2;
	}
	public JSlider getSlider3(){
		return slider3;
	}
	public JCheckBox getBox1(){
		return box1;
	}
	public JCheckBox getBox2(){
		return box2;
	}
	public JCheckBox getBox3(){
		return box3;
	}
	public JTextField getText1(){
		return text1;
	}
	public JTextField getText2(){
		return text2;
	}
	public JTextField getText3(){
		return text3;
	}
	public JTextField getText4(){
		return text4;
	}
	
}
