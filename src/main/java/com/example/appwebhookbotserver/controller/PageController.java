package com.example.appwebhookbotserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/books")
    public String booksPage(){
        return "pages/books";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/finance")
    public String financePage(){
        return "pages/finance";
    }

    @GetMapping("/basic")
    public String openBasicPage(){
        return "basic";
    }
}