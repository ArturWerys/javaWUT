package pl.edu.pw.fizyka.java.lab7.przyklady;

class MyThread extends Thread{

	final int threadId;

	public MyThread(int threadId) {
		super();
		
		this.threadId = threadId;
	}

	@Override
	public void run() {
		for(int ii = 0; ii < 10; ii++){
			System.out.println("Thread " + Thread.currentThread().getName() + " prints " + ThreadExample.nextNumber());
		}
	}

}

public class ThreadExample {

	static int currentInt = 0;

	public static void main(String[] args) {
		int nthreads = 5;

		Thread[] threads = new Thread[nthreads]; 

		for(int ii = 0; ii < nthreads; ii++){
			threads[ii] = new MyThread(ii); 
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
