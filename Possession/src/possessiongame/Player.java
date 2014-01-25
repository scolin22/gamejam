package possessiongame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

    // Constants are Here
    final int MOVESPEED = 2;

    // Robot can move this much away from center
    final int offset = 60;

    private int centerX = 100;
    private int centerY = 377;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private static Background bg = MainClass.getBg();

    private int speedX = 0;
    private int speedY = 0;

    // For Collision Detection
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);

    public void update() {

        // Moves Character or Scrolls Background accordingly in x direction.
        if (speedX == 0) {
            bg.setSpeedX(0);
        }
        if (speedX > 0 && centerX <= MainClass.SCREEN_WIDTH / 2 + offset) {
            centerX += speedX;
        }
        if (speedX < 0 && centerX >= MainClass.SCREEN_WIDTH / 2 - offset) {
            centerX += speedX;
        }
        /*if (speedX > 0 && centerX > MainClass.SCREEN_WIDTH / 2 + offset) {
            bg.setSpeedX(-MOVESPEED);
        }
        if (speedX < 0 && centerX < MainClass.SCREEN_WIDTH / 2 - offset) {
            bg.setSpeedX(+MOVESPEED);
        }*/

        // Moves Character or Scrolls Background accordingly in y direction.
        if (speedY == 0) {
            bg.setSpeedY(0);

        }
        if (speedY > 0 && centerY <= MainClass.SCREEN_HEIGHT / 2 + offset) {
            centerY += speedY;
        }
        if (speedY < 0 && centerY >= MainClass.SCREEN_HEIGHT / 2 - offset) {
            centerY += speedY;
        }
        /*if (speedY > 0 && centerY > MainClass.SCREEN_HEIGHT / 2 + offset) {
            bg.setSpeedY(-MOVESPEED);
        }
        if (speedY < 0 && centerY < MainClass.SCREEN_HEIGHT / 2 - offset) {
            bg.setSpeedY(+MOVESPEED);
        }*/

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }

        rect.setRect(centerX - 34, centerY - 63, 68, 63);
    }

    public void moveUp() {
        speedY = -MOVESPEED;
    }

    public void moveDown() {
        speedY = MOVESPEED;
    }

    public void moveRight() {
        speedX = MOVESPEED;
    }

    public void moveLeft() {
        speedX = -MOVESPEED;
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

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
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

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
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
