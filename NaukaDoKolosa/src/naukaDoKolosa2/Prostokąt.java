package naukaDoKolosa2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Prostokąt extends Shape{
	
	private int x,y,x2,y2;

    public Prostokąt(Color color, int lineWidth) {
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
		 
		
		
		int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw= 50;
        int ph= 30;   
        
        // Ustawienie koloru i grubości linii
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(lineWidth));

        // Narysowanie prostokąta
        g2d.drawRect(px, py, pw, ph);
		
	}
	public void setColor(Color color) {
	    this.color = color;
	}
	public int setLineWidth(int lineWidth) {
		return this.lineWidth = lineWidth;
	}
	
}