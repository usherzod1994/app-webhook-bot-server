package com.example.appwebhookbotserver.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqUpdateCategory {
    private Integer id;

    @NotBlank
    private String name;

    private int parentId;

    public ReqUpdateCategory() {
    }

    public ReqUpdateCategory(Integer id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
