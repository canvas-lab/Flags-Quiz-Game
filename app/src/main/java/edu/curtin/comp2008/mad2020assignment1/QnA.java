package edu.curtin.comp2008.mad2020assignment1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * QnA class is implements Serializable and contains a question, list of choices, correct answer, point, penalty, isSpecial and isClicked
 */
public class QnA implements Serializable
{
    private String questions;
    private List<String> choices;
    private int correctAns;
    private int point, penalty;
    private boolean isSpecial;
    private boolean isClicked;

    //default constructor
    public QnA()
    {
        this.questions = "";
        this.choices = new ArrayList<>();
        this.correctAns = 0;
        this.point = 0;
        this.penalty = 0;
        this.isSpecial = false;
        this.isClicked = false;
    }

    //alternate constructor
    public QnA(String questions, List<String> choices, int correctAns, int point, int penalty, boolean isSpecial)
    {
        this.questions = questions;
        this.choices = choices;
        this.correctAns = correctAns;
        this.point = point;
        this.penalty = penalty;
        this.isSpecial = isSpecial;
        this.isClicked = false;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPenalty() {
        return penalty;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public String getQuestions() {
        return questions;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectAns() {
        return correctAns;
    }
}
