package ru.andrewquiz.dao.quiz;

import ru.andrewquiz.dao.AbstractEntity;
import ru.andrewquiz.dao.Trackable;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 25.03.2017.
 */

@Entity
@Table(name = "categories")
public class CategoryEntity extends AbstractEntity<Long> implements Trackable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Calendar createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Calendar updatedAt;

    @OneToMany(mappedBy = "category")
    private List<SuitEntity> suits;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> childCategories;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<CategoryEntity> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryEntity> childCategories) {
        this.childCategories = childCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Calendar getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

}
