package lab5;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PaintMainClass extends JFrame {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
        
    public static Color lineColor = Color.black;
    public static int lineWidth = 1;

    private PaintingPanel paintingPanel;
    private JPanel optionsPanel;
    private JPanel sliderPanel;
    
    private JButton colorChanger;
    private JButton sqaureChooser;
    private JButton rulerChooser;
        
    private JButton cleanButton;
    
    private JButton lineChooser;
    
    private LineWidthSlider lineWidthSlider;
    
    private JMenuBar menuBar;
    private JMenu menu;
    
    private BufferedImage loadedImage = null;
       
    int choosenShape = 2;

    public PaintMainClass() {
    	
        super("Paint Program");
        setSize(1200, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        paintingPanel = new PaintingPanel();
        paintingPanel.setBackground(Color.white);
        add(paintingPanel);
        
        optionsPanel = new JPanel();
        optionsPanel.setPreferredSize(new Dimension(150, 900));
        optionsPanel.setBackground(Color.gray);
        add(optionsPanel, BorderLayout.WEST);

        colorChanger = new JButton("Kolor");
        optionsPanel.add(colorChanger);

        colorChanger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Wybierz kolor lini", lineColor);
                if (newColor != null) {             	
                        lineColor = newColor;
                }
            }
        });
        
        sqaureChooser = new JButton("Kwadrat");
        optionsPanel.add(sqaureChooser);
        
        sqaureChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenShape = paintingPanel.setChoosenShape(1);
				
			}
		});
        
        lineChooser = new JButton("Linia");
        optionsPanel.add(lineChooser);
        
        lineChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenShape = paintingPanel.setChoosenShape(2);
				System.out.println(choosenShape);
			}
		});
        
        
        rulerChooser = new JButton("Gumka");
        optionsPanel.add(rulerChooser);
        
        rulerChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenShape = paintingPanel.setChoosenShape(2);
				lineWidth = 8;
				lineColor = Color.white;
			}
		});
               
        cleanButton = new JButton("Wyczyść");
        optionsPanel.add(cleanButton);
        
        cleanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					paintingPanel.clearDrawnShapes();
					repaint();
				
			}
		});
        

        sliderPanel = new JPanel();
        sliderPanel.setBackground(Color.LIGHT_GRAY);
        sliderPanel.setPreferredSize(new Dimension(900, 50));
        add(sliderPanel, BorderLayout.NORTH);
       

        lineWidthSlider = new LineWidthSlider();
        sliderPanel.add(lineWidthSlider);

        lineWidthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {        	
            	lineWidth = lineWidthSlider.getSliderValue();	
            }
        });
        
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        
        JMenuItem author = new JMenuItem("Autor");
		author.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(author, "Artur Werys");
				
			}
		});
		menu.add(author);
		
		menu.addSeparator();
		
		JMenuItem saveToFile = new JMenuItem("Zapisz do pliku");
		
//		saveToFile.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// Save to file
//		        BufferedImage image = new BufferedImage(paintingPanel.getWidth(), paintingPanel.getHeight(),BufferedImage.TYPE_INT_ARGB);
//		        Graphics2D g2d = image.createGraphics();
//		        paintingPanel.paintAll(g2d);
//		        							
//		        //Tworzenie pliku zapisu w folderze projektu
//		        File outputfile = new File("saved.png");
//		        							
//		        //Zapis do pliku
//		        try {
//		        	ImageIO.write(image, "png", outputfile);
//		        } catch (IOException ee) {
//		        	System.out.println(ee.getMessage());
//		        }
//	        	System.out.println("Zapisalem do pliku");
//				
//			}
//		});
		
		saveToFile.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Otwórz okno dialogowe do wyboru miejsca zapisu i nazwy pliku
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showSaveDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            try {
		                // Utwórz obrazek o wymiarach panelu
		                BufferedImage image = new BufferedImage(paintingPanel.getWidth(), paintingPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		                Graphics2D g2d = image.createGraphics();
		                // Narysuj zawartość panelu na obrazku
		                paintingPanel.paintAll(g2d);
		                // Zapisz obrazek do wybranego pliku
		                ImageIO.write(image, "png", selectedFile);
		                System.out.println("Zapisano do pliku: " + selectedFile.getAbsolutePath());
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		menu.add(saveToFile);
		
		menu.addSeparator();

		
		JMenuItem loadFromFile = new JMenuItem("Wczytaj z pliku");
		
		// Zmodyfikuj ActionListener dla JMenuItem loadFromFile

		loadFromFile.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Otwórz okno dialogowe do wyboru pliku
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            try {
		                // Wczytaj obrazek z wybranego pliku
		                loadedImage = ImageIO.read(selectedFile);
		                // Ustaw wczytany obrazek na panelu do rysowania
		                PaintingPanel.loadedImage = loadedImage;
		                // Odśwież panel
		                paintingPanel.repaint();
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});



		menu.add(loadFromFile);
		
		menu.addSeparator();

		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		menu.add(exit);
		
        
        setJMenuBar(menuBar);

        setVisible(true);
        
    }

    public static void main(String[] args) {
        new PaintMainClass();
    }
    public void setLoadedImage(BufferedImage loadedImage) {
        this.loadedImage = loadedImage;
    }
    

}