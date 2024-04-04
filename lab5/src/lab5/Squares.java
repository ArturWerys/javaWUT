//package lab5;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//
//public class Squares extends Shape{
//    private Color color;
//    private int lineWidth;
//	private int x;
//	private int y;
//	private int width;
//	private int height;
//
//    public Squares(int x, int y, int width, int height, Color color, int lineWidth) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//        this.color = color;
//        this.lineWidth = lineWidth;
//    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    public int getLineWidth() {
//        return lineWidth;
//    }
//
//	@Override
//	public void draw(Graphics2D g2d) {
//		// TODO Auto-generated method stub
//		 g2d.setColor(color); 
//		 g2d.setStroke(new BasicStroke(lineWidth)); 
//		    
//		 g2d.drawRect(x, y, width, height); 
//		
//	}
//
//	public int getX() {
//		return x;
//	}
//
//	public int setWidth(int width) {
//		return this.width = width;
//	}
//	
//	public int getY() {
//		return y;
//	}
//
//	public int setHeight(int height) {
//		return this.height = height;
//	}
//	
//}
