package entities;

import java.sql.Date;

import entities.enums.ImportancePriority;
import entities.enums.TaskStatus;
import entities.enums.UrgencePriority;

public class Task {
	
	private String task;
	private Date date;
	
	private ImportancePriority importance;
	private UrgencePriority urgence;
	private TaskStatus status;
	
	
	public Task() {
		
	}


	public Task(String task, ImportancePriority importance, UrgencePriority urgence, TaskStatus status) {
		super();
		this.task = task;
		// this.date = date;
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


	@Override
	public String toString() {
		return "Task [task=" + task + ", importance=" + importance + ", urgence=" + urgence
				+ ", status=" + status + "]";
	}
	
	
	
	
	
}
