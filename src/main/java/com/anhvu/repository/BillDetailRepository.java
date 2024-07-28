package com.anhvu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anhvu.model.Billsdetail;

@Repository
public interface BillDetailRepository extends JpaRepository<Billsdetail, Integer> {

}
