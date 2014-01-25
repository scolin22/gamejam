
package possessiongame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

    // Constants are Here
    final int MOVESPEED = 2;

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

        centerX += speedX;
        

        // check collision

        centerY += speedY;

        rect.setRect(centerX, centerY, 27, 36);
    }

    public void stopUp() {
        movingUp = false;
        speedY += MOVESPEED;
    }

    public void stopDown() {
        movingDown = false;
        speedY -= MOVESPEED;
    }

    public void stopRight() {
        movingRight = false;
        speedX -= MOVESPEED;
    }

    public void stopLeft() {
        movingLeft = false;
        speedX += MOVESPEED;
    }

    public void startUp() {
        movingUp = true;
        if (!movingDown) {
            speedY = -MOVESPEED;
        } else {
            speedY = 0;
        }
    }

    public void startDown() {
        movingDown = true;
        if (!movingUp) {
            speedY = MOVESPEED;
        } else {
            speedY = 0;
        }
    }

    public void startRight() {
        movingRight = true;
        if (!movingLeft) {
            speedX = MOVESPEED;
        } else {
            speedX = 0;
        }
    }

    public void startLeft() {
        movingLeft = true;
        if (!movingRight) {
            speedX = -MOVESPEED;
        } else {
            speedX = 0;
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

    public boolean isMovingUp() {
        return movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }
}
