package pl.edu.pw.fizyka.java.lab7.przyklady;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.HeadlessException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class UtilTimer  extends JFrame{
    Random rand = new Random();
    
    public UtilTimer() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton autoPress = new JButton();
        autoPress.setText("Zmieniam sam kolor!");
        add(autoPress);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {  //InvokeLater
                    @Override
                    public void run() {
                        autoPress.setBackground(new Color(rand.nextInt()));
                    }
                });
            }
        }, 250, 250 );
        setSize(200, 300);
    }
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				 UtilTimer noClick = new UtilTimer();
			        noClick.setVisible(true);
			}
		});
    	
       
    }
}