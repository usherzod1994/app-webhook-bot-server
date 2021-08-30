package com.example.appwebhookbotserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private Long chatId;

    private String state;

    private String categoryName;

    private String status;

    @ElementCollection
    private List<Integer> parentId;

    private boolean isDeleted = false;

    public Customer(String fullName, Long chatId, String state) {
        this.fullName = fullName;
        this.chatId = chatId;
        this.state = state;
    }


}
