package lab5;

import java.awt.Color;
import java.awt.Rectangle;

public class Squares extends Rectangle{
    private Color color;
    private int lineWidth;

    public Squares(int x, int y, int width, int height, Color color, int lineWidth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.lineWidth = lineWidth;
    }

    public Color getColor() {
        return color;
    }

    public int getLineWidth() {
        return lineWidth;
    }
}
