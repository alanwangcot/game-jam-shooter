package game;

public class Projectile {

	// Damage of the shot
	private double shotDamage;
	// Speed of the projectile in pixels per second
	private double shotSpeed;
	// Who does the shot belong to, player or enemy
	private char whosShot;
	// Initial position of the shot
	private int posX, posY;
	// Direction the shot is traveling
	private char direction;

	private float angle;

	// Creates a new projectile, with given damage, belonging to player or an enemy,
	// and its direction
	public Projectile(double damage, char who, int posX, int posY, char dir, double initSpeed) {
		shotDamage = damage;
		whosShot = who;
		this.posX = posX + 20;
		this.posY = posY + 20;
		direction = dir;
		shotSpeed = initSpeed + 25;
	}

	public Projectile(double damage, char who, int posX, int posY, float angle) {
		shotDamage = damage;
		whosShot = who;
		this.posX = posX;
		this.posY = posY;
		this.angle = angle;
//		shotSpeed = initSpeed;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public char getDir() {
		return this.direction;
	}

	public char getWhosShot() {
		return whosShot;
	}

	public void setPos() {
		if (direction == 'l') {
			posX -= shotSpeed;
		} else if (direction == 'r') {
			posX += shotSpeed;
		} else if (direction == 'u') {
			posY -= shotSpeed;
		} else if (direction == 'd') {
			posY += shotSpeed;
		}
	}

	public boolean OutOfBounds() {
		if (posX <= 0 || posX >= Shooter.sizeX || posY <= 0 || posY >= Shooter.sizeY) {
			return true;
		}
		return false;
	}

}
