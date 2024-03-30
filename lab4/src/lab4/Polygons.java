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
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Polygons extends JFrame implements ActionListener{

	JPanel westPanel;
	DrawFunctions middlePanel;
	JPanel eastPanel;
	JPanel northPanel;
	JPanel southPanel;
	
	JButton bgColorButton;
	
	JButton lineColorButton;
	
	Color bgColor = Color.white;
		
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}


	public Polygons() throws HeadlessException {
		
		super();
		
		setSize(1000, 800);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		
		// panele
		
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(2, 1));
		
		add(westPanel, BorderLayout.WEST);
		westPanel.setBackground(Color.black);
		westPanel.setPreferredSize(new Dimension(125,500));
		
		Slider slider = new Slider();
		
		middlePanel = new DrawFunctions(slider);
		add(middlePanel, BorderLayout.CENTER);
		middlePanel.setPreferredSize(new Dimension(600,500));
		middlePanel.setBackground(bgColor);
		
		
		eastPanel = new CordinatesPanel(middlePanel);
		
		add(eastPanel, BorderLayout.EAST);
		eastPanel.setPreferredSize(new Dimension(125,500));
		
		
		northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		
		southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setPreferredSize(new Dimension(600,75));
		southPanel.setBackground(Color.GRAY);
		
		bgColorButton = new JButton("Kolor tła");
		bgColorButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Color newColor = JColorChooser.showDialog(null, "Wybierz kolor tła", middlePanel.getBackground());
		        if (newColor != null) {
		            middlePanel.setBackground(newColor);
		            middlePanel.repaint();
		        }
		    }
		});
		
		southPanel.add(bgColorButton);

		lineColorButton = new JButton("Kolor lini");
		lineColorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Color newColor = JColorChooser.showDialog(null, "Wybierz kolor tła", middlePanel.getBackground());
			        if (newColor != null) {
			            middlePanel.setLineColor(newColor);
			            middlePanel.repaint();
			        }
				
			}
		});
		
		southPanel.add(lineColorButton);
		
		
		// menu
		
		JMenuBar menuBar;
		JMenu menu;
		
		JMenuItem menuItem;

	    // Tworzenie paska menu
		menuBar = new JMenuBar();
	    
		//Dodawanie menu:
		menu = new JMenu("Menu glowne");
		menuBar.add(menu);
		
		menu.addSeparator();

		JMenuItem linia5 = new JMenuItem("Linia 5");
		linia5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((DrawFunctions) middlePanel).setLineWidth(5);
			}
		});
		
		menu.add(linia5);
		
		JMenuItem linia7 = new JMenuItem("Linia 7");
		linia7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((DrawFunctions) middlePanel).setLineWidth(7);
				
			}
		});
		
		menu.add(linia7);

	
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
 	    
		northPanel.add(slider.slider);
		
		JButton b = new JButton("Rysuj");
	
		northPanel.add(b);
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((DrawFunctions) middlePanel).isLosowy()) {
					((DrawFunctions) middlePanel).RysowanieLosowe();
				}
				else {
					((DrawFunctions) middlePanel).RysowanieWybranejWartosci();
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
				((DrawFunctions) middlePanel).setLosowy(false);

				
			}
		});
		
		
		r2.addActionListener(new ActionListener() {
			// rzutowanie panelaSrodkowego na Rysowanie
			@Override
			public void actionPerformed(ActionEvent e) {
				((DrawFunctions) middlePanel).setLosowy(true);

			}
		});
		
		westPanel.add(r1);
		westPanel.add(r2);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {

		Polygons frame = new Polygons();
		
		frame.setVisible(true);


	}

}
