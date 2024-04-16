package pl.edu.pw.fizyka.java.lab7.zadanie2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Prostokat{

    private int xPos = 50;
	private int yPos = 50;
    private int width = 20;
    private int height = 20;
    
    private Color color = Color.BLACK;
    
    private Image image = null;
    
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	private int vX;
    private int vY;
    
    public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}
	
	public int getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }


	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void paint(Graphics g){
//        g.setColor(getColor());
//        g.fillRect(xPos,yPos,getWidth(),getHeight());
        

        if (image != null) {
            g.drawImage(image, xPos, yPos, width, height, null);
        }

        else {
            g.setColor(color);
            g.fillRect(xPos, yPos, width, height);
        }
    }

	
	
}
