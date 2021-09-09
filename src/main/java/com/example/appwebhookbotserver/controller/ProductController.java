package com.example.appwebhookbotserver.controller;

import com.example.appwebhookbotserver.entity.Category;
import com.example.appwebhookbotserver.entity.ChannelAndGroup;
import com.example.appwebhookbotserver.entity.FileStore;
import com.example.appwebhookbotserver.entity.Product;
import com.example.appwebhookbotserver.feign.TelegramFeign;
import com.example.appwebhookbotserver.payload.ResultTelegram;
import com.example.appwebhookbotserver.payload.SendPhotoOwn;
import com.example.appwebhookbotserver.repository.CategoryRepository;
import com.example.appwebhookbotserver.repository.ProductRepository;
import com.example.appwebhookbotserver.service.ChannelAndGroupService;
import com.example.appwebhookbotserver.service.FileStoreService;
import com.example.appwebhookbotserver.service.ProductService;
import com.example.appwebhookbotserver.utils.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;


import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final TelegramFeign telegramFeign;

    private final CategoryRepository categoryRepository;

    private final ChannelAndGroupService channelAndGroupService;

    private final ProductService productService;

    private final FileStoreService fileStoreService;

    @PostMapping("/test")
    public void sendVideo(@RequestParam String chatId, @RequestParam String fileId) throws IOException {
        ResultTelegram resultTelegram = telegramFeign.sendVideoToUser("bot" + RestConstants.TOKEN, "BAACAgIAAxkBAAO_YSo8Z6I8cUxVs600ndWHaqYQl3sAAnoNAAK_oVBJz3dJ_-Q5s5YgBA", chatId);
        System.out.println(resultTelegram);
    }

    @PostMapping("/save")
    public String saveProduct(@RequestParam String fileId,@RequestParam String description,
                              @RequestParam int categoryId,@RequestParam String chatId,
                              @RequestParam String youtubeLink,@RequestParam String youtubeLinkName,
                              @RequestParam(name = "file") MultipartFile multipartFile){

        String link = "";
        if (!youtubeLink.isEmpty() && !youtubeLinkName.isEmpty()){
            link = "<a href='"+youtubeLink+"'>"+youtubeLinkName+"</a>";
        }

        if (fileId.isEmpty() && !(multipartFile.getSize() > 0) && chatId.isEmpty()){
            return "redirect:/product/create";
        }

        if (!fileId.isEmpty()){
            if (description.isEmpty() || categoryId == 0){
                return "redirect:/product/create";
            }
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isPresent()){
                String newDesc = description.replaceAll("<br>","\n");

                if (!link.isEmpty()){
                    newDesc += "\n"+link;
                }
                productService.save(new Product(newDesc, fileId, optionalCategory.get()));
            }else {
                return "redirect:/product/create";
            }
            return "redirect:/product/list";
        }else {
            if (description.isEmpty() || chatId.isEmpty() || categoryId == 0 || !(multipartFile.getSize() > 0)){
                return "redirect:/product/create";
            }

            if (description.trim().isEmpty() || chatId.trim().isEmpty()){
                return "redirect:/product/create";
            }
            ResultTelegram resultTelegram = null;
            if (multipartFile.getSize() > 0){
                try {
                    resultTelegram = telegramFeign.sendVideoToUser("bot" + RestConstants.TOKEN, multipartFile,chatId.trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(resultTelegram);
            }

            String newDesc = description.replaceAll("<br>","\n");

            if (!link.isEmpty()){
                newDesc += "\n"+link;
            }

            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isPresent() && resultTelegram != null){
                Product product = new Product(newDesc, resultTelegram.getResult().getVideo().getFileId(), optionalCategory.get());
                productService.save(product);
                return "redirect:/product/list";
            }else {
                return "redirect:/product/create";
            }
        }

    }

    @GetMapping("/create")
    public String createProduct(Model modelAttribute){
        List<Category> all = categoryRepository.getCreateProductCategories();
        List<ChannelAndGroup> allChannel = channelAndGroupService.getAllChannel();
        List<FileStore> files = fileStoreService.getAllFiles();
        modelAttribute.addAttribute("categories", all);
        modelAttribute.addAttribute("channels", allChannel);
        modelAttribute.addAttribute("files", files);
        return "pages/product/create";
    }

    @GetMapping("/list")
    public String getAllProductView(Model model){
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products",allProducts);
        return "pages/product/index";
    }

}
