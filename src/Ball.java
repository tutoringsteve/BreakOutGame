/**
 * Created by ForcesOfOdin on 7/11/2014.
 */

import java.awt.*;
import java.awt.geom.*;


public class Ball extends BreakoutShape {

	private static final int SIZE = 10; //ball has square bounding box WIDTH = HEIGHT = SIZE
	private static final int START_X = 200;
	private static final int START_Y = 400;

	/**
	 * maximumOfAbsoluteValueOfVelocityComponents = max{|v_x|, |v_y|} where v = velocity = {v_x, v_y}
	 * and |v_x| = absolute value of the x-component of the vector v
	 * e.g. v = {1,-1} , ball is moving right and up, maximumOfAbsoluteValueOfVelocityComponents is 1
	 */
	private int maximumOfAbsoluteValueOfVelocityComponents = 1;
	private int dx = 1; //starts ball up and right
	private int dy = -1;

	private BreakoutPanel panel;

	public Ball(Color color, BreakoutPanel panel) {
		//TODO switch to double precision speed.

		//Ellipse2D.Double( cornerBoundingBox.x , cornerBoundingBox.y, widthBoundingBox, heightBoundingBox )
		super(new Ellipse2D.Double(START_X, START_Y, SIZE , SIZE), color, true);
		this.panel = panel;
	}

	/**
	 * Generates another ball ahead of the player ball to feel out collisions and ball-loss-conditions.
	 * @return Ball the virtual 'look ahead' ball that is used to detect collisions.
	*/
	public Ball getVirtualBall(){
		return new Ball(super.getColor(), new Ellipse2D.Double(getX() + dx, getY() + dy, SIZE, SIZE));
	}
	private Ball(Color color, Ellipse2D.Double ellipse) {
		super(ellipse,color,true);
	}

	/**
	 *If virtual ball (look-ahead ball) is outside of game-panel have real ball move towards inside of panel.
	 */
	//TODO possibly have paddlespeed incorporated into how fast the bounced ball goes,
	//TODO perhaps you should measure the euclidean length of v and cap it to within some maximum.
	public void move() {

		//left of game panel
		if(getX() + dx < 0)
			goRight();

		//right of game panel
		if(getX() + getWidth() + dx > panel.getWidth())
			goLeft();

		//above game panel
		if(getY() + dy < 0)
			goDown();

		//below game panel....shouldn't actually happen!
		if(getY() + getHeight() + dy > panel.getHeight())
			goUp();

		super.move(dx, dy);
	}

	//Set speed modifier to be greater the closer the impact was to the edge of the brick/paddle.
	//Corner hits on the paddle will make the ball move much faster for twitch gameplay and 'just-in-time' hits/saves.
	public void setMaximumOfAbsoluteValueOfVelocityComponents(int maximumOfAbsoluteValueOfVelocityComponents) { //renamed setMaximumOfAbsoluteValueOfVelocityComponents from speedModifier
		this.maximumOfAbsoluteValueOfVelocityComponents = maximumOfAbsoluteValueOfVelocityComponents;
	}

	/**
	 * Send the ball downwards with the larger of 1 and maximum of the absolute value of the component velocities
	 * max { 1, |v| /2}
	 */
	public void goUp() {
		if(maximumOfAbsoluteValueOfVelocityComponents /2 > 0) {
			dy = -maximumOfAbsoluteValueOfVelocityComponents /2;
		}
		//conditional executed if maxAbsVelocityComponent is less than a half, because integer type will round off decimal!
		else {
			dy = -1;
		}
	}

	/**
	 * Send the ball downwards with the larger of 1 and maximum of the absolute value of the component velocities
	 * max { 1, |v| /2}
	 */
	public void goDown() {
		if(maximumOfAbsoluteValueOfVelocityComponents /2 > 0) {
			dy = maximumOfAbsoluteValueOfVelocityComponents /2;
		}
		else {
			dy = 1;
		}
	}

	/**
	 * makes ball go left with the same speed as its
	 * current fastest component speed.
	 */
	public void goLeft() {
		dx = -maximumOfAbsoluteValueOfVelocityComponents;
	}

	/**
	 * makes ball go right with the same speed as its
	 * current fastest component speed.
	 */
	public void goRight() {
		dx = maximumOfAbsoluteValueOfVelocityComponents;
	}


	/**
	 * Computes Euclidean norm of vector v = {dx, dy} and returns the result
	 * as a double precision variable;
	 * @return double representing Euclidean norm of vector {dx, dy}
	 */
	public double euclideanTotalSpeedAsDouble() {
		return Math.sqrt(dx*dx + dy*dy);
	}

	/**
	 * Computes Euclidean norm of vector v = {dx, dy} and returns the result
	 * as an integer cast from the original double precision method.
	 * @return integer representing Euclidean norm of vector {dx, dy}
	 * round-off / truncation oddities likely! Use with caution!
	 */
	public int euclideanTotalSpeedAsInt() {
		return (int) euclideanTotalSpeedAsDouble();
	}

}
