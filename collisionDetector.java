import java.util.ArrayList;

public class collisionDetector {
	private ArrayList<Rock> rocks;
	private ArrayList<Photon> photons;
	private Dreadnaught ship;
	boolean lost = false;

	public ArrayList<Rock> getrocks() {
		return this.rocks;
	}

	public ArrayList<Photon> getphotons() {
		return this.photons;
	}

	public Dreadnaught getship() {
		return this.ship;
	}

	public void collision(ArrayList<Rock> asteroids, ArrayList<Photon> bullets,
			Dreadnaught daub) {
		for (int c = 0; c < asteroids.size(); c++) {

			for (int i = 0; i < asteroids.size(); i++) {

				if (asteroids.get(c) != asteroids.get(i)
						&& asteroids.get(c).getBounds()
								.intersects(asteroids.get(i).getBounds())) {
					/*
					 * asteroids.get(c).setx(-(asteroids.get(c).getx()));
					 * asteroids.get(c).sety(-asteroids.get(c).gety());
					 * asteroids.get(i).setx(-asteroids.get(i).getx());
					 * asteroids.get(i).sety(-asteroids.get(i).gety());
					 */
					asteroids.get(c).setCollision();
					asteroids.get(i).setCollision();
				}
			}

			/*
			 * if (bullets.get(c).getBounds()
			 * .intersects(asteroids.get(c).getBounds())) { asteroids.remove(c);
			 * bullets.remove(c); c++; }
			 */
			if (daub.getBounds().intersects(asteroids.get(c).getBounds())) {
				asteroids.remove(c);
				this.lost = true;
				return;
			}

		}
		this.rocks = asteroids;
		this.ship = daub;
		// this.photons = bullets;
	}
}