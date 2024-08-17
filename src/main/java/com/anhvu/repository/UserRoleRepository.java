package com.anhvu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhvu.model.Users_Roles;

@Repository
public interface UserRoleRepository extends JpaRepository<Users_Roles, Integer>{

}
