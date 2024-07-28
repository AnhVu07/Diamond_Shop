package com.anhvu.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.anhvu.model.AuthenticationProvider;
import com.anhvu.repository.UserRepository;
import com.anhvu.service.IUserService;

@Component
public class AuthenticationOAuth2Success extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	IUserService service;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		CustomerOAuth2User oAuth2User = (CustomerOAuth2User) authentication.getPrincipal();
		String email = oAuth2User.getEmail();
		String name = oAuth2User.getFullName();

		userRepository.findUserByEmail(email).ifPresentOrElse(
				user -> service.updateUserAfterLoginOAth2Success(user, name, AuthenticationProvider.GOOGLE),
				() -> service.createNewUser(email, name, AuthenticationProvider.GOOGLE));

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
