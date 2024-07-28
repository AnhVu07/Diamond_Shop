package com.anhvu.service;

import java.util.HashMap;
import java.util.List;

import com.anhvu.dto.BillsDto;
import com.anhvu.dto.CartDto;
import com.anhvu.dto.OrderDto;
import com.anhvu.model.Bills;
import com.anhvu.model.Billsdetail;


public interface IBillService {
	
	Bills getLastestBill();
	
	Bills addBill(Bills bills);
	
	void deleteBill(int id);
	
	boolean ExitsById(int id);
	
	Bills getBillById(int id);
	
	Billsdetail addBillDetail(Billsdetail bills);
	
	void addBillDetail(HashMap<Long, CartDto> cart);
	
	List<BillsDto> getListOrders();
	
	List<OrderDto> getDataOrders();

	List<BillsDto> getListOrdersHistory();
	
	byte [] generateOrderPdf(int id);
	
}
