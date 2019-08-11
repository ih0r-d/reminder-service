package io.pyxiscode.reminder.api.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidEventStateException extends RuntimeException {
    private final String msg;
}
