package ru.andrewquiz.dto;


import ru.andrewquiz.rest.exception.RestException;

/**
 * Created by Andrew on 09.04.2017.
 */

public class ExceptionResponse {

    public ExceptionResponse (RestException ex) {
        this.setStatus(ex.getStatus().value());
        this.setCode(ex.getCode().value());
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    private int status;

    private long code;

    private String message;

    private String developerMessage;


}
