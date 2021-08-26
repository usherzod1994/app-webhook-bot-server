package com.example.appwebhookbotserver.controller;

import com.example.appwebhookbotserver.entity.User;
import com.example.appwebhookbotserver.service.CurrentUser;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String openHomePage(){
        return "pages/books";
    }

    @GetMapping("/profile")
    public String profilePage(@CurrentUser User user){
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        model.addAttribute("username",userPrincipal.getUsername());*/

        System.out.println(user.getPassword());
        System.out.println(user.getPassword());

        return "pages/profile";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(defaultValue = "false") boolean wrong, Model model){
        model.addAttribute("wrong", wrong);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }else {
            return "redirect:/profile";
        }
    }
}
