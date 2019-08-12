package io.pyxiscode.reminder.api.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
public class RemindMeBot extends TelegramLongPollingCommandBot {

    public RemindMeBot(DefaultBotOptions options, String botUsername) {
        super(options, botUsername);
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {

    }
}
