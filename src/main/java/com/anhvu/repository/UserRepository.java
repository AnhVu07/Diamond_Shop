package com.anhvu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anhvu.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	@Query("select u from Users u where u.email= :email")
	Optional<Users> findUserByEmail(@Param(value = "email") String email);
	
	@Query("select u from Users u where u.resetPasswordToken = ?1")
	Optional<Users> getUserByAccessToken(String token);
	
	@Query("select max(u.id) from Users u")
	Integer getIdUserLatest();
}
