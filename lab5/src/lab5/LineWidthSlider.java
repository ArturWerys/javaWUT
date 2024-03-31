package lab5;


import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineWidthSlider extends JSlider {
	
	int sliderValue = 1;
	
	static final int SLIDER_MIN = 1;
	static final int SLIDER_MAX = 11;
	static final int SLIDER_INIT = 1;

	
	public LineWidthSlider() {
	

	super(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
	setMajorTickSpacing(2); //podzialki duze
	setMinorTickSpacing(1); //podzialki male
	setPaintTicks(true); //wlacza podzialki
	setPaintLabels(true); //pokazuje opisy 
	
	addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			sliderValue = getValue();
		}
	});
	}


	public int getSliderValue() {
		return sliderValue;
	}
	
}
