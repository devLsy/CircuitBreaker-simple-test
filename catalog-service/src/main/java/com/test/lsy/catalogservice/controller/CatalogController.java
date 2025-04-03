package com.test.lsy.catalogservice.controller;

import com.test.lsy.catalogservice.model.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

    private final List<OrderDTO> productList = List.of(
            new OrderDTO(1, "mobile", "electronics", "white", 20000),
            new OrderDTO(2, "T-Shirt", "clothes", "black", 999),
            new OrderDTO(3, "Jeans", "clothes", "blue", 1999),
            new OrderDTO(4, "Laptop", "electronics", "gray", 50000),
            new OrderDTO(5, "digital watch", "electronics", "black", 2500),
            new OrderDTO(6, "Fan", "electronics", "black", 50000)
    );

    @GetMapping
    public List<OrderDTO> getCatalog(@RequestParam(value = "category", required = false) String category) {
        log.info("getCatalog :: {}", category);
        if(category == null) return productList;
        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}
