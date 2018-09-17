package ru.andrewquiz.service.auth;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.auth.UserEntity;
import ru.andrewquiz.dto.auth.User;
import ru.andrewquiz.service.Validator;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class UserValidator implements Validator<User, UserEntity> {

    @Override
    public void validateReferentialIntegrity(UserEntity userEntity) {

        return;
    }
}
