package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;

	private Robot robot = MainClass.getRobot();
	private Background bg = MainClass.getBg1();
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
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;

		r.setBounds(tileX, tileY, 40, 40);

		if (r.intersects(Robot.yellowRed) && type != 0) {
			checkVerticalCollision(Robot.rect, Robot.rect2);
		//	checkSideCollision(Robot.rect, Robot.rect2);
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

	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(r)) {
			System.out.println("upper collision");
		}

		if (rbot.intersects(r)) {
			System.out.println("lower collision");
		}
	}

	public void checkSideCollision(Rectangle rtop, Rectangle rbot) {
		if (type != 5 && type != 2 && type != 0) {
			if (rtop.intersects(r)) {
				robot.setCenterX(tileX + 102);

				robot.setSpeedX(0);

			} else if (rbot.intersects(r)) {
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}

			
		}
	}

}