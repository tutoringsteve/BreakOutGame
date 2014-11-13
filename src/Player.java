/**
 * Created by ForcesOfOdin on 7/12/2014.
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
	private static int INITIAL_NUM_LIVES = 3;
	private static int IMAGE_Y_POSITION = Paddle.START_Y + 2*Paddle.HEIGHT;
	private static int IMAGE_HORIZONTAL_GAP = 5;
	private static String imageFilePath = "C:\\Users\\ForcesOfOdin\\Documents\\NetBeansProjects\\BreakOutGame\\src\\player.gif";
	private int numLives;

	public Player() {
		this.numLives = INITIAL_NUM_LIVES;
	}

	public void killPlayer() {
		numLives--;
	}

	public boolean isAlive() {
		return (numLives > 0);
	}

	public void draw(Graphics2D g2) {
		try {
			Image image = ImageIO.read(new File(imageFilePath));

			for( int x = 0; x < numLives; x++ ) {
				g2.drawImage(image, x*(image.getWidth(null) + IMAGE_HORIZONTAL_GAP), (IMAGE_Y_POSITION ), null);
			}
		} catch (  FileNotFoundException e) {
			System.out.println("Loading the image " + imageFilePath + " caused a " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Loading the image " + imageFilePath + " caused a " + e.getMessage());
		}
	}

}
