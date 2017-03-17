package com.sanxiang.project.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.sanxiang.project.util.MyLog3;

/**
 * Created by Administrator on 2017/1/9.
 */
public class TestView extends TextView{

    private Paint mPaint;

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        context.getTheme().obtainStyledAttributes()
        mPaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        MyLog3.log("c________________mesure---"+widthMeasureSpec+"---"+heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Rect rect = new Rect(0,0,400,200);
//        mPaint.setColor(Color.BLUE);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(20);
//
//
////        canvas.drawRect(rect,mPaint);
//        canvas.drawRect(10,10,400,200,mPaint);
//        mPaint.setStrokeWidth(2);
//        canvas.drawLine(400,10,400,600,mPaint);
    }


}
