package com.sanxiang.project.ScrollConflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.sanxiang.project.util.MyLog;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyLinarLayout extends LinearLayout{
    public MyLinarLayout(Context context) {
        super(context);
    }

    public MyLinarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyLog.log("linar______________dispatch");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MyLog.log("linar_______________intercept");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        MyLog.log("linar_______________ontouch"+super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }
}
