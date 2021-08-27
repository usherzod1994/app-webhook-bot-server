package com.example.appwebhookbotserver.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResCategory {
    private int value;
    private int key;
    private int parentId;
    private String title;
    private String name;
    private int photoId;
    private List<ResCategory> children = new ArrayList<>();


    public ResCategory() {
    }

    public ResCategory(int value, int parentId, String name) {
        this.value = value;
        this.key = value;
        this.parentId = parentId;
        this.title = name;
    }

    public ResCategory(int value, int parentId, String name, int photoId) {
        this.value = value;
        this.key = value;
        this.parentId = parentId;
        this.name = name;
        this.title = name;
        this.photoId = photoId;
    }

    public ResCategory(int value, int parentId, String name, List<ResCategory> children) {
        this.value = value;
        this.key = value;
        this.parentId = parentId;
        this.name = name;
        this.title = name;
        this.children = children;
    }
}
