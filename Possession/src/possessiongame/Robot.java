/*package possessiongame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 2;

	// Robot can move this much away from center
	final int offset = 60;

	private int centerX = 100;
	private int centerY = 377;

	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean ducked = false;

	private static Background bg1 = MainClass.getBg1();
	private static Background bg2 = MainClass.getBg2();

	private int speedX = 0;
	private int speedY = 0;

	// For Collision Detection
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	// Rectangle to limit Collision checking area
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);

	public void update() {

		// Moves Character or Scrolls Background accordingly in x direction.
		if (speedX == 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		if (centerX <= MainClass.SCREEN_WIDTH / 2 + offset && speedX > 0) {
			centerX += speedX;
		}
		if (centerX >= MainClass.SCREEN_WIDTH / 2 - offset && speedX < 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > MainClass.SCREEN_WIDTH / 2 + offset) {
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		}
		if (speedX < 0 && centerX < MainClass.SCREEN_WIDTH / 2 - offset) {
			bg1.setSpeedX(+MOVESPEED);
			bg2.setSpeedX(+MOVESPEED);
		}

		// Moves Character or Scrolls Background accordingly in y direction.
		if (speedY == 0) {
			bg1.setSpeedY(0);
			bg2.setSpeedY(0);

		}
		if (centerY <= MainClass.SCREEN_HEIGHT / 2 + offset && speedY > 0) {
			centerY += speedY;
		}
		if (centerY >= MainClass.SCREEN_HEIGHT / 2 - offset && speedY < 0) {
			centerY += speedY;
		}
		if (speedY > 0 && centerY > MainClass.SCREEN_HEIGHT / 2 + offset) {
			bg1.setSpeedY(-MOVESPEED);
			bg2.setSpeedY(-MOVESPEED);
		}
		if (speedY < 0 && centerY < MainClass.SCREEN_HEIGHT / 2 - offset) {
			bg1.setSpeedY(+MOVESPEED);
			bg2.setSpeedY(+MOVESPEED);
		}

		// Handles Jumping
		if (jumped == true) {
			speedY += 1;

			if (speedY > 3) {
				jumped = true;
			}
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}

		rect.setRect(centerX - 34, centerY - 63, 68, 63);
		rect2.setRect(rect.getX(), rect.getY() + 63, 68, 64);
		yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);
	}

	public void moveUp() {
		speedY = -MOVESPEED;
	}

	public void moveDown() {
		speedY = MOVESPEED;
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopUp() {
		setMovingUp(false);
		stop();
	}

	public void stopDown() {
		setMovingDown(false);
		stop();
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

		if (isMovingUp() == false && isMovingDown() == false) {
			speedY = 0;
		}

		if (isMovingUp() == false && isMovingDown() == true) {
			moveLeft();
		}

		if (isMovingUp() == true && isMovingDown() == false) {
			moveRight();
		}
	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}
}
*/