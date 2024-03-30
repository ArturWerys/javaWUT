package lab4;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider {
	
	JSlider slider;
	int sliderValue = 3;
	
	static final int SLIDER_MIN = 3;
	static final int SLIDER_MAX = 12;
	static final int SLIDER_INIT = 3;

	
	Slider(){
		// Slider
		
	slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
	slider.setMajorTickSpacing(10); //podzialki duze
	slider.setMinorTickSpacing(1); //podzialki male
	slider.setPaintTicks(true); //wlacza podzialki
	slider.setPaintLabels(true); //pokazuje opisy 
	
	slider.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			sliderValue = slider.getValue();
		}
	});
	}


	public int getSliderValue() {
		return sliderValue;
	}
	
}
