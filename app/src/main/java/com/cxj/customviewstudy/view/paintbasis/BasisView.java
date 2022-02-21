package com.cxj.customviewstudy.view.paintbasis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class BasisView extends View {
    public BasisView(Context context) {
        super(context);
    }

    public BasisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*//设置画笔基本属性
        Paint paint = new Paint();
        paint.setColor(Color.RED);//设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//设置填充样式
        paint.setStrokeWidth(50);//设置画笔宽度
        //画圆
        canvas.drawCircle(190,200,150,paint);*/

        Paint paint = new Paint();
        paint.setColor(0xFFFF0000);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(50);
        canvas.drawCircle(190, 200, 150, paint);

        paint.setColor(0x7EFFFF00);
        canvas.drawCircle(190, 200, 100, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.e("basic_view","x="+event.getX()+"...y="+event.getY());
        }
        return super.onTouchEvent(event);
    }
}
