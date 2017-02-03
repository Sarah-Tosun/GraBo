import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
//import javax.swing.JPopupMenu;
//import javax.swing.JPopupMenu;
import javax.swing.UnsupportedLookAndFeelException;


public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hauptfenster hf;
	private JMenu menuToolbar;
	private JMenuItem menuLookaFeel;
	private JCheckBoxMenuItem checkBoxItem;
	private JMenuItem menuNavigation;
	
	//private JMenuItem popToolbar;
	//private JPopupMenu pop;
	
	MenuBar(Hauptfenster hauptfenster){
	hf = hauptfenster;
	JMenu menu = new JMenu("Ansicht");

	menu.add(menuToolbar = new JMenu("Position Toolbar"));
	JMenuItem oben = new JMenuItem("Oben");
	JMenuItem unten = new JMenuItem("Unten");
	menuToolbar.add(oben);
	menuToolbar.add(unten);
	
	menuNavigation = (JMenuItem) (new JMenuItem().add(checkBoxItem = new JCheckBoxMenuItem("Navigator anzeigen",true)));
	menu.add(menuNavigation);
	LookAndFeelInfo[] lafi = UIManager.getInstalledLookAndFeels();
	JMenuItem[] lookaFeels = new JMenuItem[lafi.length];
	
	System.out.println(UIManager.getInstalledLookAndFeels().getClass());
	menu.add(menuLookaFeel = new JMenu("Look and Feel")); 
	for(int i=0; i < lafi.length; i++){
		lookaFeels[i] = new JMenuItem();
		lookaFeels[i].setText(lafi[i].getName());
		menuLookaFeel.add(lookaFeels[i]);
		lookaFeels[i].addActionListener(new ListenerMenuLook(hf, lafi[i].getName()));
	}
	
	
	this.add(menu);
	
	
	
	
	
	
	/*pop = new JPopupMenu();
  	popToolbar = new JMenuItem();	
	pop.setLocation(100, 100);
	pop.add(popToolbar);
	pop.setVisible(true);
*/
	menuToolbar.addActionListener(new ListenerMenuToolbar(hf)); 
	checkBoxItem.addActionListener(new ListenerMenuNav(hf)); 

	
	}
}


