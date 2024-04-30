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
		
		int whatToDo;
		
		ListOfTasks listOfTasks = new ListOfTasks();
		CsvReader csvReader = new CsvReader();
		
		listOfTasks.updateFromCSVtoListOfTasks();
		
		System.out.println("-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("WELCOME to the simple task management application!");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("What do you want to do?");
		System.out.println("[1] See all tasks registred");
		System.out.println("[2] Implement task");
		System.out.println("[3] Delete all tasks 'done'");
		System.out.println("[4] Change task status");
		System.out.println("[5] Delete a specific task by Id");
		System.out.println("[6] Reset storage");
		System.out.println("[7] Save all and quit");
		System.out.print("-=-=-=-=-\nType here:");
		whatToDo = sc.nextInt();
		
		if(whatToDo == 1) {
			System.out.println("Loading file...");
			listOfTasks.separateByEisenhower();
		}
		
		if(whatToDo == 2) {		
			System.out.println("Enter data about your task:");
			System.out.print("Task name: ");
			sc.nextLine();
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
			
			System.out.print("Date [dd/mm/yyyy]:");
			try {
				date = sdf.parse(sc.next());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			System.out.print("Status:\n[Done or Doing or Pending]\nType here: ");
			TaskStatus status = TaskStatus.valueOf(sc.next().toUpperCase().strip());
			
			Task task = new Task(name, date, importance, urgence, status);
			
			listOfTasks.addTask(task);
			csvReader.updateAllData(listOfTasks);
		}
		
		if (whatToDo == 3) {
			listOfTasks.deleteAllDone();
			csvReader.updateAllData(listOfTasks);
		}
		
		if (whatToDo == 4) {
			System.out.println("Choose the task Id to change its status:");
			listOfTasks.getAllTasks();
			System.out.print("\nType here:");
			int taskId = sc.nextInt();
			System.out.print("Change to its new status\n[Done or Doing or Pending]\nType here: ");
			listOfTasks.getTasks().get(taskId).setStatus(TaskStatus.valueOf(sc.next().toUpperCase().strip()));
			csvReader.updateAllData(listOfTasks);
		}
		
		
		if (whatToDo == 5) {
			System.out.println("Choose the task Id to delete it:");
			listOfTasks.getAllTasks();
			System.out.print("\nType here:");
			listOfTasks.deleteTaskById(sc.nextInt());
			csvReader.updateAllData(listOfTasks);
		}
		
		if (whatToDo == 6) {
			System.out.println("[WARNING] Are you sure do you want to do this? (This process can't be reversed)\n[1] Yes\n[2] No");
			System.out.print("Type here:");
			int decision = sc.nextInt();
			
			if (decision == 1) {
				csvReader.resetStorage();
				listOfTasks.updateFromCSVtoListOfTasks();
				System.out.println("Storage reseted...");
			}
			
			else {
				System.out.println("Not reseted.");
			}
		}
		
		if (whatToDo == 7) {
			csvReader.updateAllData(listOfTasks);
			System.out.println("Saving and quitting progam...");
		}
		
		
		sc.close();
	}

}
