package com.cxj.customviewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FirstView extends View {

    private Paint mPaint;

    public FirstView(Context context) {
        this(context,null);
    }

    public FirstView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FirstView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setAlpha(100);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//仿抖动
//        mPaint.setBlendMode();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(new RectF(100,100,300,300),10,10,mPaint);
    }
}
