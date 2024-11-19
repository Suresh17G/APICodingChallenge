package com.hexaware.CodingChallenge.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.CodingChallenge.Model.Users;
import com.hexaware.CodingChallenge.Model.Users.Role;


public interface UserRepo extends JpaRepository<Users,Long> {

	public Optional<Users> findByUsername(String username);
	
	public Users findByRole(Role role);
		
}


