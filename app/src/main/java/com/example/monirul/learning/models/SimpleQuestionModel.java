package com.example.monirul.learning.models;

public class SimpleQuestionModel {
    String question;
    String answer;
    boolean isAnswerVisible;

    public SimpleQuestionModel(String question, String answer, boolean isAnswerVisible) {
        this.question = question;
        this.answer = answer;
        this.isAnswerVisible = isAnswerVisible;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswerVisible() {
        return isAnswerVisible;
    }

    public void setAnswerVisible(boolean answerVisible) {
        isAnswerVisible = answerVisible;
    }
}
