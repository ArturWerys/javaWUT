package artur.werys;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Przysik extends JButton {
    
    ArrayList<Integer> liczby = new ArrayList<Integer>();

    public Przysik(int id) {
        super();
        setText("1");
        setPreferredSize(new Dimension(100, 100));

        addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(id);
        
                String m = JOptionPane.showInputDialog("Podaj liczbę");
                int l;
                
                try {
                    l = Integer.parseInt(m);
                    
                    if (!GuiMain.resultsFromButtons.contains(l) && l > 1 && l < 49) {
                        GuiMain.resultsFromButtons.add(l);
                        setText(m);
                        System.out.println(l + "\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Zła liczba");
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "To nie jest prawidłowa liczba");
                }
            }
        });
    }
}
