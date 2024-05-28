package przykladowyZeStrony;

import java.awt.Color;
import java.awt.Graphics2D;

abstract public class Shape {

	public Color color;
	public int lineWidth;
	
	public Shape(Color color, int lineWidth) {

		this.lineWidth = lineWidth;
		this.color = color;

	}

	abstract public void draw(Graphics2D g2d);


}
