import java.awt.*;
import java.awt.geom.*;

/**
 * Created by ForcesOfOdin on 7/13/2014.
 */
public class Brick extends BreakoutShape {

	//TODO: Would like to leave the WIDTH , HEIGHT relationship to the implementing classes
	public static final int HEIGHT = 10; //10 @ default frame HEIGHT of 660;
	public static final int WIDTH = 30;//30 @ default frame WIDTH of 480;
	private static final int BRICK_HORIZONTAL_GAP = 2;
	private static final int BRICK_VERTICAL_GAP = 2;
	private Long timeBrickWasBroken = 0L;

	public Brick(int row, int col, Color color) {
		super(new Rectangle2D.Double(BRICK_HORIZONTAL_GAP + row*(BRICK_HORIZONTAL_GAP + Brick.WIDTH),
									 BRICK_VERTICAL_GAP + col*(BRICK_VERTICAL_GAP + Brick.HEIGHT),
									 WIDTH, HEIGHT), color, true);

	}

	private Brick(Rectangle2D rectangle, Color color) {
		super(rectangle, color, true);
	}

	public Brick add(Brick other) {
		Rectangle2D rectangle1 = this.getBounds2D();
		Rectangle2D rectangle2 = other.getBounds2D();
		rectangle1.add(rectangle2);
		return new Brick(rectangle1, this.getColor());
	}

	public void recordTimeBrickWasBrokenInMillis(Long time) {
		this.timeBrickWasBroken = time;
	}

	public Long timeBrickWasBrokenInMillis() {
		return timeBrickWasBroken;
	}

}
