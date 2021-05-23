package edu.curtin.comp2008.mad2020assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * FragC fragment class is the 'Fragment C' based on the assignment specification that implements Fragment.
 */
public class FragC extends Fragment
{
    //constants for the keys to avoid key naming conflicts by prefixing the package name
    private static final String WIN_POINT = "edu.curtin.comp2008.mad2020assignment1.winpoint";
    private static final String START_POINT = "edu.curtin.comp2008.mad2020assignment1.startpoint";

    private TextView totalPoint, winOrLose;

    private int winPoint, startPoint;

    //public constructor
    public FragC()
    {
        // Required empty public constructor
    }

    /**
     * factory method to create a new instance of this fragment using the provided parameters
     * to know the winning and starting point
     *
     * @param fragment, fragC
     * @param inWinPoint int, the winning point
     * @param inStartPoint int, the start or current point
     * @return fragment, a new instance of fragment FragC.
     */
    public static FragC newInstance(FragC fragment, int inWinPoint, int inStartPoint)
    {
        //create new bundle for the argument
        Bundle args = new Bundle();
        //put the win point in the bundle
        args.putInt(WIN_POINT, inWinPoint);
        //put the start point in the bundle
        args.putInt(START_POINT, inStartPoint);
        //set the frag arguments
        fragment.setArguments(args);
        //return the fragment fragC
        return fragment;
    }

    //define the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
        //The root View object is used to find a specific UI elements by view.findViewById(..).
        View view = inflater.inflate(R.layout.fragment_c, ui,false);

        //if getArguments is not null, get the arguments
        if (getArguments() != null)
        {
            //get the arguments that is previously put in newInstance method, which are winPoint and startPoint
            winPoint = getArguments().getInt(WIN_POINT);
            startPoint = getArguments().getInt(START_POINT);
        }
        //if getArguments is null, do nothing
        else {}

        //find each view by id
        totalPoint = (TextView) view.findViewById(R.id.totalPoint);
        winOrLose = (TextView) view.findViewById(R.id.winOrLose);

        //set the current total point
        totalPoint.setText("Total point: " + Integer.toString(startPoint));

        //set win if current point is bigger/equal to the winning point
        if(startPoint >= winPoint)
        {
            //set winOrLose to win
            winOrLose.setText("YOU WIN !");
        }
        //set lose if current point is less/equal to 0
        if(startPoint <= 0)
        {
            //set winOrLose to lose
            winOrLose.setText("YOU LOSE !");
        }

        //return the view
        return view;
    }
}
