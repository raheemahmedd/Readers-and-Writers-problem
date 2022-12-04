package application;

import java.util.concurrent.Semaphore;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Book implements Runnable {
	// the threads are placed into a FIFO queue when blocked, so any starvation
	// problems are solved.
	public static Semaphore writeLock = new Semaphore(1, true);
	
	private volatile int ticketsNo;
	
	Path path = new Path();

	public void run() {
		try {
			Reader read = new Reader();
			Thread thread = new Thread(read);


			thread.start();
			thread.join();

			int val = read.getParis();
			val-=this.ticketsNo;
			
			FileWriter myWriter = new FileWriter(path.files()+"\\paris.txt");
			myWriter.write(String.valueOf(val));
			myWriter.close();

			writeLock.release();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}

	public void setTicketsNo(int ticketsNo){
		this.ticketsNo = ticketsNo;
	}
}
