package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

    @Query(value = " select * from category where deleted = false order by id", nativeQuery = true)
    List<Category> getCategories();

    @Query(value = "select count(*) from category",nativeQuery = true)
    Integer getCount();

    @Query(value = "SELECT max(id) FROM category",nativeQuery = true)
    Integer getMaxId();

}
