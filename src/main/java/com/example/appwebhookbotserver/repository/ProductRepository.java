package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
