package lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Wielokaty extends JFrame implements ActionListener{

	JPanel panelLewy;
	JPanel panelSrodkowy;
	JPanel panelPrawy;
	JPanel panelGorny;
	JPanel panelDolny;
	
	boolean losowy = true;
	
	public Wielokaty() throws HeadlessException {
		
		super();
		
		setSize(1000, 800);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		
		// panele
		
		panelLewy = new JPanel();
		panelLewy.setLayout(new GridLayout(2, 1));
		
		add(panelLewy, BorderLayout.WEST);
		panelLewy.setBackground(Color.black);
		panelLewy.setPreferredSize(new Dimension(125,500));
		
		Slider slider = new Slider();
		
		panelSrodkowy = new DrawFunctions(slider);
		add(panelSrodkowy, BorderLayout.CENTER);
		panelSrodkowy.setPreferredSize(new Dimension(600,500));
		
		
		panelPrawy = new JPanel();
		panelPrawy.setBackground(Color.blue);
		add(panelPrawy, BorderLayout.EAST);
		panelPrawy.setPreferredSize(new Dimension(75,500));
		
		
		panelGorny = new JPanel();
		add(panelGorny, BorderLayout.NORTH);
		
		panelDolny = new JPanel();
		add(panelDolny, BorderLayout.SOUTH);
		panelDolny.setPreferredSize(new Dimension(600,75));
		panelDolny.setBackground(Color.red);

		
		
		// menu
		
		JMenuBar menuBar;
		JMenu menu;
		
		JMenuItem menuItem;

	    // Tworzenie paska menu
		menuBar = new JMenuBar();
	    
		//Dodawanie menu:
		menu = new JMenu("Menu glowne");
		menuBar.add(menu);

	
		menuItem = new JMenuItem("Autor: Artur Werys");
		menu.add(menuItem);
		
    	setJMenuBar(menuBar);
    	
    	menu.addSeparator();
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
			
		});
		menu.add(exit);
 	    
		panelGorny.add(slider.slider);
		
		JButton b = new JButton("Rysuj");
	
		panelGorny.add(b);
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(losowy) {
					((DrawFunctions) panelSrodkowy).RysowanieLosowe();
				}
				else {
					((DrawFunctions) panelSrodkowy).RysowanieWybranejWartosci();
				}
				
			}
		});
		
		
		JRadioButton r1 = new JRadioButton("Ręczny wybór");
		JRadioButton r2 = new JRadioButton("Losowy");
		
		r2.setSelected(true);
		
		// Group the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(r1);
		group.add(r2);
		
		r1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((DrawFunctions) panelSrodkowy).setLosowy(false);

				
			}
		});
		
		
		r2.addActionListener(new ActionListener() {
			// rzutowanie panelaSrodkowego na Rysowanie
			@Override
			public void actionPerformed(ActionEvent e) {
				((DrawFunctions) panelSrodkowy).setLosowy(true);

			}
		});
		
		
		panelLewy.add(r1);
		panelLewy.add(r2);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {

		Wielokaty frame = new Wielokaty();
		
		frame.setVisible(true);


	}

}
