package com.test.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.test.dto.OrderDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.experimental.var;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/userservice")
public class OrderController {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    public static final String USER_SERVICE="userService";

    private static final String BASEURL = "http://localhost:9090/orders";

    private int attempt=1;


    @GetMapping("/displayOrders")
    @CircuitBreaker(name =USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
    //@Retry(name = USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
    public List<OrderDTO> displayOrders(@RequestParam("category") String category) {
        String url = category.isBlank() ? BASEURL+"/all" : BASEURL + "/" + category;
        System.err.println("retry method called "+attempt++ +" times "+" at "+new Date());
//        System.out.println(url);
        return restTemplate.getForObject(url, ArrayList.class);
    }


    public List<OrderDTO> getAllAvailableProducts(Exception e){
        return Stream.of(
                new OrderDTO(119, "LED TV", "electronics", "white", 45000),
                new OrderDTO(345, "Headset", "electronics", "black", 7000),
                new OrderDTO(475, "Sound bar", "electronics", "black", 13000),
                new OrderDTO(574, "Puma Shoes", "foot wear", "black & white", 4600),
                new OrderDTO(678, "Vegetable chopper", "kitchen", "blue", 999),
                new OrderDTO(532, "Oven Gloves", "kitchen", "gray", 745)
        ).collect(Collectors.toList());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	
}
