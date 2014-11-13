import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Steven A. Sarasin on 8/11/2014.
 */
public class BreakoutMessage {

	final private String MESSAGE_TEXT;

	final private Font MESSAGE_FONT;
	final private Color MESSAGE_FONT_COLOR;

	final private Graphics2D G2D;

	/**
	 *
	 * @param message_text The body of the message
	 * @param message_font The font used to display the message
	 * @param message_color The color of the message_text
	 * @param x The integer x coordinate of the bottom right
	 *          of the message display rectangle
	 * @param y The integer y coordinate of the bottom right
	 *          of the message display rectangle
	 * @param g2D The graphics2D context to display the message
	 *
	 */
	public BreakoutMessage(String message_text, Font message_font, Color message_color, int x, int y, Graphics2D g2D) {
		MESSAGE_TEXT = message_text;

		MESSAGE_FONT = message_font;
		MESSAGE_FONT_COLOR = message_color;

		G2D = g2D;
	}

	/**
	 * returns the message String's bounding box, a rectangle2D object that encloses
	 * the entire message.
	 * @return Rectangle2D that bounds the message display area
	 *         Used to incorporate message size in the positioning
	 *         coordinates of the message.
	 */
	public Rectangle2D getMessageTextBox() {
		return MESSAGE_FONT.getStringBounds(MESSAGE_TEXT, G2D.getFontRenderContext());
	}

	/**
	 * displays the message_text at the constructed x and y with the constructed font, and font color.
	 * @param xCoordBottomLeftOfMessage integer x coordinate bottom left of display rectangle for message
	 * @param yCoordBottomLeftOfMessage integer y coordinate bottom left of display rectangle for message
	 */
	public void showMessage(int xCoordBottomLeftOfMessage, int yCoordBottomLeftOfMessage) {
		G2D.setFont(MESSAGE_FONT);
		G2D.setColor(MESSAGE_FONT_COLOR);
		G2D.drawString(MESSAGE_TEXT, xCoordBottomLeftOfMessage, yCoordBottomLeftOfMessage);
	}

	/**
	 *
	 * @return IntelliJ IDEA 13.1.4 default generated toString
	 */
	@Override
	public String toString() {
		return "BreakoutMessage{" +
				"MESSAGE_TEXT='" + MESSAGE_TEXT + '\'' +
				", MESSAGE_FONT=" + MESSAGE_FONT.toString() +
				", MESSAGE_FONT_COLOR=" + MESSAGE_FONT_COLOR.toString() +
				", G2D=" + G2D.toString() +
				'}';
	}
}
