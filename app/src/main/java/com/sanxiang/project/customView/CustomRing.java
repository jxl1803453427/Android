package com.sanxiang.project.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.sanxiang.project.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/1/9.
 */
public class CustomRing extends View{
    private int mFirsterColor;
    private int mSecondColor;
    private int mCircleWidth;
    private int mSpeed;
    private Paint mPaint;
    private float mProgress;

    private boolean reStart = false;

    private final int speed_fast = 0,speed_slowly = 1,speed_nomal = 2;
    public CustomRing(Context context) {
        this(context,null);
    }

    public CustomRing(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.custom_ring,defStyleAttr,0);

        int count = array.getIndexCount();

        for (int i=0;i<count;i++){
            int index = array.getIndex(i);
            switch (index){
                case R.styleable.custom_ring_circleWidth:
//                    mCircleWidth = array.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    mCircleWidth = array.getDimensionPixelSize(index,10);
                    break;
                case R.styleable.custom_ring_firstColor:
                    mFirsterColor = array.getColor(index, Color.BLACK);
                    break;
                case R.styleable.custom_ring_secondColor:
                    mSecondColor = array.getColor(index,Color.RED);

                    break;
                case R.styleable.custom_ring_speed:
                    mSpeed = array.getInt(index,0);

                    break;
            }
        }
        array.recycle();
        mPaint = new Paint();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    SystemClock.sleep(1);//一般采用最小20
//
//                    handler.sendEmptyMessage(1);
//                }
//            }
//        }).start();

        timer.schedule(timerTask,20,20);
    }

    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
//            SystemClock.sleep(1);//一般采用最小20

            handler.sendEmptyMessage(1);

        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (mSpeed){
                case speed_fast:
                    mProgress += 2;
                    break;
                case speed_slowly:
                    mProgress += 0.5;
                    break;
                case speed_nomal:
                    mProgress += 1;
                    break;
            }

//            if(mProgress == 360){
//                mProgress = 0;
//                reStart = true;
//            }else{
//                reStart = false;
//            }

            if(mProgress >360){
                mProgress = 1;
                reStart = true;
            }else {
                reStart = false;
            }
            invalidate();
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth()/2;

        int radius = center - mCircleWidth/2;
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);


        if(reStart){

            int temp = mFirsterColor;
            mFirsterColor = mSecondColor;
            mSecondColor = temp;

        }

        mPaint.setColor(mFirsterColor);
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,mPaint);


        RectF voal = new RectF(center-radius,center-radius,center+radius
                ,center+radius);
        mPaint.setColor(mSecondColor);
        canvas.drawArc(voal,-90,mProgress,false,mPaint);




    }
}
