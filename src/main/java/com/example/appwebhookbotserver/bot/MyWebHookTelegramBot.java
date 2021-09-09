package com.example.appwebhookbotserver.bot;


import com.example.appwebhookbotserver.entity.Customer;
import com.example.appwebhookbotserver.feign.TelegramFeign;
import com.example.appwebhookbotserver.payload.ResultTelegram;
import com.example.appwebhookbotserver.repository.CustomerRepository;
import com.example.appwebhookbotserver.service.FileStoreService;
import com.example.appwebhookbotserver.service.TelegramService;
import com.example.appwebhookbotserver.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Optional;

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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FileStoreService fileStoreService;

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

            Message message = update.getMessage();
            String text = message.getText();

            Video video = update.getMessage().getVideo();

            if (video != null) {
                System.out.println("************************************************************************");
                System.out.println(update);
                String caption = update.getMessage().getCaption();
                String fileId = video.getFileId();
                System.out.println("caption ++++++++ = " + caption);
                System.out.println("fileId ++++++++ = " + fileId);
                boolean isSaveFile = fileStoreService.saveFile(video.getFileId(), caption);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                if (isSaveFile){
                    sendMessage.setText(caption + " saqlandi");
                }else {
                    sendMessage.setText(caption + " saqlanmadi boshqattan o'rinib ko'ring!");
                }

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                System.out.println("************************************************************************");
            }

            if (message.hasText()) {
                if (text.equals("/start")) {
                    try {
                        execute(telegramService.mainMenu(update));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (text.equals("test")) {
                        try {
//                        BAACAgIAAxkBAAPCYSpE1tutDdNKVDo9W_fL4cFvvEMAAoYNAAK_oVBJ20xGEVtC5LogBA
//                        BAACAgIAAxkBAAO_YSo8Z6I8cUxVs600ndWHaqYQl3sAAnoNAAK_oVBJz3dJ_-Q5s5YgBA
                            ResultTelegram resultTelegram = telegramFeign.sendVideoToUser("bot" + RestConstants.TOKEN, "BAACAgIAAxkBAAPCYSpE1tutDdNKVDo9W_fL4cFvvEMAAoYNAAK_oVBJ20xGEVtC5LogBA", update.getMessage().getChatId().toString());
                            System.out.println(resultTelegram);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (text.equals("11111")){
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(update.getMessage().getChatId().toString());
                        sendMessage.setParseMode(ParseMode.HTML);
                        String str = "<b>sdsdsds<br><br><i>eeeeeeeeee</i></b> <br><br><br><br> https://www.youtube.com/watch?v=uGLfjpkk208&list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbAVMuGLfjpkk208&start_radio=1";
                        String str2 = "<a href='https://www.youtube.com/watch?v=uGLfjpkk208&amp;list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbAVMuGLfjpkk208&amp;start_radio=1'>\uD83D\uDC49Kanalga o'tish </a><br><br><b><i>bu test uchun endi</i></b><br><br><br><u>hi</u><br><br>Telegram kanali:  @ingliz0";
                        sendMessage.setText(str2.replaceAll("<br>","\n"));
                        sendMessage.setDisableWebPagePreview(true);

                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (text.equals(Constant.BACK_UZ)) {
                        Optional<Customer> optionalCustomer = customerRepository.findByChatId(update.getMessage().getChatId());
                        optionalCustomer.ifPresent(customer -> {
                            if (customer.getState().equals(BotState.CATEGORY_MENU_STATE)) {
                                try {
                                    execute(telegramService.menuCategory(update));
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    } else if (text.equals(Constant.TOP_MENU)) {
                        Optional<Customer> optionalCustomer = customerRepository.findByChatId(update.getMessage().getChatId());
                        optionalCustomer.ifPresent(customer -> {
                            try {
                                execute(telegramService.mainMenu(update));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        Optional<Customer> optionalCustomer = customerRepository.findByChatId(update.getMessage().getChatId());
                        if (optionalCustomer.isPresent()) {
                            Customer customer = optionalCustomer.get();
                            if (customer.getState().equals(BotState.CATEGORY_MENU_STATE)) {
                                try {
                                    execute(telegramService.childMenuByCategory(update));
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println(update);
                        }
                    }
                }
            }
        } else if (update.hasChatMember()) {
            System.out.println("hasChatMember");
            System.out.println(update);
        } else if (update.hasMyChatMember()) {
            System.out.println("hasMyChatMember");
            System.out.println(update);
        }
    }
}
