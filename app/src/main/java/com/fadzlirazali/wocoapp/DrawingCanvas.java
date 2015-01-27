package com.fadzlirazali.wocoapp;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

/**
 * 1. Setup the Drawing class properties and methods from implementation
 *
 * Created by FadzliRazali on 1/27/15.
 */
public class DrawingCanvas extends View {


    //Constructor with args
    public DrawingCanvas(Context context, AttributeSet attributeSet){

        //This will refer on it's superclass
        super(context,attributeSet);
        //Run on drawing setup method
        setupDrawing();

    }

    //add an instance of the custom View to the XML layout file
    private void setupDrawing() {
        //get drawing area up


    }


}
