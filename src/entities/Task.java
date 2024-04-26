package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import entities.enums.ImportancePriority;
import entities.enums.TaskStatus;
import entities.enums.UrgencePriority;

public class Task {
	
	private String task;
	private Date date;
	
	private ImportancePriority importance;
	private UrgencePriority urgence;
	private TaskStatus status;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Task() {
		
	}


	public Task(String task, Date date, ImportancePriority importance, UrgencePriority urgence, TaskStatus status) {
		super();
		this.task = task;
		this.date = date;
		this.importance = importance;
		this.urgence = urgence;
		this.status = status;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public ImportancePriority getImportance() {
		return importance;
	}


	public void setImportance(ImportancePriority importance) {
		this.importance = importance;
	}


	public UrgencePriority getUrgence() {
		return urgence;
	}


	public void setUrgence(UrgencePriority urgence) {
		this.urgence = urgence;
	}


	public TaskStatus getStatus() {
		return status;
	}


	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public Boolean getBooleanStatus() {   // this return True if its done, will help the deleteAllDone from ListOfTasks
		if (getStatus().name().toUpperCase() != "DONE") {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String whatToDoWith() {  // here i transform the list in: do, schedule, delegate or delete. So its get easier to put in a table
		if (getImportance().name() == "IMPORTANT") {
			if (getUrgence().name() == "URGENT") {
				return "Do";
			}
			else {
				return "Decide";
			}
		}
		else {
			if(getUrgence().name() == "URGENT") {
				return "Delegate";
			}
			else {
				return "Delete";
			}
		}
	}
	
	public String toCSV() {
		return task + ","
				+ sdf.format(date)
				+ ","
				+ importance.name()
				+ ","
				+ urgence.name()
				+ ","
				+ status.name();
	}
	
	@Override
	public String toString() {
		return task + ", " 
	+ sdf.format(date) 
	+ ", " 
	+ importance.name().toLowerCase().replace("_", " ") 
	+ " and " 
	+ urgence.name().toLowerCase().replace("_", " ")
	+ ", " 
	+ status.name().toLowerCase();
	}
	
	
	
	
	
}
