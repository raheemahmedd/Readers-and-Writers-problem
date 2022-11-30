package application;

import java.util.concurrent.Semaphore;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UnBook implements Runnable {
	// the threads are placed into a FIFO queue when blocked, so any starvation
	// problems are solved.
	public static Semaphore writeLock = new Semaphore(1, true);

	@Override
	public void run() {
		try {
			writeLock.acquire();
			Reader read = new Reader();
			Thread thread = new Thread(read);
			
			thread.start();
			thread.join();
			
			int val = read.getParis();
			val++;
			
			FileWriter myWriter = new FileWriter("C:\\\\Users\\\\yhya2\\\\Desktop\\\\Hamid\\\\files\\\\paris.txt");
			myWriter.write(String.valueOf(val));
			
			writeLock.release();
			myWriter.close();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
