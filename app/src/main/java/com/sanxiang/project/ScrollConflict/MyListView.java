package com.sanxiang.project.ScrollConflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

import com.sanxiang.project.util.MyLog;
import com.sanxiang.project.util.MyLog2;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyListView extends ListView{
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyLog.log("listview__________dispatch");

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MyLog.log("listview_________intercept");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        MyLog.log("listview_________ontouch"+super.onTouchEvent(ev));
        MyLog2.log(ev.getX()+"-----"+ev.getY());
        return super.onTouchEvent(ev);
    }

}
