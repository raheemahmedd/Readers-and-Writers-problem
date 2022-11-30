package application;

import java.util.concurrent.Semaphore;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Book implements Runnable {
	// the threads are placed into a FIFO queue when blocked, so any starvation
	// problems are solved.
	public static Semaphore writeLock = new Semaphore(1, true);

	@Override
	public void run() {
		try {
			Reader read = new Reader();
			Thread thread = new Thread(read);
			
			thread.start();
			thread.join();
			
			int val = read.getParis();
			val--;
			
			FileWriter myWriter = new FileWriter("C:\\Users\\yhya2\\Desktop\\Hamid\\files\\paris.txt");
			myWriter.write(String.valueOf(val));
			myWriter.close();
			
			writeLock.release();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void book(int seatNo) throws IOException {
		FileWriter fw = new FileWriter("C:\\\\Users\\\\yhya2\\\\Desktop\\\\Hamid\\\\files\\\\names.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(seatNo+"\n");
        bw.close();
		
	}
}