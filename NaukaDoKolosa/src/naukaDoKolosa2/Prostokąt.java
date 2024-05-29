package naukaDoKolosa2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Prostokąt extends Shape {
	private int px, py;
	private static Random random = new Random();

	private int x, y, x2, y2;
	private int pw = 50;
	private int ph = 50;
	private boolean isRandomlyPlaced;


	public Prostokąt(Color color, int lineWidth) {
		super(color, lineWidth);
		this.isRandomlyPlaced = false;

	}

	public void setStartPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setEndPoint(int x, int y) {
		x2 = (x);
		y2 = (y);
	}
	
	public void setRandomPosition() {
		px = random.nextInt(700);
		py = random.nextInt(700);
		isRandomlyPlaced = true;
	}

	@Override
	public void draw(Graphics2D g2d) {
	    int drawX = isRandomlyPlaced ? px : Math.min(x, x2);
	    int drawY = isRandomlyPlaced ? py : Math.min(y, y2);

	    g2d.setColor(color);
	    g2d.setStroke(new BasicStroke(lineWidth));
	    g2d.drawRect(drawX, drawY, pw, ph);
	    g2d.fillRect(drawX, drawY, pw, ph);

	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int setLineWidth(int lineWidth) {
		return this.lineWidth = lineWidth;
	}

}