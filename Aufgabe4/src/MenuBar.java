import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
//import javax.swing.JPopupMenu;

public class MenuBar extends JMenuBar {
	/**
	 * Die MenuBar wird erstellt
	 * diese beinhaltet die Toolbar oben oder unten im Fenster
	 *
	 * @author Sarah Tosun
	 * @version DionaRap
	 */
	private static final long serialVersionUID = 1L;
	private Hauptfenster hf;
	private JMenu menuToolbar;
	private JMenu menuLookaFeel;
	private JCheckBoxMenuItem checkBoxItem;
	private JMenuItem menuNavigation;
	private JRadioButton oben;
	private JRadioButton unten;
	
	MenuBar(Hauptfenster hf_){
	hf = hf_;
	JMenu menu = new JMenu("Ansicht");

	menu.add(menuToolbar = new JMenu("Position Toolbar"));
	oben = new JRadioButton("Oben");
	unten = new JRadioButton("Unten");
	ButtonGroup positionGroup = new ButtonGroup();
	positionGroup.add(oben);
	positionGroup.add(unten);
	oben.setSelected(true);
	menuToolbar.add(oben);
	menuToolbar.add(unten);
	menuToolbar.addActionListener(new ListenerMenuToolbarOben(hf));
	
	menuNavigation = (JMenuItem) (new JMenuItem().add(checkBoxItem = new JCheckBoxMenuItem("Navigator anzeigen",true)));
	menu.add(menuNavigation);
	ButtonGroup lookGroup = new ButtonGroup();
	LookAndFeelInfo[] lafi = UIManager.getInstalledLookAndFeels();
	JRadioButton[] lookaFeels = new JRadioButton[lafi.length];
	
	//System.out.println(UIManager.getInstalledLookAndFeels().getClass());
	menu.add(menuLookaFeel = new JMenu("Look and Feel")); 
	for(int i=0; i < lafi.length; i++){
		lookaFeels[i] = new JRadioButton();
		lookaFeels[i].setText(lafi[i].getName());
		lookGroup.add(lookaFeels[i]);
		lookaFeels[i].setSelected(true);
		menuLookaFeel.add(lookaFeels[i]);
		lookaFeels[i].addActionListener(new ListenerMenuLook(hf, lafi[i].getName()));
	}	
	menu.addMouseListener(new ListenerMouseMenu(hf));
	this.add(menu);
	
	checkBoxItem.addActionListener(new ListenerMenuNav(hf)); 	
	oben.addActionListener(new ListenerMenuToolbarOben(hf)); 
	unten.addActionListener(new ListenerMenuToolbarUnten(hf)); 
	}
	
}


