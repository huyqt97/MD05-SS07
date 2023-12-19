package com.ra.controller;

import com.ra.model.Product;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAll() {
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {

        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product productNew = productService.save(product);
        if (productNew == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productNew, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Integer id) {
        if (productService.findById(id) != null) {
            Product productNew = productService.save(product);
            return new ResponseEntity<>(productNew, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Integer id) {
        if (productService.findById(id) != null) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sort(@RequestParam(required = false) String product) {
        if (product != null) {
            List<Product> list = productService.sortByName(product);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/paginate")
    public ResponseEntity<Map<String, Object>> paginate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int limit
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Product> productPage = productService.getPaginate(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("product", productPage.getContent());
        data.put("total", productPage.getSize());
        data.put("totalElement", productPage.getTotalElements());
        data.put("totalPages", productPage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> search(
            @RequestParam String keyword,
            @PageableDefault(size = 2, page = 0) Pageable pageable) {
        Page<Product> productPage = productService.getAllProductByName(keyword, pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}
