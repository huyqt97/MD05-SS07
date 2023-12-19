package com.ra.repository;

import com.ra.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByProductNameContainingIgnoreCase(String keyword, Pageable pageable);

}
