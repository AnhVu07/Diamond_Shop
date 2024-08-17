package com.anhvu.repository;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anhvu.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	@Cacheable(value = "usersCache", key = "#id")
	Optional<Users> findById(Integer id);

	@CachePut(value = "usersCache", key = "#user.id")
	Users save(Users user);

	@CacheEvict(value = "usersCache", key = "#id")
	void deleteById(Integer id);
	
	@Cacheable(value = "usersCache", key = "#email")
	@Query(value = "select u from Users u where u.email= :email")
	Optional<Users> findUserByEmail(@Param(value = "email") String email);
	
	@Query(value = "select u from Users u where u.resetPasswordToken = ?1")
	Optional<Users> getUserByAccessToken(String token);
	
	@Query(value = "select max(u.id) from Users u")
	Integer getIdUserLatest();
}
