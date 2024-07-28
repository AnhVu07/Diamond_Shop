package com.anhvu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.anhvu.model.Categorys;

@Repository
public interface CategoryRepository extends JpaRepository<Categorys, Integer> {

	@Query("select c from Categorys c where c.name=?1")
	Optional<Categorys> findCategoryByName(String name);
}
