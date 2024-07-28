package com.anhvu.security.jwt;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anhvu.model.Roles;
import com.anhvu.model.Users;
import com.anhvu.security.UsersDetail;

import io.jsonwebtoken.Claims;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String accessToken = getAccessToken(request);

        if (accessToken == null || !jwtTokenUtil.validateAccessToken(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(accessToken, request);
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
        UsersDetail userDetail = (UsersDetail) getUserDetail(accessToken);
        System.out.println(userDetail);
        System.out.println("token:"+accessToken);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UserDetails getUserDetail(String accessToken) {
        Users user = new Users();
        Claims claims = jwtTokenUtil.parseClaim(accessToken);
        
        List<String> roles = (List<String>) claims.get("roles");
        
        for (String roleName : roles) {
            user.getRoles().add(new Roles(roleName));
        }
        
        String subject = (String) claims.get(Claims.SUBJECT);
        String[] subjectArray = subject.split(",");
        user.setId(Integer.parseInt(subjectArray[0]));
        user.setEmail(subjectArray[1]);
        
        return new UsersDetail(user);
    }


    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.split(" ")[1].trim();
    }

    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ");
    }
}