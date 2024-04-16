package pl.edu.pw.fizyka.java.lab7.przyklady;


class ThreadY extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++)
			System.out.print("y");
	}
}

public class WatkiXxxYyy {

	public static void main(String[] args) {
		ThreadY threadPrintY = new ThreadY();
		threadPrintY.start();

		for (int i = 0; i < 1000; i++)
			System.out.print("x");

	}

}
