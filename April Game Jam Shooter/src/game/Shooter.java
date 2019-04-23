/*
 * @author Alan Wang
 * 
 * Project description: A simple 2d space themed shooter.
 * 
 * Date created: April 13, 2019
 * 
 * Date modified: April 14. 2019
 */

package game;

import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import processing.core.*;

public class Shooter extends PApplet {

	//
	private List<Projectile> eShots = new ArrayList<Projectile>();
	private List<Enemy> enemies = new ArrayList<Enemy>();

	public static int sizeX = 800;
	public static int sizeY = 800;
	private int oldTime = 0;

	// creates the player
	Player p1 = new Player();

	public void settings() {
		size(sizeX, sizeY);
	}

	public void setup() {
		frameRate(60);
	}

	public void draw() {
		PImage img = loadImage("res/background.jpg");
		img.resize(sizeX, sizeY);

		background(img);
//		fill(255, 0, 0);
//		text("Drawing!", 450, 450);

		drawProjectiles();

		drawPlayer();

		generateEnemies();
		drawEnemies();

	}

	// draws the player
	private void drawPlayer() {

		PImage playerModel = loadImage(p1.getPlayerModel());
		playerModel.resize(40, 40);
		int oldPosX = p1.getPosX();
		int oldPosY = p1.getPosY();
		p1.movement();
		image(playerModel, p1.getPosX(), p1.getPosY());
	}

	// TODO: fix this method
	private void aniSmoothing(int i) {
		PImage playerModel = loadImage(p1.getPlayerModel());
		playerModel.resize(40, 40);
		int oldPosX = p1.getPosX();
		int oldPosY = p1.getPosY();
		image(playerModel, oldPosX + (5 * i), oldPosY + (5 * i));
	}

	// determining player input for movement
	public void keyPressed() {
		setKey(key, true);
//		System.out.println(key + " pressed");
	}

	public void keyReleased() {
		setKey(key, false);
//		System.out.println(key + " released");
	}

	/*
	 * Sets state of keys
	 * 
	 * @param c The key inputted by player
	 * 
	 * @param b State to set the key to
	 */
	private void setKey(char c, boolean b) {
//		System.out.println("received " + c);
		// movement
		if (c == 'a') {
			p1.setLeft(b);
		} else if (c == 'd') {
			p1.setRight(b);
		} else if (c == 'w') {
			p1.setUp(b);
		} else if (c == 's') {
			p1.setDown(b);
		}
//		shooting
		else if (c == 'e' && b) {

			if ((millis() - oldTime) >= p1.getAS()) {
				System.out.println("shootin");
				p1.shoot(p1.getDir());
				oldTime = millis();
			}

		}

	}

	// draws projectiles
	public void drawProjectiles() {
		// draws player's projectiles
		List<Projectile> playerShots = new ArrayList<Projectile>();
		playerShots = p1.getPlayerShots();
		for (int i = 0; i < playerShots.size(); i++) {
			fill(100, 255, 132);
			ellipse(playerShots.get(i).getPosX(), playerShots.get(i).getPosY(), 10, 10);
			playerShots.get(i).setPos();
		}

	}

	// randomly generates enemies
	public void generateEnemies() {
		if (Math.random() < 0.05 && enemies.size() < 6) {
			int genPosX = (int) (Math.random() * 800);
			int genPosY = (int) (Math.random() * 800);
			Enemy e1 = new Enemy(genPosX, genPosY);
			enemies.add(e1);
		}
	}

	// draws enemies
	public void drawEnemies() {
		for (Enemy e : enemies) {
			PImage eModel = loadImage("res/enemyDown.png");
			eModel.resize(20, 20);
			image(eModel, e.getPosX(), e.getPosY());
		}
	}

	// TODO: implement collision detection
	public void testCollision(Projectile p) {
		if (p.getWhosShot() == 'p') {
			if (p.getPosX() <= p1.getPosX() + 40 && p.getPosY() <= p1.getPosY() + 40 && p.getPosX() + 10 >= p1.getPosX()
					&& p.getPosY() + 10 >= p1.getPosY()) {
				System.out.println("player hit");
			}

		} else {
			

		}

	}

	// Driver method
	public static void main(String[] args) {
		PApplet.main("game.Shooter");
	}

}
