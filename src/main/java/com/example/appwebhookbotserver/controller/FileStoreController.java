package com.example.appwebhookbotserver.controller;

import com.example.appwebhookbotserver.entity.FileStore;
import com.example.appwebhookbotserver.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileStoreController {

    @Autowired
    private FileStoreService fileStoreService;

    @GetMapping("/all")
    public String booksPage(Model model){
        List<FileStore> files = fileStoreService.getAllFiles();
        model.addAttribute("files", files);
        return "pages/file/index";
    }

}
