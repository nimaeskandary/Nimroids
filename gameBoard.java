import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class gameBoard extends JFrame {

	int direction;
	boolean keyHeld;
	gamePanel gPanel;

	public gameBoard(int level, int score) {
		gPanel = new gamePanel(level, score);
		this.setSize(1000, 750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("NIMROIDS");
		KeyListener kl = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
					System.out.println("Thrusters!");
					direction = 0;
					keyHeld = true;
				} else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
					System.out.println();
					direction = 180;
					keyHeld = true;
				} else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
					System.out.println("STARBOARD!!!");
					direction = 90;
					keyHeld = true;
				} else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
					System.out.println("PORTSIDE!!!");
					direction = 270;
					keyHeld = true;
				} else if (e.getKeyCode() == 16) {
					System.out.println("!WARP!");
					direction = 999;
					keyHeld = true;
				} else if (e.getKeyCode() == 32) {

					System.out.println("FIRE");
					direction = 100;
					keyHeld = true;

				} else if (e.getKeyCode() == 49) {

					direction = 1;
					keyHeld = true;

				}
				gPanel.direction = direction;
				gPanel.keyHeld = keyHeld;

			}

			public void keyReleased(KeyEvent e) {
				keyHeld = false;
				gPanel.keyHeld = false;
				direction = -1;
				gPanel.direction = -1;
			}

		};

		this.addKeyListener(kl);
		this.add(gPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

}
