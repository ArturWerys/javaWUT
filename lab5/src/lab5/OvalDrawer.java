package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OvalDrawer extends JFrame {
    private int startX, startY, endX, endY;
    private boolean dragging;

    public OvalDrawer() {
        setTitle("Oval Drawer");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (dragging) {
            int width = Math.abs(endX - startX);
            int height = Math.abs(endY - startY);
            int x = Math.min(startX, endX);
            int y = Math.min(startY, endY);
            g.drawOval(x, y, width, height);
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
            endX = startX;
            endY = startY;
            dragging = true;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endX = e.getX();
            endY = e.getY();
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            endX = e.getX();
            endY = e.getY();
            dragging = false;
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OvalDrawer ovalDrawer = new OvalDrawer();
            ovalDrawer.setVisible(true);
        });
    }
}
