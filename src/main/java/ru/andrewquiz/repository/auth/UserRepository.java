package ru.andrewquiz.repository.auth;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.auth.UserEntity;

/**
 * Created by Andrew on 09.05.2017.
 */

public interface UserRepository extends CrudRepository<UserEntity, Long>
{
    public UserEntity findByUserName(String userName);
}
