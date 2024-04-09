package lab5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

class PaintingPanel extends JPanel {

    public static BufferedImage loadedImage;
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    
    private Shape currentShape;
        
    private int choosenShape =2;
    

	public PaintingPanel() {
		super();
		
	        
	addMouseMotionListener((new MouseMotionListener() {
			
		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			int x = e.getX();
			int y = e.getY();
			
			if (choosenShape ==1) {
				
				((Square) currentShape).setEndPoint(e.getX(), e.getY());
				repaint();
			}
			else{
				
				((Line) currentShape).addPoint(e.getX(), e.getY());
				repaint();
			}
			
		}
	}));
    
      addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					shapes.add(currentShape);
					currentShape = null;
					repaint();

				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					
					if (choosenShape == 1) {
											
						currentShape = new Square(getLineColor(), getLineWidth());
						((Square) currentShape).setStartPoint(e.getX(), e.getY());				
					}
					
					else {
						currentShape = new Line(getLineColor(), getLineWidth());
						((Line) currentShape).addPoint(e.getX(), e.getY());
					}
					
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
	
	public void clearDrawnShapes() {
		shapes.clear();
		repaint();
	}

	public Color getLineColor() {
		return PaintMainClass.lineColor;
	}

	public int getLineWidth() {
		return PaintMainClass.lineWidth;
	}
	
	public int setChoosenShape(int choosenShape) {
		return this.choosenShape = choosenShape;
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
}
