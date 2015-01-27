package com.fadzlirazali.wocoapp;

import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

/*import graphic library*/
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;


/**
 * 1. Setup the Drawing class properties and methods from implementation
 *
 * Created by FadzliRazali on 1/27/15.
 */
public class DrawingCanvas extends View {

    //instance variables
    //to trace user's drawing moving finger action on the canvas
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //init color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;


    //Constructor with args
    public DrawingCanvas(Context context, AttributeSet attributeSet){

        //This will refer on it's superclass
        super(context,attributeSet);
        //Run on drawing setup method
        setupDrawing();

    }

    //add an instance of the custom View to the XML layout file
    private void setupDrawing() {

        //obj variables to set the class up for drawing
        drawPath = new Path();
        drawPaint = new Paint();

        //set the init color
        drawPaint.setColor(paintColor);

        //set the init path properties
        drawPaint.setAntiAlias(true);   //smooths out the edges of what is being drawn
        drawPaint.setStrokeWidth(10);   //stroke thickness
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        /*Instantiating the canvas Paint object*/
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        //call the superclass
        super.onSizeChanged(w,h,oldw,oldh);

        /*
        * ARGB_8888
        * Each channel (RGB and alpha
        * for translucency) is stored with 8 bits of precision (256 possible values.)
        * */
        //instantiate the drawing canvas and bitmap
        //Returns a mutable bitmap with the specified width and height
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas){
        //allow to draw from custom canvas
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

    }

    /*
    * 2.Event Drawing :   detect user touch
    * */

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();

        //implement drawing are down, move and up in switch statement
        switch (event.getAction()){

            //jari user sentuh ke screen, akan mula dilukis(drawPath): move to that position to start drawing
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX,touchY);
                break;

            //jari user sentuh gerak di sekitar screen : draw the path along with their touch
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX,touchY);
                break;

            //jari user lift up on the screen : draw the Path and reset it for the next drawing operation
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;

            default:
                return false;
        }

        //invalidate: cause the onDraw method to execute
        invalidate();
        return true;
    }


    /*
    * 3.Choose Color
    * */

    /*set the color method*/
    public void setColor(String newColor){
        //set color

        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }




}
