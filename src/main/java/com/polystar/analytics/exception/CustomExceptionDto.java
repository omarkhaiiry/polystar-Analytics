package com.polystar.analytics.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CustomExceptionDto {
    private String title;
    private String message;
    private Object object;

    private String code;

    public CustomExceptionDto() {
    }

    public CustomExceptionDto(Exception e) {
        this.title = e.getClass().getSimpleName();

            if(e.getLocalizedMessage() != null)
            {
                this.message = e.getLocalizedMessage();
            }
        }


    public CustomExceptionDto(CustomException e) {
        this.title = e.getTitle();
        this.message = e.getMessage();
        if(e.getCode() !=null){
        this.code = e.getCode();}
    }

    public CustomExceptionDto(String title, String message) {
        this.title = title;
        this.message = message;
    }
    public CustomExceptionDto(String title, String message,Object object) {
        this.title = title;
        this.message = message;
        this.object = object;
    }
    public CustomExceptionDto(String title, String message, String code) {
        this.title = title;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "CustomMessageDto{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
