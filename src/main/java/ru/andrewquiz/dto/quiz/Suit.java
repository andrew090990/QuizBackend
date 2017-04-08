package ru.andrewquiz.dto.quiz;

/**
 * Created by Andrew on 25.03.2017.
 */
public class Suit {
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }


    private long id;

    private String name;
}
