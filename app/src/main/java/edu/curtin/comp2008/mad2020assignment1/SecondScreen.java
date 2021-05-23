package edu.curtin.comp2008.mad2020assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * SecondScreen activity class is the 'Second Screen' based on the assignment specification.
 * It implements WhichFrag and WhatData to communicate between its fragment.
 * This activity will save the UI state changes to the savedInstanceState and restore UI state from the savedInstanceState.
 * In this class, it adds FragA, B and C to this SecondScreen activity.
 */
public class SecondScreen extends AppCompatActivity implements WhichFrag, WhatData
{
    //constants for the keys to avoid key naming conflicts by prefixing the package name
    private static final String WIN_POINT = "edu.curtin.comp2008.mad2020assignment1.winpoint";
    private static final String START_POINT = "edu.curtin.comp2008.mad2020assignment1.startpoint";
    private static final String QNALIST = "edu.curtin.comp2008.mad2020assignment1.qnalist";
    private static final String QNA = "edu.curtin.comp2008.mad2020assignment1.qna";
    private static final String FF = "edu.curtin.comp2008.mad2020assignment1.ff";
    private static final String I = "edu.curtin.comp2008.mad2020assignment1.i";
    private static final String I2 = "edu.curtin.comp2008.mad2020assignment1.i2";
    private static final String ISRE = "edu.curtin.comp2008.mad2020assignment1.isre";

    private int winPoint, startPoint, i, i2;
    private QnA qna;
    private ArrayList<QnA> qnaList;
    private ArrayList<FlagFullStructure> ff;
    private boolean isRe;

