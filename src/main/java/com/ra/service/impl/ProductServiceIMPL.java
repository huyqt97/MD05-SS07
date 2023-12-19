package com.ra.service.impl;

import com.ra.model.Product;
import com.ra.repository.ProductRepository;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        List<Product> list = (List<Product>) productRepository.findAll();
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> sortByName(String product) {
        if (product.equals("desc")) {
            return productRepository.findAll(Sort.by("productName").descending());
        } else if (product.equals("asc")) {
            return productRepository.findAll(Sort.by("productName").ascending());
        }
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getPaginate(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProductByName(String productName, Pageable pageable) {
        return productRepository.findAllByProductNameContainingIgnoreCase(productName, pageable);
    }
}
