package com.uzdev.quizApp.model;

import java.util.List;

public class Questions {
    private List<Question> questions;

    public Questions() {
    }

    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
