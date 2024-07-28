package com.anhvu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anhvu.model.Bills;
import com.anhvu.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	void deleteByOrderId(Bills orderId);
	boolean existsByOrderId(Bills orderId);

}
