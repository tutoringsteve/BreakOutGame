import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by ForcesOfOdin on 7/19/2014.

public class BreakoutText {
 Color color;
 Font font;
 String string;
 double stringBaselineOfFirstCharacter_X, stringBaselineOfFirstCharacter_Y;

}
	public void showScore(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 20));
		g2.drawString("SCORE: " + score.toString() , 20, this.getHeight() - 5);
	}

	public void showMessage(String s, Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40););
		g2.drawString(s, (int) (getWidth()  / 2 - textBox.getWidth()  / 2), (int) (getHeight() / 2 - textBox.getHeight() / 2));

        Rectangle2D textBox = myFont.getStringBounds(s, g2.getFontRenderContext());
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = ( Graphics2D ) g;

		if(!player.isAlive()) {
			showMessage("GAME OVER!", g2);
			gameStarted = false;
		} else if(bricks.isEmpty()) {
			showMessage("YAY - you won", g2 );
		} else {
			ball.draw(g2);
			paddle.draw(g2);
			player.draw(g2);
			for( Brick brick : bricks) {
				brick.draw(g2);
			}
			showScore(g2);

		}
		if (gameStarted) {
			player.draw(g2);
		}
/*
*	} */