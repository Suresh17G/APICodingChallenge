package com.hexaware.CodingChallenge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.CodingChallenge.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
