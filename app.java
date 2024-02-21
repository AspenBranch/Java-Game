package VG3;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// import java.awt.*;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;

public class PlayerClass {

// Step 1: Define class fields

// image that represents the player's position on the board
    private Image image;
    // current position of the player on the board grid
    private Point pos;
    // keep track of the player's score
    private int score;
   
    // Step 2: Constructor

    public PlayerClass() {
        // load the assets
        loadImage();
       
        // initialize the state
        pos = new Point(0, 0);
       
        // initialize the score
        score = 0;
    }
   
   
    public void moveUp() {
        pos.translate(0, -1);
    }

    public void moveDown() {
        pos.translate(0, 1);
    }

    public void moveLeft() {
        pos.translate(-1, 0);
    }

    public void moveRight() {
        pos.translate(1, 0);
    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            image = ImageIO.read(new File("player.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
       
        // scale our image to the size of ONE tile on the gameboard
        image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
            image,
            pos.x * BoardClass.TILE_SIZE, // 1,1
            pos.y * BoardClass.TILE_SIZE, // 1 * 50, 1 * 50
            observer
        );
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
   
   
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= BoardClass.COLUMNS) {
            pos.x = BoardClass.COLUMNS - 1;
        }
        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= BoardClass.ROWS) {
            pos.y = BoardClass.ROWS - 1;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPos() {
        return pos;
    }
   
}
