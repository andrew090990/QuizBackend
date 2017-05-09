package ru.andrewquiz.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.auth.UserEntity;
import ru.andrewquiz.dto.auth.User;
import ru.andrewquiz.repository.auth.UserRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.auth.UserDtoToEntityMapper;
import ru.andrewquiz.service.mapper.auth.UserEntityToDtoMapper;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class UserService extends AbstractResourceService<User, UserEntity> {

    private UserRepository repo;

    private UserValidator validator;

    private UserDtoToEntityMapper dtoToEntityMapper;

    private UserEntityToDtoMapper entityToDtoMapper;

    @Autowired
    public UserService(UserRepository repo, UserValidator validator, UserDtoToEntityMapper dtoToEntityMapper, UserEntityToDtoMapper entityToDtoMapper) {

        this.repo = repo;
        this.validator = validator;
        this.entityToDtoMapper = entityToDtoMapper;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public void updateCurrentUser(User user) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = repo.findByUserName(userName);

        this.updateEntity(user, userEntity.getId());
    }

    @Override
    protected Class<User> getDtoClass() {
        return User.class;
    }

    @Override
    protected CrudRepository<UserEntity, Long> getRepo() {
        return repo;
    }

    @Override
    protected Validator<User, UserEntity> getValidator() {
        return validator;
    }

    @Override
    protected AbstractMapper<User, UserEntity> getDtoToEntityMapper() {
        return dtoToEntityMapper;
    }

    @Override
    protected AbstractMapper<UserEntity, User> getEntityToDtoMapper() {
        return entityToDtoMapper;
    }

}
