package naukaDoKolosa2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainClass extends JFrame {
	
	static Color kolor = Color.white;
	
    private BufferedImage loadedImage = null;
	
	PanelGlowny panelGlowny;
	JPanel panelDolny;
	
	JRadioButton red;
	JRadioButton green;
	JRadioButton blue;
    ButtonGroup g; 
    
    JButton wyczysc;
    JButton zapisz;
    JButton wczytaj;
    JButton animacja;

	
	MainClass(){
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		panelGlowny = new PanelGlowny();
		
		add(panelGlowny);
		
		/////
		
		panelDolny = new JPanel();
		add(panelDolny, BorderLayout.SOUTH);
		
		red = new JRadioButton("Czerwony");
		red.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kolor = Color.red;
				
			}
		});
		
		
		blue  = new JRadioButton("Niebieski");
		
		blue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kolor = Color.blue;
				
			}
		});
		
		green = new JRadioButton("Zielony");
		
		green.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kolor = Color.green;
				
			}
		});
		
		g = new ButtonGroup();
		
		g.add(red);
		g.add(green);
		g.add(blue);
		
		panelDolny.add(red);
		panelDolny.add(green);
		panelDolny.add(blue);

		wyczysc = new JButton("Wyczyść");
		wyczysc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					panelGlowny.clearDrawnShapes();
					repaint();
				}
				else {
		            JOptionPane.showMessageDialog(null, "Wybrano Nie.");
		        }
				
				
				
			}
		});
		
		
		zapisz = new JButton("Zapisz");
		
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
		                BufferedImage image = new BufferedImage(panelGlowny.getWidth(), panelGlowny.getHeight(), BufferedImage.TYPE_INT_ARGB);
		                Graphics2D g2d = image.createGraphics();
		                // Narysuj zawartość panelu na obrazku
		                panelGlowny.paintAll(g2d);
		                // Zapisz obrazek do wybranego pliku
		                ImageIO.write(image, "png", selectedFile);
		                System.out.println("Zapisano do pliku: " + selectedFile.getAbsolutePath());
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
				
			}
		});
		
		wczytaj = new JButton("Wczytaj");
		
		wczytaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Otwórz okno dialogowe do wyboru pliku
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            try {
		                // Wczytaj obrazek z wybranego pliku
		                loadedImage = ImageIO.read(selectedFile);
		                // Ustaw wczytany obrazek na panelu do rysowania
		                PanelGlowny.loadedImage = loadedImage;
		                // Odśwież panel
		                panelGlowny.repaint();
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
				
			}
		});
		
		
		animacja = new JButton("Animacja");
		
		panelDolny.add(wyczysc);
		panelDolny.add(zapisz);
		panelDolny.add(wczytaj); 
		panelDolny.add(animacja);

	}
 
	
	public static void main(String[] args) {
		MainClass okno = new MainClass();
		okno.setVisible(true);

	}
}
