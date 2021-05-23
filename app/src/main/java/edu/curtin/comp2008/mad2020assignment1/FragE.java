package edu.curtin.comp2008.mad2020assignment1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * FragE fragment class is the 'Fragment E' based on the assignment specification that implements Fragment.
 */
public class FragE extends Fragment
{
    //constants for the keys to avoid key naming conflicts by prefixing the package name
    private static final String CLICKED_NUM = "edu.curtin.comp2008.mad2020assignment1.clickednum";
    private static final String TEXT_CHOICE = "edu.curtin.comp2008.mad2020assignment1.textchoice";
    private static final String IN = "edu.curtin.comp2008.mad2020assignment1.in";
    private static final String TEXT_CHOICE2 = "edu.curtin.comp2008.mad2020assignment1.textchoice2";
    private static final String IN2 = "edu.curtin.comp2008.mad2020assignment1.in2";

    private Button choicex, choice1, choice2, choice3;
    private TextView question;

    private ArrayList<QnA> qnaList;
    private QnA qna;
    private ArrayList<FlagFullStructure> ff;
    private int winPoint, startPoint, clickedNum, i, i2, in, in2;
    private boolean isRe;
    private String textChoice, textChoice2;

    //to communicate from between fragment its activity container
    private WhatData mCallback;
    private ExtraData mC;

