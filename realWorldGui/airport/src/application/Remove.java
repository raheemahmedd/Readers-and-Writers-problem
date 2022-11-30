package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Remove {
	public void run(String ticketNo) {
		try {
			File inputFile = new File("C:\\\\Users\\\\yhya2\\\\Desktop\\\\Hamid\\\\files\\\\names.txt");
			File tempFile = new File("C:\\\\Users\\\\yhya2\\\\Desktop\\\\Hamid\\\\files\\\\tempfile.txt");
			
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			
			String lineToRemove = ticketNo;
			String currentLine;
			
			while((currentLine = reader.readLine()) != null) {
				// trim newline when comparing with lineToRemove
				String trimmedLine = currentLine.trim();
				
				if(trimmedLine.equals(lineToRemove)) continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}