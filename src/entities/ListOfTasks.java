package entities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.DB;
import db.DbException;
import entities.enums.ImportancePriority;
import entities.enums.TaskStatus;
import entities.enums.UrgencePriority;

public class ListOfTasks {
	
	private List<Task> taskList = new ArrayList<>();
	
	// triplice das variaveis para trabalhar com sql
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
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
		int doQuantity = 0;
		int decideQuantity = 0;
		int delegateQuantity = 0;
		int deleteQuantity = 0;
		

		for (int i = 0; i<taskList.size(); i++) {  // this will help me to personalize a topic if there isn't tasks there
			if (taskList.get(i).whatToDoWith() == "Do") {
				doQuantity++;
			}
			else if (taskList.get(i).whatToDoWith() == "Decide") {
				decideQuantity++;
			}
			else if (taskList.get(i).whatToDoWith() == "Delegate") {
				delegateQuantity++;
			}
			else if (taskList.get(i).whatToDoWith() == "Delete") {
				deleteQuantity++;
			}
		}
		
		System.out.println("---------------");
		System.out.println("=== Do ===");
		if (doQuantity > 0) {
			for (int i = 0; i<taskList.size(); i++) {
				
				if (taskList.get(i).whatToDoWith() == "Do") {
					System.out.println(taskList.get(i));
				}
			}
		}
		else {
			System.out.println("No tasks");
		}

		System.out.println("=== Schedule ===");
		if (decideQuantity > 0) {
			for (int i = 0; i<taskList.size(); i++) {
				if (taskList.get(i).whatToDoWith() == "Decide") {
					System.out.println(taskList.get(i));
				}
			}
		}
		else {
			System.out.println("No tasks");
		}
		
		System.out.println("=== Delegate ===");
		if (delegateQuantity > 0) {
			
			for (int i = 0; i<taskList.size(); i++) {
				if (taskList.get(i).whatToDoWith() == "Delegate") {
					System.out.println(taskList.get(i));
				}
			}
		}
		else {
			System.out.println("No tasks");
		}
		
		System.out.println("=== Delete ===");
		if (deleteQuantity > 0) {
			for (int i = 0; i<taskList.size(); i++) {
				if (taskList.get(i).whatToDoWith() == "Delete") {
					System.out.println(taskList.get(i));
				}
			}
		}
		else {
			System.out.println("No tasks");
		}
		System.out.println("---------------");
	}
	
	public void updateFromCSVtoListOfTasks() throws IOException, ParseException {
		CsvReader CsvReader = new CsvReader();
		String List[]; // eu vou pegar os dados,tudo,juntos e separá-los. Daí então ele vai entrar nessa lista
		taskList.clear();
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
	
	public void updateFromSQLtoLocalList() { // vou ter que pegar todos os dados e inseri-los no Task para depois adicionar na list of tasks ( fazer isso com todos)
		taskList.clear();
		// pegando dados
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement(); // cara que vai fazer a comunicação
			
			rs = st.executeQuery("select * from alltasks"); // recebendo dados
			
			while (rs.next()) {// esse next vai dar false quando não houver proxima linha
				System.out.print(rs.getString("taskid")); // escolhendo a coluna 
				System.out.print(rs.getString("date"));
				System.out.print(rs.getString("task")); 
				System.out.print(rs.getString("importance")); 
				System.out.print(rs.getString("urgence")); 
				System.out.println(rs.getString("status"));
					
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Everything [tasks=" + taskList + "]";
	}
}