    //public constructor
    public FragE()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Save and Reload State
        //if bundle is not null, get save data.
        //so when the phone/tablet rotates it will get the data by bundle, as it has the saved values
        if (savedInstanceState != null)
        {
            //restore the state before inflating the layout
            //get all the saved data
            clickedNum = (int) savedInstanceState.getSerializable(CLICKED_NUM);
            textChoice = (String) savedInstanceState.getSerializable(TEXT_CHOICE);
            in = (int) savedInstanceState.getSerializable(IN);
            textChoice = (String) savedInstanceState.getSerializable(TEXT_CHOICE2);
            in2 = (int) savedInstanceState.getSerializable(IN2);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            //declare mCallback WhatData
            mCallback = (WhatData) context;
            //declare mC ExtraData
            mC = (ExtraData) context;
        }
        catch (Exception e)
        {
            throw new ClassCastException(context.toString() + " oh, this is bcs i must implement WhatData and ExtraData interface");
        }
    }

    //ExtraData interface
    public interface ExtraData
    {
        //extraDataC method to make fragC updates every time an answer is clicked. this communicate fragC with its activity's container
        void extraDataC(int winPoint, int startPoint);
    }

    //define the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)//LayoutInflator: i.e. it reads the XML and creates the UI based on it.
    {
        //declare clickedNum to 0. clickedNum is used to know if a choice is clicked or not. clickedNum is 1 if a choice from the list of choice is clicked
        clickedNum = 0;

        //find question view by id R.id.question
        question = (TextView) getActivity().findViewById(R.id.question);

        //in phone
        //if its activity container is FourthScreen, get each data from its Activity container which is FourthScreen
        if(getActivity().getClass().equals(FourthScreen.class))
        {
            //its Activity is FourthScreen
            FourthScreen activity = (FourthScreen) getActivity();
            //get each data from FourthScreen
            winPoint = activity.gettheWinPoint();
            startPoint = activity.gettheStartPoint();
            qnaList = activity.gettheQnaList();
            qna = activity.gettheQna();
            ff = activity.gettheFF();
            i = activity.gettheI();
            i2 = activity.gettheI2();
            isRe = activity.getIsRe();
        }
        //in tablet
        //if its activity container is ThirdScreen, get each data from its Activity container which is ThirdScreen
        else
        {
            //its Activity is ThirdScreen
            ThirdScreen activity = (ThirdScreen) getActivity();
            //get each data from ThirdScreen
            winPoint = activity.getWinPoint();
            startPoint = activity.getStartPoint();
            qnaList = activity.getQnaList();
            qna = activity.getQna();
            ff = activity.getFF();
            i = activity.getI();
            i2 = activity.getI2();
            isRe = activity.getIsRe();
        }

        //Save and Reload State
        //if bundle is not null, get save data.
        //so when the phone/tablet rotates it will get the data by bundle, as it has the saved values
        if (bundle != null)
        {
            //get all the saved data
            clickedNum = (int) bundle.getSerializable(CLICKED_NUM);
            textChoice = (String) bundle.getSerializable(TEXT_CHOICE);
            in = (int) bundle.getSerializable(IN);
            textChoice2 = (String) bundle.getSerializable(TEXT_CHOICE2);
            in2 = (int) bundle.getSerializable(IN2);
        }

        //set the question text
        question.setText(qna.getQuestions());

        //View
        View view = null;
        //if there are specifically 3 choices present
        if(qna.getChoices().size() == 3)
        {
            //LayoutInflater takes a layout reference of qna_list2, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
            //create a root View object that is used to find a specific UI elements by view.findViewById(..).
            view = inflater.inflate(R.layout.qna_list2, ui, false);

            //find each view by id
            choice1 = (Button) view.findViewById(R.id.choice1);
            choice2 = (Button) view.findViewById(R.id.choice2);
            choice3 = (Button) view.findViewById(R.id.choice3);

            //set each choice text to its choice
            choice1.setText(qna.getChoices().get(0));
            choice2.setText(qna.getChoices().get(1));
            choice3.setText(qna.getChoices().get(2));

            if(textChoice != null)
            {
                if(in == 0)
                {
                    choice1.setText(textChoice);
                    doColorText(choice1);
                }
                else if (in == 1)
                {
                    choice2.setText(textChoice);
                    doColorText(choice2);
                }
                else if(in == 2)
                {
                    choice3.setText(textChoice);
                    doColorText(choice3);
                }
            }

            //when choice1 is clicked
            choice1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //call doTheChoice method and pass its button, which is choice1, and 0 as its the first choice
                    doTheChoice(choice1, 0);
                }
            });

            //when choice2 is clicked
            choice2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //call doTheChoice method and pass its button, which is choice2, and 1 as its the second choice
                    doTheChoice(choice2, 1);
                }
            });

            //when choice3 is clicked
            choice3.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //call doTheChoice method and pass its button, which is choice3, and 2 as its the third choice
                    doTheChoice(choice3, 2);
                }
            });

        }
        //if there are not 3 choices present, but 2 or 4 choices
        else
        {
            //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
            //The root View object is used to find a specific UI elements by view.findViewById(..).
            view = inflater.inflate(R.layout.fragment_b, ui, false);

            //Obtain the RecyclerView UI element by findViewById
            RecyclerView rv = (RecyclerView) view.findViewById(R.id.fragBRecyclerView);
            //specify the the layout to have 2 rows
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            rv.setLayoutManager(gridLayoutManager);
            //create the adapter. the adapter will takes the parameter flag data
            FragE.AdapterStructure adapterStructure = new FragE.AdapterStructure(qna);
            //hook it up
            rv.setAdapter(adapterStructure);
        }

        //return the view
        return view;
    }

    /**
     * color the text into green if the text is correct, else color in red for wrong answer
     *
     * @param choicex Button
     */
    public void doColorText(Button choicex)
    {
        if(textChoice.contains("CORRECT"))
        {
            choicex.setTextColor(Color.GREEN);
        }
        else
        {
            choicex.setTextColor(Color.RED);
        }
    }

    /**
     * doTheChoice method handles the list of choices part.
     * if a button choice is clicked, it will show if its correct or wrong.
     * if a button choice is clicked, other choices are not clickable anymore.
     * it will add the point if it is not a special question. it will set isRe to true and not add any point if the correct choice of a special question is chosen, else subtract current point by the penalty point
     *
     * @param choicex Button
     * @param index int, index to show which choice from the list
     */
    public void doTheChoice(Button choicex, int index)
    {
        //set in to the index
        in = index;

        //if clickedNum is 0 or no choice is clicked yet
        if(clickedNum == 0)
        {
            //if qna correct answer is index+1. +1 because index starts from 0
            if(qna.getCorrectAns() == index+1)
            {
                //set the text choice, show that it is correct
                textChoice = qna.getChoices().get(index) + "\n(O) CORRECT !";
                //set the choice text to show that it is correct
                choicex.setText(textChoice);
                //set the choice text into green
                choicex.setTextColor(Color.GREEN);

                //if qna that is previously clicked is not a special question, add the current point
                if(!qna.isSpecial())
                {
                    //add the current point. add by adding that question point
                    startPoint = startPoint + qna.getPoint();
                }
                //if qna that is previously clicked is a special question, set isRe to true
                //isRe will be used and set again in FragB. Special case: If a special question is correctly answered and (still not redeemed), clicking the previous button will take to the second screen to select a flag. The points of all the questions of the selected flag will be increased by 10 points.
                else
                {
                    //set isRe to true
                    isRe = true;
                }

            }
            //if qna correct answer is not index+1. +1 because index starts from 0
            else {
                //set the text choice, show that it is wrong
                textChoice = qna.getChoices().get(index) + "\n(X) WRONG !";
                //set the choice text to show that it is wrong
                choicex.setText(textChoice);
                //set the choice text into red
                choicex.setTextColor(Color.RED);
                //subtract the current point. subtract by subtracting that question penalty point
                startPoint = startPoint - qna.getPenalty();
            }
            //set clickedNum to 1 as a choice in the list of choices has been clicked
            clickedNum = 1;
        }

        //set the qna as isClicked, so that it know that the qna is already clicked. to know that specific qna not clickable anymore
        qnaList.get(i).setClicked(true);

        //use mC extraDataC to pass and communicate data between FragE and its activity container. it will be used for fragC
        mC.extraDataC(winPoint, startPoint);

        //in phone
        //if its activity container is FourthScreen, get each data from its Activity container which is FourthScreen
        if(getActivity().getClass().equals(FourthScreen.class))
        {
            //use mCallback dataSender to pass and communicate data between FragE and its activity container.
            mCallback.dataSender(winPoint, startPoint, qnaList, qna, ff, i, i2, isRe);
        }
        //in tablet
        //if its activity container is ThirdScreen, get each data from its Activity container which is ThirdScreen
        else
        {
            //its Activity is ThirdScreen
            ThirdScreen activity = (ThirdScreen) getActivity();
            //pass data to its activity container.
            activity.sendTheDatas(winPoint, startPoint, qnaList, qna, ff, i, i2, isRe);
        }
    }

    /**
     * AdapterStructure class that extends a RecyclerView.Adapter of FragE.TwoFourViewHolder.
     * Called when the RecyclerView needs a new ViewHolder
     */
    public class AdapterStructure extends RecyclerView.Adapter<TwoFourViewHolder>
    {
        QnA qnaa;

        //AdapterStructure
        public AdapterStructure(QnA qnaa) {
            this.qnaa = qnaa;
        }

        //RecyclerView calls onCreateViewHolder when it needs a new ViewHolder.
        @Override
        public TwoFourViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            //LayoutInflater belongs to the activity so it call Fragment’s getActivity() method. It can call getActivity() because we’re nested inside a Fragment class.
            //needs a LayoutInflater to create a ViewHolder.
            LayoutInflater li = LayoutInflater.from(getActivity());
            //adapter returns a new TwoFourViewHolder.
            return new TwoFourViewHolder(li, parent);
        }

        //onBindViewHolder(ViewHolderStructure, int) is called when RecyclerView needs to rewrite a particular list row.
        //Called when RecyclerView needs to use a ViewHolder to display a different data element.
        //The adapter updates the supplied ViewHolder.
        //index identifies which element to display.
        @Override
        public void onBindViewHolder(TwoFourViewHolder vh, int index)
        {
            //pass the data to setTwoFourDetails method, bind, in TwoFourViewHolder. pass a choice and its index
            //index identifies the data element that ViewHolder should now display.
            vh.setTwoFourDetails(qnaa.getChoices().get(index), index);
        }

        //getItemCount method return the total number of data elements of qna list of choices, which may be from 2 to 4
        @Override
        public int getItemCount()
        {
            //return qna list of choices size
            return qnaa.getChoices().size();
        }
    }

    /**
     * TwoFourViewHolder class that extends a RecyclerView.ViewHolder. contains setTwoFourDetails method
     */
    private class TwoFourViewHolder extends RecyclerView.ViewHolder
    {
        //Reference to UI element(s)
        private Button choicex;

        private String choice;
        private int index;

        //TwoFourViewHolder
        private TwoFourViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //LayoutInflater takes a layout reference, qna_list, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
            //Use the LayoutInflater to convert the XML to a View object tree. and the superclass then makes it available as 'itemView'
            super(li.inflate(R.layout.qna_list, parent, false));

            //Grab UI element reference. itemView is a superclass field.
            choicex = itemView.findViewById(R.id.choicex);

            //when a choice is clicked
            choicex.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //set in2 to index
                    in2 = index;

                    //if clickedNum is 0 or no choice is clicked yet
                    if(clickedNum == 0)
                    {
                        //if qna correct answer is index+1. +1 because index starts from 0
                        if(qna.getCorrectAns() == index+1)
                        {
                            //set the text choice, show that it is correct
                            textChoice2 = choice + "\n(O) CORRECT !";
                            //set the choice text to show that it is correct
                            choicex.setText(textChoice2);
                            //set the choice text into green
                            choicex.setTextColor(Color.GREEN);

                            //if qna that is previously clicked is not a special question, add the current point
                            if(!qna.isSpecial())
                            {
                                //add the current point. add by adding that question point
                                startPoint = startPoint + qna.getPoint();
                            }
                            //if qna that is previously clicked is a special question, set isRe to true
                            //isRe will be used and set again in FragB. Special case: If a special question is correctly answered and (still not redeemed), clicking the previous button will take to the second screen to select a flag. The points of all the questions of the selected flag will be increased by 10 points.
                            else
                            {
                                //set isRe to true
                                isRe = true;
                            }

                        }
                        //if qna correct answer is not index+1. +1 because index starts from 0
                        else
                            {
                            //set the text choice, show that it is wrong
                            textChoice2 = choice + "\n(X) WRONG !";
                            //set the choice text to show that it is wrong
                            choicex.setText(textChoice2);
                            //set the choice text into red
                            choicex.setTextColor(Color.RED);
                            //subtract the current point. subtract by subtracting that question penalty point
                            startPoint = startPoint - qna.getPenalty();
                        }
                        //set clickedNum to 1 as a choice in the list of choices has been clicked
                        clickedNum = 1;
                    }

                    //set the qna as isClicked, so that it know that the qna is already clicked. to know that specific qna not clickable anymore
                    qnaList.get(i).setClicked(true);

                    //use mC extraDataC to pass and communicate data between FragE and its activity container. it will be used for fragC
                    mC.extraDataC(winPoint, startPoint);

                    //in phone
                    //if its activity container is FourthScreen, get each data from its Activity container which is FourthScreen
                    if(getActivity().getClass().equals(FourthScreen.class))
                    {
                        //use mCallback dataSender to pass and communicate data between FragE and its activity container.
                        mCallback.dataSender(winPoint, startPoint, qnaList, qna, ff, i, i2, isRe);
                    }
                    //in tablet
                    //if its activity container is ThirdScreen, get each data from its Activity container which is ThirdScreen
                    else
                    {
                        //its Activity is ThirdScreen
                        ThirdScreen activity = (ThirdScreen) getActivity();
                        //pass data to its activity container.
                        activity.sendTheDatas(winPoint, startPoint, qnaList, qna, ff, i, i2, isRe);
                    }
                }
            });
        }

        //set the details of TwoFourViewHolder. the bind
        private void setTwoFourDetails(String choice, int index)
        {
            this.choice = choice;
            this.index = index;

            //set the choice text
            choicex.setText(choice);

            //if textChoice2 is not null,
            if(textChoice2 != null)
            {
                //if in2 is index, set the choice text and color
                if(in2 == index)
                {
                    //set the choice text
                    choicex.setText(textChoice2);

                    //if text choice contains the correct answer, set the choice text color to green
                    if (textChoice2.contains("CORRECT"))
                    {
                        //set the choice text color to green
                        choicex.setTextColor(Color.GREEN);
                    }
                    //if text choice contains the wrong answer, set the choice text color to red
                    else {
                        //set the choice text color to red
                        choicex.setTextColor(Color.RED);
                    }
                }
            }
        }
    }

    /**
     * Save UI state changes to the savedInstanceState.
     * This bundle will be passed to onCreate if the process is killed and restarted.
     * Save data when phone rotates.
     *
     * @param savedInstanceState bundle
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        //save activity data to the provided Bundle which is savedInstanceState
        super.onSaveInstanceState(savedInstanceState);

        //put each data to savedInstanceState bundle
        savedInstanceState.putSerializable(CLICKED_NUM, clickedNum);
        savedInstanceState.putSerializable(TEXT_CHOICE, textChoice);
        savedInstanceState.putInt(IN, in);
        savedInstanceState.putString(TEXT_CHOICE2, textChoice2);
        savedInstanceState.putInt(IN2, in2);
    }

    /**
     * Restore UI state from the savedInstanceState.
     * This bundle has also been passed to onCreate.
     * restore the data that is saved.
     *
     * @param savedInstanceState bundle
     */
    @Override
    public void onViewStateRestored(Bundle savedInstanceState)
    {
        //restore activity data to the provided Bundle which is savedInstanceState
        super.onViewStateRestored(savedInstanceState);

        //if bundle savedInstanceState is not null, restore the state
        if (savedInstanceState != null)
        {
            //get the savedInstanceState of each saved data
            clickedNum = savedInstanceState.getInt(CLICKED_NUM, 0);
            textChoice = (String) savedInstanceState.getSerializable(TEXT_CHOICE);
            in = (int) savedInstanceState.getSerializable(IN);
            textChoice2 = (String) savedInstanceState.getSerializable(TEXT_CHOICE2);
            in2 = (int) savedInstanceState.getSerializable(IN2);
        }
    }
}