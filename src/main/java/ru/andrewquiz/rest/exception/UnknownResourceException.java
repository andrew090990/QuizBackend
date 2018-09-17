package ru.andrewquiz.rest.exception;

import org.springframework.http.HttpStatus;
import ru.andrewquiz.dto.ExceptionCode;

/**
 * Created by Andrew on 09.04.2017.
 */

public class UnknownResourceException extends RestException {

    private static final ExceptionCode CODE = ExceptionCode.UNKNOWN_RESOURCE;

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    private String uri;

    public UnknownResourceException(String uri) {
        super("Resource not found: " + uri);

        this.uri = uri;
    }

    @Override
    public ExceptionCode getCode() {
        return CODE;
    }

    @Override
    public HttpStatus getStatus() {
        return STATUS;
    }

    @Override
    public String getDeveloperMessage() {
        return getMessage();
    }

    @Override
    public String getUserMessage() {
        return getMessage();
    }

}