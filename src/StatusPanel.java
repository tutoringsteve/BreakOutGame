import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by ForcesOfOdin on 7/13/2014.
 *
public class StatusPanel extends JPanel {

	Player player;
	String score = "00000000";
	boolean isPaused = false;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = ( Graphics2D ) g;

		if(isPaused) {
			showMessage("PAUSED", g2, Color.BLACK, 20, getOuterWidth() - textBox.getOuterWidth);
		}

		player.draw(g2);
		showMessage("Score: " + score , g2);
	}

	public void showMessage(String s, Graphics2D g2) {
		Font myFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, 40);
		g2.setFont(myFont);
		g2.setColor(Color.RED);
		Rectangle2D textBox = myFont.getStringBounds(s, g2.getFontRenderContext());
		g2.drawString(s, (int) (getOuterWidth()  / 2 - textBox.getOuterWidth()  / 2)
				, (int) (getOuterHeight() / 2 - textBox.getOuterHeight() / 2) );
	}

	public void showMessage(String s, Graphics2D g2, Color fontColor, int fontSize, int xPosition, int yPosition) {
		Font myFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, fontSize);
		g2.setFont(myFont);
		g2.setColor(fontColor);
		Rectangle2D textBox = myFont.getStringBounds(s, g2.getFontRenderContext());
		g2.drawString(s, (int) (xPosition), (int) (yPosition) );
	}

}
**/