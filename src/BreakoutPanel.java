/**
 * Created by ForcesOfOdin on 7/11/2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BreakoutPanel extends JPanel {

	private static final int NUM_BRICK_ROWS = 35 ;
	private static final int NUM_BRICK_COLUMNS = 15;// BreakoutFrame.WIDTH / Brick.WIDTH;
	private static final int SCORE_POPUP_DURATION = 200;

	private Score score;
	private Timer timer;

	private Ball ball;
	private Paddle paddle;
	private Player player;

	private boolean gameStarted = false;

	private volatile ArrayList<Brick> bricks;
	private volatile ArrayList<Brick> brokenBricksAwaitingScorePopUp;
	private volatile ArrayList<Brick> brokenBricksAlreadyScored;
	private volatile ArrayList<Long> scorePopUpTimers;


	/**
	 * Adds all game features and generates new clock to poll at 10ms
	 * Also adds KeyListeners for using arrow keys to control paddle
	 * Also adds MouseListener for using mouse to control paddle.
	 */
	public BreakoutPanel() {

		setBackground(Color.DARK_GRAY);

		ball = new Ball(Color.RED, this);

		paddle = new Paddle(Color.BLUE, this);

		bricks = new ArrayList<Brick>();
		brokenBricksAwaitingScorePopUp = new ArrayList<Brick>();
		scorePopUpTimers = new ArrayList<Long>();
		brokenBricksAlreadyScored = new ArrayList<Brick>();
		player = new Player();
		score = Score.StartWithZeroScore();
		createBricks();

		/**TODO Replace timer with frame sleep cycle */
		/** timer updates panel every 10ms. */
		timer = new Timer(10, new TimeListener());
		timer.start();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode() == KeyEvent.VK_LEFT ) { paddle.moveLeft(); }
				if( e.getKeyCode() == KeyEvent.VK_RIGHT ) { paddle.moveRight(); }
				repaint();
			}
		});

		addMouseMotionListener( new MouseMotionAdapter() {
			boolean firstTime = true;
			int oldX;

			public void mouseMoved(MouseEvent e) {
				if(firstTime) {
					oldX = e.getX();
					firstTime = false;
				}
				paddle.move(e.getX() - oldX);
				oldX = e.getX();
				repaint();
			}
		});
		setFocusable(true);
	}

	/**
	 * Resets the ball to the upperLeftCorner START_X, START_Y
	 * which are defined in the Ball class.
	 */
	public void resetBall() {
		Color currentBallColor = this.ball.getColor();
		this.ball = new Ball(currentBallColor, this);
	}

	/**
	 * Resets the player which resets the number of lives.
	 */
	public void resetPlayer() {
		this.player = new Player();
	}

	/**
	 * Starting a new game will simply pause the timer.
	 * If you loose all lives the start option will reset
	 * the number of lives and the bricks, ball, paddle to
	 * their original state.
	 *
	 * Note that opening the game
	 * counts as starting with no lives and game will start
	 * playing immediately.
	 *
	 */
	public void start() {
		gameStarted = true;

		if( timer != null ) {
			timer.stop();
			while(player.isAlive()) {
				player.killPlayer();
			}
		}
		if(!player.isAlive()) {
			resetPlayer();
			resetBall();
			createBricks();
			timer.start();
		}
	}

	/**
	 * Checks to see if a timer exists to avoid exception
	 * if paused will un-Pause
	 */
	public void togglePause() {
		if(timer == null) {
			return;
		}
		if(timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}

	}

	/**
	 * Speed is inversely proportional to the timer delay.
	 * A timeDelay of 20 results in a slower game than a t
	 * timer delay of 1 or 5. The different speeds are
	 * mapped to their qualitative descriptions (FAST, NORMAL, SLOW)
	 * via an Enum class "BallTimerDelay"
	 * @param timerDelay taken from BallTimerDelay Enum class
	 *                   higher delay means slower moving ball
	 */
	public void changeBallSpeed(int timerDelay) {
		timer.setDelay(timerDelay);
	}

	/**
	 * Changes ball's color to that of one of the Enum BallColor's
	 * predefined types.
	 * @param ballColor BallColor is an enum class for the Colors that
	 *                  are allowed.
	 */
	public void changeBallColor(BallColor ballColor) {
		ball.changeColor(ballColor.color());
		repaint();
	}

	/**
	 * Random color picker for coloring the bricks.
	 *
	 * Generates random (Red, Green, Blue) values with
	 * 0<= R,G,B <= 255 and strictly RED if chosen to be
	 * the same as the background color (which would make
	 * the brick invisible).
	 *
	 * Does NOT check neighboring bricks
	 * against each other. Also, only checks for exact color match
	 * instead of a range of RGB that are indistinguishable.
	 *
	 * @return Color that bricks will be painted.
	 */
	private Color getRandomColor() {
		Color color = new Color((int) (Math.random() * 128),
								(int) (Math.random() * 64 + 128),
								(int) (Math.random() * 128));
		if (getBackground().equals(color) || color.equals(color.GREEN)) {
			return Color.RED;
		}
		return color;
	}

	/**
	 * Generates all the bricks for a new game of Breakout
	 * and stores them in an ArrayList called bricks.
	 */
	private  void  createBricks() {
		for (int row = 0; row < NUM_BRICK_ROWS; row++){
			for (int col = 0; col < NUM_BRICK_COLUMNS; col++) {
				bricks.add(new Brick(col, row, getRandomColor()));
			}
		}
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


				for(Brick brick : brokenBricksAwaitingScorePopUp) {
					if((System.currentTimeMillis() - brick.timeBrickWasBrokenInMillis() <= SCORE_POPUP_DURATION)) {
						displayPointsEarnedTextAtBrickBreak(g2, brick);
					} else {
						brokenBricksAlreadyScored.add(brick);
					}
				}
			}
				if (gameStarted) {
					player.draw(g2);
				}

