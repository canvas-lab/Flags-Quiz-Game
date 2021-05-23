package edu.curtin.comp2008.mad2020assignment1;

import java.util.ArrayList;

/**
 * WhatData is an interface that contains dataSender method
 */
public interface WhatData
{
    //dataSender method will be used to helps communicates between fragment and its activity container.
    void dataSender(int winPoint, int startPoint, ArrayList<QnA> qnaList, QnA qna, ArrayList<FlagFullStructure> ff, int i, int i2, boolean isRe);
}
