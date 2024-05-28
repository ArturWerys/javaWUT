package przykladowyZeStrony;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PrzykladowyMain extends JFrame {

    public static Color lineColor = Color.black;

    static PanelRysowania panelRysowania;

    public static PanelRysowania getPanelRysowania() {
        return panelRysowania;
    }

    JTextArea tekst;
    JPanel panelPom;
    PanelWsp panelWsp;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem zapisz;
    JMenuItem zamknij;
    JMenuItem zmienKolor;
    JMenuItem losuj;
    JMenuItem odczyt;

    static boolean losowy = false;

    public static boolean isLosowy() {
        return losowy;
    }

    public static void setLosowy(boolean losowy) {
        PrzykladowyMain.losowy = losowy;
    }

    PrzykladowyMain() {
        super();
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tworzenie panelWsp przed panelRysowania
        panelWsp = new PanelWsp(panelRysowania);

        // Przekazanie panelWsp do panelRysowania
        panelRysowania = new PanelRysowania(panelWsp);
        add(panelRysowania, BorderLayout.CENTER);

        panelPom = new JPanel();
        panelPom.setLayout(new GridLayout(2, 1));
        add(panelPom, BorderLayout.SOUTH);

        tekst = new JTextArea("Współrzędnę prostokąta 20 x 20: ");
        tekst.setBackground(Color.yellow);
        panelPom.add(tekst);

        panelPom.add(panelWsp);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");

        zapisz = new JMenuItem("Zapisz");

        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Otwórz okno dialogowe do wyboru miejsca zapisu i nazwy pliku
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Utwórz obrazek o wymiarach panelu
                        BufferedImage image = new BufferedImage(panelRysowania.getWidth(), panelRysowania.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = image.createGraphics();
                        // Narysuj zawartość panelu na obrazku
                        panelRysowania.paintAll(g2d);
                        // Zapisz obrazek do wybranego pliku
                        ImageIO.write(image, "png", selectedFile);
                        System.out.println("Zapisano do pliku: " + selectedFile.getAbsolutePath());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        menu.add(zapisz);
        menu.addSeparator();

        zamknij = new JMenuItem("Zamknij");
        zamknij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menu.add(zamknij);
        menu.addSeparator();

        zmienKolor = new JMenuItem("Zmień kolor");

        zmienKolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Wybierz kolor lini", lineColor);
                if (newColor != null) {
                    lineColor = newColor;
                }
            }
        });

        menu.add(zmienKolor);

        menu.addSeparator();

        losuj = new JMenuItem("Losuj 15 kwadratow");
        losuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLosowy(true);
                startRandomSquaresThread();
            }
        });

        menu.add(losuj);
        
        menu.addSeparator();
        
        odczyt = new JMenuItem("Odczyt współrzędnych");
        odczyt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelRysowania.odczytZPlikuPrzezMenu();
				panelRysowania.squareFromCordThread(panelWsp);
				
			}
		});

        menu.add(odczyt);
        
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void startRandomSquaresThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    ProstokątPrzyklad randomSquare = new ProstokątPrzyklad(lineColor, 3);
                    randomSquare.setRandomPosition();
                    panelRysowania.shapes.add(randomSquare);
                    System.out.println("Dodano kwadrat: " + (i + 1));
                    panelRysowania.repaint();
                }
                setLosowy(false); // This flag is no longer necessary for the drawing logic
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        PrzykladowyMain okno = new PrzykladowyMain();
        okno.setVisible(true);
    }
}
