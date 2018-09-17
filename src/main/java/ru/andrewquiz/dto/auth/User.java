package ru.andrewquiz.dto.auth;

import ru.andrewquiz.dto.AbstractDto;

/**
 * Created by Andrew on 09.05.2017.
 */

public class User extends AbstractDto {

    private String userName;

    private String password;

    private Long id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
