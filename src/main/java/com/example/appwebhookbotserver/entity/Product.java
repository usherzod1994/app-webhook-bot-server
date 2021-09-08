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

    @Column(columnDefinition = "text")
    private String description;

    private String fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private boolean isDeleted = false;

    public Product(String description, String fileId, Category category) {
        this.description = description;
        this.fileId = fileId;
        this.category = category;
    }
}

