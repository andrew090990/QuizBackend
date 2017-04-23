package ru.andrewquiz.service.mapper;

/**
 * Created by Andrew on 23.04.2017.
 */

public class MappingException extends RuntimeException {

    public MappingException(String msg) {
        super(msg);
    }

    public MappingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
