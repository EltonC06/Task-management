package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CsvReader {
	
	private ListOfTasks ListOfTasks;
	
	public CsvReader() {
		
	}

	public CsvReader(entities.ListOfTasks listOfTasks) {
		super();
		ListOfTasks = listOfTasks;
	}

	public void reader() throws IOException {
		FileReader fr = new FileReader("C:\\temp\\ws-eclipse\\taskManagement\\src\\AllTasks.csv");
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
		FileWriter fw = new FileWriter("C:\\temp\\ws-eclipse\\taskManagement\\src\\AllTasks.csv", true); // true will not delete the last recorded data
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(data);
		bw.newLine(); // new line

		bw.close();
		fw.close();
	}
	
	public void updateAllData(ListOfTasks taskList) throws IOException { // calling the ListOfTasks so it can acess it
		FileWriter fw = new FileWriter("C:\\\\temp\\\\ws-eclipse\\\\taskManagement\\\\src\\\\AllTasks.csv");  // this doesn't have the "true" so everytime used it will reset the archive
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < taskList.getTasks().size(); i++) {  // im getting the size of list of tasks so i will implement all data from it
			bw.write(taskList.getTasks().get(i).toCSV()); // transforming to CSV so i can implement to the archive
			bw.newLine();

		}
		bw.close();
		fw.close();
	}
	
	public void readOneLine() throws IOException {
		FileReader fr = new FileReader("C:\\temp\\ws-eclipse\\taskManagement\\src\\AllTasks.csv"); // trying to read one line so i can transform one line to a Task and implement in the ListOfTasks
		BufferedReader br = new BufferedReader(fr);   // this function will be complemented by another in the ListOfTasks function
		String line = br.readLine();
		
	}
	
}
