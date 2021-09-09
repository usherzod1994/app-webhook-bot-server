package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Category;
import com.example.appwebhookbotserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = " select * from product where is_deleted = false order by id", nativeQuery = true)
    List<Product> getProducts();

}
