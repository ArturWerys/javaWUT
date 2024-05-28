package naukaDoKolosa2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;


public class PanelGlowny extends JPanel {
    
	public static BufferedImage loadedImage;

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Shape currentShape;
	
	public PanelGlowny() {
		super();
		
		setBackground(Color.black);
			
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				((Prostokąt) currentShape).setEndPoint(e.getX(), e.getY());
				shapes.add(currentShape);
				currentShape = null;
				repaint();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				currentShape = new Prostokąt(MainClass.kolor, 10);
				((Prostokąt) currentShape).setStartPoint(e.getX(), e.getY());		
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
    	    
        if(loadedImage != null) {
        	g2d.drawImage(loadedImage,  0, 0, this);
        }
        if (currentShape != null) {
            currentShape.draw(g2d);
        }
        for (Shape shape: shapes) shape.draw(g2d);

        
        g2d.dispose();

    }
	
	public void clearDrawnShapes() {
		shapes.clear();
		repaint();
	}
}
