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
		for (int i = 0; i< taskList.size(); i++) {
			boolean booleanStatus = taskList.get(i).getBooleanStatus();
			if (booleanStatus = true) {
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
	
	@Override
	public String toString() {
		return "Everything [tasks=" + taskList + "]";
	}
}
