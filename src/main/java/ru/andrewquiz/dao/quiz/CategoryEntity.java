package ru.andrewquiz.dao.quiz;

import ru.andrewquiz.dto.quiz.Category;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andrew on 25.03.2017.
 */

@Entity
@Table(name = "categories")
public class CategoryEntity {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SuitEntity> getSuits() {
        return suits;
    }

    public void setSuits(List<SuitEntity> suits) {
        this.suits = suits;
    }


    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<SuitEntity> suits;

}
