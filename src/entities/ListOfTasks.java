package entities;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.enums.ImportancePriority;
import entities.enums.TaskStatus;
import entities.enums.UrgencePriority;

public class ListOfTasks {
	
	private List<Task> taskList = new ArrayList<>();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
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
	
	public void deleteAllDone() throws IOException {

		List <Integer> listOfTasksTrues = new ArrayList<Integer>();
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getBooleanStatus() == true) {  // this will delete if boolean status return True. This function only return if its status is DONE
				// System.out.println("Boolean status of " + i + "º task = " + taskList.get(i).getBooleanStatus());
				listOfTasksTrues.add(i);
			}
		}
		Collections.reverse(listOfTasksTrues);  // Doing a reverse to make a safe deletion
		
		for (int i =0; i < listOfTasksTrues.size(); i++) {
			deleteTaskById(listOfTasksTrues.get(i));
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
	
	public void updateFromCSVtoListOfTasks() throws IOException, ParseException {
		CsvReader CsvReader = new CsvReader();
		String List[];
		for (int i = 0; i<CsvReader.numberOfLines(); i++) {
			List = CsvReader.readSpecificLine(i).split(",");  // separing all element of a Task before the "," so i can add its separately to update the tasklist
			Task task = new Task();
			task.setTask(List[0]);
			task.setDate(sdf.parse(List[1]));
			task.setImportance(ImportancePriority.valueOf(List[2]));
			task.setUrgence(UrgencePriority.valueOf(List[3]));
			task.setStatus(TaskStatus.valueOf(List[4]));
			taskList.add(task);  // updating a task to the taskList
		}
	}

	@Override
	public String toString() {
		return "Everything [tasks=" + taskList + "]";
	}
}