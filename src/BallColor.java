import java.awt.*;

/**
 * Created by ForcesOfOdin on 7/13/2014.
 */
public enum BallColor {
	Red(Color.RED),
	Blue(Color.BLUE),
	Green(Color.GREEN);

	private Color color;

	BallColor(Color color) {
		this.color = color;
	}

	Color color() {
		return color;
	}
}
