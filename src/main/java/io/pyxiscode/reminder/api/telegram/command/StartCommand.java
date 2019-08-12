package io.pyxiscode.reminder.api.telegram.command;

import io.pyxiscode.reminder.api.telegram.constants.CommandProperties;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static io.pyxiscode.reminder.api.telegram.constants.CommandProperties.*;

@Slf4j
public class StartCommand extends BotCommand {
    public StartCommand(String commandIdentifier, String description) {
        super(START.getIdentifier(),START.getDescription());

    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

    }
}
