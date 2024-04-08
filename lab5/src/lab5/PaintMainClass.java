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
    
    private int lineWidth = 1;
    private int squareLineWidth = 5;
    
    private Color lineColor = Color.black;
    private Color squareColor = Color.red;

    private PaintingPanel paintingPanel;
    private JPanel optionsPanel;
    private JPanel sliderPanel;

    private JButton colorChanger;
    private JButton sqaureChooser;
    private JButton rulerChooser;
    private JButton saveButton;
    
    private JButton lineChooser;
    
    private LineWidthSlider lineWidthSlider;
    
    private Line line = new Line(lineColor, lineWidth);
    private Square square = new Square(squareColor, squareLineWidth);
       
    int choosenShape = 2;

    public PaintMainClass() {
        super("Paint Program");
        setSize(1200, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        paintingPanel = new PaintingPanel();
        paintingPanel.setBackground(Color.white);
        add(paintingPanel);
        
        paintingPanel.addMouseMotionListener((new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				int x = e.getX();
				int y = e.getY();
				
				if (choosenShape ==1) {
					
					square.setEndPoint(x, y);
					repaint();
				}
				if (choosenShape == 2){
					
					line.addPoint(x, y);
					repaint();
				}
				
				
				
			}
		}));
        
      paintingPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				square.setEndPoint(e.getX(), e.getY());
				square = new Square(squareColor, squareLineWidth); 

			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (choosenShape == 1) {
					
//					square = new Square(squareColor, lineWidth); 
					
					square.setStartPoint(e.getX(),e.getY());
					shapes.add(square);
					
				}
				
				if (choosenShape == 2) {
					line = new Line(lineColor, lineWidth);
					shapes.add(line);
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
                	if (choosenShape == 1) {
                        square.setColor(newColor);

                	}
                	if(choosenShape == 2) {
                        lineColor = newColor;

                	}
                	else {
                		System.out.println("Nic sie nie zmienia");
                	}
                }
            }
        });
        
        sqaureChooser = new JButton("Kwadrat");
        optionsPanel.add(sqaureChooser);
        
        sqaureChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenShape = 1;
				
			}
		});
        
        lineChooser = new JButton("Linia");
        optionsPanel.add(lineChooser);
        
        lineChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenShape = 2;
			}
		});
        
        rulerChooser = new JButton("Gumka");
        optionsPanel.add(rulerChooser);
        
        rulerChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenShape = 2;
				lineWidth = 8;
				lineColor = Color.white;
			}
		});
        
        saveButton = new JButton("Zapisz do pliku");
        optionsPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Save to file
		        BufferedImage image = new BufferedImage(paintingPanel.getWidth(), paintingPanel.getHeight(),BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g2d = image.createGraphics();
		        paintingPanel.paintAll(g2d);
		        							
		        //Tworzenie pliku zapisu w folderze projektu
		        File outputfile = new File("saved.png");
		        							
		        //Zapis do pliku
		        try {
		        	ImageIO.write(image, "png", outputfile);
		        } catch (IOException ee) {
		        	System.out.println(ee.getMessage());
		        }
	        	System.out.println("Zapisalem do pliku");

				
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
            	
            	int lW = lineWidthSlider.getSliderValue();
            	
            	if (choosenShape == 1) {
            		squareLineWidth = square.setLineWidth(lW);

            	}
            	if(choosenShape == 2) {
                    lineWidth = lW;

            	}
            	else {
            		System.out.println("Nic sie nie zmienia");
            	}
            }
        });

        setVisible(true);
        
    }
    
    

    public static void main(String[] args) {
        new PaintMainClass();
    }

    class PaintingPanel extends JPanel {

        public PaintingPanel() {
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            
            g2d.setStroke(new BasicStroke(lineWidth));
     
            if (choosenShape == 1) {
                g2d.setColor(squareColor);
                for (Shape square : shapes) square.draw(g2d);
            } else if (choosenShape == 2) {
                g2d.setColor(lineColor);
                for (Shape line : shapes) line.draw(g2d);
            }
        }
        
           
    }
}