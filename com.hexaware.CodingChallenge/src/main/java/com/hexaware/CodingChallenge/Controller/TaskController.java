package com.hexaware.CodingChallenge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CodingChallenge.DTO.TaskDTO;
import com.hexaware.CodingChallenge.Exception.TaskNotFoundException;
import com.hexaware.CodingChallenge.Service.ITaskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class TaskController {

	
	@Autowired
	ITaskService service;
	
	@GetMapping("/task/getAllTasks")
	public ResponseEntity<List<TaskDTO> > getAllTasks() throws TaskNotFoundException{
		
		List<TaskDTO> task=service.getAllTasks();
		if(task==null) {
			
			throw new TaskNotFoundException("No Tasks found!!");
		}
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	@GetMapping("/task/getTaskbyId/{taskId}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable int taskId) throws TaskNotFoundException{
		
		TaskDTO task=service.getTaskByID(taskId);
		if(task==null) {
			
			throw new TaskNotFoundException("Task does not exist!!");
		}
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	@PostMapping("/task/addTask")
	public ResponseEntity<TaskDTO> addTask(@Valid @RequestBody TaskDTO task) throws TaskNotFoundException{
		
		TaskDTO taskdto=service.addTask(task);
		
		return new ResponseEntity<>(taskdto,HttpStatus.CREATED);
	}
	
	@PutMapping("/task/updateTask/{taskId}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable int taskId,@Valid @RequestBody TaskDTO task) throws TaskNotFoundException{
		
		TaskDTO taskdto=service.updateTask(taskId,task);
		if(taskdto==null) {
			throw new TaskNotFoundException("Task does not exist!!");
		}
		return new ResponseEntity<>(taskdto,HttpStatus.OK);
	}
	
	@DeleteMapping("/task/deleteTaskbyId/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable int taskId) throws TaskNotFoundException{
		
		String task=service.deleteTaskByID(taskId);
		if(task==null) {
			throw new TaskNotFoundException("Task does not exist!!");
		}
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
}
