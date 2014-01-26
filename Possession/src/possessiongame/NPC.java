package possessiongame;

import java.awt.Rectangle;

public class NPC {

	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private Background bg = MainClass.getBg();
	private Person player = MainClass.getPlayer();

	public Rectangle r = new Rectangle(0, 0, 0, 0);

	// Behavioral Methods
	public void update() {
		centerX += speedX;
		speedX = bg.getSpeedX() * 5;
		r.setBounds(centerX - 25, centerY - 25, 50, 60);

		if (r.intersects(Person.rect)) {
			checkCollision();
		}

	}

	private void checkCollision() {
		if (r.intersects(Person.rect)) {
			System.out.println("collision");
			player.setSpeedX(0);

		}
	}

	public void die() {

	}

	public void attack() {

	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

}