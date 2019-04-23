package game;

import java.util.*;

public class Enemy {
	
	/*
	 *  posX, posY Position of enemy
	 *  attackSpeed Shots per second
	 *  attackDamage Damage per shot
	 *  health Health of enemy
	 */
	private int posX, posY, attackSpeed, attackDamage, health;
	
	// list of the enemy's shots
	private List<Projectile> eShots = new ArrayList<Projectile>();
	
	// which enemyModel to use
	private String enemyModel;
	
	/*
	 * default enemy constructor
	 * @param genPosX, genPosY Where the enemy will generate
	 */  
	public Enemy(int genPosX, int genPosY) {
		posX = genPosX;
		posY = genPosY;
		attackSpeed = 200;
		attackDamage = 1;
		health = 10;
		enemyModel = "res/enemyDown.png";
	}

	/*
	 * constructor for custom enemies, with modified AD, AS, and health
	 * @param genPosX, genPosY Where the enemy will generate
	 * @param attackDamage Enemy's attackDamage
	 * @param attackSpeed Enemy's attackSpeed
	 * @param health Enemy's health
	 */
	public Enemy(int genPosX, int genPosY, int attackDamage, int attackSpeed, int health) {
		posX = genPosX;
		posY = genPosY;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.health = health;
		enemyModel = "res/enemyDown.png";
	}

	// fires a new projectile in the direction of the player
	public void shoot(int playerX, int playerY) {
		
		float angle = getAngle(playerX, playerY);

		Projectile p = new Projectile(attackDamage, 'e', posX, posY, angle);
		eShots.add(p);
	}
	
	public void setDir(int playerX, int playerY) {
		
	}
	
	/*
	 * helper method
	 * @param playerX, playerY The current position of the player
	 */
	private float getAngle(int playerX, int playerY) {
		int diffX = playerX - posX;
		int diffY = playerY - posY;
		float angle = (float)Math.atan(diffY / diffX);
		return angle;
	}
	
	// @return posX
	public int getPosX() {
		return posX;
		
	}

	public int getPosY() {
		return posY;
	}
	// @return posY
	
}
