package ru.andrewquiz.dto.quiz;

/**
 * Created by Andrew on 25.03.2017.
 */
public class Suit {

    private Long id;

    private String name;

    private Long categoryId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
