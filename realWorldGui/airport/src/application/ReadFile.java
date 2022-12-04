package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFile implements Runnable{
	Path path = new Path();

	private volatile int parisval;
	
	public void run() {
		try {
			File london = new File(path.files()+"\\london.txt");
			File newyork = new File(path.files()+"\\newyork.txt");
			File paris = new File(path.files()+"\\paris.txt");
			
			Scanner londonf = new Scanner(london);
			Scanner newyorkf= new Scanner(newyork);
			Scanner parisf= new Scanner(paris);
			
			parisval = Integer.parseInt(parisf.nextLine());
			
			londonf.close();
			newyorkf.close();
			parisf.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
	}
		
	public int paris() {
		return parisval;
	}
	
}