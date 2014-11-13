import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by ForcesOfOdin on 7/19/2014.
 */
public class BreakoutLevel {

	//TODO: BreakoutLevel Documentation
	//TODO: Implement BreakoutLevel in the panel class
	private static final int MAX_COLUMNS = 15;
	private static final int MAX_ROWS = 30;
	private static char[][] levelLayout = new char[MAX_ROWS][MAX_COLUMNS];
	private static int level;

	private BreakoutLevel() {
		for( int row = 0; row < MAX_ROWS; row++) {
			for( int col = 0; col < MAX_COLUMNS; col++) {
				levelLayout[row][col] = '1';
			}
		}

		level = 1;
	}

	private BreakoutLevel(int level) {
		this();
		this.level = level;
	}

	public static BreakoutLevel LoadBreakoutLevel(String levelFileAddress, int level) {

		BreakoutLevel breakoutLevel = new BreakoutLevel(level);

		try {
			File levelFile = new File(levelFileAddress);
			Scanner scanner = new Scanner(levelFile);

			int row = 0;

			while (scanner.hasNextLine() && row < 30) {
				breakoutLevel.setBreakoutLevelAtRow(row++, scanner.nextLine().toCharArray());
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file for level " + level + " was not found at Address: " + levelFileAddress);
		}

		return breakoutLevel;
	}

	public void setBreakoutLevelAtRow(int row, char[] levelAtRow) {
		this.levelLayout[row] = levelAtRow;
	}

	public void setBrickAtRowXandColY(int row, int col, char brick) {
		this.levelLayout[row][col] = brick;
	}

	//TODO: use this to write level changes to a current-state-of-level save file.
	public void destroyBrickAtRowXandColY(int row, int col) {
		setBrickAtRowXandColY(row, col, '0');
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Level: " + level + "\n\n");
		for(int row = 0; row < MAX_ROWS; row++) {
			for(int col = 0; col < MAX_COLUMNS; col++) {
				sb.append(levelLayout[row][col]);
			}
			sb.append("\n");
		}
		sb.append("===End===");

		return sb.toString();
	}


	/**
	 * Tests the Level Loader with a sample file.
	 * @param args
	 */
	public static void main(String[] args) {

		//TODO
		String levelFileAddress = "C:\\Users\\ForcesOfOdin\\Documents\\NetBeansProjects\\BreakOutGame\\src\\BreakoutLevel01.txt";
		BreakoutLevel breakoutLevel = LoadBreakoutLevel(levelFileAddress, 1);
		System.out.print(breakoutLevel.toString());

	}
}
