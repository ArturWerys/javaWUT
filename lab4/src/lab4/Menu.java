package lab4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu extends JFrame{
	
	public Menu(){
		super("Program do rysowania");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setJMenuBar( createMenu() );
	    
	}

	
	public JMenuBar createMenu(){
		
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
		
			
		menu.addSeparator();
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
			
		});
		menu.add(exit);

		return menuBar;
	}
	
}

