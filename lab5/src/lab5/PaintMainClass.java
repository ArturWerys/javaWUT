package lab5;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class PaintMainClass extends JFrame {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    
    private int lineWidth = 1;
    private Color lineColor = Color.black;

    private PaintingPanel paintingPanel;
    private JPanel optionsPanel;
    private JPanel sliderPanel;

    private JButton colorChanger;
    private JButton sqaureChooser;
    
    private JButton lineChooser;
    
    private LineWidthSlider lineWidthSlider;
    
    private Line line = new Line(lineColor);
    
//    private Squares currentSquare;
    
    
    int choosenShape = 0;

    public PaintMainClass() {
        super("Paint Program");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        paintingPanel = new PaintingPanel();
        add(paintingPanel);

//        paintingPanel.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
////                squares.add(new Squares(e.getX(), e.getY(), 100, 100, lineColor, lineWidth));
//                paintingPanel.repaint();
//            }
//        });
        
        paintingPanel.addMouseMotionListener((new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				
				if (choosenShape == 2){
					
					line.addPoint(x, y);
					repaint();
				}
//				if (choosenShape ==1) {
//					
//				 currentSquare.setWidth(100); 
//				 currentSquare.setHeight(100); 
//				 repaint();
//				}
			}
		}));
        
      paintingPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				
//				if(choosenShape == 1) {
//					shapes.add(currentSquare);
//				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if (choosenShape ==2 ) {
					line = new Line(lineColor);
					System.out.println("rysuje linie w wybranym kolorze");
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
        optionsPanel.setPreferredSize(new Dimension(100, 900));
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
                    System.out.println("Action listener - Nowy kolor wybrany");
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
        

        sliderPanel = new JPanel();
        sliderPanel.setPreferredSize(new Dimension(900, 50));
        add(sliderPanel, BorderLayout.NORTH);
        sliderPanel.setBackground(Color.white);

        lineWidthSlider = new LineWidthSlider();
        sliderPanel.add(lineWidthSlider);

        lineWidthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lineWidth = lineWidthSlider.getSliderValue();
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
            
//            g2d.setColor(lineColor);
            
            if(choosenShape == 1) {
                 g2d.setStroke(new BasicStroke(lineWidth));
//            	 for (Shape square: shapes) square.draw(g2d);

            }
            else {
                     g2d.setStroke(new BasicStroke(lineWidth));
                     g2d.setColor(lineColor);

                     for (Shape line: shapes) line.draw(g2d);
                 }
            	
            }

           
    }
}