    /**
     * getIntent method is used to keep all the data bundling/unbundling in one place with only one activity needs to know about it to limits coupling between activities.
     * This let an activity class provide an intent for itself.
     * Things will also be simpler on the calling side
     *
     * @param c Context
     * @param inWinPoint int, the winning point
     * @param inStartPoint int, the starting or current point
     * @param inQnaList ArrayList of QnA
     * @param inQna QnA
     * @param inFF ArrayList of FlagFullStructure
     * @param inI int, the integer index to know the specific qna attributes
     * @param inI2 int, the integer second index to know the specific flag
     * @param inIsRe boolean, the boolean to know if the special question is correctly answered or not; for the specialCase.
     * @return intent
     */
    public static Intent getIntent(Context c, int inWinPoint, int inStartPoint, ArrayList<QnA> inQnaList, QnA inQna, ArrayList<FlagFullStructure> inFF, int inI, int inI2, boolean inIsRe)
    {
        //initialize and declare the intent to pass data from context to SecondScreen class
        Intent intent = new Intent(c, SecondScreen.class);
        //put each data to the bundle
        intent.putExtra(WIN_POINT, inWinPoint);
        intent.putExtra(START_POINT, inStartPoint);
        intent.putExtra(QNALIST, inQnaList);
        intent.putExtra(QNA, inQna);
        intent.putExtra(FF, inFF);
        intent.putExtra(I, inI);
        intent.putExtra(I2, inI2);
        intent.putExtra(ISRE, inIsRe);
        //return the intent
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //set the content view to activity_secondscreen xml file
        setContentView(R.layout.activity_secondscreen);

        //get each of the passed data from the intent
        Intent intent = getIntent();
        winPoint = intent.getIntExtra(WIN_POINT, 0);
        startPoint = intent.getIntExtra(START_POINT, 0);
        qnaList = (ArrayList<QnA>) intent.getSerializableExtra(QNALIST);
        qna = (QnA) intent.getSerializableExtra(QNA);
        ff = (ArrayList<FlagFullStructure>) intent.getSerializableExtra(FF);
        i = intent.getIntExtra(I, 0);
        i2 = intent.getIntExtra(I2, 0);
        isRe = intent.getBooleanExtra(ISRE, false);

        //Save and Reload State
        //if savedInstanceState is not null, get save data.
        //so when the phone/tablet rotates it will get the data by savedInstanceState, as it has the saved values
        if(savedInstanceState != null)
        {
            //get all the saved data
            winPoint = (int) savedInstanceState.getSerializable(WIN_POINT);
            startPoint = (int) savedInstanceState.getSerializable(START_POINT);
            qnaList = (ArrayList<QnA>) savedInstanceState.getSerializable(QNALIST);
            qna = (QnA) savedInstanceState.getSerializable(QNA);
            ff = (ArrayList<FlagFullStructure>) savedInstanceState.getSerializable(FF);
            i = savedInstanceState.getInt(I, 0);
            i2= savedInstanceState.getInt(I2, 0);
            isRe = savedInstanceState.getBoolean(ISRE, false);
        }

        //finding the attached fragments. The FragmentManager keeps track of the fragments.
        FragmentManager fm = getSupportFragmentManager();

        //find fragment A based on where it’s attached and findFragmentById() returns null if there is no fragment there
        Fragment fragAVertical = fm.findFragmentById(R.id.forfragA);
        //if frag A is an instance of FragAHorizontal,
        if(fragAVertical instanceof FragAHorizontal)
        {
            //find fragment A FragAHorizontal based on where it’s attached and findFragmentById() returns null if there is no fragment there
            fragAVertical = (FragAHorizontal) fm.findFragmentById(R.id.forfragA);
        }
        //if frag A is not an instance of FragAHorizontal, so an instance of FragAVertical instead
        else
        {
            //find fragment A FragAVertical based on where it’s attached and findFragmentById() returns null if there is no fragment there
            fragAVertical = (FragAVertical) fm.findFragmentById(R.id.forfragA);
        }

        //find each fragment, fragB and C, based on where it’s attached and findFragmentById() returns null if there is no fragment there
        FragB fragB = (FragB) fm.findFragmentById(R.id.forfragB);
        FragC fragC = (FragC) fm.findFragmentById(R.id.forfragC);

        //if findFragmentById() returns null as there is no fragment A, create fragment A object and attach it
        if (fragAVertical == null)
        {
            //create fragment A object
            fragAVertical = new FragAVertical();
            //manipulate fragments by fragment transaction. The complication is for flexibility
            //queues up an operation to attach a new fragment A by add fragAVertical in R.id.forfragA
            //commit to actually makes it happen
            fm.beginTransaction().add(R.id.forfragA, fragAVertical).commit();// where to add: R.id.forfragA, and what to add: fragAVertical.
        }

        //if findFragmentById() returns null as there is no fragment B, create fragment B object and attach it
        if (fragB == null)
        {
            //create fragment B object
            fragB = new FragB();
            //manipulate fragments by fragment transaction. The complication is for flexibility
            //queues up an operation to attach a new fragment B by add fragB in R.id.forfragB
            //commit to actually makes it happen
            fm.beginTransaction().add(R.id.forfragB, fragB).commit();// where to add: R.id.forfragB, and what to add: fragB.
        }

        //if findFragmentById() returns null as there is no fragment C, create fragment C object and attach it
        if (fragC == null)
        {
            //create fragment C object
            fragC = new FragC();
            //pass data to FragC by newInstance method
            FragC.newInstance(fragC, winPoint, startPoint);
            //manipulate fragments by fragment transaction. The complication is for flexibility
            //queues up an operation to attach a new fragment C by add fragE in R.id.forfragC
            //commit to actually makes it happen
            fm.beginTransaction().add(R.id.forfragC, fragC).commit();// where to add: R.id.forfragC, and what to add: fragC.
        }
    }

