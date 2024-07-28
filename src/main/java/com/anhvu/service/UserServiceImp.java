package com.anhvu.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.exception.UserNotFoundException;
import com.anhvu.model.AuthenticationProvider;
import com.anhvu.model.Users;
import com.anhvu.model.Users_Roles;
import com.anhvu.repository.UserRepository;
import com.anhvu.repository.UserRoleRepository;
import com.anhvu.utils.UserInputSanitizer;

@Service
@Transactional
public class UserServiceImp implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Override
	@Transactional(readOnly = true)
	public Users getUserByEmail(String email) {
	    return userRepository.findUserByEmail(email)
	        .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " does not exist!"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Users> getListUsers() {

		return userRepository.findAll();
	}

	@Override
	@Transactional
	public void addUser(Users user) {
	    validateNewUser(user.getEmail());
	    prepareUserForSaving(user);
	    Users savedUser = saveUser(user);
	    assignDefaultRole(savedUser);
	}

	private void validateNewUser(String email) {
	    userRepository.findUserByEmail(email).ifPresent(u -> {
	        throw new IllegalStateException("Email is already in use");
	    });
	}

	private void prepareUserForSaving(Users user) {
	    user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
	    user.setAuthProvider(AuthenticationProvider.LOCAL);
	    user.setEnabled(true);
	    user.setDisplayName(UserInputSanitizer.sanitizer(user.getDisplayName()));
	    user.setAddress(UserInputSanitizer.sanitizer(user.getAddress()));
	}

	private Users saveUser(Users user) {
	    return userRepository.save(user);
	}

	private void assignDefaultRole(Users user) {
	    Users_Roles role = new Users_Roles(user.getId(), 10);
	    
	    userRoleRepository.save(role);
	}

	@Override
	@Transactional
	public void updateUser(Users updatedUser) {
	    Users existingUser = userRepository.findById(updatedUser.getId())
	        .orElseThrow(() -> new UserNotFoundException("User with id: " + updatedUser.getId() + " does not exist"));

	    updateDisplayName(existingUser, updatedUser.getDisplayName());
	    updateEmail(existingUser, updatedUser.getEmail());
	    updateAddressAndEnabled(existingUser, updatedUser.getAddress(), updatedUser.isEnabled());

	    userRepository.save(existingUser);
	}

	private void updateDisplayName(Users existingUser, String newDisplayName) {
	    if (StringUtils.isNotBlank(newDisplayName) && !Objects.equals(newDisplayName, existingUser.getDisplayName())) {
	        existingUser.setDisplayName(UserInputSanitizer.sanitizer(newDisplayName));
	    }
	}

	private void updateEmail(Users existingUser, String newEmail) {
	    if (StringUtils.isNotBlank(newEmail) && !Objects.equals(newEmail, existingUser.getEmail())) {
	    	validateNewUser(newEmail);
	        existingUser.setEmail(newEmail);
	    }
	}

	private void updateAddressAndEnabled(Users existingUser, String newAddress, Boolean enabled) {
	    if (enabled != null && StringUtils.isNotBlank(newAddress)) {
	        existingUser.setAddress(UserInputSanitizer.sanitizer(newAddress));
	        existingUser.setEnabled(enabled);
	    }
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		Users user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with id: " + id + " does not exist"));

		user.getRoles().forEach(role -> role.getUsers().remove(user));
		user.getRoles().clear();

		userRepository.delete(user);
	}

	@Override
	public Users getUsersById(int id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
	}

	@Override
	public Optional<Users> checkAccount(Users user) {
	    try {
	        Users userCheck = getUserByEmail(user.getEmail());
	        if (BCrypt.checkpw(user.getPassword(), userCheck.getPassword())) {
	            return Optional.of(userCheck);
	        }
	        return Optional.empty();
	    } catch (UserNotFoundException e) {
	        return Optional.empty();
	    }
	}

	@Override
	public void createNewUser(String email, String name, AuthenticationProvider provider) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty");
		}

		Users user = new Users();
		user.setEmail(email);
		user.setDisplayName(name != null ? name : "");
		user.setAuthProvider(provider);
		user.setEnabled(true);
		user.setAddress(email);
		user.setPassword("google");

		userRepository.save(user);
	}

	@Override
	public void updateUserAfterLoginOAth2Success(Users user, String name,
			com.anhvu.model.AuthenticationProvider google) {
		user.setAuthProvider(google);
		user.setDisplayName(name);

		userRepository.save(user);

	}

	@Override
	public void updateUserResetPasswordToken(String email, String token) {
		Users users = getUserByEmail(email);
		users.setResetPasswordToken(token);
		
		userRepository.save(users);

	}

	@Override
	public Users getUserByAccessToken(String token) {

		return userRepository.getUserByAccessToken(token)
				.orElseThrow(() -> new IllegalStateException("This user was not found! because Token not valid!"));
	}

	@Override
	public void updateUserPassword(String password, Users users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String newPass = passwordEncoder.encode(password);

		users.setPassword(newPass);
		users.setResetPasswordToken(null);

		userRepository.save(users);
	}

}
