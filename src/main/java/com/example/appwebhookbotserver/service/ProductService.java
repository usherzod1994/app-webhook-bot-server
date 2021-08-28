package com.example.appwebhookbotserver.service;

import com.example.appwebhookbotserver.entity.Product;
import com.example.appwebhookbotserver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.getProducts();
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
