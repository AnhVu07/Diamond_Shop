package com.anhvu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhvu.model.Slides;

@Repository
public interface SlideRepository extends JpaRepository<Slides, Integer>{

}
