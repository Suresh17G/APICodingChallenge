package com.hexaware.CodingChallenge.Test;



import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hexaware.CodingChallenge.Controller.TaskController;
import com.hexaware.CodingChallenge.DTO.TaskDTO;
import com.hexaware.CodingChallenge.Service.TaskServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {

    @Mock
    private TaskServiceImpl service;

    @InjectMocks
    private TaskController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTask_Success() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Sample task");
        taskDTO.setDescription("Sample Description");
        taskDTO.setDueDate(LocalDate.of(2024, 10, 11));

        when(service.addTask(any(TaskDTO.class))).thenReturn(taskDTO);

        ResponseEntity<TaskDTO> response = adminController.addTask(taskDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Sample task", response.getBody().getTitle());
        assertEquals("Sample Description", response.getBody().getDescription());
        assertEquals(LocalDate.of(2024, 10, 11), response.getBody().getDueDate());
    }

    @Test
    void testeditTask_Success() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Sample task");
        taskDTO.setDescription("Sample Description");
        taskDTO.setDueDate(LocalDate.of(2024, 11, 14));

        when(service.updateTask(anyInt(),any(TaskDTO.class))).thenReturn(taskDTO);

        ResponseEntity<TaskDTO> response = adminController.updateTask(1,taskDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sample task", response.getBody().getTitle());
        assertEquals("Sample Description", response.getBody().getDescription());
        assertEquals(LocalDate.of(2024, 11, 14), response.getBody().getDueDate());
    }


}

