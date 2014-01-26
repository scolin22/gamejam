
package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;

import possessiongame.framework.Animation;

public class Person {

    // Constants are Here
    final int MOVESPEED = 2;

    private int centerX;
    private int centerY;
    private int width = 25;
    private int height = 35;
    private int radius = 50;
    final int offset = 60;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean canPossess = false;

    private static Background bg = MainClass.getBg();

    private int speedX = 0;
    private int speedY = 0;

    // back - seen while moving down
    // front - seen while moving up
    // left - seen while moving left
    // right - seen while moving right

    private Image current;

    private boolean isActive;

    private Animation frontAnim, backAnim, leftAnim, rightAnim;

    private int paranoia;

    public Person(Image front, Image front2, Image front3,
            Image back, Image back2, Image back3,
            Image left, Image left2, Image left3,
            Image right, Image right2, Image right3,
            boolean isActive, int startX, int startY) {

        frontAnim = new Animation();
        frontAnim.addFrame(front, 75);
        frontAnim.addFrame(front2, 75);
        frontAnim.addFrame(front, 75);
        frontAnim.addFrame(front3, 75);

        rightAnim = new Animation();
        rightAnim.addFrame(right, 75);
        rightAnim.addFrame(right2, 75);
        rightAnim.addFrame(right, 75);
        rightAnim.addFrame(right3, 75);

        backAnim = new Animation();
        backAnim.addFrame(back, 75);
        backAnim.addFrame(back2, 75);
        backAnim.addFrame(back, 75);
        backAnim.addFrame(back3, 75);

        leftAnim = new Animation();
        leftAnim.addFrame(left, 75);
        leftAnim.addFrame(left2, 75);
        leftAnim.addFrame(left, 75);
        leftAnim.addFrame(left3, 75);

        this.isActive = isActive;
        centerX = startX;
        centerY = startY;

        current = front;

        paranoia = 0;
    }

    public void animate(int speed) {
        frontAnim.update(speed);
        backAnim.update(speed);
        leftAnim.update(speed);
        rightAnim.update(speed);
    }

    public void setActive(boolean flag) {
        isActive = flag;
    }

    // For Collision Detection
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);

    public void update() {
        if (!isActive) {
            centerX += bg.getSpeedX();
            centerY += bg.getSpeedY();
            return;
        }

        centerX += speedX;
        if (checkCollision()) {
            centerX -= speedX;
            bg.setSpeedX(0);
        } else if (speedX == 0) {
            bg.setSpeedX(0);
        } else if (bg.getBgX() == 0 && speedX < 0) {
            bg.setSpeedX(0);
        } else {
            if (bg.getBgX() < 0) {
                if (speedX < 0 && centerX < MainClass.SCREEN_WIDTH / 2 - offset) {
                    centerX -= speedX;
                    bg.setSpeedX(+MOVESPEED);
                }
            }
            if (bg.getBgX() > -100000) {
                if (speedX > 0 && centerX > MainClass.SCREEN_WIDTH / 2 + offset) {
                    centerX -= speedX;
                    bg.setSpeedX(-MOVESPEED);
                }
            }
        }

        centerY += speedY;
        if (checkCollision()) {
            centerY -= speedY;
            bg.setSpeedY(0);
        } else if (speedY == 0) {
            bg.setSpeedY(0);
        } else if (bg.getBgY() == 0 && speedY < 0) {
            bg.setSpeedY(0);
        } else {
            if (bg.getBgY() < 0) {
                if (speedY < 0 && centerY < MainClass.SCREEN_HEIGHT / 2 - offset) {
                    centerY -= speedY;
                    bg.setSpeedY(+MOVESPEED);
                }
            }
            if (bg.getBgY() > -100000) {
                if (speedY > 0 && centerY > MainClass.SCREEN_HEIGHT / 2 + offset) {
                    centerY -= speedY;
                    bg.setSpeedY(-MOVESPEED);
                }
            }
        }

        rect.setRect(centerX, centerY, width, height);
    }

    private boolean checkCollision() {
        if (centerX < 0 || centerY < 0) {
            return true;
        } else if (MainClass.getTileType(centerX - bg.getBgX(), centerY - bg.getBgY()) != 0) {
            return true;
        } else if (MainClass.getTileType(centerX - bg.getBgX(), centerY + height - bg.getBgY()) != 0) {
            return true;
        } else if (MainClass.getTileType(centerX + width - bg.getBgX(), centerY - bg.getBgY()) != 0) {
            return true;
        } else if (MainClass.getTileType(centerX + width - bg.getBgX(),
                centerY + height - bg.getBgY()) != 0) {
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
        current = frontAnim.getImage();
        if (!movingDown) {
            speedY = -MOVESPEED;
        } else {
            speedY = 0;
        }
    }

    public void startDown() {
        movingDown = true;
        current = backAnim.getImage();
        if (!movingUp) {
            speedY = MOVESPEED;
        } else {
            speedY = 0;
        }
    }

    public void startRight() {
        movingRight = true;
        current = rightAnim.getImage();
        if (!movingLeft) {
            speedX = MOVESPEED;
        } else {
            speedX = 0;
        }
    }

    public void startLeft() {
        movingLeft = true;
        current = leftAnim.getImage();
        if (!movingRight) {
            speedX = -MOVESPEED;
        } else {
            speedX = 0;
        }
    }

    public void startPossess(){
    	canPossess = true;
    }
    public void stopPossess(){
    	canPossess = false;
    }
    
    public Image getCurrent(){
    	return current;
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

    public Person enable() {
        isActive = true;
        return this;
    }
    
    public void disable(){
    	isActive = false;
    	canPossess = false;
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
 
    public boolean getPossess(){
    	return canPossess;
    }
    
    public int getRadius(){
    	return radius;
    }
    
    public boolean active(){
    	return isActive;
    }
}
