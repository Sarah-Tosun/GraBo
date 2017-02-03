import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
/**
 * ListenerSettingsButton
 * Uebernehmen (es werden die neuen Werte uebernommen), Abbrechen (es passiert nichts)
 * @author Sarah Tosun
 * @version DionaRap
 */
public class ListenerSettingsButton implements ActionListener {

	private Hauptfenster hf;


	public ListenerSettingsButton(Hauptfenster hf) {
		this.hf = hf;
	}

	/**
	 * wird der Button uebernehmen in den Einstellungen gedrueckt, 
	 * werden alle Wert uebernommen (in der Hashmap gespeichert)
	 * @param arg0 - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (((JButton) arg0.getSource()).getText().equals("Übernehmen")) {
			HashMap<String, Integer> hm = hf.getSettings().getHashmap();
			int b1;
			int b2;
			int b3;
			hm.put("slider1", (hf.getSettings().getSlider1()).getValue());
			hm.put("slider2", (hf.getSettings().getSlider2()).getValue());
			hm.put("slider3", (hf.getSettings().getSlider3()).getValue());
			JCheckBox box1 = hf.getSettings().getBox1();
			JCheckBox box2 = hf.getSettings().getBox2();
			JCheckBox box3 = hf.getSettings().getBox3();
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
			hm.put("box1", b1);
			hm.put("box2", b2);
			hm.put("box3", b3);
			hm.put("text1", (Integer.parseInt(hf.getSettings().getText1().getText())));
			hm.put("text2", (Integer.parseInt(hf.getSettings().getText2().getText())));
			hm.put("text3", (Integer.parseInt(hf.getSettings().getText3().getText())));
			hm.put("text4", (Integer.parseInt(hf.getSettings().getText4().getText())));

		}
		if (((JButton) arg0.getSource()).getText().equals("Abbruch")) {
			//nichts tun
		}
	}

}
