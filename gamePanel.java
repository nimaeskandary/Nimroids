import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class gamePanel extends JComponent {
	ArrayList<Rock> rocks = new ArrayList<Rock>();
	ArrayList<Photon> photons = new ArrayList<Photon>();
	int[] speeds = { -2, -1, 1, 2 };
	Dreadnaught Daub = new Dreadnaught(500, 325);
	int direction;
	boolean keyHeld;
	int score = 0;
	int level = 0;
	boolean lost = false;
	collisionDetector actors = new collisionDetector();
	int startlag = 100;
	int restart = 100;
	int starttime = 5;
	int levellag = 1100;

	gamePanel(int level, int score) {
		this.score = score;
		this.level = level;
		for (int i = 0; i < 25 + (5 * level); i++) {
			int randomStartXPos = (int) (Math.random() * (960) + 1);
			int randomStartYPos = (int) (Math.random() * (710) + 1);
			int xs = speeds[(int) (Math.random() * 4)];
			int ys = speeds[(int) (Math.random() * 4)];
			rocks.add(new Rock(newxpoints(randomStartXPos),
					newypoints(randomStartYPos), xs, ys));
			System.out.println("#Rocks: " + rocks.size());
		}
	}

	public static int[] newxpoints(int xposition) {
		int[] temp = { 10, 17, 26, 34, 27, 36, 26, 14, 8, 1, 5, 1, 10 };
		for (int i = 0; i < temp.length; i++) {
			temp[i] += xposition;
		}
		return temp;
	}

	public static int[] newypoints(int yposition) {
		int[] temp = { 0, 5, 1, 8, 13, 20, 31, 28, 31, 22, 16, 7, 0 };
		for (int i = 0; i < temp.length; i++) {
			temp[i] += yposition;
		}
		return temp;
	}

	public void paint(Graphics g) {
		// test mode (comment out to play)
		// actors.lost = false;
		//
		Graphics2D nGraphics = (Graphics2D) g;
		if (score < 1) {
			nGraphics.setColor(Color.WHITE);
			nGraphics.fillRect(0, 0, getWidth(), getHeight());
			nGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			nGraphics.setPaint(Color.BLACK);
			nGraphics.drawString("For Single-Player, Press 1", 400, 200);
			nGraphics.drawString("For Muilti-Player, Press 2", 400, 300);
			nGraphics.drawString("Use Arrow Keys or WASD to move ship", 400,
					450);
			nGraphics.drawString("HOLD Space to shoot", 400, 475);
			nGraphics.drawString("Press Shift to Warp", 400, 500);
			if (direction == 1) {
				score++;
			}
		} else {
			startlag--;
			if (startlag % 20 == 0)
				starttime--;
			lost = actors.lost;
			if (starttime > -3) {
				lost = false;
			}

			if (lost == false) {
				levellag--;
				nGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				nGraphics.setColor(Color.BLACK);
				if (startlag > 90)
					nGraphics.setColor(Color.GREEN);
				nGraphics.fillRect(0, 0, getWidth(), getHeight());
				nGraphics.setPaint(Color.WHITE);
				if (direction == 999) {
					Daub = new Dreadnaught(((int) (Math.random() * 999) + 1),
							((int) (Math.random() * 749) + 1));
				}

				actors.collision(rocks, photons, Daub);
				rocks = actors.getrocks();
				Daub = actors.getship();
				// photons = actors.getphotons();
				if (startlag < 1) {
					for (Rock a : rocks) {
						a.move();
						nGraphics.drawPolygon(a.getdrawx(), a.getdrawy(), 12);
					}
				} else {
					nGraphics.drawString("Start in " + starttime, 490, 400);
					if (starttime < 4) {
						for (Rock a : rocks) {
							nGraphics.drawPolygon(a.getdrawx(), a.getdrawy(),
									12);
						}
					}
				}

				Daub.move(direction, keyHeld, rocks);
				nGraphics.fillPolygon(Daub.drawnx, Daub.drawny, 6);

				if (direction == 100) {
					if (Daub.getShotDelay() == 0)
						photons.add(new Photon(Daub));
				}
				int photoncount = 0;
				ArrayList<Photon> tempPhoton = new ArrayList<Photon>();
				for (Photon a : photons) {
					photoncount++;
					rocks = a.move(rocks);
					if (a.exists && a.getLife() > 0) {
						tempPhoton.add(a);
						nGraphics.fillPolygon(a.getx(), a.gety(), 4);
					} else
						score += 1000;
				}
				photons = tempPhoton;
				score += 1;
				nGraphics.drawString(
						"Next Level: " + Integer.toString(levellag), 200, 20);
				nGraphics.drawString("Score: " + Integer.toString(score), 465,
						20);
				nGraphics.drawString("Level: " + Integer.toString(level + 1),
						700, 20);

			} else {
				// lost = 1;
				nGraphics.setColor(Color.WHITE);
				nGraphics.fillRect(0, 0, getWidth(), getHeight());
				nGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				nGraphics.setPaint(Color.BLACK);
				nGraphics.drawString("Score: " + Integer.toString(score)
						+ "   Level: " + Integer.toString(level + 1), 462, 325);

				// if (JOptionPane.showConfirmDialog(this,
				// "You Lost.. Want to try again?", "NIMROIDS", 1) == 1) {
				// restartApplication();
				// }
			}
		}
	}
}