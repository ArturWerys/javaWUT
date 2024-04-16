package pl.edu.pw.fizyka.java.lab7.przyklady;

class RunnableY implements Runnable {
	
	public void run() {
		for (int i = 0; i < 1000; i++)
			System.out.print("y");
	}
}

public class WatkiXxxYyyRunnable {

	public static void main(String[] args) {
		
		RunnableY runnablePrintY = new RunnableY();
		Thread thread = new Thread(runnablePrintY);
		thread.start();
		
		for (int i = 0; i < 1000; i++)
			System.out.print("x");
	}
}
