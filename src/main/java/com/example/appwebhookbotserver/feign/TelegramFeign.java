package com.example.appwebhookbotserver.feign;

import com.example.appwebhookbotserver.payload.ResultTelegram;
import com.example.appwebhookbotserver.utils.RestConstants;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;

@FeignClient(url = RestConstants.TELEGRAM_BASE_URL_WITHOUT_BOT, name = "tg")
public interface TelegramFeign {

    @PostMapping(value = "{path}/sendVideo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    ResultTelegram sendVideoToUser(@PathVariable String path, @RequestPart(value = "video") MultipartFile video,@RequestParam (value = "chat_id") String chat_id)throws IOException;

    @PostMapping(value = "{path}/sendPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    ResultTelegram sendPhotoToUser(@PathVariable String path, @RequestPart(value = "photo") MultipartFile photo,@RequestParam (value = "chat_id") String chat_id)throws IOException;

    @PostMapping(value = "{path}/sendMessage")
    ResultTelegram sendMessageToUser(@PathVariable String path, @RequestBody SendMessage sendMessage);

}
