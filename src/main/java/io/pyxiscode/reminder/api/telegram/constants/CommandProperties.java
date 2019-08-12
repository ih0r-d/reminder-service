package io.pyxiscode.reminder.api.telegram.constants;

import lombok.Getter;

@Getter
public enum CommandProperties {
    START("start", " start using bot\n");

    private String identifier;
    private String description;

    CommandProperties(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }
}
