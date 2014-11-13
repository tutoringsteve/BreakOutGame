/**
 * Created by ForcesOfOdin on 7/15/2014.
 */

import java.math.BigInteger;

/**
 * Tracks a games score and outputs score as a String
 * The default zero-score is stored as "00000000" and
 * score can be modified by updateScore which takes
 * the number of scoring events * the score multiplier * the score incrementer
 *
 * The score multiplier and score incrementer are both defaulted but can be set.
 *
 * BigInteger class is used to store the value of the score so that
 * the score can exceed the max integer value of "2147483647"
 */
public class Score {

	private static BigInteger currentScoreValue;
	private static int scoreIncrementer, scoreMultiplier;


	private Score() {
		currentScoreValue = BigInteger.ZERO;
		scoreIncrementer = 100;
		scoreMultiplier = 1;
	}

	private Score(String lastScoreString) {
		this();
		currentScoreValue = currentScoreValue.add(new BigInteger(lastScoreString));
	}

	public static Score StartWithScore(String lastScoreString) {
		return new Score(lastScoreString);
	}

	//TODO: Perhaps save score to map? If map is saved for replay, score should save as well!
	public static Score StartWithScore(int lastScoreValue) {
		return new Score( (new Integer(lastScoreValue)).toString());
	}

	public static Score StartWithZeroScore() {
		return new Score();
	}

	public void setScoreMultiplier(int scoreMultiplier ) {
		this.scoreMultiplier = scoreMultiplier;
	}

	public void setScoreIncrementer(int scoreIncrementer) {
		this.scoreIncrementer = scoreIncrementer;
	}

	public int pointsForScoringEvent() {
		return scoreIncrementer*scoreMultiplier;
	}

	/**
	 * Compute score as the number of scoring events (enemies destroyed, bricks broken,...)
	 * times the score incrementer times the score multiplier.
	 * @param numberOfScoringEvents the number of events that would cause a score increase
	 *                              NOTE: this is NOT the direct score increase, which is
	 *                              calculated USING the numberOfScoringEvents
	 */
	public void updateScore(int numberOfScoringEvents) {
		currentScoreValue = currentScoreValue.add(new BigInteger((new Integer(numberOfScoringEvents*scoreIncrementer*scoreMultiplier)).toString()));
	}


	/**
	 * Returns a String formatted to add enough leading zeroes to
	 * the current score output is at least 8 characters:
	 *
	 * Example: a score of 1234 would be put into a string
	 * "00001234"
	 *
	 * @return The String representing the score value with leading
	 * zeroes.
	 */
	public String toString(){

		String currentScoreString = currentScoreValue.toString();

		if(currentScoreString.length() < 8) {
			StringBuilder sb = new StringBuilder();
			int zeroesToPad = 8 - currentScoreString.length();
			while(zeroesToPad > 0) {
				sb.append('0');
				zeroesToPad--;
			}
			sb.append(currentScoreString);
			currentScoreString = sb.toString();
		}

		return currentScoreString;
	}

}
