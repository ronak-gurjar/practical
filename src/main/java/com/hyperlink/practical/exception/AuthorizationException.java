package com.hyperlink.practical.exception;

public class AuthorizationException extends BaseException {

    private static final long serialVersionUID = 1119066419517920757L;

    public AuthorizationException(String applicationMessage) {
        super(applicationMessage);
    }

}