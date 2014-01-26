package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
	
	
	private int tileX, tileY, speedX, speedY, type, safeY, safeX;
	public Image tileImage;

	private Player player = MainClass.getPlayer();
	private Background bg = MainClass.getBg();
	private Rectangle r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

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

		r.setBounds(tileX, tileY, 40, 40);

		if (r.intersects(Player.rect) && type != 0) {
			player.setSpeedX(0);
		    player.setSpeedY(0);
		    player.setCenterX(safeX);
		    player.setCenterY(safeY);
		} else {
			safeY = player.getCenterY();
			safeX = player.getCenterX();
		}

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

	public void checkCollision(Rectangle prect) {
		if (prect.intersects(r)) {
			System.out.println("collision");
			player.setCenterY(safeY + 1);
			player.setSpeedY(0);
		} else {
			safeY = player.getCenterY();
		}
	}

}