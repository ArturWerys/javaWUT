package przykladowyZeStrony;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class PanelWsp extends JPanel{

    JLabel xLabel;
    JLabel yLabel;
    JTextField xPTF;
    JTextField yPTF;
    

	public PanelWsp(PanelRysowania panelRysowania) {
        super();

        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(1, 4));

        xLabel = new JLabel("x:");
        add(xLabel);

        xPTF = new JTextField();
        add(xPTF);

        yLabel = new JLabel("y:");
        add(yLabel);

        yPTF = new JTextField();
        add(yPTF);
    }

    public void updateXCoordinateText(int x) {
        xPTF.setText(String.valueOf(x));
    }
    
    public void updateYCoordinateText(int y) {
        yPTF.setText(String.valueOf(y));
    }
}
