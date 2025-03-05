package com.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Order;
import com.test.repository.OrderRepository;
import com.test.service.OrderService;

import jakarta.annotation.PostConstruct;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getOrders() {
		
//		System.out.println("************************************************************");
//		System.out.println("get orders called");
//		System.out.println(orderRepository.findAll());
//		
//		for(Order order:orderRepository.findAll()) {
//			System.out.println(order.toString());
//		}
		
		return orderRepository.findAll();
	}

	@Override
	public List<Order> getOrdersByCategory(String category) {
		// TODO Auto-generated method stub
		return orderRepository.findByCategory(category);
	}

	
  @PostConstruct
  public void initOrdersTable() {
      orderRepository.saveAll(Stream.of(
                      new Order("mobile", "electronics", "white", 20000d),
                      new Order("T-Shirt", "clothes", "black", 999d),
                      new Order("Jeans", "clothes", "blue", 1999d),
                      new Order("Laptop", "electronics", "gray", 50000d),
                      new Order("digital watch", "electronics", "black", 2500d),
                      new Order("Fan", "electronics", "black", 50000d)
              ).
              collect(Collectors.toList()));
  }

}
