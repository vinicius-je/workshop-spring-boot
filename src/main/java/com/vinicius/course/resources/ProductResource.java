package com.vinicius.course.resources;

import com.vinicius.course.entities.Product;
import com.vinicius.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService productServiceService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productServiceService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable Long id){
        return productServiceService.findById(id);
    }
}
