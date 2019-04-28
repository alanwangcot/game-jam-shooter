package game;

import java.util.*;

public class Player {

	/*
	 * Health Total points attackSpeed Shots per second attackDamage Damage per shot
	 * Speed Pixels per second
	 */
	private int health, attackSpeed, attackDamage, speed, range, regen, maxHP;

	// position of the player
	private int posX, posY;

	// which playerModel to use
	private String playerModel;

	// detects for input of direction
	private int xVel, yVel = 0;

	// list of the player's shots
	private List<Projectile> pShots = new ArrayList<Projectile>();

	// Constructor with default values
	public Player() {
		health = 100;
		attackSpeed = 200;
		attackDamage = 4;
		speed = 5;
		range = 500;
		regen = 1;
		maxHP = 100;
		posX = 500;
		posY = 500;
		playerModel = "res/playerUp.png";

	}

	/*
	 * changes the player's health
	 * 
	 * @param change The amount to change health by
	 * 
	 * @return if the player is dead.
	 */
	public boolean setHealth(double change) {
		this.health += change;
		if (health <= 0) {
			return true;
		}
		if (health > maxHP) {
			health = maxHP;
		}
		return false;
	}

	// @param change The amount to change maxHP by
	public void setMaxHP(int change) {
		this.maxHP += change;
	}

	// @param change The amount to change attackSpeed by
	public void setAttackSpeed(int change) {
		this.attackSpeed += change;
	}

	// @param change The amount to change attackDamage by
	public void setAttackDamage(int change) {
		this.attackDamage += change;
	}

	// @param change The amount to change speed by
	public void setSpeed(int change) {
		this.speed += change;
	}

	// @param change The amount to change range by
	public void setRange(int change) {
		this.range += change;
	}

	// @param change The amount to change regen by
	public void setRegen(int change) {
		this.regen += change;
	}



	// Changes position of the player
	public void movement(char c) {
	
		// Movement control.
		if (c == 'a') {
			posX -= speed;
			playerModel = "res/playerLeft.png";
		} else if (c == 'd') {
			posX += speed;
			playerModel = "res/playerRight.png";
		} else if (c == 'w') {
			posY -= speed;
			playerModel = "res/playerUp.png";
		} else if (c == 's') {
			posY += speed;
			playerModel = "res/playerDown.png";
		}

		// Makes sure the player doesn't leave the map
		if (posX <= 0) {
			posX = 0;
		} else if (posX >= Shooter.sizeX - 60) {
			posX = Shooter.sizeX - 60;
		}
		if (posY <= 0) {
			posY = 0;
		} else if (posY >= Shooter.sizeY - 60) {
			posY = Shooter.sizeY - 60;
		}

	}

	/*
	 * Fires a shot in the given direction
	 * 
	 * @param dir The direction the shot will travel
	 */
	public void shoot() {
		char dir = getDir();
		Projectile p = new Projectile(attackDamage, 'p', posX + 10, posY + 10, dir, 0);
		pShots.add(p);

	}

	/*
	 * @param The index position of the shot
	 * 
	 * @return The player shots
	 */
	public ArrayList getPlayerShots() {
		return (ArrayList) pShots;
	}

	// @return The playerModel to draw
	public String getPlayerModel() {
		return playerModel;
	}

	// @return The posX of player
	public int getPosX() {
		return posX;
	}

	// @return The posY of player
	public int getPosY() {
		return posY;
	}

	// @return The attackDamage of player
	public int getAD() {
		return attackDamage;
	}

	// @return The attackSpeed of player
	public int getAS() {
		return attackSpeed;
	}
	
	// @return The direction player is facing
	public char getDir() {
		if (playerModel == "res/playerUp.png") {
			return 'u';
		} else if (playerModel == "res/playerDown.png") {
			return 'd';
		} else if (playerModel == "res/playerLeft.png") {
			return 'l';
		} else {
			return 'r';
		}
	}

}
