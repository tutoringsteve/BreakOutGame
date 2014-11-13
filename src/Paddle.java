/**
 * Created by ForcesOfOdin on 7/11/2014.
 */
import java.awt.*;
import java.awt.geom.*;


public class Paddle extends BreakoutShape {

        private static final int WIDTH = 50;
        public static final int HEIGHT = 10;
	    public static final int START_X = BreakoutFrame.WIDTH/2;
		public static final int START_Y = (int) ((4.0/5.0)* (double)BreakoutFrame.HEIGHT);

		//TODO: Paddle movement broken for keyboard play, probably due to timer granularity, increasing speed just makes paddle jump
		private static final int SPEED = 10;

		private BreakoutPanel panel;

		public Paddle (Color innerColor, BreakoutPanel panel) {
			super(new Rectangle2D.Double(START_X, START_Y, WIDTH, HEIGHT), innerColor, true);
			this.panel = panel;
		}

		public void move(int dx) {
			if( (getX() + dx >= 0) && (getX() + dx + WIDTH <= panel.getWidth())) {
				move(dx, 0);
			}
		}

		public void moveRight() {
			move(SPEED);
		}

		public void moveLeft() {
			move(-SPEED);
		}
}
