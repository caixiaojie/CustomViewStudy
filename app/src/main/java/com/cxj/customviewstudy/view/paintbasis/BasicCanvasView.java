package com.cxj.customviewstudy.view.paintbasis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BasicCanvasView extends View {
    public BasicCanvasView(Context context) {
        super(context);
    }

    public BasicCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布背景设置
//        canvas.drawRGB(255,0,255);

        //画直线
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(5);
//        canvas.drawLine(100,100,200,200,paint);

        //多条直线
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(5);
//        float[] pts = {10,10,100,100,200,200,400,400};
//        canvas.drawLines(pts,2,4,paint);

        //画点
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(15);
//        canvas.drawPoint(100,100,paint);

        //多点
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(25);
//        float[] pts = {10,10,100,100,200,200,400,400};
////        canvas.drawPoints(pts,paint);
//        canvas.drawPoints(pts,2,4,paint);

        //矩形
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(15);
//
//        //直接构造
////        canvas.drawRect(10,10,100,100,paint);
//
//        //使用RecF构造
//        paint.setStyle(Paint.Style.FILL);
//        RectF rect = new RectF(210,10,300,100);
//        canvas.drawRect(rect,paint);

        //圆角矩形
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(15);
//
//        RectF rect = new RectF(100,10,300,100);
//        canvas.drawRoundRect(rect,20,20,paint);

        //圆
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(15);
//
//        canvas.drawCircle(150, 150, 100, paint);

        //椭圆
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(15);
//        RectF rect = new RectF(100, 10, 300, 100);
//        canvas.drawRect(rect, paint);
//
//        paint.setColor(Color.GREEN);//更改画笔颜色
//        canvas.drawOval(rect, paint);//同一个矩形画椭圆

        //画弧
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

        RectF rect1 = new RectF(10, 10, 100, 100);
        canvas.drawArc(rect1, 0, 90, true, paint);

        RectF rect2 = new RectF(110, 10, 200, 100);
        canvas.drawArc(rect2, 0, 90, false, paint);
    }
}
