package pl.edu.pw.fizyka.java.lab7.zadanie1;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThreeDynamicElements extends JFrame {

    private JButton b1;
    private JLabel l1;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> sc1;
    private ScheduledFuture<?> sc2;
    private ScheduledFuture<?> sc3;
    private JPanel p1;

    public static void main(String[] args) {
        ThreeDynamicElements frame = new ThreeDynamicElements();
        frame.setVisible(true);
        }

    public ThreeDynamicElements() throws HeadlessException {
        super();
        setSize(800, 600);
        
        p1 = new JPanel();
        p1.setSize(800,600);
        p1.setBackground(Color.BLUE);
        add(p1);
        
        b1 = new JButton("");
        p1.add(b1);
        
        l1 = new JLabel("");
        l1.setBackground(Color.white);
        p1.add(l1);

        scheduler = Executors.newScheduledThreadPool(2);

        sc1 = scheduler.scheduleAtFixedRate(new Runnable() {
            int i = 0;
            String[] buttonText = { "Animowany", "przycisk" };

            @Override
            public void run() {
                b1.setText(buttonText[i++ % buttonText.length]);
            }
        }, 0, 2, SECONDS);
        
        ////////
        
        sc2 = scheduler.scheduleAtFixedRate(new Runnable() {
			
        	 int i = 0;
             String[] labelText = {"Animowany","label"};
        	
			@Override
			public void run() {
				l1.setText(labelText[i++ % labelText.length]);
				
			}
		}, 0, 1, SECONDS);
       
        sc3 = scheduler.scheduleAtFixedRate(new Runnable() {
			
       	 int i = 0; 
         Color[] labelColor = { Color.white, Color.red };

			@Override
			public void run() {
				p1.setBackground(labelColor[i++ % labelColor.length]);
			}
		}, 0, 1, SECONDS);

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Koniec programu");
                sc1.cancel(true);
                scheduler.shutdown();
                System.exit(0);
            }
        }, 10, SECONDS);
    }
}
