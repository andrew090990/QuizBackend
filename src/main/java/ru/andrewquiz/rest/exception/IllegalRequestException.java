package ru.andrewquiz.rest.exception;

import org.springframework.http.HttpStatus;
import ru.andrewquiz.dto.ExceptionCode;

/**
 * Created by Andrew on 13.04.2017.
 */
public class IllegalRequestException extends RestException {

    public IllegalRequestException(String msg) {
        super(msg);
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
        return "Illegal request";
    }

    private static final ExceptionCode CODE = ExceptionCode.ILLEGAL_REQUEST;

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
}
