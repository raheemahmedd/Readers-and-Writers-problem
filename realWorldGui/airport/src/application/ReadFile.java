package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFile implements Runnable{
	Path path = new Path();
	
	private volatile int londonval;
	private volatile int parisval;
	private volatile int newyorkval;
	
	public void run() {
		try {
			File london = new File(path.files()+"\\london.txt");
			File newyork = new File(path.files()+"\\newyork.txt");
			File paris = new File(path.files()+"\\paris.txt");
			
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