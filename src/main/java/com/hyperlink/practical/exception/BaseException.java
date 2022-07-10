package com.hyperlink.practical.exception;

public class BaseException extends RuntimeException {

    private String developerMessage;

    BaseException(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
