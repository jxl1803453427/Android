package com.sanxiang.project.ScrollConflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.sanxiang.project.util.Constant;
import com.sanxiang.project.util.MyLog;
import com.sanxiang.project.util.MyLog2;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyScrollView extends ScrollView{
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyLog.log("scrollview__________________________dispatch");

        return super.dispatchTouchEvent(ev);
    }
    private double lastY;
    private double nowY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MyLog.log("scrollview____________________________intercept");

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                nowY = ev.getY();

                if(Constant.listSlide == Constant.slide_top){
                    if((nowY - lastY) <0){
                        requestDisallowInterceptTouchEvent(true);
                        Constant.listSlide = Constant.slide_body;
                    }
                }else if(Constant.listSlide == Constant.slide_body){
                    requestDisallowInterceptTouchEvent(true);
                    MyLog2.log("--------------");
                }else if(Constant.listSlide == Constant.slide_bottom){
                    if((nowY - lastY) >0){
                        requestDisallowInterceptTouchEvent(true);
                        Constant.listSlide = Constant.slide_body;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        MyLog.log("scrollview_____________________________ontouch"+super.onTouchEvent(ev));
//        MyLog2.log(ev.getX()+"-----"+ev.getY());

        return super.onTouchEvent(ev);
    }
}
