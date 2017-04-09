package ru.andrewquiz.dto;

/**
 * Created by Andrew on 09.04.2017.
 */

public enum ExceptionCode {

    //4xx
    CLIENT_ERROR(40000),
    UNKNOWN_RESOURCE(40400),

    //5xx
    SERVER_ERROR(50000);

    private long value;

    private ExceptionCode(long value) {
        this.value = value;
    }

    public long value() {
        return this.value;
    }
}
