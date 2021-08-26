package com.example.appwebhookbotserver.entity;

import com.example.appwebhookbotserver.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private String filePath;

    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private boolean isActive = true;

    private boolean isDiscount = false;

    private boolean isDeleted = false;

}

