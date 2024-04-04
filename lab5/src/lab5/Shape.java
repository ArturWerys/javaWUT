package lab5;

import java.awt.Color;
import java.awt.Graphics2D;

abstract public class Shape {

	public Color color;
	
	public Shape(Color color) {
		// TODO Auto-generated constructor stub
        this.color = color;

	}

	abstract public void draw(Graphics2D g2d);


}
