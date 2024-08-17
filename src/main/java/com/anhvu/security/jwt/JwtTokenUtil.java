package com.anhvu.security.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.anhvu.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;// 24h

	@Value("${app.jwt.secret}")
	private String key_secret;

	public String createAccessToken(Users user) {
		 Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
		return Jwts.builder()
		.setSubject(user.getId() + "," + user.getEmail())
		.claim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
		.setIssuer("AnhVu")
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
		.signWith(SignatureAlgorithm.HS512, key_secret)
		.compact();

	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(key_secret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			LOGGER.error("jwt not support",e);
		} catch (ExpiredJwtException e) {
			LOGGER.error("jwt expired",e);
		} catch (MalformedJwtException e) {
			LOGGER.error("jwt is invalid",e);
		} catch (SignatureException e) {
			LOGGER.error("Signature validation failed",e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("token is null or empty",e);
		}
		
		return false;
	}
	
	public String getSuject(String token) {
		return parseClaim(token).getSubject();
	}
	
	public Claims parseClaim(String token) {
		return Jwts.parser().setSigningKey(key_secret).parseClaimsJws(token).getBody();
	}
	
}
