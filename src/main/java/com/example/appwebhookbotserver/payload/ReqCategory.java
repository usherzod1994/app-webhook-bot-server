package com.example.appwebhookbotserver.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqCategory {
    @NotBlank
    private String name;

    private int parentId;
}
