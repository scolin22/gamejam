package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;

import possessiongame.framework.Animation;

public class Person {
	
	// Constants are Here
    final int MOVESPEED = 2;

    private int centerX;
    private int centerY;
    final int offset = 60;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private static Background bg = MainClass.getBg();

    private int speedX = 0;
    private int speedY = 0;
    
    //back - seen while moving down
    //front - seen while moving up
    //left - seen while moving left
    //right - seen while moving right
    
	private Image current, front, front2, front3, 
				  back, back2, back3, 
				  left, left2, left3, 
				  right, right2, right3;
	
	private boolean isActive;
	
	private Animation frontAnim, backAnim, leftAnim, rightAnim;
	
	private int paranoia;
	
	public Person(Image front, Image front2, Image front3, 
				  Image back, Image back2, Image back3, 
				  Image left, Image left2, Image left3, 
			      Image right, Image right2, Image right3, 
			      boolean isActive, int startX, int startY) {
		
		this.front = front;
		this.front2 = front2;
		this.front3 = front3;
		
		this.back = back;
		this.back2 = back2;
		this.back3 = back3;
				
		this.left = left;
		this.left2 = left2;
		this.left3 = left3;
				
		this.right = right;
		this.right2 = right2;
		this.right3 = right3;
		
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
	
	public void animate( int speed ) {
		frontAnim.update( speed );
		backAnim.update( speed );
		leftAnim.update( speed );
		rightAnim.update( speed );
	}
	
	public void setActive( boolean flag ) {
		isActive = flag;
	}
	
	// For Collision Detection
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);

    public void update() {
    	if( !isActive )
    		return;
        centerX += speedX;

        centerY += speedY;
        
        if (speedX == 0) {
            bg.setSpeedX(0);
        }
        if (speedY == 0) {
            bg.setSpeedY(0);
        }
        if (speedX > 0 && centerX > MainClass.SCREEN_WIDTH / 2 + offset) {
            bg.setSpeedX(-MOVESPEED);
        }
        if (speedX < 0 && centerX < MainClass.SCREEN_WIDTH / 2 - offset) {
            bg.setSpeedX(+MOVESPEED);
        }
        if (speedY > 0 && centerY > MainClass.SCREEN_HEIGHT / 2 + offset) {
            bg.setSpeedY(-MOVESPEED);
        }
        if (speedY < 0 && centerY < MainClass.SCREEN_HEIGHT / 2 - offset) {
            bg.setSpeedY(+MOVESPEED);
        }

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