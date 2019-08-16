package io.pyxiscode.reminder.api.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@PropertySource("classpath:bot.properties")
public class RemindMeBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${bot.command.start}")
    private String START;
    @Value("${bot.command.start.msg}")
    private String START_MSG;
    @Value("${bot.command.edit}")
    private String EDIT;
    @Value("${bot.command.edit.msg}")
    private String EDIT_MSG;
    @Value("${bot.command.help}")
    private String HELP;
    @Value("${bot.command.help.msg}")
    private String HELP_MSG;
    @Value("${bot.command.default.msg}")
    private String DEFAULT;

    @Override
    public void onUpdateReceived(Update update) {
        getCommandByMessage(update.getMessage());
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    private void getCommandByMessage(Message message) {
        if (message != null && message.hasText()) {
            String cmd = message.getText();
            if (cmd.equalsIgnoreCase(START)) {
                sendMessage(message, START_MSG);
            } else if (cmd.equalsIgnoreCase(EDIT)) {
                sendMessage(message, EDIT_MSG);
            } else if (cmd.equalsIgnoreCase(HELP)) {
                sendMessage(message, HELP_MSG);
            } else {
                sendMessage(message, DEFAULT);
            }
        }

    }

    private void sendMessage(Message msg, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(msg.getChatId());
        message.setReplyToMessageId(msg.getMessageId());
        message.setText(text);
        try {
            setButtons(message);
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed sending a message {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private void setButtons(SendMessage msg) {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        msg.setReplyMarkup(keyboard);
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("add event"));
        row.add(new KeyboardButton("edit "));
        keyboardRows.add(row);
        keyboard.setKeyboard(keyboardRows);

    }


    @Override
    public String getBotToken() {
        return token;
    }
}

