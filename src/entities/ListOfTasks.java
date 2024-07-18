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
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sqlsdf = new SimpleDateFormat("yyyy/MM/dd");
	
	
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
		String List[];
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
	
	public void updateFromSQLtoLocalList() throws ParseException { // NÃO ESQUECER DE MUDAR SENHA ANTES DO COMMIT
		try {
			taskList.clear();
			String dateToFormat[];
			String dateFormated;
			String List[];
			conn = DB.getConnection(); // conexão estabelecida NÃO ESQUECER DE FECHAR CONEXÃO
			st = conn.createStatement(); // statement criado
			rs = st.executeQuery("select * from alltasks"); // comando enviado para o SQL, armazenando resposta no 'rs'
			
			while (rs.next()) { // esse comando é um booleano, e quando não houver outra linha ele vai dar false
				Task task = new Task();
				task.setTask(rs.getString("task"));
				dateToFormat = rs.getString("date").split(" "); // separando a hora da data
				dateFormated = dateToFormat[0];
				dateFormated = dateFormated.replace("-", "/");
				task.setDate(sqlsdf.parse(dateFormated)); // eu formato aqui no formato do sql, mas la na classe task é formatado em padrão BR então ele se ajusta
				task.setImportance(ImportancePriority.valueOf("IMPORTANT"));
				task.setUrgence(UrgencePriority.valueOf(rs.getString("urgence").toString().toUpperCase()));
				task.setStatus(TaskStatus.valueOf(rs.getString("status").toString().toUpperCase()));
				taskList.add(task);
			} // primeiro vou deixar por enquanto duas listas, uma local e uma no sql, assim não vou precisar mexer nos comandos de delete e de input. So no de salvar tarefa e carregar
			
			
		} catch (SQLException msg) {
			throw new DbException(msg.getMessage());
		}
		
	}
	
	// vou atualizar o sql daqui mesmo, de linha e linha e a hora do dia eu boto DEFAULT
	// é so eu pegar a tasklist local e jogar tudo para o SQL
	public void updateFromLocalListtoSQL() throws ParseException {
		//ArrayList<String> task = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			//st.executeUpdate("truncate table alltasks"); // limpando a tabela para poder inserir todos os dados sem erro
			for (int i = 0; i<taskList.size(); i++) {
				String query = String.format("insert into alltasks value (DEFAULT, '" + sqlsdf.format(taskList.get(i).getDate()).toString().replace("/", "-") + "', '" + taskList.get(i).getTask() + "', '" + taskList.get(i).getImportance() + "', '" + taskList.get(i).getUrgence() + "', '" + taskList.get(i).getStatus() + "');"); //, "tasktestnumber1", "important", "urgent", "done" 
				st.execute(query);
	
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