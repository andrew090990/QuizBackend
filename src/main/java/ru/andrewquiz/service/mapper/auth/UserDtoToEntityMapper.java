package ru.andrewquiz.service.mapper.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.auth.UserEntity;
import ru.andrewquiz.dto.auth.User;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class UserDtoToEntityMapper extends AbstractMapper<User, UserEntity> {

    private static final String ROLE_USER = "ROLE_USER";

    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserDtoToEntityMapper(BCryptPasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    protected UserEntity performMapping(User src) {
        return performMapping(src, new UserEntity());
    }

    @Override
    protected UserEntity performMapping(User src, UserEntity dst) {

        dst.setUserName(src.getUserName());
        dst.setPassword(bcryptEncoder.encode(src.getPassword()));

        dst.setActive(true);

        if (dst.getRoleNames().size() == 0) {
            dst.getRoleNames().add(ROLE_USER);
        }

        return dst;
    }
}
