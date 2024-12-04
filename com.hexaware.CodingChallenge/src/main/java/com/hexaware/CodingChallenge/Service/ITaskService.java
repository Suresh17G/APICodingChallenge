package com.hexaware.CodingChallenge.Service;

import java.util.List;

import com.hexaware.CodingChallenge.DTO.TaskDTO;

import jakarta.validation.Valid;

public interface ITaskService {

	TaskDTO getTaskByID(int taskId);

	List<TaskDTO> getAllTasks();

	TaskDTO addTask(TaskDTO task);

	TaskDTO updateTask(int taskId, @Valid TaskDTO task);

	String deleteTaskByID(int taskId);


}
