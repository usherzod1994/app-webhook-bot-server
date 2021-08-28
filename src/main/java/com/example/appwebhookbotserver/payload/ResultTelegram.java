package com.example.appwebhookbotserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultTelegram {
    private boolean ok;

    private Message result;
}
