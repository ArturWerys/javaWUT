package lab4;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.*;

public class DrawFunctions extends JPanel {
    private static final int RECT_X = 400;
    private static final int RECT_Y = RECT_X;
    private static final int RECT_WIDTH = 400;
    private static final int RECT_HEIGHT = RECT_WIDTH;

    int[] xRegular = new int[33];
    int[] yRegular = new int[33];
    
    int[] xRandom = new int[33];
    int[] yRandom = new int[33];
    
    int wierzcholkiLosowe = 0;
    
    boolean losowy = true;
    
	Color lineColor = Color.blue;
	
	int lineWidth = 1;
	
	Random r = new Random();
    Random r2 = new Random();    
    Slider slider;
    
    int R = 100;
    
    
	// Getters and Setters
    
    
    public int[] getxRegular() {
		return xRegular;
	}
    
    public int[] getyRegular() {
		return yRegular;
	}
    
    public int[] getyRandom() {
		return yRandom;
	}

	public int[] getxRandom() {
		return xRandom;
	}
    
    public boolean isLosowy() {
		return losowy;
	}

	public void setLosowy(boolean losowy) {
		this.losowy = losowy;
	}
    
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

    public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

    public DrawFunctions(Slider slider) {
    	this.slider = slider;
    }
    
    
    
    public void RysowanieWybranejWartosci() {

    	int iloscW = slider.getSliderValue();
    	
    	xRegular = new int[iloscW];
        yRegular = new int[iloscW];

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        for (int i = 0; i < iloscW; i++) {
            double angle = 2 * Math.PI * i / iloscW;
            xRegular[i] = (int) (centerX + radius * Math.cos(angle));
            yRegular[i] = (int) (centerY + radius * Math.sin(angle));
        }

        repaint();
    }


    
    public void RysowanieLosowe() {
        wierzcholkiLosowe = r2.nextInt(31);
        
        xRandom = new int[wierzcholkiLosowe]; 
        yRandom = new int[wierzcholkiLosowe]; 
        
        for (int i = 0; i < wierzcholkiLosowe; i++) {
            xRandom[i] = r.nextInt(RECT_WIDTH);
            yRandom[i] = r.nextInt(RECT_HEIGHT);
            
        }
        repaint(); 
    }



    @Override
    protected void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
        Graphics2D g2d = (Graphics2D) g.create();
        
        isLosowy();
        getLineWidth();
        
        BasicStroke bs1 = new BasicStroke(lineWidth);
        g2d.setStroke(bs1);
        g2d.setColor(getLineColor());


    	 if(losowy) {
    		 g2d.drawPolygon(xRandom, yRandom, wierzcholkiLosowe);
    		 g2d.dispose();
    	 }
    	 else {
    		 if (xRegular != null && yRegular != null) {
    			 
                 g2d.drawPolygon(xRegular, yRegular, slider.sliderValue);
                 g2d.dispose();
                 
             }
    	 }
    	    
    }
    
    @Override
    public Dimension getPreferredSize() {

    	// Zmniejsz obszar o określoną wartość, aby pozostawić margines
        int margin = 50;
        return new Dimension(RECT_WIDTH + 2 * RECT_X - margin, RECT_HEIGHT + 2 * RECT_Y - margin);
    }
}
