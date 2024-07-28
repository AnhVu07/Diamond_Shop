package com.anhvu.dto;

import java.util.Date;

public class OrderDto {
	private Date ngay;
	private long total_orders;
	private double total_revenue;

	public OrderDto() {
		
	}

	public OrderDto(Date ngay, long total_orders, double total_revenue) {
		this.ngay = ngay;
		this.total_orders = total_orders;
		this.total_revenue = total_revenue;
	}
	
	

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public long getTotal_orders() {
		return total_orders;
	}

	public void setTotal_orders(long total_orders) {
		this.total_orders = total_orders;
	}

	public double getTotal_revenue() {
		return total_revenue;
	}

	public void setTotal_revenue(double total_revenue) {
		this.total_revenue = total_revenue;
	}

	@Override
	public String toString() {
		return "OrderDto [ngay=" + ngay + ", total_orders=" + total_orders + ", total_revenue=" + total_revenue + "]";
	}

}
