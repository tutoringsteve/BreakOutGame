/**
 * Created by Steven A. Sarasin on 7/15/2014.
 */
public class ScoreTest {

	//static Score scoreDefaultConstructor = new Score();
	static Score scoreSmallInitialScore = Score.StartWithScore("12345678900");

	public static void main(String[] args) {

		System.out.println("scoreSmallInitialScore: " + scoreSmallInitialScore.toString());
		//System.out.println("scoreDefaultConstructor: " + scoreDefaultConstructor.toString());

		scoreSmallInitialScore.setScoreIncrementer(10550);
		scoreSmallInitialScore.setScoreMultiplier(3);

		System.out.println("                                 ");
		System.out.println("------TESTING ADDING POINTS------");
		System.out.println("                                 ");
		System.out.println("multiplier: " + 3 + "\tincrementer: " + 10550);
		for(int bricks = 1; bricks < 10; bricks++ ) {
			scoreSmallInitialScore.updateScore(1);
			System.out.println("scoreSmallInitialScore: " + scoreSmallInitialScore.toString());
		}
	}
}
