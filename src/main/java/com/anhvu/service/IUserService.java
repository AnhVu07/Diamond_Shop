package com.anhvu.service;

import java.util.List;
import java.util.Optional;

import com.anhvu.model.AuthenticationProvider;
import com.anhvu.model.Users;


public interface IUserService {
	
	Users getUserByEmail(String email);
	
	List<Users> getListUsers();
	
	void addUser(Users users);
	
	void updateUser(Users users);
	
	void deleteUser(int id);
	
	Users getUsersById(int id);
	
	Optional<Users> checkAccount(Users user);
	
	void createNewUser(String email, String name, AuthenticationProvider provider);
	
	void updateUserAfterLoginOAth2Success(Users user, String name, AuthenticationProvider google);
	
	void updateUserResetPasswordToken(String email, String token);
	
	Users getUserByAccessToken(String token);
	
	void updateUserPassword(String password, Users users);
	
}