    /**
     * dataSender method communicates data between SecondScreen activity and its fragment
     *
     * @param winPoint int, the winning point
     * @param startPoint int, the starting or current point
     * @param qnaList ArrayList of QnA
     * @param qna QnA
     * @param ff ArrayList of FlagFullStructure
     * @param i int, the integer index to know the specific qna attributes
     * @param i2 int, the integer second index to know the specific flag
     * @param isRe boolean, the boolean to know if the special question is correctly answered or not; for the specialCase.
     */
    @Override
    public void dataSender(int winPoint, int startPoint, ArrayList<QnA> qnaList, QnA qna, ArrayList<FlagFullStructure> ff, int i, int i2, boolean isRe)
    {
        Intent intent = ThirdScreen.getIntent(this, winPoint, startPoint, qnaList, qna, ff, i, i2, isRe);
        startActivity(intent);
    }

    /**
     * changeFrag is an override method from WhichFrag used to change the fragment, between FragAHorizontal and FragAVertical, based on the parameter frag
     *
     * @param frag Fragment
     */
    @Override
    public void changeFrag(Fragment frag)
    {
        //finding the attached fragments. The FragmentManager keeps track of the fragments.
        FragmentManager fm = getSupportFragmentManager();
        //manipulate fragments by fragment transaction. The complication is for flexibility
        FragmentTransaction ft = fm.beginTransaction();
        //replace fragment A in R.id.forfragA by the new fragment A by replace
        ft.replace(R.id.forfragA, frag, frag.toString());
        //add it to the back stack by addToBackStack
        ft.addToBackStack(frag.toString());
        //commit to actually makes it happen
        ft.commit();
    }

    /**
     * changeFragRowCol is an override method from WhichFrag used to change the row or col and the layout of the fragment, based on the parameter rowCol and fragChosen
     *
     * @param rowCol int, the number of row
     * @param fragChosen String, the fragment chosen, either vertical("V") or horizontal("H")
     */
    @Override
    public void changeFragRowCol(int rowCol, String fragChosen)
    {
        //pass data to FragB by newInstance method and use it as frag
        Fragment frag = FragB.newInstance(rowCol, fragChosen);

        //finding the attached fragments. The FragmentManager keeps track of the fragments.
        FragmentManager fm = getSupportFragmentManager();
        //manipulate fragments by fragment transaction. The complication is for flexibility
        FragmentTransaction ft = fm.beginTransaction();
        //replace fragment B in R.id.forfragB by the new fragment B by replace
        ft.replace(R.id.forfragB, frag, frag.toString());
        //add it to the back stack by addToBackStack
        ft.addToBackStack(frag.toString());
        //commit to actually makes it happen
        ft.commit();
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
        //put each data to savedInstanceState bundle
        savedInstanceState.putSerializable(WIN_POINT, winPoint);
        savedInstanceState.putSerializable(START_POINT, startPoint);
        savedInstanceState.putSerializable(QNALIST, qnaList);
        savedInstanceState.putSerializable(QNA, qna);
        savedInstanceState.putSerializable(FF, ff);
        savedInstanceState.putInt(I, i);
        savedInstanceState.putInt(I2, i2);
        savedInstanceState.putBoolean(ISRE, isRe);

        //save activity data to the provided Bundle which is savedInstanceState
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Restore UI state from the savedInstanceState.
     * This bundle has also been passed to onCreate.
     * restore the data that is saved.
     *
     * @param savedInstanceState bundle
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        //restore activity data to the provided Bundle which is savedInstanceState
        super.onRestoreInstanceState(savedInstanceState);

        //get the savedInstanceState of each saved data
        winPoint = savedInstanceState.getInt(WIN_POINT, 0);
        startPoint = savedInstanceState.getInt(START_POINT, 0);
        qnaList = (ArrayList<QnA>) savedInstanceState.getSerializable(QNALIST);
        qna = (QnA) savedInstanceState.getSerializable(QNA);
        ff = (ArrayList<FlagFullStructure>) savedInstanceState.getSerializable(FF);
        i = savedInstanceState.getInt(I, 0);
        i2 = savedInstanceState.getInt(I2, 0);
        isRe = savedInstanceState.getBoolean(ISRE, false);
    }
}
