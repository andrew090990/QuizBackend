package ru.andrewquiz.service;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dto.AbstractDto;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public interface Validator <D extends AbstractDto, E> {

    public abstract void validateReferentialIntegrity(E entity);
}
