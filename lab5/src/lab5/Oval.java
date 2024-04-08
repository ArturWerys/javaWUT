package lab5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Oval extends Shape {

	 private int startX, startY, endX, endY;	
	
	public Oval(Color color, int lineWidth) {
		super(color, lineWidth);
	}

	@Override
	public void draw(Graphics2D g2d) {
		
		int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(lineWidth));
        
        
        g2d.drawOval(x, y, width, height);
		
	}

}
