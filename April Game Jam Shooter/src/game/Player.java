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
	private boolean isUp, isDown, isLeft, isRight = false;

	// list of the player's shots
	private List<Projectile> pShots = new ArrayList<Projectile>();

	// Constructor with default values
	public Player() {
		health = 100;
		attackSpeed = 200;
		attackDamage = 3;
		speed = 20;
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

	/*
	 * methods to set the movement state of the player
	 * 
	 * @param b true or false for whether the player has inputted movement
	 */
	public void setLeft(boolean b) {
		this.isLeft = b;
	}

	public void setRight(boolean b) {
		this.isRight = b;
	}

	public void setUp(boolean b) {
		this.isUp = b;
	}

	public void setDown(boolean b) {
		this.isDown = b;
	}

	// Changes position of the player. Takes values from -1 to 1 for x and y. -1 is
	// movement down/to the left, 1 is the opposite. 0 is no movement. When the
	// player hits an edge, they can't move further that way.
	public void movement() {
		double diagonalSpeed = 2 * Math.pow(speed, 0.5) + 1;
		// Movement control.
		if (isDown && !isUp && !isLeft && !isRight) {
			posY += speed;
			playerModel = "res/playerDown.png";
		} else if (isUp && !isDown && !isLeft && !isRight) {
			posY -= speed;
			playerModel = "res/playerUp.png";
		} else if (isLeft && !isDown && !isUp && !isRight) {
			posX -= speed;
			playerModel = "res/playerLeft.png";
		} else if (isRight && !isUp && !isDown && !isLeft) {
			posX += speed;
			playerModel = "res/playerRight.png";
		}
		// up+right
		else if (isRight && isUp && !isDown && !isLeft) {
			posX += diagonalSpeed;
			posY -= diagonalSpeed;
			playerModel = "res/playerRight.png";
		}
		// up+left
		else if (!isRight && isUp && !isDown && isLeft) {
			posX -= diagonalSpeed;
			posY -= diagonalSpeed;
			playerModel = "res/playerLeft.png";
		}
		// down+right
		else if (isRight && !isUp && isDown && !isLeft) {
			posX += diagonalSpeed;
			posY += diagonalSpeed;
			playerModel = "res/playerRight.png";
		}
		// down+left
		else if (!isRight && !isUp && isDown && isLeft) {
			posX -= diagonalSpeed;
			posY += diagonalSpeed;
			playerModel = "res/playerLeft.png";
		}

		// Makes sure the player doesn't leave the map
		if (posX <= 0) {
			posX = 0;
		} else if (posX >= Shooter.sizeX - 40) {
			posX = Shooter.sizeX - 40;
		}
		if (posY <= 0) {
			posY = 0;
		} else if (posY >= Shooter.sizeY - 40) {
			posY = Shooter.sizeY - 40;
		}

	}

	/*
	 * Fires a shot in the given direction
	 * 
	 * @param dir The direction the shot will travel
	 */
	public void shoot(char dir) {
		double initSpeed = 0;
		Projectile p = new Projectile(attackDamage, 'p', posX, posY, dir, initSpeed);

		pShots.add(p);

	}

	/*
	 * @param The index position of the shot
	 * 
	 * @return The player shots
	 */
	public List getPlayerShots() {
		return pShots;
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
