package ru.andrewquiz.dao.quiz;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andrew on 25.03.2017.
 */

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "category")
    private List<SuitEntity> suits;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> childCategories;

    public Long getId() {
        return id;
    }

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

}
