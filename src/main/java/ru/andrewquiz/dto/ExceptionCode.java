package ru.andrewquiz.dto;

/**
 * Created by Andrew on 09.04.2017.
 */

public enum ExceptionCode {

    //4xx
    CLIENT_ERROR(400000),
    ENTITY_NOT_FOUND(400010),
    UNKNOWN_RESOURCE(404000),

    //5xx
    SERVER_ERROR(500000),
    DATABASE_ERROR(500010);

    private long value;

    private ExceptionCode(long value) {
        this.value = value;
    }

    public long value() {
        return this.value;
    }
}
