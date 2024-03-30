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

    int[] x = new int[33]; 
    int[] y = new int[33];
    
    int wierzcholkiLosowe = 0;
    
    boolean losowy = true;
    
    public boolean isLosowy() {
		return losowy;
	}

	public void setLosowy(boolean losowy) {
		this.losowy = losowy;
	}

	Color color = Color.blue;
    int gruboscLini = 5;
    
    Random r = new Random();
    Random r2 = new Random();    
    Slider s;
    
    int R = 100;

    public DrawFunctions(Slider slider) {
    	this.s = slider;
    }
    
    public void RysowanieWybranejWartosci() {

    	int iloscW = s.getSliderValue();
    	
    	x = new int[iloscW];
        y = new int[iloscW];

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        for (int i = 0; i < iloscW; i++) {
            double angle = 2 * Math.PI * i / iloscW;
            x[i] = (int) (centerX + radius * Math.cos(angle));
            y[i] = (int) (centerY + radius * Math.sin(angle));
        }

        repaint();
    }


    
    public void RysowanieLosowe() {
        wierzcholkiLosowe = r2.nextInt(31);
        x = new int[wierzcholkiLosowe]; 
        y = new int[wierzcholkiLosowe]; 
        
        for (int i = 0; i < wierzcholkiLosowe; i++) {
            x[i] = r.nextInt(RECT_WIDTH);
            y[i] = r.nextInt(RECT_HEIGHT);
        }
        repaint(); 
    }

    public int getIloscWierzcholkow() {
        return wierzcholkiLosowe;
    }

    public void setIloscWierzcholkow(int iloscWierzcholkow) {
        this.wierzcholkiLosowe = iloscWierzcholkow;

    }

    @Override
    protected void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
        Graphics2D g2d = (Graphics2D) g.create();
        
        isLosowy();

        System.out.println(losowy);
    	 if(losowy) {
    		 g2d.drawPolygon(x, y, wierzcholkiLosowe);
    		 g2d.dispose();
    	 }
    	 if(losowy == false) {
    		 if (x != null && y != null) {
                 
                 g2d.setColor(Color.BLACK);
                 g2d.drawPolygon(x, y, s.sliderValue);
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
