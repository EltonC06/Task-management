package application;

import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import entities.CsvReader;
import entities.ListOfTasks;
import entities.Task;
import entities.enums.ImportancePriority;
import entities.enums.TaskStatus;
import entities.enums.UrgencePriority;

public class Program {

	public static void main(String[] args) throws ParseException, IOException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ImportancePriority importance = null;
		UrgencePriority urgence = null;
		int importanceValue;
		int urgenceValue;
		Date date = null;
		
		String name;
		/*
		System.out.println("Enter data about your task:");
		System.out.print("Task name: ");
		name = sc.nextLine();
		System.out.print("Importance:\n[1] Important\n[2] Not important\nType here: "); // I put numbers so the program wont look like too complex for the user
		importanceValue = sc.nextInt();
		if (importanceValue == 1) {
			importance = ImportancePriority.valueOf("IMPORTANT");
		}
		if (importanceValue == 2) {
			importance = ImportancePriority.valueOf("NOT_IMPORTANT");
		}
		
		System.out.print("Urgence:\n[1] Urgent\n[2] Not urgent\nType here: ");
		urgenceValue = sc.nextInt();
		if (urgenceValue == 1) {
			urgence = UrgencePriority.valueOf("URGENT");
		}
		if (urgenceValue == 2) {
			urgence = UrgencePriority.valueOf("NOT_URGENT");
		}
		
		System.out.print("Date:");
		try {
			date = sdf.parse(sc.next());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		System.out.print("Status: ");
		TaskStatus status = TaskStatus.valueOf(sc.next());

		Task tk = new Task(name, date, importance, urgence, status);
		System.out.println(tk);
		
		
		ListOfTasks taskList = new ListOfTasks();
		*/
		
		ListOfTasks taskList = new ListOfTasks();
		CsvReader cr = new CsvReader();
		
		taskList.updateFromCSVtoListOfTasks();
		

		taskList.getAllTasks();
		
		// Now i can update all tasks of .csv to the program e do the reverse too
		
		// storage system working
		
		// i need to optimize somethings, like the CsvReader, creating another class to contains only the link of archive
		
		
	}

}
