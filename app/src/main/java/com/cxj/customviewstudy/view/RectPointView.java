package com.cxj.customviewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class RectPointView extends View {

    private Paint mPaint;
    private Rect mRect;
    private int mX;
    private int mY;

    public RectPointView(Context context) {
        super(context);
        init();
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mRect = new Rect(100, 10, 300, 100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int) event.getX();
        mY = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
            return true;
        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            mX = -1;
            mY = -1;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRect.contains(mX,mY)) {
            mPaint.setColor(Color.RED);
        }else {
            mPaint.setColor(Color.GREEN);
        }
        canvas.drawRect(mRect,mPaint);
    }
}
