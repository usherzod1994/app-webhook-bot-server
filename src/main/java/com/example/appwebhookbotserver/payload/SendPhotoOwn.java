package com.example.appwebhookbotserver.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendPhotoOwn {

    @JsonProperty(value = "chat_id")
    private String chatId;

    private String caption;

    private MultipartFile photo;

}
