package VG3;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.awt.Point;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.ImageObserver;

public class Hacker {
	
	private Image image;
	private Point pos;
	private Random rand;
	private int moveDelay;
	private int delayCount = 0;
	
	public Hacker(int moveDelay) {
		loadImage();
		rand = new Random();
		
		pos = new Point(rand.nextInt(BoardClass.COLUMNS), 0);
		this.moveDelay = moveDelay;
	}
	
	private void loadImage() {
		try {
			image = ImageIO.read(new File("hacker.png"));
		} catch (IOException exc) {
			System.out.println("Error :(");
		}
    
		image = image.getScaledInstance(BoardClass.TILE_SIZE, BoardClass.TILE_SIZE, image.SCALE_DEFAULT);
	}
	public void move() {
		if(++delayCount >= moveDelay) {
			pos.translate(0, 1);
			
			if (pos.y >= BoardClass.ROWS) {
				pos.setLocation(rand.nextInt(BoardClass.COLUMNS), 0);
			}
		}
	}
	public void draw(Graphics g, ImageObserver obs) {
		g.drawImage(image, pos.x * BoardClass.TILE_SIZE, pos.y * BoardClass.TILE_SIZE, obs);
	}
	public Point getPos() {
		return pos;
	}
}
