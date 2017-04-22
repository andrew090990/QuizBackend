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
@Table(name = "suits")
public class SuitEntity extends AbstractEntity<Long> implements Trackable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suit_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Calendar createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Calendar updatedAt;

    @OneToMany(mappedBy = "suit")
    private List<QuizEntity> quizes;

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
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

    public List<QuizEntity> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<QuizEntity> quizes) {
        this.quizes = quizes;
    }

    @Override
    public void attachChildrenToParent() {
        return;
    }
}
