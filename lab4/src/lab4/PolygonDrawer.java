package lab4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PolygonDrawer extends JPanel {

    private int numberOfVertices = 3; // Początkowa liczba wierzchołków
    private int[] xPoints;
    private int[] yPoints;

    public PolygonDrawer() {
        setPreferredSize(new Dimension(400, 400));

        // Tworzenie suwaka do wyboru liczby wierzchołków
        JSlider verticesSlider = new JSlider(JSlider.HORIZONTAL, 3, 10, numberOfVertices);
        verticesSlider.setMajorTickSpacing(1);
        verticesSlider.setPaintTicks(true);
        verticesSlider.setSnapToTicks(true);

        verticesSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                numberOfVertices = verticesSlider.getValue();
                updatePolygon();
            }
        });

        // Utworzenie panelu dla suwaka
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(new JLabel("Vertices:"));
        sliderPanel.add(verticesSlider);

        // Dodanie paneli do głównego panelu
        setLayout(new BorderLayout());
        add(sliderPanel, BorderLayout.NORTH);
    }

    // Metoda do aktualizacji współrzędnych wielokąta i przerysowania panelu
    private void updatePolygon() {
        xPoints = new int[numberOfVertices];
        yPoints = new int[numberOfVertices];

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        for (int i = 0; i < numberOfVertices; i++) {
            double angle = 2 * Math.PI * i / numberOfVertices;
            xPoints[i] = (int) (centerX + radius * Math.cos(angle));
            yPoints[i] = (int) (centerY + radius * Math.sin(angle));
        }

        repaint();
    }

    // Metoda do rysowania wielokąta na panelu
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (xPoints != null && yPoints != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLUE);
            g2d.fillPolygon(xPoints, yPoints, numberOfVertices);
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(xPoints, yPoints, numberOfVertices);
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Polygon Drawer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new PolygonDrawer());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

