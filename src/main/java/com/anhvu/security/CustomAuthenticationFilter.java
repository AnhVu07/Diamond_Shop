package com.anhvu.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.anhvu.model.AuthResponse;
import com.anhvu.security.jwt.JwtTokenUtil;
import com.anhvu.service.LoginAttemptService;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    @Autowired
	AuthenticationManager auth;
    
   
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String captchaCode = request.getParameter("captcha");
        
        String sessionCaptchaCode = (String) request.getSession().getAttribute("captchaCode");

        if (!captchaCode.equals(sessionCaptchaCode)) {
            request.setAttribute("error", "Invalid captcha. Please try again.");
            return null;
        }
        
        if (loginAttemptService.isBlocked(username)) {
        	request.setAttribute("error", "Your account is blocked. Please try again.");
           return null;
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        
        
        Authentication authentication = auth
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		UsersDetail usersDetail = (UsersDetail) authentication.getPrincipal();
		String token = jwtTokenUtil.createAccessToken(usersDetail.getUser());
		System.out.println(token);
		AuthResponse authResponse = new AuthResponse(usersDetail.getUser().getEmail(), token);
		
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = obtainUsername(request);
        loginAttemptService.loginSucceeded(username);
       
        super.successfulAuthentication(request, response, chain, authResult); 
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    	String username = obtainUsername(request);

    	loginAttemptService.loginFailed(username);
    	 request.setAttribute("error", "Invalid username or password!");
        
        super.unsuccessfulAuthentication(request, response, failed);
    }

    public void setLoginAttemptService(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;

    }
    
   
}

