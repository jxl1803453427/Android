package com.sanxiang.project.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.sanxiang.project.R;
import com.sanxiang.project.util.MyLog;
import com.sanxiang.project.util.MyLog3;

/**
 * Created by Administrator on 2017/1/3.
 */
public class MyTextView extends View{

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseLineX = 0;
        int baseLineY = 200;

        Paint paint = new Paint();
        paint.setTextSize(120);

        String str = "1232kg";


        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float asseceet = baseLineY + fontMetrics.ascent;
        float deccent = baseLineY + fontMetrics.descent;
        float top = baseLineY + fontMetrics.top;
        float bottom = baseLineY + fontMetrics.bottom;
        //画需要背景
        int width = (int) paint.measureText(str);
        paint.setColor(getResources().getColor(R.color.red));
        canvas.drawRect(baseLineX,top,baseLineX+width,bottom,paint);

        //画最小矩形
        Rect rect = new Rect();
        paint.getTextBounds(str,0,str.length(),rect);
        paint.setColor(Color.CYAN);
        canvas.drawRect(rect.left,rect.top+baseLineY,rect.right,rect.bottom+baseLineY,paint);

        MyLog3.log(rect.height()+"***"+(rect.bottom - rect.top)+"***"+(deccent-asseceet)+"***"+(bottom-top));

        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX,baseLineY,800000,baseLineY,paint);//画baseline

        paint.setColor(Color.YELLOW);
        canvas.drawLine(0,asseceet,600,asseceet,paint);//

        paint.setColor(Color.BLUE);
        canvas.drawLine(0,top,600,top,paint);

        paint.setColor(Color.CYAN);
        canvas.drawLine(0,deccent,600,deccent,paint);

        paint.setColor(Color.BLACK);
        canvas.drawLine(0,bottom,600,bottom,paint);

        //        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(str,baseLineX,baseLineY,paint);


        paint.getTextBounds("我的国家",0,"我的国家".length(),rect);
        MyLog3.log(rect.height()+"--"+rect.width() +"--"+ rect.left+"--"+rect.right+"--"+rect.top+"--"+rect.bottom);


    }
}
