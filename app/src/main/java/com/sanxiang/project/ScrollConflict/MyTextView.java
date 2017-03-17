package com.sanxiang.project.ScrollConflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.sanxiang.project.util.MyLog;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyTextView extends TextView{
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        MyLog.log("textview_______dispatch");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MyLog.log("textview_______ontouch"+super.onTouchEvent(event));
        return super.onTouchEvent(event);


    }
}
