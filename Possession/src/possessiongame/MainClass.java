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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import possessiongame.framework.PersonAnim;

public class MainClass extends Applet implements Runnable, KeyListener {

    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 570;
    public final static int INVENTORY_Y = 500;

    // Relative Positioning of Speech Bubble to Player
    public final static int speech_OffsetX = 10;
    public final static int speech_OffsetY = 0;
    public final static int speechtext_OffsetX = -25;
    public final static int speechtext_OffsetY = -30;

    private static Person currentPerson;
    private ArrayList<Person> People;
    private Person player, security, employee1, employee2, secretary;
    private Image image, background, inventory;

    private static PersonAnim playerAnims, securityAnims, employeeAnims, secretaryAnims;

    public static Image dialog_image;

    //private Dialog dialog;

    public static Image wall, doorV, doorH, deskHL, deskHR, deskHC, deskVT, deskVB, deskVC, chairL,
            chairR, safe, cameraOR, cameraOL, cameraXR, cameraXL, computer, garbage;

    // private Animation charAnim, char_backwardsAnim, char_leftAnim,
    // char_rightAnim;

    private Graphics second;
    public static URL base;
    private static Background bg;

    private static Tile[][] tiles;
    private static int height = 0;
    private static int width = 0;

    @Override
    public void init() {

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Possession");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        //dialog = new Dialog(getImage(base, "data/small_speech.jpg"));

        bg = new Background(0, 0);

        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("data/Rest for the Wicked.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        initCharacters();

        background = getImage(base, "data/background.jpg");
        inventory = getImage(base, "data/inventory.png");
        dialog_image = getImage(base, "data/small_speech.jpg");

        BufferedImage tileImg = null;
        try {
            tileImg = ImageIO.read(new File("data/wall.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wall = tileImg;

        try {
            tileImg = ImageIO.read(new File("data/ObjectSpriteSheet.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        doorV = tileImg.getSubimage(100, 25, 25, 25); 
        doorH = tileImg.getSubimage(25, 75, 25, 25);
        deskVT = tileImg.getSubimage(75, 100, 25, 25);
        deskVB = tileImg.getSubimage(75, 75, 25, 25);
        deskVC = tileImg.getSubimage(100, 75, 25, 25);
        deskHL = tileImg.getSubimage(25, 25, 25, 25);
        deskHR = tileImg.getSubimage(0, 25, 25, 25);
        deskHC = tileImg.getSubimage(25, 0, 25, 25);
        chairL = tileImg.getSubimage(6, 97, 18, 25);
        chairR = tileImg.getSubimage(28, 97, 18, 25);
        safe = tileImg.getSubimage(0, 50, 25, 25);
        cameraOR = tileImg.getSubimage(25, 50, 25, 20);
        cameraOL = tileImg.getSubimage(75, 50, 25, 20);
        cameraXR = tileImg.getSubimage(50, 50, 25, 20);
        cameraXL = tileImg.getSubimage(100, 50, 25, 20);
        computer = tileImg.getSubimage(0, 0, 12, 12);
        garbage = tileImg.getSubimage(0, 12, 12, 13);
        //recycling = tileImg.getSubimage(13, 14, 12, 11);
    }

    private void initCharacters() {
        
        ArrayList<String> security_dialog;
        
        BufferedImage charImg = null;
        try {
            charImg = ImageIO.read(new File("data/personSpriteSheet.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        playerAnims = new PersonAnim(charImg.getSubimage(130, 0, 16, 21),charImg.getSubimage(146, 0, 15, 21),
                charImg.getSubimage(161, 0, 15, 21), charImg.getSubimage(0, 0, 16, 21),
                charImg.getSubimage(16, 0, 15, 21), charImg.getSubimage(31, 0, 15, 21),
                charImg.getSubimage(46, 0, 14, 21), charImg.getSubimage(60, 0, 14, 21),
                charImg.getSubimage(74, 0, 14, 21), charImg.getSubimage(88, 0, 14, 21),
                charImg.getSubimage(102, 0, 14, 21), charImg.getSubimage(116, 0, 14, 21) );
        
        securityAnims = new PersonAnim(charImg.getSubimage(130, 21, 16, 21),charImg.getSubimage(146, 21, 15, 21),
                charImg.getSubimage(161, 21, 15, 21), charImg.getSubimage(0, 21, 16, 21),
                charImg.getSubimage(16, 21, 15, 21), charImg.getSubimage(31, 21, 15, 21),
                charImg.getSubimage(46, 21, 14, 21), charImg.getSubimage(60, 21, 14, 21),
                charImg.getSubimage(74, 21, 14, 21), charImg.getSubimage(88, 21, 14, 21),
                charImg.getSubimage(102, 21, 14, 21), charImg.getSubimage(116, 21, 14, 21) );
        
        employeeAnims = new PersonAnim(charImg.getSubimage(46, 42, 14, 21), charImg.getSubimage(60, 42, 14, 21),
                charImg.getSubimage(74, 42, 14, 21), charImg.getSubimage(0, 42, 16, 21),
                charImg.getSubimage(16, 42, 15, 21), charImg.getSubimage(31, 42, 15, 21),
                charImg.getSubimage(88, 42, 14, 21),charImg.getSubimage(102, 42, 14, 21), 
                charImg.getSubimage(116, 42, 14, 21),charImg.getSubimage(130, 42, 16, 21),
                charImg.getSubimage(146, 42, 15, 21),charImg.getSubimage(161, 42, 15, 21) );
        
        secretaryAnims = new PersonAnim(charImg.getSubimage(88, 63, 14, 21),charImg.getSubimage(102, 63, 14, 21), 
                charImg.getSubimage(116, 63, 14, 21), charImg.getSubimage(0, 63, 16, 21),
                charImg.getSubimage(16, 63, 15, 21), charImg.getSubimage(31, 63, 15, 21), 
                charImg.getSubimage(130, 63, 16, 21),charImg.getSubimage(146, 63, 15, 21),
                charImg.getSubimage(46, 63, 14, 21), charImg.getSubimage(60, 63, 14, 21),
                charImg.getSubimage(74, 63, 14, 21), charImg.getSubimage(161, 63, 15, 21) );
        
        security_dialog = new ArrayList<String>();
        security_dialog.add("I am a security guard.");
        security_dialog.add("Good thing the camera is on.");
        security_dialog.add("Otherwise, someone could steal money.");
        security_dialog.add("From the safe.");
        
        player = new Person( true, 550, 300, playerAnims, null );
        security = new Person( false, 275, 300, securityAnims, security_dialog );
        employee1 = new Person( false, 275, 100, employeeAnims, null );
        employee2 = new Person( false, 275, 200, employeeAnims, null );
        secretary = new Person( false, 275, 400, secretaryAnims, null );
        
        People = new ArrayList<Person>();
        People.add(security);
        People.add(player);
        People.add(employee1);
        People.add(employee2);
        People.add(secretary);
        currentPerson = player;
    }

    @Override
    public void start() {
        // Initialize Tiles
        try {
            loadMap("data/map2.txt");

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
                    Tile t = new Tile(i, j, ch);
                    tiles[j][i] = t;
                } else {
                    Tile t = new Tile(i, j, '0');
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
            security.update();
            player.update();
            secretary.update();
            employee1.update();
            employee2.update();

            if (currentPerson.getPossess()) {
                int x_cur = currentPerson.getCenterX();
                int y_cur = currentPerson.getCenterY();
                double rad = currentPerson.getRadius();
                int index = -1;
                for (int i = 0; i < People.size(); i++) {
                    Person cur = People.get(i);
                    if (!cur.active()) {
                        int x = cur.getCenterX();
                        int y = cur.getCenterY();
                        double min = Math.sqrt((x - x_cur) * (x - x_cur) + (y - y_cur) * (y - y_cur));
                        if (min < rad) {
                            index = i;
                            rad = min;
                        }
                    }
                }
                if (index != -1) {
                    currentPerson.disable();
                    currentPerson = (People.get(index)).enable();
                } else {
                    currentPerson.stopPossess();
                }
            }
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
        g.drawImage(inventory, 0, INVENTORY_Y, this);

        paintTiles(g);
        for (int i = 0; i < People.size(); i++) {
        	g.drawImage(People.get(i).getCurrent(), People.get(i).getCenterX(),People.get(i).getCenterY(), this);
        }

        outputDialog(g, security);

    }

    private void outputDialog(Graphics g, Person p) {
        if (p.outputMessage != null) {
            int y = p.getCenterY() + speech_OffsetY;
            
            g.drawString(p.outputMessage, p.getCenterX() + speechtext_OffsetX,
                    y);
        }
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
                currentPerson.startPossess();
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

    public Image getImageResource(URL base, String location) {
        Image image = getImage(base, location);
        return image;
    }

    public static char getTileType(int x, int y) {
        x = x / Tile.TILE_SIZE;
        y = y / Tile.TILE_SIZE;
        if (x >= width || y >= height) {
            return '1';
        } else {
            return  tiles[y][x].getTileType();
        }
    }
    
}
