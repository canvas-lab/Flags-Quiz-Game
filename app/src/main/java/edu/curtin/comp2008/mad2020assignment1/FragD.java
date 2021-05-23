package edu.curtin.comp2008.mad2020assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * FragD fragment class is the 'Fragment D' based on the assignment specification that implements Fragment.
 */
public class FragD extends Fragment
{
    //the fragment initialization parameters, constants for the keys to avoid key naming conflicts by prefixing the package name
    private static final String ARG_ROWCOL = "edu.curtin.comp2008.mad2020assignment1.rowcol";
    private static final String ARG_FRAGCHOSEN = "edu.curtin.comp2008.mad2020assignment1.fragchosen";

    //constants for the keys to avoid key naming conflicts by prefixing the package name
    private static final String WIN_POINT = "edu.curtin.comp2008.mad2020assignment1.winpoint";
    private static final String START_POINT = "edu.curtin.comp2008.mad2020assignment1.startpoint";
    private static final String QNALIST = "edu.curtin.comp2008.mad2020assignment1.qnalist";
    private static final String QNA = "edu.curtin.comp2008.mad2020assignment1.qna";
    private static final String FF = "edu.curtin.comp2008.mad2020assignment1.ff";
    private static final String I = "edu.curtin.comp2008.mad2020assignment1.i";
    private static final String I2 = "edu.curtin.comp2008.mad2020assignment1.i2";
    private static final String ISRE = "edu.curtin.comp2008.mad2020assignment1.isre";

    private int rowCol, winPoint, startPoint, i2;
    private String fragChosen;
    private ArrayList<QnA> qnaList;
    private ArrayList<FlagFullStructure> ff;
    private boolean isRe;

    //to communicate from between fragment its activity container
    private WhatData mCallback;

    //public constructor
    public FragD()
    {
        // Required empty public constructor
    }

    /**
     * factory method to create a new instance of this fragment using the provided parameters
     * to know how many row or col and if it is vertical or horizontal scrolling
     *
     * @param inRowCol int, the number of row
     * @param inFragChosen String, the fragment chosen, either vertical("V") or horizontal("H")
     * @return fragment, a new instance of fragment FragD.
     */
    public static FragD newInstance(int inRowCol, String inFragChosen)
    {
        //create new fragD object
        FragD fragment = new FragD();
        //create new bundle for the argument
        Bundle args = new Bundle();
        //put the row col in the bundle
        args.putInt(ARG_ROWCOL, inRowCol);
        //put the frag chosen in the bundle
        args.putString(ARG_FRAGCHOSEN, inFragChosen);
        //set the frag arguments
        fragment.setArguments(args);
        //return the fragment fragD
        return fragment;
    }

