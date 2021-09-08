package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

    List<Category> findAllByParentId(int parentId);

    @Query(value = " select * from category where deleted = false order by id", nativeQuery = true)
    List<Category> getCategories();

    @Query(value = " select * from category where deleted = false and is_child_product = false order by id", nativeQuery = true)
    List<Category> getCreateCategories();

    @Query(value = " select * from category where deleted = false and parent_id = 0 order by id", nativeQuery = true)
    List<Category> getParentCategories();

    @Query(value = " select * from category where deleted = false and parent_id != 0 and is_child_category = false order by id", nativeQuery = true)
    List<Category> getCreateProductCategories();

    @Query(value = "select count(*) from category",nativeQuery = true)
    Integer getCount();

    @Query(value = "select count(*) from category where parent_id = :parentId",nativeQuery = true)
    Integer getChildCount(@Param("parentId") int parentId);



    @Query(value = "SELECT max(id) FROM category",nativeQuery = true)
    Integer getMaxId();

}
