package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
	
    public static final int TILE_SIZE = 40;
	
	private int tileX, tileY, speedX, speedY, type;
	public Image tileImage;

	private Background bg = MainClass.getBg();
	private Rectangle r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * TILE_SIZE;
		tileY = y * TILE_SIZE;

		type = typeInt;

		r = new Rectangle();

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
		} else {
			type = 0;
		}

	}

	public void update() {
		speedX = bg.getSpeedX();
		speedY = bg.getSpeedY();
		
		tileX += speedX;
		tileY += speedY;

		r.setBounds(tileX, tileY, TILE_SIZE, TILE_SIZE);
	}

	public int getTileX() {
		return tileX;
	}

    public int getTileY() {
        return tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }
    
    public int getTileType() {
        return type;
    }

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
}