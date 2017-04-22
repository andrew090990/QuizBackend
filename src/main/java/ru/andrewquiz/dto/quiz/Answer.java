package ru.andrewquiz.dto.quiz;

/**
 * Created by Andrew on 16.04.2017.
 */

public class Answer {

    private Long id;

    private String content;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
