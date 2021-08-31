package com.example.appwebhookbotserver.entity;

import java.util.Comparator;

public class CategoryComparator implements Comparator<Category> {
    @Override
    public int compare(Category category1, Category category2) {
        return category1.getId().compareTo(category2.getId());
    }
}
