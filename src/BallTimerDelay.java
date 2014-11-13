/**
 * Created by ForcesOfOdin on 7/13/2014.
 */
public enum BallTimerDelay {
	FAST(1),
	NORMAL(10),
	SLOW(20);

	private int timerDelay;

	BallTimerDelay(int timerDelay) {
		this.timerDelay = timerDelay;
	}

	int speed(){
		return timerDelay;
	}
}
