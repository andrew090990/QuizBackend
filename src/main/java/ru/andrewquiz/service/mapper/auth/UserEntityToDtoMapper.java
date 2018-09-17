package ru.andrewquiz.service.mapper.auth;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.auth.UserEntity;
import ru.andrewquiz.dto.auth.User;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class UserEntityToDtoMapper extends AbstractMapper<UserEntity, User> {

    @Override
    protected User performMapping(UserEntity src) {
        return performMapping(src, new User());
    }

    @Override
    protected User performMapping(UserEntity src, User dst) {

        dst.setId(src.getId());
        dst.setUserName(src.getUserName());

        return dst;
    }
}
