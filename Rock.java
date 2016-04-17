import java.awt.Polygon;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Rock extends Polygon {
	private int[] xpoints;
	private int[] ypoints;
	private boolean collision = false;
	private int xspeed;
	private int yspeed;
	private int[] drawx = new int[13];
	private int[] drawy = new int[13];

	public void setCollision() {
		this.collision = true;
	}

	public int[] getdrawx() {
		return this.drawx;
	}

	public int[] getdrawy() {
		return this.drawy;
	}

	public int getx() {
		return this.xspeed;
	}

	public int gety() {
		return this.yspeed;
	}

	public void setx(int x) {
		this.xspeed = x;
	}

	public void sety(int y) {
		this.yspeed = y;
	}

	public Rock(int[] startX, int[] startY, int xs, int ys) {
		super(startX, startY, 12);
		this.drawx = startX;
		this.drawy = startY;
		this.xpoints = startX;
		this.ypoints = startY;
		this.xspeed = xs;
		this.yspeed = ys;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.drawx[0], this.drawy[0], 26, 31);
	}

	public void move() {
		// int tempxspeed;
		// int tempyspeed;

		/*
		 * for (int i = 0; i < rocks.size(); i++) { if (check != rocks.get(i) &&
		 * check.getBounds().intersects(rocks.get(i).getBounds())) {
		 * 
		 * 
		 * tempxspeed = check.xspeed; tempyspeed = check.yspeed; check.xspeed =
		 * rocks.get(i).xspeed; check.yspeed = rocks.get(i).yspeed;
		 * rocks.get(i).xspeed = tempxspeed; rocks.get(i).yspeed = tempyspeed;
		 * 
		 * 
		 * check.xspeed = -check.xspeed; check.yspeed = -check.yspeed;
		 * rocks.get(i).xspeed = -rocks.get(i).xspeed; rocks.get(i).yspeed =
		 * -rocks.get(i).yspeed;
		 * 
		 * } }
		 */
		if (this.drawx[0] < 10 || this.drawx[0] > 955)
			this.xspeed = -this.xspeed;
		if (this.drawy[0] < 10 || this.drawy[0] > 700)
			this.yspeed = -this.yspeed;

		xmove();
		ymove();
	}

	public void xmove() {
		if (this.collision) {
			this.xspeed = -this.xspeed;
		}
		for (int i = 0; i < xpoints.length; i++) {
			this.drawx[i] += this.xspeed;
		}
		this.collision = false;
	}

	public void ymove() {
		if (this.collision) {
			this.yspeed = -this.yspeed;
		}
		for (int i = 0; i < ypoints.length; i++) {
			this.drawy[i] += this.yspeed;
		}
		this.collision = false;
	}
}
