package application;

import java.sql.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Task;
import entities.enums.ImportancePriority;
import entities.enums.TaskStatus;
import entities.enums.UrgencePriority;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ImportancePriority importance = null;
		UrgencePriority urgence = null;
		int importanceValue;
		int urgenceValue;
		
		String name;
		Date date;

		System.out.println("Enter data about your task:");
		System.out.print("Name: ");
		name = sc.next();
		System.out.print("Importance:\n[1] Important\n[2] Not important\nType here: "); // I put numbers so the program wont look like too complex for the user
		importanceValue = sc.nextInt();
		System.out.print("Urgence:\n[1] Urgent\n[2] Not urgente\nType here: ");
		urgenceValue = sc.nextInt();
		System.out.println("Status: ");
		TaskStatus status = TaskStatus.valueOf(sc.next());
		if (importanceValue == 1) {
			importance = ImportancePriority.valueOf("IMPORTANT");
		}
		if (importanceValue == 2) {
			importance = ImportancePriority.valueOf("NOT_IMPORTANT");
		}
		
		if (urgenceValue == 1) {
			urgence = UrgencePriority.valueOf("URGENT");
		}
		if (urgenceValue == 2) {
			urgence = UrgencePriority.valueOf("NOT_URGENT");
		}
		
		
		Task tk = new Task(name, importance, urgence, status);
		System.out.println(tk);
		
	}

}
