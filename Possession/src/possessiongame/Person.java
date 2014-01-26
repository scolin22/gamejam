
package possessiongame;


import possessiongame.framework.PersonAnim;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import possessiongame.framework.Animation;

public class Person {

    // Constants are Here
    final int MOVESPEED = 2;

    private int centerX;
    private int centerY;
    private int width = 16;
    private int height = 19;
    private int radius = 50;
    final int offset = 60;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean canPossess = false;

    private static Background bg = MainClass.getBg();
    
    private PersonAnim anims;
    
    private int speedX = 0;
    private int speedY = 0;

    // back - seen while moving down
    // front - seen while moving up
    // left - seen while moving left
    // right - seen while moving right

    private Image current;

    private boolean isActive = false;

    private int paranoia = 0;
    
    private ArrayList<String> inventory = new ArrayList<String>();

    private ArrayList<String> dialog = new ArrayList<String>();
    public String outputMessage;
    private long ticks = 0;
    private int mIndex = 0;
    
    private ArrayList<Character> allowedTiles = new ArrayList<Character>();

    public Person( boolean isActive, int startX, int startY, PersonAnim anims, ArrayList<String> d) {
        
        if (d != null) {
            dialog = d;
        } else {
            dialog = null;
        }
        this.anims = anims;
    	current = anims.getFront().getImage();
    	
    	allowedTiles.add('a');
    	allowedTiles.add(' ');
    	allowedTiles.add('0');

       

        this.isActive = isActive;
        centerX = startX;
        centerY = startY;
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
            if (dialog != null) {
                if (System.currentTimeMillis() - ticks > 3000) {
                    outputMessage = dialog.get(mIndex);
                    mIndex++;
                    if (mIndex >= dialog.size()) {
                        mIndex = 0;
                    }
                    ticks = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - ticks > 2500) {
                    outputMessage = null;
                }
            }
            return;
        }

        outputMessage = null;
        
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
        } else if(!this.allowedTile(MainClass.getTileType(centerX - bg.getBgX(), centerY - bg.getBgY()))){
        	return true;
        } else if(!this.allowedTile(MainClass.getTileType(centerX - bg.getBgX(), centerY + height - bg.getBgY()))){
        	return true;
        } else if(!this.allowedTile(MainClass.getTileType(centerX + width - bg.getBgX(), centerY - bg.getBgY()))){
        	return true;
        } else if(!this.allowedTile(MainClass.getTileType(centerX + width - bg.getBgX(), centerY + height - bg.getBgY()))){
        	return true;
        } 
        return false;
    }
    
    private void displayInventory(Graphics g) {
        int i = 0;
        for (String e : inventory) {
            if (e.equals("a")) {
                g.drawImage(null, 0, MainClass.INVENTORY_Y, null);
            }
            i += 1;
        }
    }
    
    public void addInventory(String i) {
        inventory.add(i);
    }
    
    public boolean checkInventory(String i) {
        for (String e : inventory) {
            if (e.equals(i)) {
                return true;
            }
        }
        return false;
    }

    public void stopUp() {
        movingUp = false;
        speedY = 0;
    }

    public void stopDown() {
        movingDown = false;
        speedY = 0;
    }

    public void stopRight() {
        movingRight = false;
        speedX = 0;
    }

    public void stopLeft() {
        movingLeft = false;
        speedX = 0;
    }

    public void startUp() {
        movingUp = true;
        current = anims.getFront().getImage();
        if (!movingDown) {
            speedY = -MOVESPEED;
        } else {
            speedY = 0;
        }
    }

    public void startDown() {
        movingDown = true;
        current = anims.getBack().getImage();
        if (!movingUp) {
            speedY = MOVESPEED;
        } else {
            speedY = 0;
        }
    }

    public void startRight() {
        movingRight = true;
        current = anims.getRight().getImage();
        if (!movingLeft) {
            speedX = MOVESPEED;
        } else {
            speedX = 0;
        }
    }

    public void startLeft() {
        movingLeft = true;
        current = anims.getLeft().getImage();
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
        this.setSpeedY(0);
        this.setSpeedX(0);
        return this;
    }
    
    public void disable(){
            isActive = false;
            canPossess = false;
            speedY = 0;
            speedX = 0;
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

	public void animate(int i) {
		anims.getFront().update(i);
		anims.getBack().update(i);
		anims.getRight().update(i);
		anims.getLeft().update(i);
	}

    
    public boolean allowedTile(char tile){
    	boolean allowed = false;
    	for(int i = 0; i < allowedTiles.size(); i++){
    		if(tile == allowedTiles.get(i)){
    			System.out.println(allowedTiles.get(i));
    			allowed = true;
    			break;
    		}
    	}
    	//System.out.println(allowed);
    	return allowed;
    }
}
