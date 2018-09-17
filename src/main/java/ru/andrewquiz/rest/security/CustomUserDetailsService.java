package ru.andrewquiz.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.andrewquiz.dao.auth.UserEntity;
import ru.andrewquiz.repository.auth.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 09.05.2017.
 */

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = repo.findByUserName(userName);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + userName);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String roleName : userEntity.getRoleNames()) {
            authorities.add(new SimpleGrantedAuthority(roleName));
        }

        return new User(userEntity.getUserName(), userEntity.getPassword(), userEntity.isActive(), true, true, true, authorities);
    }
}
