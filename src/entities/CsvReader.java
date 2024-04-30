package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvReader {
	
	
	CsvLink CsvLink = new CsvLink();
	
	
	public CsvReader() {
		
	}

	public void reader() throws IOException {
		FileReader fr = new FileReader(CsvLink.csvPathLink());
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null) {
			System.out.println(line);
			line = br.readLine();
		}
		
		br.close();
		fr.close();
	}
	
	public void implementData(String data) throws IOException{
		FileWriter fw = new FileWriter(CsvLink.csvPathLink(), true); // true will not delete the last recorded data
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(data);
		bw.newLine(); // new line

		bw.close();
		fw.close();
	}
	
	public void updateAllData(ListOfTasks taskList) throws IOException { // calling the ListOfTasks so it can acess it
		FileWriter fw = new FileWriter(CsvLink.csvPathLink());  // this doesn't have the "true" so everytime used it will reset the archive
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < taskList.getTasks().size(); i++) {  // im getting the size of list of tasks so i will implement all data from it
			bw.write(taskList.getTasks().get(i).toCSV()); // transforming to CSV so i can implement to the archive
			bw.newLine();
		}
		
		bw.close();
		fw.close();
	}
	
	public String readOneLine() throws IOException {
		FileReader fr = new FileReader(CsvLink.csvPathLink()); // trying to read one line so i can transform one line to a Task and implement in the ListOfTasks
		BufferedReader br = new BufferedReader(fr);   // this function will be complemented by another in the ListOfTasks function
		
		String line = br.readLine();
		while (true) {
			line = br.readLine();
			break;
		}
		
		br.close();
		fr.close();
		return line;
		// everytime i use this function its read only the first line because everytime tha position of readline resets to start
	}
	
	public String readSpecificLine(int specific) throws IOException {
		FileReader fr = new FileReader(CsvLink.csvPathLink()); // trying to read one line so i can transform one line to a Task and implement in the ListOfTasks
		BufferedReader br = new BufferedReader(fr);   // this function will be complemented by another in the ListOfTasks function
		
		for (int i = 0; i<specific; i++) {  // reading all lines after reach the specified line
			br.readLine();
		}
		String line = br.readLine();
		
		br.close();
		fr.close();
		return line; 
	}
	
	public Integer numberOfLines() throws IOException {
		FileReader fr = new FileReader("C:\\temp\\ws-eclipse\\taskManagement\\src\\AllTasks.csv"); // trying to read one line so i can transform one line to a Task and implement in the ListOfTasks
		BufferedReader br = new BufferedReader(fr);   // this function will be complemented by another in the ListOfTasks function
		
		int lines = 0;
		while (br.readLine() != null) lines++; // simple line count
		
		br.close();
		fr.close();
		return lines;	
	}
	
	public void resetStorage() throws IOException {
		FileWriter fw = new FileWriter(CsvLink.csvPathLink());  // this doesn't have the "true" so everytime used it will reset the archive
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("");
		
		bw.close();
		fw.close();
	}
}