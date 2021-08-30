package com.example.appwebhookbotserver.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface TelegramService {
    SendMessage shareContact(Update update);
    SendMessage welcomeToTheBot(Update update);
    SendMessage mainMenu(Update update);
    SendMessage menuCategory(Update update);
    SendMessage childMenuByCategory(Update update);
    SendMessage printProducts(Update update);
}
