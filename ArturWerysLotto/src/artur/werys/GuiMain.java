package artur.werys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.*;


public class GuiMain extends JFrame{
	
	JPanel panelGorny;
	JPanel panelDolnyPom;
	
	WykresLotto panelWykresu;
	
	JTextArea textArea;
	JScrollPane scrollPane;
	Przysik przycisk1;
	Przysik przycisk2;
	Przysik przycisk3;
	Przysik przycisk4;
	Przysik przycisk5;
	Przysik przycisk6;
	
	JButton pojLos;
	
	Random random = new Random();

	
	ArrayList<String> textDoEtykiety = new ArrayList<>();
    
	static Set<Integer> resultsFromButtons = new HashSet<>();

	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem losowan1000;
	JMenuItem podsumowanie;
	
	Integer counter = 0;
	Integer powtorkiCounter = 0;

	static int ile0;
	static int ile1;
	static int ile2;
	static int ile3;
	static int ile4;
	static int ile5;
	static int ile6;
	
	GuiMain(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,700);
		setLayout(new BorderLayout());

		
		panelGorny = new JPanel();
		add(panelGorny,BorderLayout.NORTH);
		
		
		przycisk1 = new Przysik(1);
		panelGorny.add(przycisk1);
		
		przycisk2 = new Przysik(2);
		panelGorny.add(przycisk2);
		
		przycisk3 = new Przysik(3);
		panelGorny.add(przycisk3);
		
		przycisk4 = new Przysik(4);
		panelGorny.add(przycisk4);
		
		przycisk5 = new Przysik(5);
		panelGorny.add(przycisk5);
		
		przycisk6 = new Przysik(6);
		panelGorny.add(przycisk6);
		
		pojLos = new JButton("Pojedyncze losowanie");
		panelGorny.add(pojLos);
		
		pojLos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				losowanie();
			}
		});
		
		
		panelDolnyPom = new JPanel();
		panelDolnyPom.setLayout(new GridLayout(1,2));
		panelDolnyPom.setBackground(Color.blue);
		add(panelDolnyPom);
		
		panelWykresu = new WykresLotto();
		panelDolnyPom.add(panelWykresu.getChartPanel());

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		panelDolnyPom.add(scrollPane);
		
		
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		
		losowan1000 = new JMenuItem("Wykonaj 1000 losowan");
		losowan1000.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				watek();
			}
		});
		
		menu.add(losowan1000);
		menu.addSeparator();
		
		podsumowanie = new JMenuItem("Podsumowanie");
		
		podsumowanie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
		        StringBuilder sb = new StringBuilder();

				textDoEtykiety.clear();
				repaint();
				
				textDoEtykiety.add("Liczba losowań: " + counter);
				textDoEtykiety.add("Liczba zer: " + ile0);
				textDoEtykiety.add("Liczba jedynek: " + ile1);
				textDoEtykiety.add("Liczba dwojek: " + ile2);
				textDoEtykiety.add("Liczba trojek: " + ile3);
				textDoEtykiety.add("Liczba czworek: " + ile4);
				textDoEtykiety.add("Liczba piatek: " + ile5);
				textDoEtykiety.add("Liczba szostek: " + ile6);


                for (String text : textDoEtykiety) {
					sb.append(text).append("\n");
				}
				textArea.setText(sb.toString());
				panelDolnyPom.repaint();

				
			}
		});
		
		menu.add(podsumowanie);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		
		
	}
	
	public void losowanie() {
		counter ++;

        int high = 49; 
        int low = 1;   
        StringBuilder sb = new StringBuilder();

        Set<Integer> results = new HashSet<>();
        Set<Integer> trafienia = new HashSet<>();


        while (results.size() < 6) {
            int result = random.nextInt(high - low) + low;
            if (!results.contains(result)) {
                results.add(result);		                
            }

        }
        
        
        Set<Integer> set = new HashSet<>();
        
        for (int element : results) {
            set.add(element);
        }

        for (int element : resultsFromButtons) {
            if (set.contains(element)) {
            	trafienia.add(element);
            	
                powtorkiCounter++;
            } 
        }
        
        
		textDoEtykiety.add("Losowanie numer: " + counter + " Liczba trafien: ," + powtorkiCounter +" Trafione: ," + trafienia.toString()+" Wylosowane liczby: ," + results.toString());
		
        
        for (String text : textDoEtykiety) {
			sb.append(text).append("\n");
		}
		textArea.setText(sb.toString());
		panelDolnyPom.repaint();
		trafienia.clear();
	
		if (powtorkiCounter == 0) {
			ile0++;
		}
		else if (powtorkiCounter == 1) {
			ile1++;
		}
		else if (powtorkiCounter == 2) {
			ile2++;
		}
		else if (powtorkiCounter == 3) {
			ile3++;
		}
		else if (powtorkiCounter == 4) {
			ile4++;
		}
		else if (powtorkiCounter == 5) {
			ile5++;
		}
		else if (powtorkiCounter == 6) {
			ile6++;
		}
		
		panelWykresu.addDataToDataset();
		
		powtorkiCounter = 0;
	
	}
	
	
	public void watek() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Wykonujemy losowanie 1000 razy
				for (int i = 0; i < 1000; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					losowanie();
					repaint();
				}
			}
		});
		thread.start();
	}

	public static void main(String[] args) {
		GuiMain okienko = new GuiMain();
		okienko.setVisible(true);
	}

}
