package possessiongame;

import java.awt.Image;

public class Tile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;

	private Background bg = MainClass.getBg1();

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		if (type == 1) {
			tileImage = MainClass.tileocean;
		} else if (type == 2) {

			tileImage = MainClass.tiledirt;
		}

	}

	public void update() {
		// TODO Auto-generated method stub
		if (type == 1) {
			if (bg.getSpeedX() == 0) {
				speedX = -1;
			} else {
				speedX = -2;
			}

		} else {
			speedX = bg.getSpeedX() * 5;
		}

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