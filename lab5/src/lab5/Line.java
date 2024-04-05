package lab5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Line extends Shape {

    private List<Integer> xList;  // Lista współrzędnych x 
    private List<Integer> yList;  // Lista współrzędnych y

    // Konstruktor
    public Line(Color color, int lineWidth) {
       super(color, lineWidth);
       System.out.println("Ustawilem kolor lini");
       xList = new ArrayList<Integer>();
       yList = new ArrayList<Integer>();
    }
  
    // Dodawanie punktów do linii
    public void addPoint(int x, int y) {
       xList.add(x);
       yList.add(y);
    }
  
    // Metoda pozwalająca linii na rysowanie siebie samej, jeżeli będzie miała dostęp do Graphics2D/Graphics
    public void draw(Graphics2D g2d) { 
    	
       for (int i = 0; i < xList.size() - 1; ++i) 
       {	
    	  g2d.setColor(color);
    	  g2d.setStroke(new BasicStroke(lineWidth));
          g2d.drawLine(xList.get(i), yList.get(i), xList.get(i + 1), yList.get(i + 1));
       }
    }

}
