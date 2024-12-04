package com.hexaware.CodingChallenge.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.CodingChallenge.Model.Users;
import com.hexaware.CodingChallenge.Repository.UserRepo;


@Service
public class UserInfoUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user=userRepo.findByUsername(username);
		if(user.isPresent()) {
			Users userObj=user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(userObj.getRole().name())
					.build();
		}else {
			throw new UsernameNotFoundException(username);
		}
	}



}