    //init creation of frag
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //if getArguments is not null, get the arguments
        if (getArguments() != null)
        {
            //get the arguments that is previously put in newInstance method, which are rowCol and fragChosen
            rowCol = getArguments().getInt(ARG_ROWCOL);
            fragChosen = getArguments().getString(ARG_FRAGCHOSEN);
        }
        //if getArguments is null, set the rowCol and fragChosen into its default, which are 2 col or row and vertical scrolling
        else
        {
            //row and col is 2 as it is the default row and col
            rowCol = 2;
            //fragChosen is V as the default is vertical layout
            fragChosen = "V";
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
        }
        catch (Exception e)
        {
            throw new ClassCastException(context.toString() + " oh, this is bcs i must implement WhatData interface");
        }
    }

    //define the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
        //The root View object is used to find a specific UI elements by view.findViewById(..).
        View view = inflater.inflate(R.layout.fragment_b, ui, false);

        //Obtain the RecyclerView UI element by findViewById
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.fragBRecyclerView);

        //get intent from its Activity which is ThirdScreen
        Intent intent = getActivity().getIntent();
        winPoint = intent.getIntExtra(WIN_POINT, 0);
        startPoint = intent.getIntExtra(START_POINT, 0);
        qnaList = (ArrayList<QnA>) intent.getSerializableExtra(QNALIST);
        ff = (ArrayList<FlagFullStructure>) intent.getSerializableExtra(FF);
        i2 = intent.getIntExtra(I2, 0);
        isRe = intent.getBooleanExtra(ISRE, false);

        //specify the layout or how it should be laid out
        //if fragChosen is Vertical
        if(fragChosen.equals("V"))
        {
            //specify the cols of the layout and make it to a vertical scrolling
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), rowCol);
            rv.setLayoutManager(gridLayoutManager);
        }
        //if fragChosen is Horizontal
        else if(fragChosen.equals("H"))
        {
            //specify the row of the layout and make it to a horizontal scrolling
            rv.setLayoutManager(new GridLayoutManager(rv.getContext(), rowCol, GridLayoutManager.HORIZONTAL, false));
        }

        //create the adapter. the adapter will takes the parameter flag data
        AdapterStructure adapterStructure = new AdapterStructure(qnaList);
        //hook it up
        rv.setAdapter(adapterStructure);
        //return the view
        return view;
    }

    /**
     * ViewHolderStructure class that extends a RecyclerView.ViewHolder. contains bind method
     */
    private class ViewHolderStructure extends RecyclerView.ViewHolder
    {
        //Reference to UI element(s)
        Button numPointPenalty;

        private QnA qnaa;
        private int i;

        //ViewHolderStructure
        public ViewHolderStructure(LayoutInflater li, ViewGroup parent)
        {
            //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
            //Use the LayoutInflater to convert the XML to a View object tree. and the superclass then makes it available as 'itemView'
            super(li.inflate(R.layout.qpoint_list, parent, false));

            //Grab UI element reference. itemView is a superclass field.
            numPointPenalty = (Button) itemView.findViewById(R.id.numPointPenalty);

            //When numPointPenalty is pressed, user goes to the fourth screen
            numPointPenalty.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //if the qna isClicked is not true
                    if(qnaa.isClicked() == false)
                    {
                        //communicate between fragD and its activity container which is ThirdScreen, pass the data to start activity.
                        mCallback.dataSender(winPoint, startPoint, qnaList, qnaa, ff, i, i2, isRe);
                    }
                }
            });
        }

        //bind method is called by AdapterStructure
        public void bind(QnA inQna, int index)
        {
            //set the data, to be used and send for when SecondScreen start ThirdScreen activity
            qnaa = inQna;
            i = index;
            //declare ii as index+1 so that it start from Q1 instead of Q2
            int ii = index+1;
            //declare boolean d as qna isClicked
            boolean d = qnaa.isClicked();
            //set the flag qna as boolean d
            ff.get(i2).getQnaList().get(index).setClicked(d);

            //if qna is not a special question. //may put qnaa.isClicked() in the setText to show whether it is clicked or not
            if(qnaa.isSpecial() == false)
            {
                //set numPointPenalty text without special
                numPointPenalty.setText(" Q" + ii + "\npoint: " + qnaa.getPoint() + "\npenalty: " + qnaa.getPenalty());
            }
            //if qna is a special question
            else
            {
                //set numPointPenalty text with special
                numPointPenalty.setText(" Q" + ii + "(special)" + "\npoint: " + qnaa.getPoint() + "\npenalty: " + qnaa.getPenalty());
            }
        }
    }

    /**
     * AdapterStructure class that extends a RecyclerView.Adapter of ViewHolderStructure.
     * Called when the RecyclerView needs a new ViewHolder
     */
    public class AdapterStructure extends RecyclerView.Adapter<ViewHolderStructure>
    {
        ArrayList<QnA> qnaArrayList;

        //AdapterStructure
        public AdapterStructure(ArrayList<QnA> qnaArrayList)
        {
            this.qnaArrayList = qnaArrayList;
        }

        //RecyclerView calls onCreateViewHolder when it needs a new ViewHolder.
        @Override
        public ViewHolderStructure onCreateViewHolder(ViewGroup parent, int viewType)
        {
            //LayoutInflater belongs to the activity so it call Fragment’s getActivity() method. It can call getActivity() because we’re nested inside a Fragment class.
            //needs a LayoutInflater to create a ViewHolder.
            LayoutInflater li = LayoutInflater.from(getActivity());
            //adapter returns a new ViewHolder.
            return new ViewHolderStructure(li, parent);
        }

        //onBindViewHolder(ViewHolderStructure, int) is called when RecyclerView needs to rewrite a particular list row.
        //Called when RecyclerView needs to use a ViewHolder to display a different data element.
        //The adapter updates the supplied ViewHolder.
        //index identifies which element to display.
        @Override
        public void onBindViewHolder(ViewHolderStructure vh, int index)
        {
            //pass the data to bind method in ViewHolderStructure. pass the qna and its index
            //index identifies the data element that ViewHolder should now display.
            vh.bind(qnaArrayList.get(index), index);
        }

        //getItemCount return the total number of data elements of qna array list, which may be from 5 to 10
        @Override
        public int getItemCount()
        {
            //return qna list size
            return qnaArrayList.size();
        }
    }
}

