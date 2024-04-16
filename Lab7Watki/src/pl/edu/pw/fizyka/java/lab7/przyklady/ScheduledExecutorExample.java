package pl.edu.pw.fizyka.java.lab7.przyklady;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.*;

public class ScheduledExecutorExample {


	static Runnable r = new Runnable() {
		int i=0;
		@Override
		public void run() {
			System.out.println(i++);
			
		}
	};
	
	
	public static void main(String[] args) {
	
		
		final ScheduledExecutorService scheduler = 
			       Executors.newScheduledThreadPool(2);

	    
		scheduler.scheduleAtFixedRate(r, 0, 1, SECONDS);
		
		scheduler.scheduleWithFixedDelay( (new Runnable() {

			@Override
			public void run() {
				System.out.println("Po 5 sekundach - potem co 3 sekundy");
				
			}
		}), 5, 3, SECONDS);
		
		
		scheduler.schedule(new Runnable() {
			                @Override
							public void run() { System.out.println("Koniec programu po 15 sekundach"); 
			                scheduler.shutdownNow();
			                System.exit(0);}
			            }, 15, SECONDS);
	    
	    
	}
		

}
