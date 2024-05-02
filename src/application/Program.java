package application;

import java.util.Date;
import java.util.InputMismatchException;
import java.io.IOException;
import java.text.ParseException;
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
		while (true) {
			System.out.println("\n-=-=-=-=-");
			System.out.println("What do you want to do?");
			System.out.println("[1] See all tasks registred");
			System.out.println("[2] Implement task");
			System.out.println("[3] Delete all tasks 'done'");
			System.out.println("[4] Change task status");
			System.out.println("[5] Delete a specific task by Id");
			System.out.println("[6] Reset storage");
			System.out.println("[7] Save all and quit");
			System.out.print("-=-=-=-=-\nType here:");
			while (true) {   // a loop that is impossible to break the program
				try {
					whatToDo = sc.nextInt();
					if (whatToDo > 0 && whatToDo < 8) {
						break;
					}
					else {
						System.out.print("Please, try again:");
					}
				} catch(InputMismatchException e) {
					System.out.print("Please, enter a valid value:");
					sc.next();
				}
			}
			
			System.out.println();
			
			if(whatToDo == 1) {
				listOfTasks.updateFromCSVtoListOfTasks();
				listOfTasks.separateByEisenhower();
			}
			
			if(whatToDo == 2) {		
				System.out.println("Enter data about your task:");
				System.out.print("Task name:");
				sc.nextLine();
				name = sc.nextLine();
				System.out.print("Importance:\n[1] Important\n[2] Not important\nType here:"); // I put numbers so the program wont look like too complex for the user
				while (true) {
					try {
						importanceValue = sc.nextInt();
						if (importanceValue > 0 && importanceValue < 3) {
							break;
						}
						else {
							System.out.print("Please, try again:");
						}	
					} catch (InputMismatchException e) {
						System.out.print("Please, enter a valid value:");
						sc.next();
					}
				}
				
				if (importanceValue == 1) {
					importance = ImportancePriority.valueOf("IMPORTANT");
				}
				if (importanceValue == 2) {
					importance = ImportancePriority.valueOf("NOT_IMPORTANT");
				}
				
				System.out.print("Urgence:\n[1] Urgent\n[2] Not urgent\nType here:");
				while (true) {
					try {
						urgenceValue = sc.nextInt();
						if (urgenceValue > 0 && urgenceValue < 3) {
							break;
						}
						else {
							System.out.print("Please, try again:");
						}
					} catch (InputMismatchException e) {
						System.out.print("Please, enter a valid value:");
						sc.next();
					}
				}
				if (urgenceValue == 1) {
					urgence = UrgencePriority.valueOf("URGENT");
				}
				if (urgenceValue == 2) {
					urgence = UrgencePriority.valueOf("NOT_URGENT");
				}
				
				date = new Date();
				
				System.out.print("Status:\n[Done or Doing or Pending]\nType here:");
				TaskStatus status;
				while (true) {
					try {
						status = TaskStatus.valueOf(sc.next().toUpperCase().strip());
						break;
					} catch (IllegalArgumentException e) {
						System.out.print("Please, enter a valid value:");
					}
				}
				
				Task task = new Task(name, date, importance, urgence, status);
				System.out.println("\n-=-=-\nTask saved\n-=-=-");
				
				listOfTasks.addTask(task);
				csvReader.updateAllData(listOfTasks);
			}
			
			if (whatToDo == 3) {
				listOfTasks.deleteAllDone();
				csvReader.updateAllData(listOfTasks);
				
				System.out.println("-=-=-\nAll tasks 'done' deleted successfully!\n-=-=-");
			}
			
			if (whatToDo == 4) {
				System.out.println("Choose the task Id to change its status:");
				int taskId = 0;
				listOfTasks.getAllTasks();
				System.out.println();
				while (true) {
					System.out.print("Type here:");
						try {
							taskId = sc.nextInt();
							if (taskId >= 0 && taskId < csvReader.numberOfLines()) {
								break;
						}
						else {
							System.out.print("Please, enter a valid value:\n");
						}
					} catch (InputMismatchException e) {
						System.out.print("Please, enter a valid value:\n");
					} catch (IndexOutOfBoundsException e) {
						System.out.print("Please, enter a valid value:\n");
					}
				}
				
				System.out.print("Change to its new status\n[Done or Doing or Pending]\n");
				while (true) {
					System.out.print("Type here:");
					try {
						listOfTasks.getTasks().get(taskId).setStatus(TaskStatus.valueOf(sc.next().toUpperCase().strip()));
						break;
					} catch (IllegalArgumentException e) {
						System.out.print("Please, enter a valid value:\n");
					} 	
				}
				csvReader.updateAllData(listOfTasks);
				System.out.println("\n-=-=-\nStatus of task [" + taskId + "] changed successfully!\n-=-=-");
			}
			
			if (whatToDo == 5) {
				System.out.println("Choose the task Id to delete it:\n");
				listOfTasks.getAllTasks();
				int decision;
				while (true) {
					System.out.print("Type here:");
					try {
						decision = sc.nextInt();
						if (decision >= 0 && decision < csvReader.numberOfLines()) {
							break;
						}
						else {
							System.out.print("Please, enter a valid value:\n");
						}
					} catch (InputMismatchException e) {
						System.out.print("Please, enter a valid value:\n");
						sc.next();
					}
				}
				listOfTasks.deleteTaskById(decision);
				csvReader.updateAllData(listOfTasks);
				
				System.out.println("\n-=-=-\nTask number [" + decision + "] deleted successfully!\n-=-=-");
			}
			
			if (whatToDo == 6) {
				System.out.println("[WARNING] Are you sure do you want to do this? (This process can't be reversed)\n[1] Yes\n[2] No");
				System.out.print("Type here:");
				int decision;
				while (true) {
					try {
						decision = sc.nextInt();
						if (decision > 0 && decision < 3) {
							break;
						}
						else {
							System.out.print("Please, try again:");
						}
					} catch (InputMismatchException e) {
						System.out.print("Please, enter a valid value:");
						sc.next();
					}
				}
				
				if (decision == 1) {
					csvReader.resetStorage();
					listOfTasks.updateFromCSVtoListOfTasks();
					System.out.println("\n-=-=-\nStorage reseted successfully!\n-=-=-");
				}
				
				else {
					System.out.println("\n-=-=-\nStorage not reseted.\n-=-=-");
				}
			}
			if (whatToDo == 7) {
				csvReader.updateAllData(listOfTasks);
				break;
			}
		} // end of big loop
	
		System.out.println("Saving and quitting progam...");
		System.out.println("System saved and shutted down!");
		sc.close();
	}
}
