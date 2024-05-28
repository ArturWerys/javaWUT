package przykladowyZeStrony;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class PanelRysowania extends JPanel {
    public static BufferedImage loadedImage;
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Shape currentShape;

    private int xCord;
    private int yCord;
    int xOdczyt;
    int yOdczyt;

    public PanelRysowania(PanelWsp panelWsp) {
        super();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentShape != null) {
                    ((ProstokątPrzyklad) currentShape).setEndPoint(e.getX(), e.getY());
                    shapes.add(currentShape);
                    currentShape = null;
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentShape = new ProstokątPrzyklad(getLineColor(), 3);
                ((ProstokątPrzyklad) currentShape).setStartPoint(e.getX(), e.getY());
                setxCord(e.getX());
                setyCord(e.getY());

                panelWsp.updateXCoordinateText(getxCord());
                panelWsp.updateYCoordinateText(getyCord());
            }

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {}
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setxCord(e.getX());
                setyCord(e.getY());

                panelWsp.updateXCoordinateText(getxCord());
                panelWsp.updateYCoordinateText(getyCord());
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }
    
    public void squareFromCordThread(PanelWsp panelWsp) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
             
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
                panelWsp.updateXCoordinateText(xOdczyt);
                panelWsp.updateYCoordinateText(yOdczyt);
                
                ProstokątPrzyklad currentShape = new ProstokątPrzyklad(getLineColor(), 3);
                ((ProstokątPrzyklad) currentShape).setStartPoint(xOdczyt, yOdczyt);
                
                if (currentShape != null) {
                    ((ProstokątPrzyklad) currentShape).setEndPoint(xOdczyt+50, yOdczyt+50);
                    shapes.add(currentShape);
                    currentShape = null;
                    repaint();
                }
                
            }
        });
        thread.start();
    }
    
    
    public void odczytZPlikuPrzezMenu() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] numbers = line.split(" ");

                    if (numbers.length == 2) {
                        try {
                            xOdczyt = Integer.parseInt(numbers[0]);
                            yOdczyt = Integer.parseInt(numbers[1]);
                            System.out.println("Liczba 1: " + xOdczyt + ", Liczba 2: " + yOdczyt);
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy format liczby w linii: " + line);
                        }
                    } else {
                        System.out.println("Nieprawidłowy format linii: " + line);
                    }
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku.");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (loadedImage != null) {
            g2d.drawImage(loadedImage, 0, 0, this);
        }
        if (currentShape != null) {
            currentShape.draw(g2d);
        }
        for (Shape shape : shapes) shape.draw(g2d);

        g2d.dispose();
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }
    
    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public void clearDrawnShapes() {
        shapes.clear();
        repaint();
    }
    
    public Color getLineColor() {
        return PrzykladowyMain.lineColor;
    }
}
