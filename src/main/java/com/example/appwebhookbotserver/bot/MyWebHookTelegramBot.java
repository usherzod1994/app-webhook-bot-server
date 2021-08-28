package com.example.appwebhookbotserver.bot;


import com.example.appwebhookbotserver.feign.TelegramFeign;
import com.example.appwebhookbotserver.payload.ResultTelegram;
import com.example.appwebhookbotserver.service.TelegramService;
import com.example.appwebhookbotserver.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@Component
public class MyWebHookTelegramBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.path}")
    private String botPath;


    @Autowired
    private TelegramService telegramService;

    @Autowired
    private TelegramFeign telegramFeign;

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            System.out.println(update);
            Message message = update.getMessage();
            String text = message.getText();
            if (message.hasText()) {
                if (text.equals("/start")) {
                    try {
//                        execute(telegramService.welcomeToTheBot(update));
                        execute(telegramService.mainMenu(update));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else if(text.equals("test")){
                    try {
//                        BAACAgIAAxkBAAPCYSpE1tutDdNKVDo9W_fL4cFvvEMAAoYNAAK_oVBJ20xGEVtC5LogBA
//                        BAACAgIAAxkBAAO_YSo8Z6I8cUxVs600ndWHaqYQl3sAAnoNAAK_oVBJz3dJ_-Q5s5YgBA
                        ResultTelegram resultTelegram = telegramFeign.sendVideoToUser("bot" + RestConstants.TOKEN, "BAACAgIAAxkBAAPCYSpE1tutDdNKVDo9W_fL4cFvvEMAAoYNAAK_oVBJ20xGEVtC5LogBA", update.getMessage().getChatId().toString());
                        System.out.println(resultTelegram);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }else if (update.hasChatMember()){
            System.out.println("hasChatMember");
            System.out.println(update);
        }else if (update.hasMyChatMember()){
            System.out.println("hasMyChatMember");
            System.out.println(update);
        }
    }
}
