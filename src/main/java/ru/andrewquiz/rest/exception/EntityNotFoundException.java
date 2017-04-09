package ru.andrewquiz.rest.exception;

import org.springframework.http.HttpStatus;
import ru.andrewquiz.dto.ExceptionCode;

/**
 * Created by Andrew on 09.04.2017.
 */

public class EntityNotFoundException extends RestException {

    public EntityNotFoundException(String msg) {
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
        return getMessage();
    }

    private static final ExceptionCode CODE = ExceptionCode.ENTITY_NOT_FOUND;

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
}
