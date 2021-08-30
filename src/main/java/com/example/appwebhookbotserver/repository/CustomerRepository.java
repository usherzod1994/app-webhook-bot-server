package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByChatId(Long chatId);

}
