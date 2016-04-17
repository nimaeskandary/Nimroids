import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Photon extends Polygon {

	static int[] originalx = { -2, 2, 2, -2 };
	static int[] originaly = { 5, 5, 0, 0 };
	private int[] drawnx = new int[4];
	private int[] drawny = new int[4];
	private int life;

	public int getLife() {
		return life;
	}

	public int[] getx() {
		return this.drawnx;
	}

	public int[] gety() {
		return this.drawny;
	}

	private int slopex;
	private int slopey;
	boolean exists = true;

	public Photon(Dreadnaught ship) {
		super(originalx, originaly, 4);
		for (int i = 0; i < 4; i++) {
			drawnx[i] = (int) (originalx[i] + ship.drawnx[0]);
			drawny[i] = (int) (originaly[i] + ship.drawny[0]);
		}
		this.slopex = (ship.drawnx[0] - ship.drawnx[3]) / 5;
		this.slopey = (ship.drawny[0] - ship.drawny[3]) / 5;
		this.life = 200;
	}

	public Rectangle getBounds() {
		return new Rectangle(drawnx[3], drawny[3], 4, 4);
	}

	public ArrayList<Rock> move(ArrayList<Rock> rocks) {

		for (int i = 0; i < rocks.size(); i++) {
			if (this.getBounds().intersects(rocks.get(i).getBounds())) {
				rocks.remove(i);
				this.exists = false;
				return rocks;
			}
		}

		for (int i = 0; i < 4; i++) {
			drawnx[i] += this.slopex;
			drawny[i] += this.slopey;
		}

		flip();
		this.life--;
		return rocks;
	}

	public void flip() {
		if (drawnx[0] < 10) {
			for (int i = 0; i < 4; i++) {
				drawnx[i] += 900;
			}
		}
		if (drawnx[0] > 980) {
			for (int i = 0; i < 4; i++) {
				drawnx[i] -= 900;
			}
		}
		if (drawny[0] < 10) {
			for (int i = 0; i < 4; i++) {
				drawny[i] += 700;
			}
		}
		if (drawny[0] > 720) {
			for (int i = 0; i < 4; i++) {
				drawny[i] -= 700;
			}
		}
	}
}