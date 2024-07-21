package br.com.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAllProducts() {
        List<Map<String, Object>> productList = createFakeProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    private List<Map<String, Object>> createFakeProducts() {
        List<Map<String, Object>> productList = new ArrayList<>();

        Map<String, Object> product1 = new HashMap<>();
        product1.put("name", "Camiseta");
        product1.put("price", 29.99);

        Map<String, Object> product2 = new HashMap<>();
        product2.put("name", "Calça Jeans");
        product2.put("price", 79.90);

        Map<String, Object> product3 = new HashMap<>();
        product3.put("name", "Tênis esportivo");
        product3.put("price", 129.99);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        return productList;
    }
}