/*
*/	}

	public void displayPointsEarnedTextAtBrickBreak(Graphics2D g2, Brick brickBroken) {
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("SansSerif", Font.BOLD, 18));
		AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F);
		g2.setComposite(newComposite);
		g2.drawString(new StringBuilder().append("+").append(score.pointsForScoringEvent()).append("!").toString(),(int) brickBroken.getCenterX(),(int) brickBroken.getCenterY());
	}

	//TODO: delete showScore showMessage methods from BreakoutPanel class
	//TODO: replace deleted showScore, showMessage with use of BreakoutMessage
	public void showScore(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 20));
		g2.drawString("SCORE: " + score.toString() , 20, this.getHeight() - 5);
	}

	/**
	 * Displays the message text s = "...<message> ..." on the screen in
	 * large red italics. Currently covers whole panel with the background color
	 * and the red, bold italic text on top of that.
	 * @param s message text to be displayed.
	 * @param g2 the BreakoutPanel
	 *           Probably will transfer lives, scoring to another panel.
	 */
	public void showMessage(String s, Graphics2D g2) {
		Font myFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, 40);
		g2.setFont(myFont);
		g2.setColor(Color.RED);
		Rectangle2D textBox = myFont.getStringBounds(s, g2.getFontRenderContext());
		g2.drawString(s, (int) (getWidth()  / 2 - textBox.getWidth()  / 2)
					   , (int) (getHeight() / 2 - textBox.getHeight() / 2));
	}

	/**
	 * Auto-generated toString by IntelliJ IDEA Community Edition 13.1.3
	 * @return
	 */
	@Override
	public String toString() {
		return "BreakoutPanel{" +
				"timer=" + timer +
				", ball=" + ball +
				", bricks=" + bricks +
				", paddle=" + paddle +
				", player=" + player +
				", gameStarted=" + gameStarted +
				'}';
	}

	/**
	 * Handles collision detection for the ball.
	 * Handles collision occurrences for the ball.
	 * Handles continuation of ball movement.
	 */
	class TimeListener implements ActionListener {

		public void bounceBall(Ball ball, Brick brick) {

			if(ball.below(brick)) {
				ball.goDown();
			}
			if(ball.above(brick)) {
				ball.goUp();
			}
			if (ball.leftOf(brick)) {
				ball.goLeft();
			}
			if (ball.rightOf(brick)) {
				ball.goRight();
			}
		}

		public void bounceBall(Ball ball, ArrayList<Brick> bricks) {
			if(bricks.size() == 0) {
				return; //game won
			}
			if(bricks.size() == 1) {
				bounceBall(ball, bricks.get(0));
				return;
			}
			Brick combinedBrick = bricks.get(0).add(bricks.get(1));
			bounceBall(ball, combinedBrick);
		}

		public void actionPerformed( ActionEvent e) {

			if(scorePopUpTimers.size() > 0) {
				for (int index = 0; index < scorePopUpTimers.size(); index++) {
					if (scorePopUpTimers.get(index) <= 0) {
						brokenBricksAlreadyScored.add(brokenBricksAwaitingScorePopUp.get(index));
						brokenBricksAwaitingScorePopUp.remove(index);
					}
				}
			}

			Ball newBall = ball.getVirtualBall();
			ArrayList<Brick> bricksToBeDeleted = new ArrayList<>();

			for (Brick brick : bricks) {
				if (brick.intersects(newBall)) {
					bricksToBeDeleted.add(brick);
					score.updateScore(1);
					brick.recordTimeBrickWasBrokenInMillis(System.currentTimeMillis());
					brokenBricksAwaitingScorePopUp.add(brick);
				}
			}
			bounceBall(ball, bricksToBeDeleted);
			for (Brick brick : bricksToBeDeleted) {
				bricks.remove(brick);
			}
			for (Brick brick : brokenBricksAlreadyScored) {
				brokenBricksAwaitingScorePopUp.remove(brick);
			}


			if (newBall.intersects(paddle) && !ball.below(paddle)) {
				ball.setMaximumOfAbsoluteValueOfVelocityComponents((int) (ball.fractionOfRadiusAwayFromCenter(paddle) * 3.0 + 2.0));
				ball.goUp();

				if (newBall.getCenterX() < paddle.getCenterX()) {
					ball.goLeft();
				} else {
					ball.goRight();
				}
			} else if (ball.getY() + ball.getHeight() > paddle.getY() + paddle.getHeight()) {
				resetBall();
				player.killPlayer();
			}
			ball.move();
			repaint();
		}
	}
}
