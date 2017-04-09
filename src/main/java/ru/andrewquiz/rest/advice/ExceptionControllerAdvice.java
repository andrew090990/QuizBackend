package ru.andrewquiz.rest.advice;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.andrewquiz.dto.ExceptionCode;
import ru.andrewquiz.dto.ExceptionResponse;
import ru.andrewquiz.rest.exception.RestException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andrew on 09.04.2017.
 */

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = RestException.class)
    protected ResponseEntity<ExceptionResponse> handleUnknownResourceException(RestException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(ex);

        logger.error(ex.getDeveloperMessage(), ex);

        return new ResponseEntity<ExceptionResponse>(response, ex.getStatus());
    }

    @ExceptionHandler(value = TransactionException.class)
    protected ResponseEntity<ExceptionResponse> handleTransactionException(TransactionException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionCode code = ExceptionCode.DATABASE_ERROR;
        String userMessage = "Database error";
        String developerMessage = "An exception has occurred performing transaction: " + ex.getMessage();

        ExceptionResponse response = new ExceptionResponse(status, code);

        response.setUserMessage(userMessage);
        response.setDeveloperMessage(developerMessage);

        logger.error(developerMessage, ex);

        return new ResponseEntity<ExceptionResponse>(response, status);
    }

    private static Logger logger = Logger.getLogger(ExceptionControllerAdvice.class);
}
