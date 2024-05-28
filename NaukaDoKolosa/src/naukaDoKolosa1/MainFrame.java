package naukaDoKolosa1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPopupMenu.Separator;


public class MainFrame extends JFrame{

	JButton kolor;
	JButton losuj;
	JLabel etykieta;
	JPanel panelGorny;
	JPanel panelDolny;
	JScrollPane scrollPane;
	JTextArea textArea;
	Wykres wykres;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem zapisz;
	JMenuItem wyczysc;
	
	ArrayList<String> textDoEtykiety = new ArrayList<>();
	
	Integer counter=1;
	static Integer greenC= 0;
	static Integer redC= 0;
	static Integer blueC= 0;
	static Integer blackC= 0;


	
	Random random = new Random();
	
	static String wylosowanyKolor = "";

	
	
	MainFrame(){
		
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,900);
		
		setLayout(new BorderLayout());
		
		///
		
		
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		
		zapisz = new JMenuItem("Zapisz");
		
		zapisz.addActionListener(new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 wykres.saveChartToFile(MainFrame.this, wykres.getChart(), 800, 600);
		    }
		});
		
		menu.add(zapisz);
		
		menu.addSeparator();
		
		wyczysc = new JMenuItem("Wyczyść");
		
		wyczysc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
			        if (answer == JOptionPane.YES_OPTION) {
			        	wykres.dataset.clear();
						counter=1;
						greenC= 0;
						redC= 0;
						blackC= 0;
						
						textDoEtykiety.clear();
						StringBuilder sb = new StringBuilder();

						for (String text : textDoEtykiety) {
				            sb.append(text).append("\n");
				        }
				        textArea.setText(sb.toString());
						panelDolny.repaint();
						
						etykieta.setText("Jeszcze nic nie wylosowano");
			        } else {
			            JOptionPane.showMessageDialog(null, "Wybrano Nie.");
			        }

			}
		});
		
		menu.add(wyczysc);
		
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		///
		panelGorny = new JPanel();
		
		kolor = new JButton("Losuj Kolor");		
		kolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				double losowa = random.nextDouble();
				if (losowa < 0.5) { // Blue (50%)
					wylosowanyKolor = "Niebieski";
                    etykieta.setForeground(Color.blue);
                    blueC++;

                } else if (losowa < 0.8) { // Red (30%)
    				wylosowanyKolor = "Czerwony";
                    etykieta.setForeground(Color.red);
                    redC++;
                    
                } else if (losowa < 0.95) { // Green (15%)
    				wylosowanyKolor = "Zielony";
                    etykieta.setForeground(Color.green);
                    greenC++;
                    
                } else { // Black (5%)
    				wylosowanyKolor = "Czarny";
                    etykieta.setForeground(Color.black);
                    blackC++;

                }
				
				// Aktualizacja wykresu
                wykres.updateChart(greenC, redC, blueC, blackC);
				
				etykieta.setText(wylosowanyKolor);
				textDoEtykiety.add("Losowanie numer: " + counter + " wylosowano kolor: " + wylosowanyKolor );
				
				counter++;
		       
				StringBuilder sb = new StringBuilder();

				for (String text : textDoEtykiety) {
		            sb.append(text).append("\n");
		        }
		        textArea.setText(sb.toString());
				panelDolny.repaint();
				
			}
		});
		
		
		panelGorny.add(kolor);
		
		losuj = new JButton("Wykonaj 1000 losowań");	
		
		losuj.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Tworzymy wątek
		        Thread thread = new Thread(new Runnable() {
		            @Override
		            public void run() {
		                // Wykonujemy losowanie 1000 razy
		                for (int i = 0; i < 1000; i++) {
		                    try {
		                        // Losujemy kolor co 10 milisekund
		                        Thread.sleep(10);
		                    } catch (InterruptedException ex) {
		                        ex.printStackTrace();
		                    }
		                    // Tutaj dodaj kod losowania koloru, np. wywołując metodę actionPerformed z przycisku kolor
		                    kolor.doClick();
		                }
		            }
		        });
		        // Uruchamiamy wątek
		        thread.start();
		    }
		});

		
		panelGorny.add(losuj);
		
		etykieta = new JLabel("Jeszcze nic nie wylosowano");
		panelGorny.add(etykieta);
		
		add(panelGorny, BorderLayout.NORTH);
		
		panelDolny = new JPanel();
		
		panelDolny.setLayout(new GridLayout(1,2));
		
		textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        panelDolny.add(scrollPane);
		
		wykres = new Wykres();
		panelDolny.add(wykres.getChartPanel());

		add(panelDolny);
		
		
	}

	public static void main(String[] args) {
		MainFrame okno = new MainFrame();
		okno.setVisible(true);

	}

}
