package com.ra.service;

import com.ra.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    Product save(Product product);

    void delete(Integer id);

    List<Product> sortByName(String product);

    Page<Product> getPaginate(Pageable pageable);

    Page<Product> getAllProductByName(String productName, Pageable pageable);
}
