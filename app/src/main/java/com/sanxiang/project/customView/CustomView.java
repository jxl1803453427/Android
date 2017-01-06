package com.sanxiang.project.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.sanxiang.project.R;
import com.sanxiang.project.util.MyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/3.
 */
public class CustomView extends View{

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Rect mBound;
    private Paint mPaint;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView,defStyleAttr,0);

        int n = a.getIndexCount();
        for (int i=0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.CustomView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomView_titleTextColor:
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomView_titleTextSize:
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getResources().getDisplayMetrics()));
//                    mTitleTextSize = (int) a.getDimension(attr, (float) 0.0);
                    break;
            }
        }
        a.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText = randomText();

                invalidate();
            }
        });
    }

    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize  = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        switch (widthMode){
            case MeasureSpec.EXACTLY:
                width = widthSize;

                break;

            case MeasureSpec.AT_MOST:
                mPaint.setTextSize(mTitleTextSize);
                mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
                float textWidth = mBound.width();

                int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
                width = desired;
                break;

//            case MeasureSpec.UNSPECIFIED:
//                width = 0;
//                break;
            default: width = 0;

        }

        switch (heightMode){
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                mPaint.setTextSize(mTitleTextSize);
                mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
                float textHeight = mBound.height();
                int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());

                height = desired;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = 0;
                break;
            default: height = 0;
                break;
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.blue));

        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
//
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(0,0,mBound.width(),mBound.height(),mPaint);



        mPaint.setColor(mTitleTextColor);
//        canvas.drawText(mTitleText,getWidth()/2 - mBound.width()/2,getHeight()/2 + mBound.height()/2,mPaint);
//        canvas.drawText(mTitleText, 0 + getPaddingLeft(), getHeight() / 2 + mBound.height() / 2, mPaint);

//        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2 - mBound.left, getHeight() / 2 + mBound.height() / 2, mPaint);
        MyLog.log(mBound.width()+"--"+mBound.height()+"--"+mBound.left+"--"+mBound.right+"--"+mBound.top+"--"+mBound.bottom);
        MyLog.log(mPaint.measureText(mTitleText)+"--------");

        Paint.FontMetrics metrics = mPaint.getFontMetrics();

        canvas.drawText(mTitleText,-mBound.left,-mBound.top,mPaint);


    }
}
