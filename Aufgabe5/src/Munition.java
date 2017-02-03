import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.swing.JPanel;

/**
 * Munition für die Toolbar 
 * Munitionsbild laden und zeichnen / Anzeigetext und Munitionsbild laden und zeichnen
 * @author Sarah Tosun
 * @version DionaRap
 */
public class Munition extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image bild;
	private int hoehe = 50;
	private int breite = 50;
	private String anzeigeText = "";
	private String themeName;

	public Munition(String themeName) {
		this.setPreferredSize(new Dimension(50, 50));
		this.themeName = themeName;
		this.repaint();
	}

	/**
	 * Munition als Bild
	 */
	public void setAmmo() {
		anzeigeText = null;
		String datei = System.getProperty("user.dir") + File.separator + "images" + File.separator + themeName+ File.separator + "ammo.png";
		bild = this.getToolkit().getImage(datei);
		// Bild laden, this = ImageObserver
		this.prepareImage(bild, this);
	}

	/**
	 * Munition ohne Bild - Anzahl der Schuesse groesser 3, als Text dargestellt
	 * @param anzahl - vorhandene Anzahl an Schuessen vom Model
	 */
	public void setAmmo(int anzahl) {
		anzeigeText = anzahl +"+";
		this.repaint(); //neu zeichnen
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		// wenn es ein Bild ist
		if (anzeigeText == null) {
			try {
				Thread.sleep(100); // warten, sonst wird ERROR nicht korrekt erkannt
			} catch (InterruptedException e) {
			}

			if ((this.checkImage(bild, this) & ImageObserver.ERROR) == ImageObserver.ERROR) {
				System.out.println("Fehler Bild");
			}

			while ((this.checkImage(bild, this) & HEIGHT) != HEIGHT) {
			}

			while ((this.checkImage(bild, this) & WIDTH) != WIDTH) {
			}

			// Panel und Fenster der Bildgroesse anpassen
			this.setMinimumSize(new Dimension(breite, hoehe));
			this.setPreferredSize(new Dimension(breite, hoehe));
			this.setSize(breite, hoehe);

			while ((this.checkImage(bild, this) & ALLBITS) != ALLBITS) {
			}

			// Jetzt ist das Bild komplett geladen
			g2D.clearRect(0, 0, breite, hoehe); // Hintegrund zeichen
			g2D.drawImage(bild, 0, 0, breite, hoehe, this); // Bild zeichnen

		} else {
			// Der Text wird gezeichnet
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Serif", Font.PLAIN, 30);
			g2D.setFont(font);
			g2D.drawString(anzeigeText, 10, hoehe-10);
		}
	}
}
