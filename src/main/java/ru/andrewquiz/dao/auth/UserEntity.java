package ru.andrewquiz.dao.auth;

import ru.andrewquiz.dao.Identifiable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 09.05.2017.
 */

@Entity
@Table(name = "`users`", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class UserEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ElementCollection
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_name")
    private List<String> roleNames = new ArrayList<String>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleName) {
        this.roleNames = roleNames;
    }
}
