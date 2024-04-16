package pl.edu.pw.fizyka.java.lab7.przyklady;


import java.util.concurrent.TimeUnit;

//Przyklad Wyscigow (Race Conditions)

//Wątek
class MyRunnable implements Runnable{
	
	final int threadId;
	
	public MyRunnable(int threadId) {
		super();
		this.threadId = threadId;
	}

	@Override
	public void run() {
		for (;;){
            //Poboieramy liczbę
			int no = ThreadHell.nextNumber();
            // Jeśli jest nieparzysta to informiujemy o błędzie.
			if (no % 2  != 0){
				System.out.println("Thread found error" + no); 
			}
		}
		
	}
	
}

public class ThreadHell {


	static int currentInt = 0;

    public static void main(String[] args) {

		int nthreads = 15;
		
		Thread[] threads = new Thread[nthreads]; 

        //Uruchamiamy 15 wątków
		for(int ii = 0; ii < nthreads; ii++){
			threads[ii] = new Thread(new MyRunnable(ii)); 
		}
		
		for(int ii = 0; ii < nthreads; ii++){
			threads[ii].start();  
		}		
					
	}
	
	//Zwraca następna liczbę (zawsze parzystą bo dodajemy do niej dwa)
	static int nextNumber(){		
		
		currentInt++;
		
		try {
			TimeUnit.NANOSECONDS.sleep(3);
		} catch (InterruptedException e) {
			Thread.currentThread();
			Thread.interrupted();
		} 
				
		currentInt++;		
		
		return currentInt; 
	}
	
}
