
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

public class MainClass extends Applet implements Runnable, KeyListener {

    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 480;

    private static Person currentPerson;
	private Person trainer;
	private Person grunt;
    private Image image, background, front, front2, front3, 
    			  back, back2, back3, left, left2, left3, right, right2, right3;

    public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight;

    private Graphics second;
    private URL base;
    private static Background bg;

    private ArrayList<Tile> tilearray = new ArrayList<Tile>();

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
        BufferedImage charImg = null;
		try {
			charImg = ImageIO.read(new File("data/personSpriteSheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        bg = new Background(0, 0);
	     // The above line throws an checked IOException which must be caught.
		
		trainer = new Person(charImg.getSubimage( 130, 0, 16, 19 ), charImg.getSubimage( 146, 0, 15, 19 ),
					     	 charImg.getSubimage( 161, 0, 15, 19 ), charImg.getSubimage( 0, 0, 16, 19 ),
					     	 charImg.getSubimage( 16, 0, 15, 19 ), charImg.getSubimage( 31, 0, 15, 19 ),
					    	 charImg.getSubimage( 46, 0, 14, 19 ), charImg.getSubimage( 60, 0, 14, 19 ),
					    	 charImg.getSubimage( 74, 0, 14, 19 ), charImg.getSubimage( 88, 0, 14, 19 ),
					    	 charImg.getSubimage( 102, 0, 14, 19 ), charImg.getSubimage( 116, 0, 14, 19 ), 
					    	 true, 100, 377);
		
		grunt = new Person(charImg.getSubimage( 130, 20, 16, 19 ), charImg.getSubimage( 146, 20, 15, 19 ),
		     	 		   charImg.getSubimage( 161, 20, 15, 19 ), charImg.getSubimage( 0, 20, 16, 19 ),
					       charImg.getSubimage( 16, 20, 15, 19 ), charImg.getSubimage( 31, 20, 15, 19 ),
					       charImg.getSubimage( 46, 20, 14, 19 ), charImg.getSubimage( 60, 20, 14, 19 ),
					       charImg.getSubimage( 74, 20, 14, 19 ), charImg.getSubimage( 88, 20, 14, 19 ),
					       charImg.getSubimage( 102, 20, 14, 19 ), charImg.getSubimage( 116, 20, 14, 19 ), 
					       false, 100, 100);
		
        background = getImage(base, "data/background2.jpg");
        
        BufferedImage tileImg = null;
		try {
			tileImg = ImageIO.read(new File("data/tile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     // The above line throws an checked IOException which must be caught.
        
        tilegrassTop = tileImg.getSubimage( 0, 0, 40, 40 );
        tilegrassBot = tileImg.getSubimage( 0, 80, 40, 40 );
        tilegrassLeft = tileImg.getSubimage( 0, 40, 40, 40 );
        tilegrassRight = tileImg.getSubimage( 40, 40, 40, 40 );
    }

    @Override
    public void start() {
        currentPerson = trainer;

        // Initialize Tiles
        try {
            loadMap("data/map1.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    private void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
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

        for (int j = 0; j < 12; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {
                //System.out.println(i + "is i ");

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
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
            grunt.update();
            trainer.update();
            
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
		currentPerson.animate(10);
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

        g.drawRect((int) grunt.rect.getX(), (int) grunt.rect.getY(),
                (int) grunt.rect.getWidth(), (int) grunt.rect.getHeight());
        
        g.drawImage(grunt.getCurrent(), grunt.getCenterX(),
        		grunt.getCenterY(), this);
        
        g.drawRect((int) trainer.rect.getX(), (int) trainer.rect.getY(),
                (int) trainer.rect.getWidth(), (int) trainer.rect.getHeight());
        
        g.drawImage(trainer.getCurrent(), trainer.getCenterX(),
        		trainer.getCenterY(), this);
    }

    private void updateTiles() {
        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            t.update();
        }
    }

    private void paintTiles(Graphics g) {
        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentPerson.startUp();
                break;

            case KeyEvent.VK_DOWN:
                currentPerson.startDown();
                break;

            case KeyEvent.VK_LEFT:
                currentPerson.startLeft();
                break;

            case KeyEvent.VK_RIGHT:
                currentPerson.startRight();
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
                currentPerson.stopUp();
                break;

            case KeyEvent.VK_DOWN:
            	currentPerson.stopDown();
                break;

            case KeyEvent.VK_LEFT:
            	currentPerson.stopLeft();
                break;

            case KeyEvent.VK_RIGHT:
            	currentPerson.stopRight();
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

    public static Person getPlayer() {
        return currentPerson;
    }

}