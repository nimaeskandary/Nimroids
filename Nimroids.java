import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Nimroids implements Runnable {
	static int level = 0;
	static int levelcount = 0;
	private int score;
	static gameBoard myBoard;
	int lostlag = 150;

	public Nimroids() {

	}

	public static void main(String[] args) {
		myBoard = new gameBoard(0, 0);
		ScheduledThreadPoolExecutor painter = new ScheduledThreadPoolExecutor(5);
		painter.scheduleAtFixedRate(new Nimroids(), 0L, 25L,
				TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {

		if (myBoard.gPanel.lost == false) {
			levelcount++;
			if (levelcount < 1100) {
				myBoard.repaint();
			} else {
				level++;
				myBoard.dispose();
				myBoard = new gameBoard(level, myBoard.gPanel.score);
				levelcount = 0;
			}
		} else {
			this.lostlag--;
			if (this.lostlag < 0) {
				level = 0;
				myBoard.dispose();
				myBoard = new gameBoard(level, 0);
				levelcount = 0;
				this.lostlag = 150;
			}
		}
	}

}
