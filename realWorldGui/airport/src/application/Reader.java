package application;

import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
	// the threads are placed into a FIFO queue when blocked, so any starvation
	// problems are solved.
	public static Semaphore readLock = new Semaphore(1, true);
	public volatile static int readCount = 0; // every thread will read/write the value in main memory only. (atomic)
	
	private volatile int parisval;
	
	public void run() {
		try {
			// Acquire Section
			readLock.acquire();
			synchronized (this) { // only thread it can proceed, otherwise it will wait.
				readCount++;
			}
			// tack the lock from Writer and prevent from enter with Reader, so any deadlock
			// problems are solved.
			if (readCount == 1) {
				Book.writeLock.acquire();
			}
			readLock.release();

			// Reading section
			ReadFile read = new ReadFile();
			Thread tickets = new Thread(read);
			
			tickets.start();
			tickets.join();
			
			parisval = read.paris();
			
			// Releasing section
			readLock.acquire();
			synchronized (this) {
				readCount--;
			}
			// leave the lock to Writer and allow to enter after Reader finish, so any
			// deadlock problems are solved.
			if (readCount == 0) {
				Book.writeLock.release();
			}
			readLock.release();
		
		} catch (InterruptedException e) {	
			System.out.println(e.getMessage());
		
		}
	}

	public int getParis() {
		return parisval;
		
	}
}
