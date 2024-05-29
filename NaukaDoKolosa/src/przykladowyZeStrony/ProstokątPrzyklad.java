package przykladowyZeStrony;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class ProstokątPrzyklad extends Shape {

	private static Random random = new Random();
	private int px, py;
	private int pw = 50;
	private int ph = 50;
	private boolean isRandomlyPlaced;

	private int x, y, x2, y2;

	public ProstokątPrzyklad(Color color, int lineWidth) {
		super(color, lineWidth);
		this.isRandomlyPlaced = false;
	}

	public void setStartPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setEndPoint(int x, int y) {
		x2 = x;
		y2 = y;
	}

	public void setRandomPosition() {
		px = random.nextInt(600);
		py = random.nextInt(200);
		isRandomlyPlaced = true;
	}

	@Override
	public void draw(Graphics2D g2d) {
		int drawX;
		int drawY;
		
		if (isRandomlyPlaced) {
		    drawX = px;
		} else {
		    drawX = Math.min(x, x2);
		}		
		if (isRandomlyPlaced) {
		    drawY = py;
		} else {
		    drawY = Math.min(y, y2);
		}		

		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(lineWidth));
		g2d.drawRect(drawX, drawY, pw, ph);
	}
}
