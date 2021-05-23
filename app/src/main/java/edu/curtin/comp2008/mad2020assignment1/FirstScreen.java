package edu.curtin.comp2008.mad2020assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.Math;

/**
 *  FirstScreen activity class is the 'First Screen' based on the assignment specification.
 *  It contains a logo, winning point, starting point and a start button.
 *  This activity will save the UI state changes to the savedInstanceState and restore UI state from the savedInstanceState.
 *  In this class, it will generate the random win and start point.
 *  Also, if the start button is pressed, it will pass the data and start SecondScreen activity
 */
public class FirstScreen extends AppCompatActivity
{
    private TextView logo, winPoint, startPoint;
    private Button start;

    private int randomWinPoint, randomStartPoint;

    //constants for the keys to avoid key naming conflicts by prefixing the package name
    private static final String WINPOINT = "edu.curtin.comp2008.mad2020assignment1.winpoint";
    private static final String STARTPOINT = "edu.curtin.comp2008.mad2020assignment1.startpoint";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //set the content view to activity_firstscreen xml file
        setContentView(R.layout.activity_firstscreen);

        //if savedInstanceState is null, create the data
        if(savedInstanceState == null)
        {
            //generate the randomly generated winning point
            randomWinPoint = generateRandomInt();
            //generate the randomly generated starting point
            randomStartPoint = generateRandomInt();
            //start point must not be greater than the win point
            while(randomStartPoint >= randomWinPoint)
            {
                randomStartPoint = generateRandomInt();
            }

        }
        //Save and Reload State
        //if savedInstanceState is not null, get save data.
        //so when the phone/tablet rotates it will get the data by savedInstanceState, as it has the saved values
        else if(savedInstanceState != null)
        {
            //get all the saved data which are randomWinPoint and randomStartPoint
            randomWinPoint = savedInstanceState.getInt(WINPOINT);
            randomStartPoint = savedInstanceState.getInt(STARTPOINT);
        }

        //find each view by an id
        logo = (TextView) findViewById(R.id.logo);
        startPoint = (TextView) findViewById(R.id.startPoint);
        winPoint = (TextView) findViewById(R.id.winPoint);
        start = (Button) findViewById(R.id.start);

        //set winPoint test
        winPoint.setText("Randomly generated win point: " + Integer.toString(randomWinPoint));
        //set startPoint test
        startPoint.setText("Randomly generated start point: " + Integer.toString(randomStartPoint));


        //When start button is pressed, user goes to the second screen
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //pass the data and start the SecondScreen activity
                Intent intent = SecondScreen.getIntent(FirstScreen.this, randomWinPoint, randomStartPoint, null, null, null, -1, -1, false);
                startActivity(intent);
            }
        });
    }

    /**
     * generateRandomInt method returns a random integer to be use for win and start point
     * for this project, generateRandomInt method returns a number between max 1000 and min 50
     * The assignment doesn't say that I need to have a max and min, but I just put it because the random number may be to high or low.
     *
     * @return randomNum
     */
    public int generateRandomInt()
    {
        //init and declare the max and min number
        int max = 1000;
        int min = 50;

        //calculate the randomNum from 50 to 1000
        int randomNum = (int)(Math.random()*((max-min)+1))+min;
        //return the randomNum
        return randomNum;
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
        //put randomWinPoint and randomStartPoint to savedInstanceState bundle
        savedInstanceState.putInt(WINPOINT, randomWinPoint);
        savedInstanceState.putInt(STARTPOINT, randomStartPoint);

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

        //get the savedInstanceState of randomWinPoint and randomStartPoint
        randomWinPoint = savedInstanceState.getInt(WINPOINT);
        randomStartPoint = savedInstanceState.getInt(STARTPOINT);
    }
}
