package com.example.appwebhookbotserver.service;

import com.example.appwebhookbotserver.bot.Constant;
import com.example.appwebhookbotserver.entity.Category;
import com.example.appwebhookbotserver.payload.ResCategory;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramServiceImp implements TelegramService{

    @Autowired
    private CategoryService categoryService;


    @Override
    public SendMessage shareContact(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(Constant.SHARE_CONTACT_TEXT);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText(Constant.SHARE_CONTACT_BUTTON_TEXT_UZ);
        keyboardRow.add(keyboardButton);
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public SendMessage welcomeToTheBot(Update update) {
        SendMessage sendMessage =  new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(Constant.WELCOME_TEXT_MAX);
        return sendMessage;
    }

    @Override
    public SendMessage mainMenu(Update update) {

        List<Category> allCategories = categoryService.getCategories();


        SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setParseMode(ParseMode.MARKDOWN);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        for (int i = 0; i < allCategories.size(); i++) {
            Category category = allCategories.get(i);
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText(category.getName());
            keyboardRow.add(keyboardButton);
            if (!(i%2==1 || allCategories.size()-1==i)) continue;
            keyboardRows.add(keyboardRow);
            keyboardRow = new KeyboardRow();
        }
        sendMessage.setText(Constant.LETS_GO_ORDER_UZ);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
