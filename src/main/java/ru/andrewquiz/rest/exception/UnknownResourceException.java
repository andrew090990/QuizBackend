package ru.andrewquiz.rest.exception;

import org.springframework.http.HttpStatus;
import ru.andrewquiz.dto.ExceptionCode;

/**
 * Created by Andrew on 09.04.2017.
 */

public class UnknownResourceException extends RestException {

    public UnknownResourceException(String msg) {
        super(msg);
    }

    @Override
    public ExceptionCode getCode() {
        return code;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    private static ExceptionCode code = ExceptionCode.UNKNOWN_RESOURCE;

    private static HttpStatus status = HttpStatus.NOT_FOUND;
}