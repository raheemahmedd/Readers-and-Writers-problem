package application;

import java.util.concurrent.Semaphore;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class UnBook implements Runnable {
	Path path = new Path();
	// the threads are placed into a FIFO queue when blocked, so any starvation
	// problems are solved.
	public static Semaphore writeLock = new Semaphore(1, true);
	private volatile int ticketsNo;

	@Override
	public void run() {
		try {
			writeLock.acquire();
			Reader read = new Reader();
			Thread thread = new Thread(read);

			thread.start();
			thread.join();

			int val = read.getParis();
			val += this.ticketsNo;

			FileWriter myWriter = new FileWriter(path.files() + "\\paris.txt");
			myWriter.write(String.valueOf(val));

			writeLock.release();
			myWriter.close();
			
		} catch (InterruptedException e) {
			e.printStackTrace();;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setTicketsNo(int ticketsNo) {
		this.ticketsNo = ticketsNo;
	
	}
}
