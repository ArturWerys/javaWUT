package pl.edu.pw.fizyka.java.lab7.przyklady;

class MyRunnableExample implements Runnable{

	final int threadId;

	public MyRunnableExample(int threadId) {
		super();
		this.threadId = threadId;
	}

	@Override
	public void run() {
		for(int ii = 0; ii < 10; ii++){
			System.out.println("Thread " + Thread.currentThread().getName() + " prints " + RunnableExample.nextNumber());
		}
	}

}

public class RunnableExample {

	static int currentInt = 0;

	public static void main(String[] args) {
		int nthreads = 5;

		Thread[] threads = new Thread[nthreads]; 

		for(int ii = 0; ii < nthreads; ii++){
			threads[ii] = new Thread(new MyRunnableExample(ii));
		}

		for(int ii = 0; ii < nthreads; ii++){
			threads[ii].start();  
		}		
		
	}

	static int nextNumber(){
		currentInt++;
		return currentInt; 
	}

}
