package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
