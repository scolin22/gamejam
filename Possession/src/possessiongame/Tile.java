package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
        
    public static final int TILE_SIZE = 25;
        
	private int tileX, tileY, speedX, speedY;
	private char type;
	public Image tileImage;

	private Person player = MainClass.getPlayer();

	private Background bg = MainClass.getBg();
	private Rectangle r;

	public Tile(int x, int y, char ch) {
		tileX = x * TILE_SIZE;
		tileY = y * TILE_SIZE;

		type = ch;
		r = new Rectangle();

		if (type == '1') {
			tileImage = MainClass.wall;
		}
		else if (type == '2') {
			tileImage = MainClass.doorV;
		}
		else if (type == '3') {
			tileImage = MainClass.doorH;
		}
		else if (type == '4') {
			tileImage = MainClass.deskVT;
		}
		else if (type == '5') {
			tileImage = MainClass.deskVB;
		}
		else if (type == '6') {
			tileImage = MainClass.deskVC;
		}
		else if (type == '7') {
			tileImage = MainClass.deskHL;
		}
		else if (type == '8') {
			tileImage = MainClass.deskHR;
		}
		else if (type == '9') {
			tileImage = MainClass.deskHC;
		}
		else if (type == 'z') {
			tileImage = MainClass.chairL;
		}
		else if (type == 'y') {
			tileImage = MainClass.chairR;
		}
		else if (type == 'x') {
			tileImage = MainClass.safe;
		}
		else if (type == 'w') {
			tileImage = MainClass.cameraOR;
		}
		else if (type == 'v') {
			tileImage = MainClass.cameraOL;
		}
		else if (type == 'u') {
			tileImage = MainClass.cameraXR;
		}
		else if (type == 't') {
			tileImage = MainClass.cameraXR;
		}
		else if (type == 'r') {
			tileImage = MainClass.computer;
		}else if (type == ' '){
			type = '0';
		}else if( type == 'g' ){
			tileImage = MainClass.garbage;
		}else {
			
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
    
    public char getTileType() {
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