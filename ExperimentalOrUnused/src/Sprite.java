import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

/**
 * Created by ForcesOfOdin on 7/24/2014.
 */
public class Sprite {

//TODO Move getters and setters past the Constructor
	/**
	 * TODO: check the creation method for Brick, looks like it might be creating
	 * TODO: them with their "top-left-corner" as the bottom right corner...
	 * *
	 */
	private JPanel panel;
	private Graphics2D g2d;
	private BreakoutShape breakoutShape;
	private Image image;
	private boolean alive;
	private Point2D upperLeftCorner;
	private Point2D center;
	private Point2D velocity;
	private double angularSpeed; //internally a radaian measure, but can be returned as either and user MUST specify.

	private int currentState;

	private int currentFrame; /* how would currentFrame differ from frameCount? */
	private int totalFrames;
	private int animationDirection;
	private int frameCount;
	private int frameDelay;
	private int frameWidth, frameHeight;
	private int columns;

	private double moveAngle, faceAngle;


	public AnimatedSprite(JPanel panel, Graphics2D g2d) {
		this.panel = panel;
		this.g2d = g2d;
		image = null;
		alive = true;
		upperLeftCorner = new Point2D.Double(0.0, 0.0);
		velocity = new Point2D.Double(0.0, 0.0);
		angularSpeed = 0.0;
		currentState = 0;
		currentFrame = 0;
		totalFrames = 1;
		animationDirection = 1;
		frameCount = 0;
		frameDelay = 0;
		frameWidth = 0;
		frameHeight = 0;
		columns = 1;
		moveAngle = 0.0;
		faceAngle = 0.0;
	}

	public double getAngularSpeedInDegrees() {
		return Math.toDegrees(angularSpeed);
	}

	public double getAngularSpeedInRadians() {
		return angularSpeed;
	}

	/**
	 * Alive is synonymous with still-in-play, a broken brick would return false, a player with lives remaining would return true
	 * until the last life was taken, in which case it would return true;
	 *
	 * @return true if sprite is still in play.
	 */
	public boolean isAlive() {
		return this.alive;
	}

	/**
	 * private mutators, both kill and destroy are the same thing, they set alive to false;
	 */
	private void kill() {
		this.alive = false;
	}

	private void destroy() {
		this.kill();
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public Graphics2D getGraphics2D() {
		return this.g2d;
	}

	public void setGraphics2D(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public void setGraphics(Graphics g) {
		this.g2d = (Graphics2D) g;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getWidth() {
		if (image != null) {
			return breakoutShape.getWidth();
		} else {
			return 0;
		}
	}

	public double getHeight() {
		if (image != null) {
			return breakoutShape.getHeight();
		} else {
			return 0;
		}
	}

	public double getCenterX() {return this.center.getX();}
	public double getCenterY() {return this.center.getY();}
	public Point2D getCenter() {return this.center;}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(upperLeftCorner.getX(), upperLeftCorner.getY(), getWidth(), getHeight());
	}
	public void load(String fileName, int columns, int totalFrames,
	                 int width, int height) {

		Toolkit tk = Toolkit.getDefaultToolkit();
		image = tk.getImage(getURL(fileName));
		while (image.getWidth(currentFrame) <= 0);
		this.columns = columns;
		this.totalFrames = totalFrames;
		frameWidth = width;
		frameHeight = height;

	}

}