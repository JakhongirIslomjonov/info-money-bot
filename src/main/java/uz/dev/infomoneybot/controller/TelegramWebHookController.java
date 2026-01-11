package uz.dev.infomoneybot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.dev.infomoneybot.configuration.FinanceWebhookBot;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class TelegramWebHookController {
    private final FinanceWebhookBot telegramWebHookConfig;

    @PostMapping
    public ResponseEntity<?> onUpdateReceived(@RequestBody Update update) {
        telegramWebHookConfig.onWebhookUpdateReceived(update);
        return ResponseEntity.ok().build();
    }
}
