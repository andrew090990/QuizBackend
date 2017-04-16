package ru.andrewquiz.dao.quiz;

import ru.andrewquiz.dao.AbstractEntity;
import ru.andrewquiz.dao.Trackable;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Andrew on 15.04.2017.
 */

@Entity
@Table(name = "quizes")
@Inheritance (strategy=InheritanceType.JOINED)
public class QuizEntity extends AbstractEntity<Long> implements Trackable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @ManyToOne
    @JoinColumn(name = "suit_id")
    private SuitEntity suit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Calendar createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Calendar updatedAt;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public SuitEntity getSuit() {
        return suit;
    }

    public void setSuit(SuitEntity suit) {
        this.suit = suit;
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

}
