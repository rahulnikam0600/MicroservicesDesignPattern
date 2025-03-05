package com.test.service;

import java.util.List;

import com.test.entity.Order;

public interface OrderService {

	public List<Order> getOrders();
	public List<Order> getOrdersByCategory(String category);
	
}
