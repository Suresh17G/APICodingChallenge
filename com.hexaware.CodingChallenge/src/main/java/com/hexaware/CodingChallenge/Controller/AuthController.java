package com.hexaware.CodingChallenge.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CodingChallenge.DTO.UsersDTO;
import com.hexaware.CodingChallenge.Exception.UserExistsException;
import com.hexaware.CodingChallenge.Service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UsersDTO user) {
		
		String login= authService.authenticateUser(user);
		return new ResponseEntity<>(login,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UsersDTO user) throws UserExistsException {
		
		String newuser= authService.addUser(user);
		if(newuser==null) {
			throw new UserExistsException("Username: "+user.getUsername()+" already Exists!!");
		}
		return new ResponseEntity<>(newuser,HttpStatus.ACCEPTED);
	}
		
}
