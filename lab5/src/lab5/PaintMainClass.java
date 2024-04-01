package lab5;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class PaintMainClass extends JFrame {

    private ArrayList<Squares> squares = new ArrayList<>();
    
    private int lineWidth = 1;
    private Color lineColor = Color.black;

    private PaintingPanel paintingPanel;
    private JPanel optionsPanel;
    private JPanel sliderPanel;

    private JButton colorChanger;
    private LineWidthSlider lineWidthSlider;

    public PaintMainClass() {
        super("Paint Program");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        paintingPanel = new PaintingPanel();
        add(paintingPanel);

        paintingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                squares.add(new Squares(e.getX(), e.getY(), 100, 100, lineColor, lineWidth));
                paintingPanel.repaint();
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
                }
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
            
            
            for (Squares square : squares) {
                g2d.setColor(square.getColor());
                g2d.setStroke(new BasicStroke(square.getLineWidth()));
                g2d.drawRect(square.x, square.y, square.width, square.height);
            }

    }
}
}