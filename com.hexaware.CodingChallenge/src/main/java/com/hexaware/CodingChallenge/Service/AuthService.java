package com.hexaware.CodingChallenge.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.CodingChallenge.Config.JWTService;
import com.hexaware.CodingChallenge.Config.UserInfoUserDetailsService;
import com.hexaware.CodingChallenge.DTO.ResponseDTO;
import com.hexaware.CodingChallenge.DTO.UsersDTO;
import com.hexaware.CodingChallenge.Model.Users;
import com.hexaware.CodingChallenge.Model.Users.Role;
import com.hexaware.CodingChallenge.Repository.UserRepo;

import jakarta.annotation.PostConstruct;

@Service
public class AuthService {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JWTService jwtService;

	@Autowired
	UserInfoUserDetailsService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepo userRepo;

	@PostConstruct
	public void init() {
		if (userRepo.findByRole(Role.ADMIN) == null) {
			
			Users admin = new Users();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("Admin@123")); // hashed password
			admin.setRole(Role.ADMIN);
			userRepo.save(admin);
		}
	}

	public ResponseDTO authenticateUser(UsersDTO user) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			ResponseDTO response=new ResponseDTO();
			Users u=userRepo.findByUsername(user.getUsername()).orElse(null);
			String jwt=jwtService.generateToken(userService.loadUserByUsername(user.getUsername()));
			response.setJwt(jwt);
			response.setUserid(u.getUserId());
			response.setRole(u.getRole().name());
			return response;
			
		} else {
			throw new UsernameNotFoundException("Invalid Username and Password");
		}
	}

	public String addUser(UsersDTO u) {
		if (userRepo.findByUsername(u.getUsername()).isEmpty()) {

			Users user = new Users();
			user.setUsername(u.getUsername());
			user.setPassword(passwordEncoder.encode(u.getPassword()));
			user.setRole(Users.Role.USER);
			Users savedUser = userRepo.save(user);
			return "New user Created with Username: "+savedUser.getUsername();
		}
		return null;
	}
}
