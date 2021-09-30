package com.polystar.analytics.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private String title;
    private String code;

    public CustomException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }

    public CustomException(String title, String errorMessage) {
        super(errorMessage);
        this.title = title;
    }

    public CustomException(String title, String errorMessage, String code) {
        super(errorMessage);
        this.title = title;
        this.code = code;
    }
}
