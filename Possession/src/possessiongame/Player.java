package possessiongame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

    // Constants are Here
    final int MOVESPEED = 2;

    // Robot can move this much away from center
    final int offset = 0;

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
        
        //check collision
        
        centerY += speedY;
        
        // Moves Character or Scrolls Background accordingly in x direction.
        /*if (speedX == 0) {
            bg.setSpeedX(0);
        }
        if (speedX > 0 && centerX <= MainClass.SCREEN_WIDTH / 2 + offset) {
            centerX += speedX;
        }
        if (speedX < 0 && centerX >= MainClass.SCREEN_WIDTH / 2 - offset) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX > MainClass.SCREEN_WIDTH / 2 + offset) {
            bg.setSpeedX(-MOVESPEED);
        }
        if (speedX < 0 && centerX < MainClass.SCREEN_WIDTH / 2 - offset) {
            bg.setSpeedX(+MOVESPEED);
        }*/

        // Moves Character or Scrolls Background accordingly in y direction.
        /*if (speedY == 0) {
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
        /*if (centerX + speedX <= 60) {
            centerX = 61;
        }*/

        rect.setRect(centerX - 34, centerY - 63, 68, 63);
    }

    public void stopUp() {
        speedY -= MOVESPEED;
    }

    public void stopDown() {
        speedY += MOVESPEED;
    }

    public void stopRight() {
        speedX -= MOVESPEED;
    }

    public void stopLeft() {
        speedX += MOVESPEED;
    }
    
    public void startUp() {
        speedY -= MOVESPEED;
    }

    public void startDown() {
        speedY += MOVESPEED;
    }

    public void startRight() {
        speedX += MOVESPEED;
    }

    public void startLeft() {
        speedX -= MOVESPEED;
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
}
