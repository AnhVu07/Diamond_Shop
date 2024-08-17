package com.anhvu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.anhvu.exception.UserNotFoundException;
import com.anhvu.model.Users;
import com.anhvu.repository.UserRepository;

public class UserDetailsServiceImp implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepository.findUserByEmail(username)
				.orElseThrow(() -> new UserNotFoundException("Not found user by email: " + username));
		
		return new UsersDetail(users);
	}

}
