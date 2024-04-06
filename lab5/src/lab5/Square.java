package lab5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Square extends Shape{
    private Color color;
    private int lineWidth;
	private int x,y,x2,y2;

    public Square(Color color, int lineWidth) {
    	super(color, lineWidth);
      

    }
    
    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		 
		g2d.setColor(color); 
		g2d.setStroke(new BasicStroke(lineWidth)); 
		int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw=Math.abs(x-x2);
        int ph=Math.abs(y-y2);    
		g2d.drawRect(px, py, pw, ph); 
		
	}
	
}
