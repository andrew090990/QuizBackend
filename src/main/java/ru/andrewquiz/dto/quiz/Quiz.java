package ru.andrewquiz.dto.quiz;

import ru.andrewquiz.dto.AbstractDto;

import java.util.Calendar;

/**
 * Created by Andrew on 15.04.2017.
 */

public class Quiz extends AbstractDto {

    private Long id;

    private String name;

    private QuizType type;

    private Long suitId;

    private Calendar createdAt;

    private Calendar updatedAt;

    public QuizType getType() {
        return type;
    }

    public void setType(QuizType type) {
        this.type = type;
    }

    public Long getSuitId() {
        return suitId;
    }

    public void setSuitId(Long suitId) {
        this.suitId = suitId;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
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

}
