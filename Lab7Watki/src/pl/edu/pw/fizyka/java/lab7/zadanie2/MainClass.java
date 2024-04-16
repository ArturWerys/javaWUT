package pl.edu.pw.fizyka.java.lab7.zadanie2;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainClass {
    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Prostokaty");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                final PanelRysowania panel = new PanelRysowania();

                panel.dodajProstokat(100, 100, 80, 160, Color.BLUE);
                panel.wczytajObrazki();

                panel.dodajProstokatDoRysowania(150, 150, 150, 150, Color.cyan, PanelRysowania.car1);

                for (int i = 1; i < 20; i++) {

                    panel.dodajLosowyProstokat();
                }

                f.add(panel);
                f.setSize(900, 900);
                f.setResizable(false);
                f.setVisible(true);

             // Runnable to move rectangles
                Runnable moveRectangles = new Runnable() {
                    @Override
                    public void run() {
                        int numberOfImages = panel.obrazki.size();
                        if (numberOfImages > 0) {
                            int currentIndex = 0;
                            while (true) {
                                panel.podmienObrazek(currentIndex); // Podmień obrazek na panelu
                                panel.przesunProstokaty(); // Przesuń prostokąty
                                panel.sprawdzKrawedz(); // Sprawdź krawędzie
                                panel.repaint(); // Odrysuj panel

                                currentIndex = (currentIndex + 1) % numberOfImages; // Przejdź do kolejnego obrazka
                                try {
                                    Thread.sleep(10); // Poczekaj 1 sekundę
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                };

                // Uruchom pętlę w wątku
                new Thread(moveRectangles).start();


                // Schedule shutdown after 10 seconds
                scheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        scheduler.shutdown();
                        f.setVisible(false);
                        f.dispose();
                        System.exit(0);
                    }
                }, 10, SECONDS);
            }
        });
    }
}
