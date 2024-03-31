package lab5;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintMain extends JFrame {

    private int squareX;
    private int squareY;
    private int squareW=100;
    private int squareH=100;
    
    private int lineWidth=1;

	private PaitingPanel paintingPanel;
    private JPanel optionsPanel;
    private JPanel sliderPanel;
    
    private JButton colorChanger;
    private LineWidthSlider lineWidthSlider;
    
    
    private Color lineColor = Color.black;
    
    public PaintMain() {
        super("Paint Program");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        paintingPanel = new PaitingPanel();
        add(paintingPanel);

        paintingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                squareX = e.getX();
                squareY = e.getY();
                paintingPanel.repaint(); 
            }
        });
        
        optionsPanel = new JPanel();
        optionsPanel.setPreferredSize(new Dimension(100,900));
        optionsPanel.setBackground(Color.gray);
        add(optionsPanel, BorderLayout.WEST);
        
        colorChanger = new JButton("Kolor");
        optionsPanel.add(colorChanger);
        
        colorChanger.addActionListener(new ActionListener() {
			
        	@Override
			public void actionPerformed(ActionEvent e) {
				 Color newColor = JColorChooser.showDialog(null, "Wybierz kolor tła", lineColor);
			        if (newColor != null) {
			        	lineColor = newColor;
			            paintingPanel.repaint();
			        }
				
			}
		});
        
        sliderPanel = new JPanel();
        sliderPanel.setPreferredSize(new Dimension(900,50));
        add(sliderPanel, BorderLayout.NORTH);
        sliderPanel.setBackground(Color.white);
        
        lineWidthSlider = new LineWidthSlider();
        sliderPanel.add(lineWidthSlider);
        
        lineWidthSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				lineWidth = lineWidthSlider.getSliderValue();
				paintingPanel.repaint();

				
			}
		});
        
        setVisible(true);
    }
     
    public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

    public static void main(String[] args) {
        new PaintMain();
    }

    class PaitingPanel extends JPanel {
        
        public PaitingPanel() {
        	setLineWidth(lineWidth);
        }
        @Override
        protected void paintComponent(Graphics g) {
  
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g.create();
            
            BasicStroke bs1 = new BasicStroke(lineWidth);
            g2d.setStroke(bs1);
            
            g2d.setColor(lineColor);
            g2d.drawRect(squareX, squareY, squareW, squareH);
        }
     
    }
}
