import javax.swing.*;

public class Popup extends JPopupMenu {
	/**
	 * Das Popup wird erstellt
	 * die verschiedenen Themen koennen ausgewaehlt werden
	 *
	 * @author Sarah Tosun
	 * @version DionaRap
	 */
	private static final long serialVersionUID = 1L;

	private String[] name = { "Alien", "Dracula", "Helsing", "Luke", "Spacewars", "Squarehead", "Vader" };
	private Hauptfenster hf;
	public Popup(Hauptfenster hf_) {
		hf = hf_;
		ButtonGroup themaGroup = new ButtonGroup();
		JRadioButton[] theme = new JRadioButton[name.length];
		// RadioButtons und eine ButtonGroupe fuer die Themen wird erzeugt mit gewähltem Design
		for (int i = 0; i < name.length; i++) {
			theme[i] = new JRadioButton();
			theme[i].setText(name[i]);
			themaGroup.add(theme[i]);
			theme[i].setSelected(true);
			this.add(theme[i]);
			theme[i].addActionListener(new ListenerPopup(hf, name[i]));
		}
		this.setVisible(false);

	}



}
