package com.sanxiang.project.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sanxiang.project.util.MyLog3;

/**
 * Created by Administrator on 2017/1/10.
 */
public class MyViewGroup extends ViewGroup{
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int cCount = getChildCount();
        MyLog3.log("p________________mesure1---"+widthMeasureSpec+"----"+heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        MyLog3.log("p________________mesure2---"+widthMeasureSpec+"----"+heightMeasureSpec);

        int cWidth = 0;
        int cHeight = 0;

        MarginLayoutParams cParams = null;

        int lHeigh = 0;
        int rHeight = 0;
        int tWidth = 0;
        int bWidth = 0;

        for (int i=0;i<cCount;i++){
            View cView = getChildAt(i);
            cWidth = cView.getMeasuredWidth();
            cHeight = cView.getMeasuredHeight();
            cParams = (MarginLayoutParams) cView.getLayoutParams();

//            if(i==0 || i==1){
//                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
//            }
//            if(i == 2 || i==3){
//                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
//            }

            switch (i){
                case 0:
                    tWidth += cWidth +cParams.leftMargin + cParams.rightMargin;
                    lHeigh += cHeight + cParams.topMargin + cParams.bottomMargin;
                    break;
                case 1:
                    tWidth += cWidth +cParams.leftMargin + cParams.rightMargin;
                    rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
                    break;
                case 2:
                    lHeigh += cHeight + cParams.topMargin + cParams.bottomMargin;
                    bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;

                    break;
                case 3:
                    bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
                    rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
                    break;

            }

            width = Math.max(tWidth,bWidth);
            height = Math.max(lHeigh,rHeight);

            setMeasuredDimension(widthMode == MeasureSpec.EXACTLY?widthSize:width,
                    heightMode == MeasureSpec.EXACTLY?heightSize:height);

        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        MyLog3.log("p________________layout1");
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;

        MarginLayoutParams cParams = null;

        for (int a=0;a< cCount;a++){
            View cView = getChildAt(a);
            cWidth = cView.getMeasuredWidth();
            cHeight = cView.getMeasuredHeight();
            MyLog3.log("p________________layout2");
            cParams = (MarginLayoutParams) cView.getLayoutParams();

            int cl=0,ct=0,cr=0,cb=0;

            switch (a){
                case 0:
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                case 1:
                    cl = getMeasuredWidth() - cWidth - cParams.rightMargin;
                    ct = cParams.topMargin;
                    break;
                case 2:
                    cl = cParams.leftMargin;
                    ct = getMeasuredHeight() - cParams.bottomMargin - cHeight;
                    break;
                case 3:
                    cl = getMeasuredWidth() - cWidth - cParams.rightMargin;
                    ct = getMeasuredHeight() - cHeight - cParams.bottomMargin;
                    break;
            }

            cr = cl + cWidth;
            cb = ct + cHeight;

            cView.layout(cl,ct,cr,cb);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
//

}
