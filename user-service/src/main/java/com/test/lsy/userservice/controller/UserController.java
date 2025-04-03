package com.test.lsy.userservice.controller;

import com.test.lsy.userservice.model.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final RestTemplate restTemplate;
    public static final String USER_SERVICE="userService";
    private static final String BASE_URL = "http://localhost:8081/catalog";
    private int attemp = 1;

    @GetMapping("/displayOrders")
//    @CircuitBreaker(name = "catalogService", fallbackMethod = "getAllAvailableProducts")
//    @Retry(name = USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
    public List<OrderDto> getOrders(@RequestParam(name = "category",required = false) String category) throws Exception{

        String url = category == null ? BASE_URL : BASE_URL + "?category=" + category;
        log.info("url : {}", url);
        log.info("retry method called " + attemp + " times at {}", new Date());
        return restTemplate.getForObject(url, ArrayList.class);
    }
    // fallback method
    public List<OrderDto> getAllAvailableProducts(Exception e){
        return Stream.of(
                new OrderDto(120, "TEMP_TV", "electronics", "white", 450000),
                new OrderDto(119, "LED TV", "electronics", "white", 45000),
                new OrderDto(345, "Headset", "electronics", "black", 7000),
                new OrderDto(475, "Sound bar", "electronics", "black", 13000),
                new OrderDto(574, "Puma Shoes", "foot wear", "black & white", 4600),
                new OrderDto(678, "Vegetable chopper", "kitchen", "blue", 999),
                new OrderDto(532, "Oven Gloves", "kitchen", "gray", 745)
        ).collect(Collectors.toList());
    }
}
