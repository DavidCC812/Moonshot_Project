package com.example.moonshot.exception;

import lombok.Getter;

@Getter
public class MoonshotException extends RuntimeException {

    private final String errorMessage;

    public MoonshotException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public MoonshotException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

}
