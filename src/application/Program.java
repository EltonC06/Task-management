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
		
		taskList.addTask(new Task("Tarefa1", sdf.parse("26/10/2020"), ImportancePriority.valueOf("IMPORTANT"), UrgencePriority.valueOf("URGENT"), TaskStatus.valueOf("PENDING")));
		
		taskList.addTask(new Task("Tarefa2", sdf.parse("24/10/2020"), ImportancePriority.valueOf("NOT_IMPORTANT"), UrgencePriority.valueOf("URGENT"), TaskStatus.valueOf("DOING")));
		
		taskList.addTask(new Task("Tarefa3", sdf.parse("23/10/2020"), ImportancePriority.valueOf("NOT_IMPORTANT"), UrgencePriority.valueOf("NOT_URGENT"), TaskStatus.valueOf("DONE")));
		
		taskList.addTask(new Task("Tarefa4", sdf.parse("22/10/2020"), ImportancePriority.valueOf("IMPORTANT"), UrgencePriority.valueOf("NOT_URGENT"), TaskStatus.valueOf("PENDING")));
		
		taskList.addTask(new Task("Tarefa5", sdf.parse("22/10/2010"), ImportancePriority.valueOf("IMPORTANT"), UrgencePriority.valueOf("NOT_URGENT"), TaskStatus.valueOf("DONE")));
		
		*/
		ListOfTasks taskList = new ListOfTasks();
		CsvReader cr = new CsvReader();
		
		taskList.getAllTasks();
		System.out.println("------");
		// thing to do: find how do i update the ListOfTasks from the CSV archive. The inverse its done: CsvReader.UpdateAllData(
		
		cr.reader();
		
		
	}

}
