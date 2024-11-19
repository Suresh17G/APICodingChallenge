package com.hexaware.CodingChallenge.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CodingChallenge.DTO.TaskDTO;
import com.hexaware.CodingChallenge.Model.Task;
import com.hexaware.CodingChallenge.Repository.TaskRepository;

import jakarta.validation.Valid;

@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public TaskDTO getTaskByID(int taskId) {
		Task task=taskRepo.findById(taskId).orElse(null);
		if(task!=null) {
			return modelMapper.map(task, TaskDTO.class);
		}
		return null;
	}

	@Override
	public List<TaskDTO> getAllTasks() {
		List<Task> list=taskRepo.findAll();
		if(list.isEmpty()) {
			return null;
		}
		return list.stream().map(i->modelMapper.map(i, TaskDTO.class)).toList();
	}

	@Override
	public TaskDTO addTask(TaskDTO task) {
		Task t=modelMapper.map(task, Task.class);
		Task saved=taskRepo.save(t);
		return modelMapper.map(saved, TaskDTO.class);
	}

	@Override
	public TaskDTO updateTask(int taskId, @Valid TaskDTO task) {
		Task t=taskRepo.findById(taskId).orElse(null);
		if(t==null) {
			return null;
		}
		if(task.getDescription()!=null) {
			t.setDescription(task.getDescription());
		}
		if(task.getTitle()!=null) {
			t.setTitle(task.getTitle());
		}
		if(task.getDueDate()!=null) {
			t.setDueDate(task.getDueDate());
		}
		if(task.getPriority()!=null) {
			t.setPriority(task.getPriority());
		}
		if(task.getStatus()!=null) {
			t.setStatus(task.getStatus());
		}
		Task updated=taskRepo.save(t);
		
		return modelMapper.map(updated, TaskDTO.class);
	}

	@Override
	public String deleteTaskByID(int taskId) {
		TaskDTO t=getTaskByID(taskId);
		if(t==null) {
			return null;
		}
		taskRepo.deleteById(taskId);
		return "Task with Id: "+taskId+" was deleted Successfully!!";
	}


	
	
}
