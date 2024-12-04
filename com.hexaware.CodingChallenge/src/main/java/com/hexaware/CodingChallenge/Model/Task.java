package com.hexaware.CodingChallenge.Model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int taskId;
	
	@NotNull
	String title;
	
	String description;
	
	@NotNull
	LocalDate dueDate;
	
	@Enumerated(EnumType.STRING)
	Priority priority;
	
	@Enumerated(EnumType.STRING)
	Status status;
	
	
	public enum Priority {
        LOW, MEDIUM, HIGH
    } 
    public enum Status {
        PENDING, INPROGRESS, COMPLETED
    }
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(int taskId, @NotNull String title, String description, @NotNull LocalDate dueDate, Priority priority,
			Status status) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.status = status;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", title=" + title + ", Description=" + description + ", dueDate=" + dueDate
				+ ", priority=" + priority + ", status=" + status + "]";
	}
    
}
