/*package possessiongame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

    // Constants are Here
    final int MOVESPEED = 2;

    private int centerX = 100;
    private int centerY = 377;
    private int width = 25;
    private int height = 35;
    final int offset = 60;

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
        if (checkCollision()) {
            centerX -= speedX;
            bg.setSpeedX(0);
        } else {
            if (speedX == 0) {
                bg.setSpeedX(0);
            } else if (bg.getBgX() > 0) {
                bg.setSpeedX(0);
            } else {
                if (bg.getBgX() < 0) {
                    if (speedX < 0 && centerX < MainClass.SCREEN_WIDTH / 2 - offset) {
                        centerX -= speedX;
                        bg.setSpeedX(+MOVESPEED);
                    }
                }
                if (bg.getBgX() > -100000){
                    if (speedX > 0 && centerX > MainClass.SCREEN_WIDTH / 2 + offset) {
                        centerX -= speedX;
                        bg.setSpeedX(-MOVESPEED);
                    }
                }
            }
        }

        centerY += speedY;
        if (checkCollision()) {
            centerY -= speedY;
            bg.setSpeedY(0);
        } else {
            if (speedY == 0) {
                bg.setSpeedY(0);
            } else if (bg.getBgY() > 0) {
                bg.setSpeedY(0);
            } else {
                if (bg.getBgY() > 0) {
                    if (speedY > 0 && centerY > MainClass.SCREEN_HEIGHT / 2 + offset) {
                        centerY -= speedY;
                        bg.setSpeedY(-MOVESPEED);
                    } else if (speedY < 0 && centerY < MainClass.SCREEN_HEIGHT / 2 - offset) {
                        centerY -= speedY;
                        bg.setSpeedY(+MOVESPEED);
                    } else if (speedY == 0) {
                        centerY -= speedY;
                        bg.setSpeedY(0);
                    }
                }
            }
        }
        
        rect.setRect(centerX, centerY, width, height);
    }
    
    private boolean checkCollision () {
        if (centerX < 0 || centerY < 0) {
            return true;
        } else if (MainClass.getTileType(centerX - bg.getBgX(), centerY - bg.getBgY()) != 0) {
            return true;
        } else if (MainClass.getTileType(centerX - bg.getBgX(), centerY+height - bg.getBgY()) != 0) {
            return true;
        } else if (MainClass.getTileType(centerX+width - bg.getBgX(), centerY - bg.getBgY()) != 0) {
            return true;
        } else if (MainClass.getTileType(centerX+width - bg.getBgX(), centerY+height - bg.getBgY()) != 0) {
            return true;
        } else {
            return false;
        }
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
*/
