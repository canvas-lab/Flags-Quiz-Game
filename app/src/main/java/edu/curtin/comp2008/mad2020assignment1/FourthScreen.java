package edu.curtin.comp2008.mad2020assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * FourthScreen activity class is the 'Fourth Screen' based on the assignment specification.
 * It implements WhatData to communicate between its fragment and FragE.ExtraData to get the current fragC data every time an answer is clicked.
 * This activity will save the UI state changes to the savedInstanceState and restore UI state from the savedInstanceState.
 * In this class, it adds FragE and FragC to this FourthScreen activity.
 * Also, if the prev2 button is pressed, it will pass the data and start ThirdScreen activity.
 */
public class FourthScreen extends AppCompatActivity implements WhatData, FragE.ExtraData
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

    private ArrayList<QnA> qnaList;
    private int winPoint, startPoint, i, i2;
    private QnA qna;
    private ArrayList<FlagFullStructure> ff;
    private boolean isRe;

    private Button prev2;

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
        //initialize and declare the intent to pass data from context to FourthScreen class
        Intent intent = new Intent(c, FourthScreen.class);
        //put each data to the bundle
        Bundle bundle = new Bundle();
        bundle.putInt(WIN_POINT, inWinPoint);
        bundle.putInt(START_POINT, inStartPoint);
        bundle.putSerializable(QNALIST, inQnaList);
        bundle.putSerializable(QNA, inQna);
        bundle.putSerializable(FF, inFF);
        bundle.putInt(I, inI);
        bundle.putInt(I2, inI2);
        bundle.putBoolean(ISRE, inIsRe);
        //put the bundle to the intent
        intent.putExtras(bundle);

        //return the intent
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //set the content view to activity_fourthscreen xml file
        setContentView(R.layout.activity_fourthscreen);

        //find prev2 view by an id of prev2
        prev2 = (Button) findViewById(R.id.prev2);

        //if savedInstanceState is null, create the data
        if (savedInstanceState == null)
        {
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
        }
        //Save and Reload State
        //if savedInstanceState is not null, get save data.
        //so when the phone/tablet rotates it will get the data by savedInstanceState, as it has the saved values
        if (savedInstanceState != null)
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
        //find each fragment, fragE and fragC, based on where it’s attached and findFragmentById() returns null if there is no fragment there
        FragE fragE = (FragE) fm.findFragmentById(R.id.forfragE);
        FragC fragC = (FragC) fm.findFragmentById(R.id.forfragC);

        //if findFragmentById() returns null as there is no fragment E, create fragment E object and attach it
        if (fragE == null)
        {
            //create fragment E object
            fragE = new FragE();
            //manipulate fragments by fragment transaction. The complication is for flexibility
            //queues up an operation to attach a new fragment E by add fragE in R.id.forfragE
            //commit to actually makes it happen
            fm.beginTransaction().add(R.id.forfragE, fragE).commit();// where to add: R.id.forfragE, and what to add: fragE.
        }

        //if findFragmentById() returns null as there is no fragment C, create fragment C object and attach it
        if (fragC == null)
        {
            //create the fragment C object
            fragC = new FragC();
            //pass data to FragC by newInstance method
            FragC.newInstance(fragC, winPoint, startPoint);
            //manipulate fragments by fragment transaction. The complication is for flexibility
            //queues up an operation to attach a new fragment C by add fragE in R.id.forfragC
            //commit to actually makes it happen
            fm.beginTransaction().add(R.id.forfragC, fragC).commit();// where to add: R.id.forfragC, and what to add: fragC.
        }

        //In phone, when prev2 is pressed, the user goes to the Third screen to select a question from the qna list or to go back to Second screen
        prev2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //pass the data and start the ThirdScreen activity
                Intent resultIntent = ThirdScreen.getIntent(FourthScreen.this, winPoint, startPoint, qnaList, qna, ff, i, i2, isRe);
                startActivity(resultIntent);
            }
        });
    }

    /**
     * extraDataC method override from FragE.ExtraData interface to make fragC in specifically fourth screen, updates every time an answer is clicked
     * this communicate fragC with its activity's container
     *
     * @param winPoint int, the winning point
     * @param startPoint int, the starting or current point
     */
    @Override
    public void extraDataC(int winPoint, int startPoint)
    {
        //create the fragment C object
        FragC frag = new FragC();
        //pass data to FragC by newInstance method
        FragC.newInstance(frag, winPoint, startPoint);

        //finding the attached fragments. The FragmentManager keeps track of the fragments.
        FragmentManager fm = getSupportFragmentManager();
        //manipulate fragments by fragment transaction. The complication is for flexibility
        FragmentTransaction ft = fm.beginTransaction();
        //replace fragment C in R.id.forfragC by the new fragment C by replace
        ft.replace(R.id.forfragC, frag, frag.toString());
        //add it to the back stack by addToBackStack
        ft.addToBackStack(frag.toString());
        //commit to actually makes it happen
        ft.commit();
    }


    /**
     * dataSender method communicates data between FourthScreen activity and its fragment
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
        this.winPoint = winPoint;
        this.startPoint = startPoint;
        this.qnaList = qnaList;
        this.qna = qna;
        this.ff = ff;
        this.i = i;
        this.i2 = i2;
        this.isRe = isRe;
    }

    public int gettheWinPoint()
    {
        return this.winPoint;
    }

    public int gettheStartPoint()
    {
        return this.startPoint;
    }

    public ArrayList<QnA> gettheQnaList()
    {
        return this.qnaList;
    }

    public QnA gettheQna()
    {
        return this.qna;
    }

    public ArrayList<FlagFullStructure> gettheFF()
    {
        return this.ff;
    }

    public int gettheI()
    {
        return this.i;
    }

    public int gettheI2()
    {
        return this.i2;
    }

    public boolean getIsRe()
    {
        return this.isRe;
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
        winPoint = (int) savedInstanceState.getSerializable(WIN_POINT);
        startPoint = (int) savedInstanceState.getSerializable(START_POINT);
        qnaList = (ArrayList<QnA>) savedInstanceState.getSerializable(QNALIST);
        qna = (QnA) savedInstanceState.getSerializable(QNA);
        ff = (ArrayList<FlagFullStructure>) savedInstanceState.getSerializable(FF);
        i = savedInstanceState.getInt(I, 0);
        i2 = savedInstanceState.getInt(I2, 0);
        isRe = savedInstanceState.getBoolean(ISRE, false);
    }
}
