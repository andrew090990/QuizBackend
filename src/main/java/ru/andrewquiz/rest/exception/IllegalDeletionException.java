package ru.andrewquiz.rest.exception;

import org.springframework.http.HttpStatus;
import ru.andrewquiz.dto.ExceptionCode;

/**
 * Created by tararaksin on 14.04.2017.
 */

public class IllegalDeletionException  extends RestException {

    private static final ExceptionCode CODE = ExceptionCode.ILLEGAL_DELETION;

    private static final HttpStatus STATUS = HttpStatus.CONFLICT;

    private StringBuilder stringBuilderUser= new StringBuilder("Unable to delete due to existing links:");

    private StringBuilder stringBuilderDeveloper= new StringBuilder("Unable to delete due to existing links:");

    public IllegalDeletionException() {
        super("A conflict occurred while deleting the resource.");
    }

    public void addDependentObject(String typeName, Long id, String name) {
        stringBuilderDeveloper.append(" " + typeName + " #" + String.valueOf(id) + " " + name + ";");
        stringBuilderUser.append(" " + typeName + " " + name + ";");
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
        return stringBuilderDeveloper.toString();
    }

    @Override
    public String getUserMessage() {
        return stringBuilderUser.toString();
    }

}
