package ru.andrewquiz.service;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.AbstractEntity;
import ru.andrewquiz.dto.AbstractDto;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.rest.exception.IllegalRequestException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 15.04.2017.
 */

public abstract class AbstractResourceService<D extends AbstractDto, E extends AbstractEntity<PK>, PK extends Serializable> {

    protected abstract Class<D> getDtoClass();

    protected abstract Class<E> getEntityClass();

    protected abstract CrudRepository<E, PK> getRepo();

    protected abstract Validator<D, E> getValidator();

    protected abstract CustomDozerBeanMapper getMapper();

    public List<D> getAllDtos() {

        Iterable<E> entities = getRepo().findAll();

        List<D> dtos = getMapper().mapList(entities, getDtoClass());

        return dtos;
    }

    public D getDto(PK id) {

        E entity = findEntity(id);

        D dto = getMapper().map(entity, getDtoClass());

        return dto;
    }

    public PK createEntity(D dto) {

        //TODO validation

        if (dto.getId() != null) {
            throw new IllegalRequestException("Id must be null when posting new resource.");
        }

        E entity = getMapper().map(dto, getEntityClass());

        entity.setCreatedAt(Calendar.getInstance());

        getRepo().save(entity);

        return (PK)entity.getId();
    }

    public void updateEntity(D dto, PK id) {

        //TODO validation

        E oldEntity = findEntity(id);

        E newEntity = getMapper().map(dto, getEntityClass());

        newEntity.setId(id);
        newEntity.setCreatedAt(oldEntity.getCreatedAt());
        newEntity.setUpdatedAt(Calendar.getInstance());

        getRepo().save(newEntity);
    }

    public void deleteEntity(PK id) {

        E entity = findEntity(id);

        getValidator().validateReferentialIntegrity(entity);

        getRepo().delete(entity);
    }

    private E findEntity(PK id) {

        E entity = getRepo().findOne(id);

        if (entity == null) {
            throw new EntityNotFoundException(getDtoClass(), id);
        }

        return entity;
    }
}
