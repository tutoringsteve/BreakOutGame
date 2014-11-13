/**
 * Created by ForcesOfOdin on 7/11/2014.
 */

import javax.swing.*;
import java.awt.event.*;

import static java.lang.System.*;


public class BreakoutFrame extends JFrame {
	public static final int HEIGHT = 660;
	public static final int WIDTH = 480;
	public static final int SCALE_FACTOR = 1;


	private BreakoutPanel panel = new BreakoutPanel();

	public BreakoutFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayMenu();
		setLocation(100,100);
		setSize(WIDTH*SCALE_FACTOR,HEIGHT*SCALE_FACTOR);
		add(panel);
		setResizable(false);
	}

	public void displayMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add( new GameMenu());
		menuBar.add( new ColorMenu());
		menuBar.add( new SpeedMenu());
		setJMenuBar(menuBar);
	}

	private class GameMenu extends JMenu {

		public GameMenu() {
			super("Game");
			JMenuItem startGameMenuItem = new JMenuItem("Start", 'S');
			startGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

			JMenuItem pauseMenuItem = new JMenuItem("Pause", 'P');
			pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
			pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));

			JMenuItem quitMenuItem = new JMenuItem("Quit", 'Q');
			quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));

			startGameMenuItem.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e) {
					panel.start();
				}
			});

			pauseMenuItem.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e) {
					panel.togglePause();
				}
			});

			quitMenuItem.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e) {
					exit(0);
				}
			});

			add(startGameMenuItem);
			add(pauseMenuItem);
			add(quitMenuItem);
		}
	}

	private class ColorMenu extends JMenu {
		public ColorMenu() {
			super("Ball Color");
			for (BallColor color : BallColor.values()) {
				JMenuItem menuItem = new JMenuItem(color.name() + " Ball");
				menuItem.addActionListener(new BallColorListener(color));
				add(menuItem);
			}
		}
	}

	private class BallColorListener implements ActionListener {
		private BallColor color;

		public void actionPerformed( ActionEvent e) {
			panel.changeBallColor( color );
		}

		public BallColorListener(BallColor color) {
			this.color = color;
		}
	}

	private class SpeedMenu extends JMenu {
		public SpeedMenu( ) {
			super( "Ball Speed" );
			for ( BallTimerDelay s : BallTimerDelay.values() ) {
				JMenuItem menuItem = new JMenuItem( s.name( ) );
				menuItem. addActionListener(new BallSpeedListener ( s.speed( ) ));
				add(menuItem);
			}
		}
	}

	private class BallSpeedListener implements ActionListener {
		private int speed;
		public void actionPerformed (ActionEvent e ) {
			panel.changeBallSpeed ( speed );
		}
		public BallSpeedListener ( int speed ) {
			this.speed = speed;
		}
	}

}
