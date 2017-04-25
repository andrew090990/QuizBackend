package ru.andrewquiz.service;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.Identifiable;
import ru.andrewquiz.dao.Trackable;
import ru.andrewquiz.dto.AbstractDto;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.rest.exception.IllegalRequestException;
import ru.andrewquiz.service.mapper.AbstractMapper;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 15.04.2017.
 */

public abstract class AbstractResourceService<D extends AbstractDto, E extends Identifiable> {

    protected abstract Class<D> getDtoClass();

    protected abstract CrudRepository<E, Long> getRepo();

    protected abstract Validator<D, E> getValidator();

    protected abstract AbstractMapper<D, E> getDtoToEntityMapper();

    protected abstract AbstractMapper<E, D> getEntityToDtoMapper();

    public List<D> getAllDtos() {

        Iterable<E> entities = getRepo().findAll();

        List<D> dtos = getEntityToDtoMapper().mapList(entities);

        return dtos;
    }

    public D getDto(Long id) {

        E entity = findEntity(id);

        D dto = getEntityToDtoMapper().map(entity);

        return dto;
    }

    public Long createEntity(D dto) {

        //TODO validation

        if (dto.getId() != null) {
            throw new IllegalRequestException("Id must be null when posting new resource.");
        }

        E entity = getDtoToEntityMapper().map(dto);

        if (entity instanceof Trackable) {
            ((Trackable)entity).setCreatedAt(Calendar.getInstance());
            ((Trackable)entity).getCreatedAt().clear(Calendar.MILLISECOND);
        }

        getRepo().save(entity);

        return entity.getId();
    }

    public void updateEntity(D dto, Long id) {

        //TODO validation

        E oldEntity = findEntity(id);

        E newEntity = getDtoToEntityMapper().map(dto);

        newEntity.setId(id);

        if (newEntity instanceof Trackable) {
            ((Trackable)newEntity).setCreatedAt(((Trackable)oldEntity).getCreatedAt());

            ((Trackable)newEntity).setUpdatedAt(Calendar.getInstance());
            ((Trackable)newEntity).getUpdatedAt().clear(Calendar.MILLISECOND);
        }

        getRepo().save(newEntity);

    }

    public void deleteEntity(Long id) {

        E entity = findEntity(id);

        getValidator().validateReferentialIntegrity(entity);

        getRepo().delete(entity);
    }

    private E findEntity(Long id) {

        E entity = getRepo().findOne(id);

        if (entity == null) {
            throw new EntityNotFoundException(getDtoClass(), id);
        }

        return entity;
    }
}
