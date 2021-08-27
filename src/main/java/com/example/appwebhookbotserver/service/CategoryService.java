package com.example.appwebhookbotserver.service;


import com.example.appwebhookbotserver.entity.Category;
import com.example.appwebhookbotserver.payload.ReqCategory;
import com.example.appwebhookbotserver.payload.ResCategory;
import com.example.appwebhookbotserver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ResCategory> getAllCategores(){
        List<Category> categoriesList = categoryRepository.getCategories();
        Map<Integer, ResCategory> resCategoryMap = new HashMap<>();

        categoriesList.forEach(category -> {
            resCategoryMap.put(category.getId(), new ResCategory(category.getId(), category.getParentId(), category.getName()));
        });

        List<ResCategory> reqCategories = new ArrayList<>();
        Set<Integer> integers = resCategoryMap.keySet();
        integers.forEach(integer -> {
            ResCategory resCategory = resCategoryMap.get(integer);
            if (resCategory.getParentId() == 0){
                reqCategories.add(resCategoryMap.get(integer));
            }else {
                if (resCategoryMap.containsKey(resCategory.getParentId())){
                    resCategoryMap.get(resCategory.getParentId()).getChildren().add(resCategory);
                }
            }
        });
        return reqCategories;

    }

    public boolean save(ReqCategory reqCategory){
        Optional<Category> optionalCategory = categoryRepository.findByName(reqCategory.getName());
        if (optionalCategory.isPresent()){
            return false;
        }
        Category category = new Category();
        category.setId(getMaxId() + 1);
        category.setName(reqCategory.getName());
        if (reqCategory.getParentId() > 0){
            category.setParentId(reqCategory.getParentId());
        }
        categoryRepository.save(category);
        return true;
    }

    public int getMaxId() {
        if (categoryRepository.getCount() == 0){
            return 0;
        }
        return categoryRepository.getMaxId();
    }

    public boolean delete(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setDeleted(true);
            categoryRepository.save(category);
            return true;
        }else {
            return false;
        }
    }
}
