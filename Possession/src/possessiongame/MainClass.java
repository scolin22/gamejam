
package possessiongame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import possessiongame.framework.Animation;

public class MainClass extends Applet implements Runnable, KeyListener {

    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 480;

    private static Player player;
    private Image image, currentSprite, character, background, character_backwards,
            	  character_left, character_right, character2, character_backwards2,
            	  character_left2, character_right2, character3, character_backwards3,
            	  character_left3, character_right3;

    public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
            tilegrassRight, tiledirt;
    
    private Animation charAnim, char_backwardsAnim, char_leftAnim, char_rightAnim;

    private Graphics second;
    private URL base;
    private static Background bg;

    private static Tile[][] tiles;
    private static int height = 0;
    private static int width = 0;

    @Override
    public void init() {

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Possession");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Image Setups
        BufferedImage bigImg = null;
		try {
			bigImg = ImageIO.read(new File("data/TrainerSpriteSheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     // The above line throws an checked IOException which must be caught.
		
		character_backwards = bigImg.getSubimage( 0, 0, 14, 19 );
		character_backwards2 = bigImg.getSubimage( 14, 0, 15, 19 );
		character_backwards3 = bigImg.getSubimage( 29, 0, 15, 19 );
		
		character_left = bigImg.getSubimage( 44, 0, 14, 19 );
		character_left2 = bigImg.getSubimage( 58, 0, 14, 19 );
		character_left3 = bigImg.getSubimage( 72, 0, 14, 19 );
		
		character = bigImg.getSubimage( 86, 0, 14, 19 );
		character2 = bigImg.getSubimage( 100, 0, 14, 19 );
		character3 = bigImg.getSubimage( 114, 0, 14, 19 );
		
		character_right = bigImg.getSubimage( 128, 0, 14, 19 );
		character_right2 = bigImg.getSubimage( 142, 0, 14, 19 );
		character_right3 = bigImg.getSubimage( 156, 0, 14, 19 );

        currentSprite = character;
        background = getImage(base, "data/background2.jpg");
        
        tiledirt = getImage(base, "data/tiledirt.png");
        tilegrassTop = getImage(base, "data/tilegrasstop.png");
        tilegrassBot = getImage(base, "data/tilegrassbot.png");
        tilegrassLeft = getImage(base, "data/tilegrassleft.png");
        tilegrassRight = getImage(base, "data/tilegrassright.png");
        
        
        charAnim = new Animation();
        charAnim.addFrame(character, 75);
        charAnim.addFrame(character2, 75);
        charAnim.addFrame(character, 75);
        charAnim.addFrame(character3, 75);
		
        char_rightAnim = new Animation();
        char_rightAnim.addFrame(character_right, 75);
        char_rightAnim.addFrame(character_right2, 75);
        char_rightAnim.addFrame(character_right, 75);
        char_rightAnim.addFrame(character_right3, 75);
        
        char_backwardsAnim = new Animation();
        char_backwardsAnim.addFrame(character_backwards, 75);
        char_backwardsAnim.addFrame(character_backwards2, 75);
        char_backwardsAnim.addFrame(character_backwards, 75);
        char_backwardsAnim.addFrame(character_backwards3, 75);
        
        char_leftAnim = new Animation();
        char_leftAnim.addFrame(character_left, 75);
        char_leftAnim.addFrame(character_left2, 75);
        char_leftAnim.addFrame(character_left, 75);
        char_leftAnim.addFrame(character_left3, 75);
		
		currentSprite = charAnim.getImage();
    }

    @Override
    public void start() {
        bg = new Background(0, 0);
        player = new Player();

        // Initialize Tiles
        try {
            loadMap("data/map1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    private void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }
        height = lines.size();
        
        tiles = new Tile[height][width];

        for (int j = 0; j < height; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {
                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tiles[j][i] = t;
                } else {
                    Tile t = new Tile(i, j, 0);
                    tiles[j][i] = t;
                }

            }
        }

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void run() {
        while (true) {
            player.update();
            
            updateTiles();
            bg.update();
            animate();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void animate() {
		charAnim.update(10);
		char_leftAnim.update(10);
		char_rightAnim.update(10);
		char_backwardsAnim.update(10);
	}
    
    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, bg.getBgX(), bg.getBgY(), this);
        paintTiles(g);
        
        g.drawImage(currentSprite, player.getCenterX(),
                player.getCenterY(), this);
    }

    private void updateTiles() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Tile t = (Tile) tiles[j][i];
                t.update();
            }
        }
    }

    private void paintTiles(Graphics g) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Tile t = (Tile) tiles[j][i];
                g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentSprite = charAnim.getImage();
                player.startUp();
                break;

            case KeyEvent.VK_DOWN:
                currentSprite = char_backwardsAnim.getImage();
                player.startDown();
                break;

            case KeyEvent.VK_LEFT:
                currentSprite = char_leftAnim.getImage();
                player.startLeft();
                break;

            case KeyEvent.VK_RIGHT:
                currentSprite = char_rightAnim.getImage();
                player.startRight();
                break;

            case KeyEvent.VK_SPACE:
                //player.jump();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.stopUp();
                break;

            case KeyEvent.VK_DOWN:
                player.stopDown();
                break;

            case KeyEvent.VK_LEFT:
                player.stopLeft();
                break;

            case KeyEvent.VK_RIGHT:
                player.stopRight();
                break;

            case KeyEvent.VK_SPACE:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public static Background getBg() {
        return bg;
    }

    public static Player getPlayer() {
        return player;
    }
    
    public static int getTileType(int x, int y) {
        x = x / Tile.TILE_SIZE;
        y = y / Tile.TILE_SIZE;
        if (x >= width || y >= height) {
            return 1;
        } else {
            return tiles[y][x].getTileType();
        }
    }

}