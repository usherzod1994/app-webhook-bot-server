package com.example.appwebhookbotserver.controller;

import com.example.appwebhookbotserver.payload.ReqCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/list")
    public String categoryList(Model modelAttribute){
        return "pages/category/index";
    }

    @GetMapping("/create")
    public String createCategory(ReqCategory reqCategory){
        return "pages/category/create";
    }


    @PostMapping("/save")
    public String saveCategory(@Valid ReqCategory reqCategory, BindingResult result){
        System.out.println(reqCategory.getName());
        if (result.hasErrors()){
            return "pages/category/create";
        }
        return "redirect:/category/list";
    }

}
