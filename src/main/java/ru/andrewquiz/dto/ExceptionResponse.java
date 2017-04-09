package ru.andrewquiz.dto;


import org.springframework.http.HttpStatus;
import ru.andrewquiz.rest.exception.RestException;

/**
 * Created by Andrew on 09.04.2017.
 */

public class ExceptionResponse {

    public ExceptionResponse (RestException ex) {
        this(ex.getStatus(), ex.getCode());

        this.setDeveloperMessage(ex.getDeveloperMessage());
        this.setUserMessage(ex.getUserMessage());
    }

    public ExceptionResponse (HttpStatus status, ExceptionCode code) {
        this.status = status.value();
        this.code = code.value();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    private int status;

    private long code;

    private String userMessage;

    private String developerMessage;


}
