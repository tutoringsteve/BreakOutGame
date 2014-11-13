/**
 * Created by ForcesOfOdin on 7/11/2014.
 */
import java.awt.*;
import java.awt.geom.*;

public class BreakoutShape {

	private Color color;
	private boolean fill;
	private RectangularShape shape;

	public BreakoutShape( RectangularShape shape, Color color, boolean fill) {
		this.shape = shape;

		this.color = color;
		this.fill = fill;
	}

	public Color getColor() {return color;}
	public void changeColor( Color color ) {
		this.color = color;
	}

	public void draw(Graphics2D g2) {

		g2.setPaint(color);
		g2.draw(shape);
		if( fill ) {

			g2.fill(shape);
		}
	}

	public double getX() {
		return shape.getX();
	}
	public double getY() {
		return shape.getY();
	}
	public double getWidth() {
		return shape.getWidth();
	}
	public double getHeight() { return shape.getHeight(); }
	public double getCenterX() {
		return shape.getCenterX();
	}
	public double getCenterY() {
		return shape.getCenterY();
	}

	public Rectangle2D getBounds2D() {
		return shape.getBounds2D();
	}
	public boolean intersects( BreakoutShape other) {
		return shape.intersects(other.shape.getBounds());
	}

	public boolean below(BreakoutShape other) {
		return getY() >= other.getY() + other.getHeight();
	}
	public boolean above(BreakoutShape other) {
		return getY() + getHeight() <= other.getY();
	}
	public boolean leftOf(BreakoutShape other) {
		return getX() + getWidth() <= other.getX();
	}
	public boolean rightOf(BreakoutShape other) {
		return getX() >= other.getX() + other.getWidth();
	}

	public void move(int dx, int dy) {
		shape.setFrame(getX() + dx, getY() + dy, getWidth(), getHeight());
	}

	public double fractionOfRadiusAwayFromCenter( BreakoutShape other) {
			return Math.abs((other.getCenterX() - getCenterX()) / (other.getX() - other.getCenterX()));
			}

}
