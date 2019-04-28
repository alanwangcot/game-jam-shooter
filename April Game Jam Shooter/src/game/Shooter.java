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
	private int numKilled = 0;

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
		collisions();
		scoreKeeping();
	}

	// draws the player
	private void drawPlayer() {

		PImage playerModel = loadImage(p1.getPlayerModel());
		playerModel.resize(60, 60);
		int oldPosX = p1.getPosX();
		int oldPosY = p1.getPosY();

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
		playerActions(key, true);
//		System.out.println(key + " pressed");
	}

	public void keyReleased() {
		playerActions(key, false);
//		System.out.println(key + " released");
	}

	/*
	 * Sets state of keys
	 * 
	 * @param c The key inputted by player
	 * 
	 * @param b State to set the key to
	 */
	private void playerActions(char c, boolean b) {
//		System.out.println("received " + c);
		// movement
		if (b) {
			p1.movement(c);
		}

//		shooting
		if (c == 'e' && b) {

			if ((millis() - oldTime) >= p1.getAS()) {
//				System.out.println("shootin");
				p1.shoot();
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
			if (playerShots.get(i).OutOfBounds()) {
				playerShots.remove(i);
			}
		}

	}

	// randomly generates enemies
	public void generateEnemies() {
		if (Math.random() < 0.05 && enemies.size() < 3) {
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
			eModel.resize(40, 40);
			image(eModel, e.getPosX(), e.getPosY());
		}
	}

	// helper method for collisions, returns true if collision happens.
	private boolean testCollision(Projectile p) {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy curr = enemies.get(i);
			if (p.getPosX() <= curr.getPosX() + 40 && p.getPosY() <= curr.getPosY() + 40
					&& p.getPosX() + 10 >= curr.getPosX() && p.getPosY() + 10 >= curr.getPosY()) {
//				System.out.println("enemy hit");
				enemies.remove(curr);
				numKilled++;
				return true;
			}
		}
		return false;
	}

	// collision detection
	public void collisions() {
		List pShots = p1.getPlayerShots();
		for (int j = 0; j < pShots.size(); j++) {
			if (testCollision((Projectile) pShots.get(j))) {
				pShots.remove(j);
			}
		}

	}

	public void scoreKeeping() {

		if (numKilled < 5) {
			fill(255, 255, 255);
			textSize(32);
			text("Current score: " + numKilled, 10, 30);
		} else {
			clear();
			textSize(100);
			fill(255, 215, 0);
			text("VICTORY", 200, 400);
		}
		
	}

	// Driver method
	public static void main(String[] args) {
		PApplet.main("game.Shooter");
	}

}
