package edu.curtin.comp2008.mad2020assignment1;

import androidx.fragment.app.Fragment;

/**
 * WhichFrag is an interface that contains changeFrag and changeFragRowCol method
 */
public interface WhichFrag
{
    //changeFrag method will be used to change the fragment, between FragAHorizontal and FragAVertical
    void changeFrag(Fragment frag);
    //changeFragRowCol method will be used to change the row or col and the layout of the fragment
    void changeFragRowCol(int rowCol, String fragChosen);
}
