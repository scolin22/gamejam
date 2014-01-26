
package possessiongame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainClass extends Applet implements Runnable, KeyListener {

    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 480;
    
    //Relative Positioning of Speech Bubble to Player
    public final static int speech_OffsetX = 20;
    public final static int speech_OffsetY = -60;
    public final static int speechtext_OffsetX = 10;
    public final static int speechtext_OffsetY = -30;

    private static Player player;
    
    private Image image, currentSprite, character, background, character_backwards,
    character_left, character_right, character2, character_backwards2,
    character_left2, character_right2, character3, character_backwards3,
    character_left3, character_right3;
    
    private Dialog dialog;

    public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
            tilegrassRight, tiledirt;

    //private Animation charAnim, char_backwardsAnim, char_leftAnim, char_rightAnim;
    
    private Graphics second;
    public static URL base;
    private static Background bg;

    private ArrayList<Tile> tilearray = new ArrayList<Tile>();

    @Override
    public void init() {

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("The Game");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Image Setups
        dialog = new Dialog(getImage(base, "data/small_speech.jpg"));

        character = getImage(base, "data/Char_forward.png");
        character_backwards = getImage(base, "data/Char_backwards.png");
        character_left = getImage(base, "data/Char_left.png");
        character_right = getImage(base, "data/Char_right.png");
        

        currentSprite = character;
        background = getImage(base, "data/background2.jpg");
        
        tiledirt = getImage(base, "data/tiledirt.png");
        tilegrassTop = getImage(base, "data/tilegrasstop.png");
        tilegrassBot = getImage(base, "data/tilegrassbot.png");
        tilegrassLeft = getImage(base, "data/tilegrassleft.png");
        tilegrassRight = getImage(base, "data/tilegrassright.png");
    }

    @Override
    public void start() {
        bg = new Background(0, 0);
        player = new Player();
        

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
                System.out.println(i + "is i ");

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
            player.update();
            currentSprite = character;
            
            updateTiles();
            bg.update();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

        g.drawRect((int) player.rect.getX(), (int) player.rect.getY(),(int) player.rect.getWidth(), (int) player.rect.getHeight());    
        g.drawImage(currentSprite, player.getCenterX(), player.getCenterY(), this);
        
        outputDialog(dialog, g);
    }
    
    private void outputDialog(Dialog dialog, Graphics g){
    	g.drawImage(dialog.getDialog_background(), player.getCenterX() + speech_OffsetX, player.getCenterY() + speech_OffsetY, this);
		g.drawString(dialog.outputDialog(), player.getCenterX() + speechtext_OffsetX, player.getCenterY() + speechtext_OffsetY);
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
                currentSprite = character;
                player.startUp();
                break;

            case KeyEvent.VK_DOWN:
                currentSprite = character_backwards;
                player.startDown();
                break;

            case KeyEvent.VK_LEFT:
                currentSprite = character_left;
                player.startLeft();
                break;

            case KeyEvent.VK_RIGHT:
                currentSprite = character_right;
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
    
    public Image getImageResource(URL base, String location){
    	Image image = getImage(base, location);
    	return image;
    	
    }

}