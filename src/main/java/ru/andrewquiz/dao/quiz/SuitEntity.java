package ru.andrewquiz.dao.quiz;


import ru.andrewquiz.dao.Identifiable;
import ru.andrewquiz.dao.Trackable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 25.03.2017.
 */

@Entity
@Table(name = "suits")
public class SuitEntity implements Identifiable, Trackable, Serializable {

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
    private List<QuizEntity> quizes = new ArrayList<QuizEntity>();

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
        setCategory(category, true);
    }

    public void setCategory(CategoryEntity category, boolean updateReference) {
        if (this.category!= null) {
            this.category.removeSuit(this, false);
        }

        this.category = category;

        if (category != null && updateReference) {
            category.addSuit(this, false);
        }
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

    public void addQuiz(QuizEntity quiz) {
        addQuiz(quiz, true);
    }

    public void addQuiz(QuizEntity quiz, boolean updateReference) {
        if (quiz == null) {
            return;
        }

        if (quizes.contains(quiz)) {
            quizes.set(quizes.indexOf(quiz), quiz);
        } else {
            quizes.add(quiz);
        }

        if (updateReference) {
            quiz.setSuit(this, false);
        }
    }

    public void removeQuiz(QuizEntity quiz) {
        removeQuiz(quiz, true);
    }

    public void removeQuiz(QuizEntity quiz, boolean updateReference) {
        if (quiz == null) {
            return;
        }

        quizes.remove(quiz);

        if (updateReference) {
            quiz.setSuit(null, false);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass() || id == null) {
            return false;
        }

        return id.equals(((SuitEntity)o).getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
