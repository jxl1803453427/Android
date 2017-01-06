package com.sanxiang.project.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.sanxiang.project.R;

/**
 * Created by Administrator on 2017/1/5.
 */
public class CustomImageView extends View{

    private String mTitle;
    private int mTitleTextSize;
    private int mTitleTextColor;
    private Rect rect;
    private Rect mTextBound;
    private Paint mPaint;
    private Bitmap mImage;
    private int mImageScale;
    private int IMAGE_SCALE_FITXY = 0;
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView,defStyleAttr,0);

        int count = array.getIndexCount();
        for (int i=0;i<count;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(),array.getResourceId(attr,0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = array.getInt(attr,0);
                    break;
                case R.styleable.CustomImageView_titleTextColor:
                    mTitleTextColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_titleTextSize:
                    mTitleTextSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomImageView_titleText:
                    mTitle = array.getString(attr);
                    break;
            }
        }

        array.recycle();
        rect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();

        mPaint.setTextSize(mTitleTextSize);

        mPaint.getTextBounds(mTitle,0,mTitle.length(),mTextBound);

    }

    private int mWidth;
    private int mHeight;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthMeas = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeas = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode){
            case MeasureSpec.EXACTLY:
                mWidth = widthMeas;
                break;
            case MeasureSpec.AT_MOST:
                int desireByImage = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
                int desireByText = getPaddingLeft() + getPaddingRight() + mTextBound.width();
                int desire = Math.max(desireByImage,desireByText);
                mWidth = Math.min(desire,widthMeas);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        switch (heightMode){
            case MeasureSpec.EXACTLY:
                mHeight = heightMeas;
                break;
            case MeasureSpec.AT_MOST:
                int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();

                mHeight = Math.min(desire,heightMeas);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.gray));
        canvas.drawRect(0,0,mWidth,mHeight,mPaint);

        rect.left = getPaddingLeft();
        rect.right = mWidth - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = mHeight - getPaddingBottom();
        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        if(mTextBound.width() >mWidth){
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle,paint,mWidth-getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight - getPaddingBottom(),mPaint);
        }else {
            canvas.drawText(mTitle,mWidth/2 - mTextBound.width() * 1.0f/2,mHeight - getPaddingBottom(),mPaint);
        }

//        取消使用掉的快
        rect.bottom -= mTextBound.height();

        if (mImageScale == IMAGE_SCALE_FITXY)
        {
            canvas.drawBitmap(mImage, null, rect, mPaint);
        } else
        {
            //计算居中的矩形范围
            rect.left = mWidth / 2 - mImage.getWidth() / 2;
            rect.right = mWidth / 2 + mImage.getWidth() / 2;
            rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            rect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;

            canvas.drawBitmap(mImage, null, rect, mPaint);

        }
    }
}
