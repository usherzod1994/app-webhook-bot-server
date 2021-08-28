package com.example.appwebhookbotserver.service;

import com.example.appwebhookbotserver.entity.ChannelAndGroup;
import com.example.appwebhookbotserver.repository.ChannelAndGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelAndGroupService {

    private final ChannelAndGroupRepository channelAndGroupRepository;

    public List<ChannelAndGroup> getAllChannel() {
        return channelAndGroupRepository.findAll();
    }

    public void save(String name, String chatId) {
        ChannelAndGroup channelAndGroup = new ChannelAndGroup(name,chatId);
        channelAndGroupRepository.save(channelAndGroup);
    }
}
