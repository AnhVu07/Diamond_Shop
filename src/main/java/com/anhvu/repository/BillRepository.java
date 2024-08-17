package com.anhvu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anhvu.dto.BillsDto;
import com.anhvu.dto.OrderDto;
import com.anhvu.model.Bills;

@Repository
public interface BillRepository extends JpaRepository<Bills, Integer>{
	@Query("SELECT b FROM Bills b WHERE b.id = (SELECT MAX(b2.id) FROM Bills b2)")
	Bills getLastestBill();
	
	@Query("SELECT new com.anhvu.dto.BillsDto(b.id, b.users, b.phone, b.displayName, b.note,b.address,"
			+ " bd.quatity, bd.total, bd.product, p.name, p.price, p.image, p.detail, p.category) "
			+ "FROM Bills b INNER JOIN Billsdetail bd ON b.id = bd.bill "
			+ "INNER JOIN Products p ON bd.product = p.idProducts")
	List<BillsDto> getListOrders();
	
	@Query("SELECT new com.anhvu.dto.BillsDto(b.id, b.users, b.phone, b.displayName, b.note,b.address,"
			+ " bd.quatity, bd.total, bd.product, p.name, p.price, p.image, p.detail, p.category) "
			+ "FROM Bills b INNER JOIN Billsdetail bd ON b.id = bd.bill "
			+ "INNER JOIN Products p ON bd.product = p.idProducts "
			+ "WHERE b.users = :email")
	List<BillsDto> getListOrdersHistory(@Param("email") String email);
	
	@Query("SELECT new com.anhvu.dto.BillsDto(b.id, b.users, b.phone, b.displayName, b.note,b.address,"
			+ " bd.quatity, bd.total, bd.product, p.name, p.price, p.image, p.detail, p.category) "
			+ "FROM Bills b INNER JOIN Billsdetail bd ON b.id = bd.bill "
			+ "INNER JOIN Products p ON bd.product = p.idProducts "
			+ "WHERE b.id = :id")
	List<BillsDto> generateOrdersPdf(@Param("id") int id);
	
	@Query("SELECT new com.anhvu.dto.OrderDto( FUNCTION('DATE', b.created_at), COUNT(b.id), SUM(b.total)) "
			+ "FROM Bills b GROUP BY FUNCTION('DATE', b.created_at) ORDER BY FUNCTION('DATE', b.created_at)")
	List<OrderDto> getDataOrders();

}
