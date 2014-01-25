package possessiongame;

import java.awt.Image;

public class Tile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;
	
	private Robot robot = MainClass.getRobot();
	private Background bg = MainClass.getBg1();

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		if (type == 5) {
			tileImage = MainClass.tiledirt;
		} else if (type == 8) {
			tileImage = MainClass.tilegrassTop;
		} else if (type == 4) {
			tileImage = MainClass.tilegrassLeft;

		} else if (type == 6) {
			tileImage = MainClass.tilegrassRight;

		} else if (type == 2) {
			tileImage = MainClass.tilegrassBot;
		}

	}

	public void update() {
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

}