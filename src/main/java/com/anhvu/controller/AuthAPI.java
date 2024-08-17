package com.anhvu.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.model.AuthResponse;
import com.anhvu.security.UsersDetail;
import com.anhvu.security.jwt.JwtTokenUtil;

		@RestController
		public class AuthAPI {
		
			@Autowired
			JwtTokenUtil jwtTokenUtil;
			
			@Autowired
			AuthenticationManager auth;
			
			@PostMapping("/auth/login")
			public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
				try {
					Authentication authentication = auth
							.authenticate(new UsernamePasswordAuthenticationToken(username, password));
					UsersDetail usersDetail = (UsersDetail) authentication.getPrincipal();
					String accessToken = jwtTokenUtil.createAccessToken(usersDetail.getUser());
					System.out.println(accessToken);
					AuthResponse authResponse = new AuthResponse(usersDetail.getUser().getEmail(), accessToken);
					return ResponseEntity.ok(authResponse);
				} catch (BadCredentialsException e) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
				
			}
		}
