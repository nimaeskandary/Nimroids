import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Dreadnaught extends Polygon {
	int centerX;
	int centerY;
	private boolean accelerating;
	static int[] originalx = { 20, 10, 15, 20, 25, 30 };
	static int[] originaly = { 30, 10, 15, 15, 15, 10 };
	double xVelocity;
	double yVelocity;
	double rotationAngle = 0;
	int[] drawnx = new int[6];
	int[] drawny = new int[6];
	int slopex = 0;
	int slopey = 0;
	boolean lost = false;
	private int shotDelay;

	public int getShotDelay() {
		return this.shotDelay;
	}

	public Dreadnaught(int cx, int cy) {
		super(originalx, originaly, 6);
		centerX = cx;
		centerY = cy;
		for (int i = 0; i < 6; i++) {
			drawnx[i] = (int) (originalx[i] + centerX);
			drawny[i] = (int) (originaly[i] + centerY);
		}
		this.shotDelay = 10;
	}

	public Rectangle getBounds() {
		return new Rectangle(drawnx[2], drawny[2], 10, 10);
	}

	public void move(int direction, boolean keyHeld, ArrayList<Rock> rocks) {
		/*
		 * for (int i = 0; i < rocks.size(); i++) { if
		 * (this.getBounds().intersects(rocks.get(i).getBounds())) {
		 * rocks.remove(i); this.lost = true; return rocks; } }
		 */
		if (keyHeld) {
			if (direction == 0) {
				this.accelerating = true;
			} else if (direction == 90) {
				if (rotationAngle >= 355) {
					rotationAngle = 0;
				}

				else {
					rotationAngle += 15;
				}
				turn();
			} else if (direction == 270) {
				if (rotationAngle < 0) {
					rotationAngle = 355;
				}

				else {
					rotationAngle -= 15;
				}
				turn();
			} else if (direction == 999) {

			}
		}
		if (this.accelerating) {
			this.slopex += (drawnx[0] - drawnx[3]) / 5;
			this.slopey += (drawny[0] - drawny[3]) / 5;
			this.accelerating = false;
		} else {
			this.slopex *= .9;
			this.slopey *= .9;
		}
		if (slopex != 0 | slopey != 0) {
			for (int i = 0; i < 6; i++) {
				drawnx[i] += this.slopex;
				drawny[i] += this.slopey;
			}
			this.centerX += slopex;
			this.centerY += slopey;
			flip();
		}
		this.shotDelay--;
		if (this.shotDelay < 0)
			this.shotDelay = 10;
	}

	public void flip() {
		if (drawnx[0] < 10) {
			for (int i = 0; i < 6; i++) {
				drawnx[i] += 900;
				centerX += 900;
			}
		}
		if (drawnx[0] > 980) {
			for (int i = 0; i < 6; i++) {
				drawnx[i] -= 900;
				centerX -= 900;
			}
		}
		if (drawny[0] < 10) {
			for (int i = 0; i < 6; i++) {
				drawny[i] += 700;
				centerY += 700;
			}
		}
		if (drawny[0] > 720) {
			for (int i = 0; i < 6; i++) {
				drawny[i] -= 700;
				centerY -= 700;
			}
		}

	}

	public void turn() {
		double angle = Math.toRadians(rotationAngle);
		int x = centerX;
		int y = centerY;
		for (int i = 0; i < 6; i++) {
			int X = originalx[i];
			int Y = originaly[i];
			drawnx[i] = (int) (X * Math.cos(angle) - Y * Math.sin(angle) + x + .5);
			drawny[i] = (int) (X * Math.sin(angle) + Y * Math.cos(angle) + y + .5);
		}
	}
}
