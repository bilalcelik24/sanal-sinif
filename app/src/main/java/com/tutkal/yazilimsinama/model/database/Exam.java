package com.tutkal.yazilimsinama.model.database;

import com.tutkal.yazilimsinama.model.IResponse;

public class Exam {

    private String Questions, answerA,answerB,answerC,answerD,correctAnswer,category,questionID;




    public Exam() {
    }

    public Exam(String questions, String answerA, String answerB, String answerC, String answerD, String correctAnswer, String category, String questionID ) {
        Questions = questions;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.questionID=questionID;
    }

    public void getQuestions(final IResponse ıResponse){



    }
        //$_POST["question"],$_POST["answerA"],$_POST["answerB"],$_POST["answerC"],$_POST["answerD"],$_POST["correct"],$_POST["category"]);
    public void addQestions(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public String getQuestionID() {
        return questionID;
    }
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}
