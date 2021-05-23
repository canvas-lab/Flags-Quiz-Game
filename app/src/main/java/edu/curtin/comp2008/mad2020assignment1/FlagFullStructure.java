package edu.curtin.comp2008.mad2020assignment1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  FlagFullStructure implements Serializable and contains the flag drawable in integer, qna ArrayList and boolean isClicked.
 *  boolean isClicked is functioned to know if the flag is still clickable or not, based on if all questions of that specific flag is already answered by the user.
 */
public class FlagFullStructure implements Serializable
{
    //private fields
    private int flag;
    private ArrayList<QnA> qnaList;
    private boolean isClicked;

    //default constructor
    public FlagFullStructure()
    {
        this.flag = 0;
        this.qnaList = new ArrayList<>();
        this.isClicked = false;
    }

    //alternate constructor
    public FlagFullStructure(int flag, ArrayList<QnA> qnaList)
    {
        this.flag = flag;
        this.qnaList = qnaList;
        this.isClicked = false;
    }

    public boolean isClicked()
    {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getFlag() {
        return flag;
    }

    public ArrayList<QnA> getQnaList()
    {
        return qnaList;
    }

    public void setQnaList(ArrayList<QnA> qnaList)
    {
        this.qnaList = qnaList;
    }
}
