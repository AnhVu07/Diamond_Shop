package com.anhvu.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhvu.model.LoginAttempt;
import com.anhvu.repository.LoginAttemptRepository;

@Service
public class LoginAttemptService {
	
	private static final int MAX_ATTEMPTS = 3;
    private static final long LOCK_TIME_DURATION = 30;
    
    @Autowired
    LoginAttemptRepository attemptRepository;
    
    public void loginSucceeded(String username) {
    	 LoginAttempt loginAttempt = attemptRepository.findByUsername(username);
         if (loginAttempt != null) {
        	 attemptRepository.delete(loginAttempt);
         }
    }
    
    public void loginFailed(String username) {
        LoginAttempt loginAttempt = attemptRepository.findByUsername(username);
        if (loginAttempt == null) {
            loginAttempt = new LoginAttempt();
            loginAttempt.setUsername(username);
            loginAttempt.setAttempts(0);
        }
        loginAttempt.setAttempts(loginAttempt.getAttempts() + 1);
        loginAttempt.setLastModified(LocalDateTime.now());
        attemptRepository.save(loginAttempt);
    }
    
    
    public boolean isBlocked(String username) {
        LoginAttempt loginAttempt = attemptRepository.findByUsername(username);
        if (loginAttempt != null) {
            if (loginAttempt.getAttempts() >= MAX_ATTEMPTS) {
                if (loginAttempt.getLastModified().plusMinutes(LOCK_TIME_DURATION).isAfter(LocalDateTime.now())) {
                    return true;
                } else {
                	attemptRepository.delete(loginAttempt);
                }
            }
        }
        return false;
    }
}
