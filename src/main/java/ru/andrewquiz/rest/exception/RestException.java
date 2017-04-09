package ru.andrewquiz.rest.exception;

import org.springframework.http.HttpStatus;
import ru.andrewquiz.dto.ExceptionCode;

/**
 * Created by Andrew on 09.04.2017.
 */

public abstract class RestException extends RuntimeException {

    public RestException(String msg) {
        super(msg);
    }

    public abstract ExceptionCode getCode();

    public abstract HttpStatus getStatus();

    public abstract String getDeveloperMessage();

    public abstract String getUserMessage();
}