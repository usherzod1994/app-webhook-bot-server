package com.example.appwebhookbotserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private String status;

    public Customer(String fullName, Long chatId, String state) {
        this.fullName = fullName;
        this.chatId = chatId;
        this.state = state;
    }


}
