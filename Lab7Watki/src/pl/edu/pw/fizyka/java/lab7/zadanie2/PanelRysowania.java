package pl.edu.pw.fizyka.java.lab7.zadanie2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class PanelRysowania extends JPanel {

    private static final long serialVersionUID = 1L;
    List<Prostokat> prostakaty = new ArrayList<Prostokat>();
    
    List<Image> obrazki = new ArrayList<Image>();
    
	public static Image car1 = null;
	public static Image car2 = null;
	public static Image car3 = null;
	public static Image car4 = null;
	public static Image car5 = null;

	

    public PanelRysowania() {

    }

    public void dodajLosowyProstokat() {
        Random r = new Random();

        Prostokat p = new Prostokat();
        p.setX(r.nextInt(550));
        p.setY(r.nextInt(550));
        p.setWidth(r.nextInt(80));
        p.setHeight(r.nextInt(80));
        p.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        
        p.setvX(r.nextInt(5));
        p.setvY(r.nextInt(5)); 


        prostakaty.add(p);
    }


    public void dodajProstokat(int x, int y, int width, int height, Color c){
    	Random r = new Random();
    	
        Prostokat p = new Prostokat();
        p.setX(x);
        p.setY(y);
        p.setWidth(width);
        p.setHeight(height);
        p.setColor(c);
        
        p.setvX(r.nextInt(5)); 
        p.setvY(r.nextInt(5)); 

        prostakaty.add(p); 
    }
    
    public void wczytajObrazki() {

        try {
            File imageFile1 = new File(Prostokat.class.getResource("auto1.png").getFile());
            File imageFile2 = new File(Prostokat.class.getResource("auto2.png").getFile());
            File imageFile3 = new File(Prostokat.class.getResource("auto3.png").getFile());
            File imageFile4 = new File(Prostokat.class.getResource("auto4.png").getFile());
            File imageFile5 = new File(Prostokat.class.getResource("auto5.png").getFile());
            
            car1 = ImageIO.read(imageFile1);
            car2 = ImageIO.read(imageFile2);
            car3 = ImageIO.read(imageFile3);
            car4 = ImageIO.read(imageFile4);
            car5 = ImageIO.read(imageFile5);
            
            obrazki.add(car1);
            obrazki.add(car2);
            obrazki.add(car3);
            obrazki.add(car4);
            obrazki.add(car5);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void podmienObrazek(int numer) {
        if (numer >= 0 && numer < obrazki.size()) { // Sprawdź, czy numer jest w zakresie listy
            // Podmień obrazek na wszystkich prostokątach z obrazkami
            for (Prostokat prostokat : prostakaty) {
                if (prostokat.getImage() != null) {
                    prostokat.setImage(obrazki.get(numer));
                }
            }
            repaint(); // Odśwież panel, aby zaktualizować obrazek
        } else {
            System.out.println("Nieprawidłowy numer obrazka.");
        }
    }
    
    
    
    public void dodajProstokatDoRysowania(int x, int y, int width, int height, Color c, Image i) {
        Random r = new Random();

        Prostokat p = new Prostokat();
        p.setX(x);
        p.setY(y);
        p.setWidth(width);
        p.setHeight(height);
        p.setColor(c);
        p.setImage(i);

        p.setvX(r.nextInt(100)); 
        p.setvY(r.nextInt(100)); 

        prostakaty.add(p); 
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Prostokat pr : prostakaty) {
            pr.paint(g); 
            
        }
    }

    public void przesunProstokaty() {
        for (Prostokat pr : prostakaty) {
            int newX = pr.getX() + pr.getvX();
            int newY = pr.getY() + pr.getvY();
            
            pr.setX(newX);
            pr.setY(newY);
        }
        repaint();
    }
    
    public void sprawdzKrawedz() {
    	for (Prostokat pr : prostakaty) {
    		
    		int xPos = pr.getX();
            int yPos = pr.getY();
            int vX = pr.getvX();
            int vY = pr.getvY();
            int width = pr.getWidth();
            int height = pr.getHeight();
            
            if(xPos <= 0 || xPos + width >= 850) {
                pr.setvX(-vX);
            }
            if(yPos <= 0 || yPos + height >= 850) {
                pr.setvY(-vY);
            }
    	}
    	repaint();
    }
    
  

    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}
