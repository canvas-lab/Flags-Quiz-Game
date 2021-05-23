package edu.curtin.comp2008.mad2020assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * FragAHorizontal fragment class is the 'Fragment A' based on the assignment specification that implements Fragment. It is the horizontal Fragment A.
 * It contains a 4 buttons which are oneRow, twoRow, threeRow and scrollDirectionToV buttons, to change how many row it displays or change the scroll direction.
 * The default layout for fragment A is vertically scrollable in 2 cols.
 * This fragment uses WhichFrag interface to communicate data to its activity, which is SecondScreen or ThirdScreen
 */
public class FragAHorizontal extends Fragment
{
    private Button oneRow, twoRow, threeRow, scrollDirectionToV;
    private int row;
    //to communicate from between fragment its activity container
    private WhichFrag whichFragB;

    //define the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
        //The root View object is used to find a specific UI elements by view.findViewById(..).
        View view = inflater.inflate(R.layout.fragment_a_horizontal, ui,false);

        //find each view by id
        oneRow = (Button) view.findViewById(R.id.oneRow);
        twoRow = (Button) view.findViewById(R.id.twoRow);
        threeRow = (Button) view.findViewById(R.id.threeRow);
        scrollDirectionToV = (Button) view.findViewById(R.id.scrollDirectionToH);

        //row is 2 as it is the default row
        row = 2;
        //declare whichFragB based on the activity container
        whichFragB = (WhichFrag) getActivity();

        //When oneRow button is pressed, user will have one row scroll layout
        oneRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //change row to 1 for one row scroll layout
                row = 1;
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(row, "H");
            }
        });

        //When twoRow button is pressed, user will have two row scroll layout
        twoRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //change row to 2 for two row scroll layout
                row = 2;
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(row, "H");
            }
        });

        //When threeRow button is pressed, user will have three row scroll layout
        threeRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //change row to 3 for three row scroll layout
                row = 3;
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(row, "H");
            }
        });

        //When scrollDirectionToV button is pressed, it will change the scroll direction from horizontal to vertical scrolling
        scrollDirectionToV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //initialize and declare a new FragAVertical object
                Fragment frag = new FragAVertical();
                //use whichFragB changeFrag method and pass the FragAVertical object to change into vertical scrolling
                whichFragB.changeFrag(frag);
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(row, "V");
            }
        });

        //return the view
        return view;
    }
}
