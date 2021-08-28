package com.example.appwebhookbotserver.controller;

import com.example.appwebhookbotserver.entity.Category;
import com.example.appwebhookbotserver.payload.ReqCategory;
import com.example.appwebhookbotserver.payload.ReqUpdateCategory;
import com.example.appwebhookbotserver.payload.ResCategory;
import com.example.appwebhookbotserver.repository.CategoryRepository;
import com.example.appwebhookbotserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String categoryList(Model modelAttribute){
        List<Category> all = categoryRepository.getCategories();
        modelAttribute.addAttribute("categories", all);
        return "pages/category/index";
    }

    @GetMapping("/create")
    public String createCategory(ReqCategory reqCategory,Model modelAttribute){
        Category category = new Category(0,"No Select Parent",0);
        List<Category> all = categoryRepository.getCreateCategories();
        all.add(0,category);
        modelAttribute.addAttribute("categories", all);
        return "pages/category/create";
    }


    @PostMapping("/save")
    public String saveCategory(@Valid ReqCategory reqCategory, BindingResult result){
        System.out.println(reqCategory);
        if (result.hasErrors()){
            return "pages/category/create";
        }
        boolean save = categoryService.save(reqCategory);
        if (!save){
            return "redirect:/category/create";
        }
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        System.out.println("category id ***** " + id);
        boolean delete = categoryService.delete(id);
        return "redirect:/category/list";
    }

    @GetMapping("/update/{id}")
    public String updateCategory(@PathVariable int id,Model modelAttribute){
        System.out.println("category id ***** " + id);
        List<Category> all = categoryRepository.getCreateCategories();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            Category prCategory = new Category(0,"No Select Parent",0);
            if (category.getParentId() == 0){
                all.add(0,prCategory);
            }else {
                Optional<Category> parentOptional = categoryRepository.findById(category.getParentId());
                if (parentOptional.isPresent()){
                    Category category1 = parentOptional.get();
                    all.remove(category1);
                    all.add(0,category1);
                }
                all.add(prCategory);
            }
            all.remove(category);
            modelAttribute.addAttribute("categories", all);
            ReqCategory reqCategory = new ReqCategory(category.getId(), category.getName(), category.getParentId());
            modelAttribute.addAttribute("reqCategory",reqCategory);
            return "pages/category/update";
        }else {
            return "redirect:/category/list";
        }
    }

}
