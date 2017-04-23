package ru.andrewquiz.dao.quiz;


import ru.andrewquiz.dao.Identifiable;
import ru.andrewquiz.dao.Trackable;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 25.03.2017.
 */

@Entity
@Table(name = "categories")
public class CategoryEntity implements Identifiable, Trackable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, updatable = false)
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

    public void addSuit(SuitEntity suit) {
        addSuit(suit, true);
    }

    public void addSuit(SuitEntity suit, boolean updateReference) {
        if (suit == null) {
            return;
        }

        if (suits.contains(suit)) {
            suits.set(suits.indexOf(suit), suit);
        } else {
            suits.add(suit);
        }

        if (updateReference) {
            suit.setCategory(this, false);
        }
    }

    public void removeSuit(SuitEntity suit) {
        removeSuit(suit, true);
    }

    public void removeSuit(SuitEntity suit, boolean updateReference) {
        if (suit == null) {
            return;
        }

        suits.remove(suit);

        if (updateReference) {
            suit.setCategory(null, false);
        }
    }

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        setParentCategory(parentCategory, true);
    }

    public void setParentCategory(CategoryEntity parentCategory, boolean updateReference) {
        if (this.parentCategory!= null) {
            this.parentCategory.removeChildCategory(this, false);
        }

        this.parentCategory = parentCategory;

        if (parentCategory != null && updateReference) {
            parentCategory.addChildCategory(this, false);
        }
    }

    public List<CategoryEntity> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryEntity> childCategories) {
        this.childCategories = childCategories;
    }

    public void addChildCategory(CategoryEntity childCategory) {
        addChildCategory(childCategory, true);
    }

    public void addChildCategory(CategoryEntity childCategory, boolean updateReference) {
        if (childCategory == null) {
            return;
        }

        if (childCategories.contains(childCategory)) {
            childCategories.set(childCategories.indexOf(childCategory), childCategory);
        } else {
            childCategories.add(childCategory);
        }

        if (updateReference) {
            childCategory.setParentCategory(this, false);
        }
    }

    public void removeChildCategory(CategoryEntity childCategory) {
        removeChildCategory(childCategory, true);
    }

    public void removeChildCategory(CategoryEntity childCategory, boolean updateReference) {
        if (childCategory == null) {
            return;
        }

        childCategories.remove(childCategory);

        if (updateReference) {
            childCategory.setParentCategory(null, false);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || id == null) {
            return false;
        }

        return id.equals(((CategoryEntity)o).getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

//TODO toString() for all entities, dtos; refactor EntityNotFoundException
}
