package com.anhvu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhvu.model.Menus;

@Repository
public interface MenuRepository extends JpaRepository<Menus, Integer> {

}
