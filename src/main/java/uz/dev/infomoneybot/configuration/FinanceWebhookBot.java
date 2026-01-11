package uz.dev.infomoneybot.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Configuration
@RequiredArgsConstructor
public class FinanceWebhookBot  extends TelegramWebhookBot {

  @Override
  public String getBotPath() {
    return "";
  }

  @Override
  public String getBotUsername() {
    return "";
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return null;
  }
}
