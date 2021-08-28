package com.example.appwebhookbotserver.controller;

import com.example.appwebhookbotserver.entity.ChannelAndGroup;
import com.example.appwebhookbotserver.service.ChannelAndGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/channel")
@RequiredArgsConstructor
public class ChannelAndGroupController {

    private final ChannelAndGroupService channelAndGroupService;


    @GetMapping("/list")
    public String getAllChannel(Model model){
        List<ChannelAndGroup> allChannel = channelAndGroupService.getAllChannel();
        model.addAttribute("channels", allChannel);
        return "pages/channel/index";
    }

    @GetMapping("/create")
    public String createChannel(){
        return "pages/channel/create";
    }

    @PostMapping("/save")
    public String saveChannel(@RequestParam String name,@RequestParam String chatId){
        if (name.isEmpty() || chatId.isEmpty()){
            return "redirect:/channel/create";
        }
        if (name.trim().isEmpty() || chatId.trim().isEmpty()){
            return "redirect:/channel/create";
        }
        channelAndGroupService.save(name.trim(), chatId.trim());
        return "redirect:/channel/list";
    }
}
