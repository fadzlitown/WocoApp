package com.fadzlirazali.wocoapp;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*3.Choosing Colors: ability for the user to choose colors from the palette*/
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

    //instance variable: represents the instance of the DrawingCanvas class View
    private DrawingCanvas drawCanvas;

    //instance variable to represent the paint color button
    private ImageButton currentPaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* akan invoked DrawingCanvas view by ID di activity_main.xml : View that is displayed in the Activity*/
        drawCanvas=(DrawingCanvas)findViewById(R.id.drawing);

        /*invoked to ColorPallete from id paint_colors : retrieve the first paint color, which is to be selected*/
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);

        /*Dapatkan first btn dan simpan di instance variable*/
        currentPaint=(ImageButton)paintLayout.getChildAt(0);

        /*drawable area img is selected*/
        currentPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
    }

    /*
    * let the user choose colors
    * */
    public void paintCLicked(View view){
        //user pick the color

        /*check that the user has clicked a paint color*/
        if(view != currentPaint){
            //update color

            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();    //get the color palate tag values

            /*call the setColor method on DrawingCanvas View obj*/
            drawCanvas.setColor(color);

            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currentPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currentPaint=(ImageButton)view;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
