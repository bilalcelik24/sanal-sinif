package com.tutkal.yazilimsinama.model.Class;

public class AddQuestions {
    private String answerType,examID,userID,questionID;


    public AddQuestions(String answerType, String examID, String userID, String questionID) {
        this.answerType = answerType;
        this.examID = examID;
        this.userID = userID;
        this.questionID = questionID;
    }

    public AddQuestions() {
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

}
