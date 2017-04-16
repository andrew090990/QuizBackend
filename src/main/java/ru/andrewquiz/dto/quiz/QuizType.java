package ru.andrewquiz.dto.quiz;

/**
 * Created by Andrew on 15.04.2017.
 */

public enum QuizType {

    MULTIPLE_CHOICE(1),
    OPEN_CLOZE(2);

    private long id;

    public static QuizType fromId(long id) {
        for (QuizType quizType : QuizType.values()) {
            if (quizType.id() == id) {
                return quizType;
            }
        }

        return null;
    }

    QuizType(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }
}
