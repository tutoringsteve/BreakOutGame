import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by ForcesOfOdin on 7/24/2014.
 */
public class ImageEntity {
	protected Image image;
	protected JFrame jFrame;
	protected AffineTransform affineTransform;
	protected Graphics2D g2d;
	protected boolean alive;

	public ImageEntity(JFrame jFrame) {
		this.jFrame = jFrame;
		setImage(null);
		setAlive(true);
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
		double x = jFrame.getSize().width/2 - image.getWidth(jFrame)/2;
		double y = jFrame.getSize().height/2 - image.getHeight(jFrame)/2;
		affineTransform = AffineTransform.getTranslateInstance(x,y);
	}

	public int getWidth() {
		if(image != null ) {
			return (int) this.image.getWidth(jFrame);
		} else {
			return 0;
		}
	}

	public int getHeight() {
		if(image != null ) {
			return (int) this.image.getHeight(jFrame);
		} else {
			return 0;
		}
	}

	public double getCenterX() {
		return getX() + image.getHeight(jFrame)/2;
	}
	public double getCenterY() {
		return getY() + image.getHeight(jFrame)/2;
	}

	public double getX() {
		return
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return this.alive;
	}

}