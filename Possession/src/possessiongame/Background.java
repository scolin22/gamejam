package possessiongame;

public class Background {

	private int bgX;
	private int bgY;
	private int speedX;
	private int speedY;
	
	private Person player = MainClass.getPlayer();
	
	public Background(int x, int y) {
		bgX = x;
		bgY = y;
		speedX = 0;
		speedY = 0;
	}

	public void update() {
		
		bgX += speedX;
		bgY += speedY;
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY){
		this.speedY = speedY;
	}
}

