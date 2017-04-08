package ru.andrewquiz.dao.quiz;

import javax.persistence.*;

/**
 * Created by Andrew on 25.03.2017.
 */

@Entity
@Table(name = "suits")
public class SuitEntity {
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }


    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;
}
