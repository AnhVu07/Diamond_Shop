package com.anhvu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.anhvu.security.jwt.JwtTokenFilter;
import com.anhvu.security.oauth.AuthenticationOAuth2Success;
import com.anhvu.security.oauth.CustomerOAuth2UserService;
import com.anhvu.service.LoginAttemptService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = false,
		securedEnabled = false,
		jsr250Enabled = true
		)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	private AuthenticationOAuth2Success auth2Success;
	
	@Autowired
	private CustomerOAuth2UserService auth2UserService;
	
	@Autowired
    private LoginAttemptService loginAttemptService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	
	@Bean
	public UserDetailsService getUsersDetailsService() {
		return new UserDetailsServiceImp();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider configurer = new DaoAuthenticationProvider();
		
		configurer.setUserDetailsService(getUsersDetailsService());
		configurer.setPasswordEncoder(passwordEncoder());
		return configurer;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
//	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	    http
	        .authorizeRequests()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/home","/assets/**","/assets/user/img_review/**","/WEB-INF/views/**","/register","/detail/**",
	                     "/about_us","/getPByPaginationHome/**","/category/**","/dang-ky","/search/**",
	                     "/forgot_password","/reset_password/**","/form_forgot_password", "/captcha").permitAll()
	        .antMatchers("/auth/login").permitAll()
	        .antMatchers("/oauth2/**").permitAll()
	        .anyRequest().authenticated();

	    http
	        .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .successHandler(successHandler);

	    http
	        .oauth2Login()
	        .loginPage("/login")
	        .userInfoEndpoint().userService(auth2UserService)
	        .and()
	        .successHandler(auth2Success);

	    http
	        .logout()
	        .logoutUrl("/logout")
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID")
	        .permitAll();

	    http
	        .exceptionHandling().accessDeniedPage("/403");

	    http.csrf().disable();
	    
	    http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	
	}
	
	@Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setLoginAttemptService(loginAttemptService);
        
        return filter;
    }
	
}
