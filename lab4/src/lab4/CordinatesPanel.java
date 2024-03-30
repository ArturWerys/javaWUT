package lab4;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CordinatesPanel extends JPanel {
    
    DrawFunctions draw;
    
    JLabel label;
    
    JButton button;
    
    int[] xRandom;
    int[] yRandom;
    
    int[] xRegular;
    int[] yRegular;
    
    String text;
    
    
    public CordinatesPanel(DrawFunctions draw) throws HeadlessException {
        super();
        
        this.draw = draw;
        
        label = new JLabel(); 
        
        button = new JButton("Współrzędne");
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(draw.isLosowy()) {
                    xRandom = draw.getxRandom();
                    yRandom = draw.getyRandom();
                    StringBuilder coordinatesText = new StringBuilder(); // StringBuilder do przechowywania tekstu
                    for(int i = 0; i < xRandom.length; i++) {
                        text = "x: " + xRandom[i] + " y: " + yRandom[i] + "<br>"; // Nowa linia tekstu
                        coordinatesText.append(text); // Dodawanie tekstu do StringBuilder
                       
                    }
                    label.setText("<html>" + coordinatesText.toString() + "</html>"); // Ustawianie tekstu w JLabel
                }
                else {
                    xRegular = draw.getxRegular();
                    yRegular = draw.getyRegular();
                    StringBuilder coordinatesText = new StringBuilder(); // StringBuilder do przechowywania tekstu
                    for(int i = 0; i < xRegular.length; i++) {
                        text = "x: " + xRegular[i] + " y: " + yRegular[i] + "<br>"; // Nowa linia tekstu
                        coordinatesText.append(text); // Dodawanie tekstu do StringBuilder
                        
                    }
                    label.setText("<html>" + coordinatesText.toString() + "</html>"); // Ustawianie tekstu w JLabel
                }
                
            }
        });
        
        add(button);
        add(label); 
        
        setBackground(Color.gray);
    }

}
