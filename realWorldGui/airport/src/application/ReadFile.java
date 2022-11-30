package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFile implements Runnable{
	
	private volatile int londonval;
	private volatile int parisval;
	private volatile int newyorkval;
	
	public void run() {
		try {
			File london = new File("C:\\Users\\yhya2\\Desktop\\Hamid\\files\\london.txt");
			File newyork = new File("C:\\Users\\yhya2\\Desktop\\Hamid\\files\\newyork.txt");
			File paris = new File("C:\\Users\\yhya2\\Desktop\\Hamid\\files\\paris.txt");
			
			Scanner londonf = new Scanner(london);
			Scanner newyorkf= new Scanner(newyork);
			Scanner parisf= new Scanner(paris);
			
			londonval = Integer.parseInt(londonf.nextLine());
			newyorkval = Integer.parseInt(newyorkf.nextLine());
			parisval = Integer.parseInt(parisf.nextLine());
			
			londonf.close();
			newyorkf.close();
			parisf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int london() {
		return londonval;
	}
	
	public int paris() {
		return parisval;
	}
	
	public int newyork() {
		return newyorkval;
	}
}