package edu.curtin.comp2008.mad2020assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * FragAVertical fragment class is the 'Fragment A' based on the assignment specification that implements Fragment. It is the vertical Fragment A.
 * It contains a 4 buttons which are oneRow, twoRow, threeRow and scrollDirectionToV buttons, to change how many col it displays or change the scroll direction.
 * The default layout for fragment A is vertically scrollable in 2 cols.
 * This fragment uses WhichFrag interface to communicate data to its activity, which is SecondScreen or ThirdScreen
 */
public class FragAVertical extends Fragment
{
    private Button oneRow, twoRow, threeRow, scrollDirectionToH;
    private int col;
    //to communicate from between fragment its activity container
    private WhichFrag whichFragB;

    //define the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
        //The root View object is used to find a specific UI elements by view.findViewById(..).
        View view = inflater.inflate(R.layout.fragment_a_vertical, ui,false);

        //find each view by id
        oneRow = (Button) view.findViewById(R.id.oneRow);
        twoRow = (Button) view.findViewById(R.id.twoRow);
        threeRow = (Button) view.findViewById(R.id.threeRow);
        scrollDirectionToH = (Button) view.findViewById(R.id.scrollDirectionToH);

        //col is 2 as it is the default col
        col = 2;
        //declare whichFragB based on the activity container
        whichFragB = (WhichFrag) getActivity();


        //When oneRow button is pressed, user will have 1 col scroll layout
        oneRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //change col to 1 for one col scroll layout
                col = 1;
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(col, "V");
            }
        });

        //When twoRow button is pressed, user will have 2 col scroll layout
        twoRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //change col to 2 for two col scroll layout
                col = 2;
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(col, "V");
            }
        });

        //When threeRow button is pressed, user will have 3 col scroll layout
        threeRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //change col to 3 for three col scroll layout
                col = 3;
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(col, "V");

            }
        });

        //When scrollDirectionToH button is pressed, it will change the scroll direction from vertical to horizontal scrolling
        scrollDirectionToH.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //initialize and declare a new FragAHorizontal object
                Fragment frag = new FragAHorizontal();
                //use whichFragB changeFrag method and pass the FragAHorizontal object to change into horizontal scrolling
                whichFragB.changeFrag(frag);
                //use whichFragB changeFragRowCol method to pass data
                whichFragB.changeFragRowCol(col, "H");
            }
        });

        //return the view
        return view;
    }
}
