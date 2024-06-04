package artur.werys;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Martyna extends JFrame{

	JPanel panelZDanymi;
	ChartPanel panelWykres;
	JPanel panelGora;
	JPanel panelDwa;
	JPanel wszystko;
	JButton pierwsza;
	JButton druga;
	JButton trzecia;
	JButton czwarta;
	JButton piata;
	JButton szosta;
	JButton losowanie;
	
	JTextArea poleWypisaneDane;
	String tekst = "";
	
	int ileNic;
	int ile1;
	int ile2;
	int ile3;
	int ile4;
	int ile5;
	int ile6;
	
	int ile0c;
	int ile1c;
	int ile2c;
	int ile3c;
	int ile4c;
	int ile5c;
	int ile6c;
	
	int nrLosowania = 1;
	int ileWylosowano = 0;
	
	DefaultCategoryDataset dataset;
	
	Random rand;
	int[] wybrane = new int[6];
	int[] wylosowane = new int[6];
	//int[] trafione = new int[];
	List<Integer> trafione = new ArrayList<>();
	
	JMenuBar pasekMenu;
	JMenu menu;
	JMenuItem wykonaj1000;
	JMenuItem podsumowanie;
	
	Martyna() {
		this.setSize(900, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		rand = new Random();
		
		menu = new JMenu("Menu");
		wykonaj1000 = new JMenuItem("Wykonaj 1000 losowań");
		menu.add(wykonaj1000);
		podsumowanie = new JMenuItem("Podsumowanie");
		podsumowanie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tekst += "\n\nPodsumowanie";
				tekst += String.valueOf(nrLosowania);
				tekst += " losowań: \n Liczba trafionych 6:";
				tekst += String.valueOf(ile6c);
				tekst += "\n Liczba trafionych 5: ";
				tekst += String.valueOf(ile5c);
				tekst += "\n Liczba trafionych 4: ";
				tekst += String.valueOf(ile4c);
				tekst += "\n Liczba trafionych 3: ";
				tekst += String.valueOf(ile3c);
				tekst += "\n Liczba trafionych 2: ";
				tekst += String.valueOf(ile2c);
				tekst += "\n Liczba trafionych 1: ";
				tekst += String.valueOf(ile1c);
				tekst += "\n Liczba trafionych 0: ";
				tekst += String.valueOf(ile0c);
				
				poleWypisaneDane.setText(tekst);
			}
		});
		menu.add(podsumowanie);
		pasekMenu = new JMenuBar();
		pasekMenu.add(menu);
		
		panelZDanymi = new JPanel();
		panelGora = new JPanel();
		panelDwa = new JPanel();
		wszystko = new JPanel();
		
		dataset = new DefaultCategoryDataset();
		dataset.setValue(0, "Seria1", "brak trafień");
		dataset.setValue(0, "Seria1", "jedynka");
		dataset.setValue(0, "Seria1", "dwójka");
		dataset.setValue(0, "Seria1", "trójka");
		dataset.setValue(0, "Seria1", "czwórka");
		dataset.setValue(0, "Seria1", "piątka");
		dataset.setValue(0, "Seria1", "szóstka");
		
		JFreeChart chart = ChartFactory.createBarChart(
				"Histogram trafień",//Tytul
				"Liczba trafień", // opisy osi
				"Ilość losowań", 
				dataset, // Dane 
				PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
				false, // legenda
				true, // tooltips
				false
			);
		panelWykres = new ChartPanel(chart);
		
		wszystko.setLayout(new BorderLayout());
		wszystko.add(BorderLayout.NORTH, panelGora);
		wszystko.add(BorderLayout.CENTER, panelDwa);
		panelDwa.setLayout(new GridLayout(1,2));
		panelDwa.add(panelWykres);
		panelDwa.add(new JScrollPane(panelZDanymi));
		
		pierwsza = new JButton("??");
		pierwsza.setPreferredSize(new Dimension(80,80));
		pierwsza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = JOptionPane.showInputDialog("Podaj liczbę");
				wybrane[0] = Integer.parseInt(l);
				if(wybrane[0] == wybrane[1] || wybrane[0] == wybrane[2] || wybrane[0] == wybrane[3] || wybrane[0] == wybrane[4] || wybrane[0] == wybrane[5]) {
					JOptionPane.showMessageDialog(null, "ta liczba została już wybrana");
				} else if(wybrane[0] > 50 || wybrane[0] < 1) {
					JOptionPane.showMessageDialog(null, "Liczba spoza zakresu");
				} else if(wybrane[0] == 0) {
					//tu bedzie warunek na tekst ale jeszcze nie wiem jak
				} else {
					pierwsza.setText(l);
				}
			}
		});
		druga = new JButton("??");
		druga.setPreferredSize(new Dimension(80,80));
		druga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = JOptionPane.showInputDialog("Podaj liczbę");
				wybrane[1] = Integer.parseInt(l);
				if(wybrane[1] == wybrane[0] || wybrane[1] == wybrane[2] || wybrane[1] == wybrane[3] || wybrane[1] == wybrane[4] || wybrane[1] == wybrane[5]) {
					JOptionPane.showMessageDialog(null, "ta liczba została już wybrana");
				} else if(wybrane[1] > 50 || wybrane[1] < 1) {
					JOptionPane.showMessageDialog(null, "Liczba spoza zakresu");
				} else if(wybrane[1] == 0) {
					//tu bedzie warunek na tekst ale jeszcze nie wiem jak
				} else {
					druga.setText(l);
				}
			}
		});
		trzecia = new JButton("??");
		trzecia.setPreferredSize(new Dimension(80,80));
		trzecia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = JOptionPane.showInputDialog("Podaj liczbę");
				wybrane[2] = Integer.parseInt(l);
				if(wybrane[2] == wybrane[0] || wybrane[2] == wybrane[1] || wybrane[2] == wybrane[3] || wybrane[2] == wybrane[4] || wybrane[2] == wybrane[5]) {
					JOptionPane.showMessageDialog(null, "ta liczba została już wybrana");
				} else if(wybrane[2] > 50 || wybrane[2] < 1) {
					JOptionPane.showMessageDialog(null, "Liczba spoza zakresu");
				} else if(wybrane[2] == 0) {
					//tu bedzie warunek na tekst ale jeszcze nie wiem jak
				} else {
					trzecia.setText(l);
				}
			}
		});
		czwarta = new JButton("??");
		czwarta.setPreferredSize(new Dimension(80,80));
		czwarta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = JOptionPane.showInputDialog("Podaj liczbę");
				wybrane[3] = Integer.parseInt(l);
				if(wybrane[3] == wybrane[0] || wybrane[3] == wybrane[1] || wybrane[3] == wybrane[2] || wybrane[3] == wybrane[4] || wybrane[3] == wybrane[5]) {
					JOptionPane.showMessageDialog(null, "ta liczba została już wybrana");
				} else if(wybrane[3] > 50 || wybrane[3] < 1) {
					JOptionPane.showMessageDialog(null, "Liczba spoza zakresu");
				} else if(wybrane[3] == 0) {
					//tu bedzie warunek na tekst ale jeszcze nie wiem jak
				} else {
					czwarta.setText(l);
				}
			}
		});
		piata = new JButton("??");
		piata.setPreferredSize(new Dimension(80,80));
		piata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = JOptionPane.showInputDialog("Podaj liczbę");
				wybrane[4] = Integer.parseInt(l);
				if(wybrane[4] == wybrane[0] || wybrane[4] == wybrane[1] || wybrane[4] == wybrane[2] || wybrane[4] == wybrane[3] || wybrane[4] == wybrane[5]) {
					JOptionPane.showMessageDialog(null, "ta liczba została już wybrana");
				} else if(wybrane[4] > 50 || wybrane[4] < 1) {
					JOptionPane.showMessageDialog(null, "Liczba spoza zakresu");
				} else if(wybrane[4] == 0) {
					//tu bedzie warunek na tekst ale jeszcze nie wiem jak
				} else {
					piata.setText(l);
				}
			}
		});
		szosta = new JButton("??");
		szosta.setPreferredSize(new Dimension(80,80));
		szosta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = JOptionPane.showInputDialog("Podaj liczbę");
				wybrane[5] = Integer.parseInt(l);
				if(wybrane[5] == wybrane[0] || wybrane[5] == wybrane[1] || wybrane[5] == wybrane[2] || wybrane[5] == wybrane[3] || wybrane[5] == wybrane[4]) {
					JOptionPane.showMessageDialog(null, "ta liczba została już wybrana");
				} else if(wybrane[5] > 50 || wybrane[5] < 1) {
					JOptionPane.showMessageDialog(null, "Liczba spoza zakresu");
				} else if(wybrane[5] == 0) {
					//tu bedzie warunek na tekst ale jeszcze nie wiem jak
				} else {
					szosta.setText(l);
				}
			}
		});
		losowanie = new JButton("Pojedyncze losowanie");
		losowanie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//losowanie 6 loiczba
				ileWylosowano = 0;
				for(int i = 0; i < 6; i++) {
					wylosowane[i] = rand.nextInt(49) + 1;
				}
				tekst += "Losowanie nr: ";
				tekst += String.valueOf(nrLosowania);
				tekst += "wylosowane liczby: ";
				tekst += Arrays.toString(wylosowane);
				
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 6; j++) {
						if(wybrane[i] == wylosowane[j]) {
							ileWylosowano++;
							trafione.add(wybrane[i]);
						}
					}
				}
				
				if(ileWylosowano == 0) {
					tekst += "\n Brak trafień";
					ileNic++;
					ile0c++;
				} else {
					tekst += "\n Liczba trafień: ";
					tekst += String.valueOf(ileWylosowano);
					tekst += " Trafione liczby: ";
					String doWypisania = trafione.toString();
					tekst += doWypisania;
					tekst += "\n";
				}
				
				if(ileWylosowano == 1) {ile1++; ile1c++;}
				if(ileWylosowano == 2) {ile2++; ile2c++;}
				if(ileWylosowano == 3) {ile3++; ile3c++;}
				if(ileWylosowano == 4) {ile4++; ile4c++;}
				if(ileWylosowano == 5) {ile5++; ile5c++;}
				if(ileWylosowano == 6) {ile6++; ile6c++;}
				
				aktualizujWykres(ileNic, ile1, ile2, ile3, ile4, ile5, ile6);
				nrLosowania++;
				poleWypisaneDane.setText(tekst);
			}
		});
		
		panelGora.add(pierwsza);
		panelGora.add(druga);
		panelGora.add(trzecia);
		panelGora.add(czwarta);
		panelGora.add(piata);
		panelGora.add(szosta);
		panelGora.add(losowanie);
		
		poleWypisaneDane = new JTextArea();
		panelZDanymi.add(poleWypisaneDane);
		
		this.add(wszystko);
		this.setJMenuBar(pasekMenu);
		
	}
	
	void aktualizujWykres(int il0, int il1, int il2, int il3, int il4, int il5, int il6) {
		dataset.setValue(il0, "Seria1", "brak trafień");
		dataset.setValue(il1, "Seria1", "jedynka");
		dataset.setValue(il2, "Seria1", "dwójka");
		dataset.setValue(il3, "Seria1", "trójka");
		dataset.setValue(il4, "Seria1", "czwórka");
		dataset.setValue(il5, "Seria1", "piątka");
		dataset.setValue(il6, "Seria1", "szóstka");
	}
	
	public static void main(String[] args) {
		Martyna okno = new Martyna();
		okno.setVisible(true);
	}

}
