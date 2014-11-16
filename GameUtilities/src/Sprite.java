import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steven A. Sarasin on 8/7/2014.
 */
public interface Sprite {
	void setImage(String name)
	// assign the name image to the sprite  // end of setImage()
	;

	BufferedImage getImage();

	/**
	 * Switch on loop playing
	 * @param animPeriod time length between game updates
	 * @param seqDuration time length of animation loop for the sprite
	 */
	void loopImage(int animPeriod, double seqDuration);

	/**
	 * Stop sprite animation looping
	 */
	void stopLooping();

	/**
	 *
	 * @return width of bounding box of sprite
	 */
	int getWidth();

	/**
	 *
	 * @return height of bounding box of sprite
	 */
	int getHeight();

	/**
	 *
	 * @return width of game panel
	 */
	int getPWidth();

	/**
	 *
	 * @return height of game panel
	 */
	int getPHeight();

	/**
	 *
	 * @return true if sprite should be updated
	 */
	boolean isActive();

	void setActive(boolean a);

	void setPosition(int x, int y);

	void translate(int xDist, int yDist);

	int getXPosn();

	int getYPosn();

	void setStep(int dx, int dy);

	int getXStep();

	int getYStep();

	Rectangle getMyRectangle();

	void updateSprite();
	// move the sprite

	void drawSprite(Graphics g);

}
