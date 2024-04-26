package entities;

import java.util.ArrayList;
import java.util.List;

public class ListOfTasks {
	
	private List<Task> taskList = new ArrayList<>();
	
	public ListOfTasks() {
		
	}

	public ListOfTasks(List<Task> tasks) {
		super();
		this.taskList = tasks;
	}

	public List<Task> getTasks() {
		return taskList;
	}
	
	public void addTask(Task tk){
		taskList.add(tk);
	}
	
	public void deleteTaskById(int index) {
		taskList.remove(index);
	}
	
	public void getAllTasks(){
		for (int i = 0; i < taskList.size(); i++) {
			System.out.println("[" + i + "]: " + taskList.get(i));
		}
	}
	
	public void deleteAllDone() {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getBooleanStatus() == true) {  // this will delete if boolean status return True. This function only return if its status is DONE
				System.out.println("Boolean status of " + i + "º task = " + taskList.get(i).getBooleanStatus());
				deleteTaskById(i);
			}
		}
	}
	
	public void separateByEisenhower() {
		System.out.println("---------------");
		System.out.println("=== Do ===");
		for (int i = 0; i<taskList.size(); i++) {
			if (taskList.get(i).whatToDoWith() == "Do") {
				System.out.println(taskList.get(i));
			}
		}
		System.out.println("=== Schedule ===");
		for (int i = 0; i<taskList.size(); i++) {
			if (taskList.get(i).whatToDoWith() == "Decide") {
				System.out.println(taskList.get(i));
			}
		}
		System.out.println("=== Delegate ===");
		for (int i = 0; i<taskList.size(); i++) {
			if (taskList.get(i).whatToDoWith() == "Delegate") {
				System.out.println(taskList.get(i));
			}
		}
		System.out.println("=== Delete ===");
		for (int i = 0; i<taskList.size(); i++) {
			if (taskList.get(i).whatToDoWith() == "Delete") {
				System.out.println(taskList.get(i));
			}
		}
		System.out.println("---------------");
	}
	
	public void updateFromCSVtoListOfTasks() {
		CsvReader CsvReader = new CsvReader();
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Everything [tasks=" + taskList + "]";
	}
}
