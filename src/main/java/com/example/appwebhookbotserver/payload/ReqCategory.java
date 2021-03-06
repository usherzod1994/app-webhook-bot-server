package com.example.appwebhookbotserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqCategory {
    private Integer id;
    @NotBlank
    private String name;

    private int parentId;
}
