package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Order;
import com.test.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<Order> getOrders(){
		
		System.err.println("************************************************************");
		
		List<Order> orders = orderService.getOrders();
		
		for(Order order:orders) {
			System.out.println(order.toString());
		}

		
		return orders; 
	}
	
	@GetMapping("/{category}")
	public List<Order>  getOrdersByCategory(@PathVariable String category){
		return orderService.getOrdersByCategory(category);
	}
	
}
