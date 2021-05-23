package edu.curtin.comp2008.mad2020assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * FragB fragment class is the 'Fragment B' based on the assignment specification that implements Fragment.
 */
public class FragB extends Fragment
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

    private int rowCol, winPoint, startPoint, i, i2;
    private String fragChosen;
    private ArrayList<QnA> qnaList = new ArrayList<>();
    private QnA qna;
    private ArrayList<FlagFullStructure> flagFullStructure;
    private boolean isRe;

    //to communicate from between fragment its activity container
    private WhatData mCallback;

    //public constructor
    public FragB()
    {
        // Required empty public constructor
    }

    /**
     * factory method to create a new instance of this fragment using the provided parameters
     * to know how many row or col and if it is vertical or horizontal scrolling
     *
     * @param inRowCol int, the number of row
     * @param inFragChosen String, the fragment chosen, either vertical("V") or horizontal("H")
     * @return fragment, a new instance of fragment FragB.
     */
    public static FragB newInstance(int inRowCol, String inFragChosen)
    {
        //create new fragB object
        FragB fragment = new FragB();
        //create new bundle for the argument
        Bundle args = new Bundle();
        //put the row col in the bundle
        args.putInt(ARG_ROWCOL, inRowCol);
        //put the frag chosen in the bundle
        args.putString(ARG_FRAGCHOSEN, inFragChosen);
        //set the frag arguments
        fragment.setArguments(args);
        //return the fragment fragB
        return fragment;
    }

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
            throw new ClassCastException(context.toString() + " oh, this is because i must implement WhatData interface");
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

        //get intent from its Activity which is SecondScreen
        Intent intent = getActivity().getIntent();
        winPoint = intent.getIntExtra(WIN_POINT, 0);
        startPoint = intent.getIntExtra(START_POINT, 0);
        qnaList = (ArrayList<QnA>) intent.getSerializableExtra(QNALIST);
        qna = (QnA) intent.getSerializableExtra(QNA);
        flagFullStructure = (ArrayList<FlagFullStructure>) intent.getSerializableExtra(FF);
        i = intent.getIntExtra(I, 0);
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

        //create FlagData object
        FlagData flagData = new FlagData();
        //if qna list is not null
        if(qnaList != null)
        {
            //set the qna list based on the flag. which flag is based on index i2.
            flagFullStructure.get(i2).setQnaList(qnaList);
            //set the flag data list
            flagData.setFlagDataList(flagFullStructure);
        }
        //if qna list is null
        else
        {
            //create a new flag data
            flagData.createFlagData();
        }

        //create the adapter. the adapter will takes the parameter flag data
        AdapterStructure adapterStructure = new AdapterStructure(flagData);
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
        ImageView imageView;

        ArrayList<QnA> qnaList;
        ArrayList<FlagFullStructure> flagFullStructure;
        private int ii2;

        //ViewHolderStructure
        public ViewHolderStructure(LayoutInflater li, ViewGroup parent)
        {
            //LayoutInflater takes a layout reference, instantiates all the View objects and returns the root View object. It reads the XML and creates the UI based on it.
            //Use the LayoutInflater to convert the XML to a View object tree. and the superclass then makes it available as 'itemView'
            super(li.inflate(R.layout.flag_list, parent, false));

            //Grab UI element reference. itemView is a superclass field.
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            //set imageView flags into clickable
            imageView.setClickable(true);

            //when imageView/a flag is pressed, user goes to the third screen
            imageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //if the flag isClicked is not true, because all questions of that specific flag is not yet answered by the user,
                    if(!flagFullStructure.get(ii2).isClicked())
                    {
                        //Special case: If a special question is correctly answered and (still not redeemed), clicking the previous button will take to the second screen to select a flag. The points of all the questions of the selected flag will be increased by 10 points.
                        //if isRe is true, increase all the selected flag's question list by 10 points
                        if(isRe == true)
                        {
                            //loop from 0 to the qna list size
                            for(int ie=0; ie<qnaList.size(); ie++)
                            {
                                //increase all the selected flag's question list by 10 points
                                qnaList.get(ie).setPoint(qnaList.get(ie).getPoint()+10);
                            }
                            //set isRe to false because a flag is already clicked
                            isRe = false;
                        }

                        //communicate between fragB and its activity container which is SecondScreen, pass the data to start activity.
                        mCallback.dataSender(winPoint, startPoint, qnaList, qna, flagFullStructure, i, ii2, isRe);
                    }
                }
            });
        }

        //bind method is called by AdapterStructure
        public void bind(int flag, ArrayList<QnA> inQnaList, ArrayList<FlagFullStructure> inFlagFullStructure, int ii)
        {
            //set the data, to be used and send for when SecondScreen start ThirdScreen activity
            i2 = ii;
            ii2 = ii;
            flagFullStructure = inFlagFullStructure;
            qnaList = inQnaList;

            //set the flag image
            imageView.setImageResource(flag);
        }
    }

    /**
     * AdapterStructure class that extends a RecyclerView.Adapter of ViewHolderStructure.
     * Called when the RecyclerView needs a new ViewHolder
     */
    public class AdapterStructure extends RecyclerView.Adapter<ViewHolderStructure>
    {
        private FlagData flagData;

        //AdapterStructure
        public AdapterStructure(FlagData flagData)
        {
            this.flagData = flagData;
        }

        //RecyclerView calls onCreateViewHolder(ViewGroup, int) when it needs a new ViewHolder.
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
            //if the flag ArrayList is null
            if(flagFullStructure != null)
            {
                //get the flag qna list size
                int ttl = flagFullStructure.get(index).getQnaList().size();
                //declare theBool as 0
                int theBool=0;
                //loop from 0 to the flag qna list size
                for(i=0; i<ttl; i++)
                {
                    //if the flag qna isClicked is true, increment theBool by 1
                    if(flagFullStructure.get(index).getQnaList().get(i).isClicked())
                    {
                        //increment theBool
                        theBool++;
                    }
                }
                //if theBool is equal to the flag qna list size, set the flag isClicked to true
                if(theBool == ttl)
                {
                    //set the flag isClicked to true
                    flagFullStructure.get(index).setClicked(true);
                }
            }

            //declare flag ArrayList and qna ArrayList based on the index, to be send by bind method
            ArrayList<FlagFullStructure> flagFullStructures = flagData.getFlagDataList();
            ArrayList<QnA> qnaLists = flagData.getFlagDataList().get(index).getQnaList();

            //pass the data to bind method in ViewHolderStructure
            //index identifies the data element that ViewHolder should now display.
            vh.bind(flagData.getFlagInt(index), qnaLists, flagFullStructures, index);
        }

        //getItemCount return the total number of data elements of flag data which is 16, as there is 16 flags
        @Override
        public int getItemCount()
        {
            //return flag data size
            return flagData.sizeFlagData();
        }
    }
}

