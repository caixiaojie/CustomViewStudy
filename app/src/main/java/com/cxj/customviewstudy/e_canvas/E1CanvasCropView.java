package com.cxj.customviewstudy.e_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.cxj.customviewstudy.BaseView;

public class E1CanvasCropView extends BaseView {

    private int centerX;

    public E1CanvasCropView(Context context) {
        super(context);
    }

    public E1CanvasCropView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public E1CanvasCropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 灰色背景
        canvas.drawColor(Color.DKGRAY);

        // 裁剪范围
        int width = (int) dp2px(70);
        int top = (int) dp2px(30);
        int left = centerX - width;
        Rect rect = new Rect(left, top, centerX + width, top + width * 2);

        // 裁剪
        canvas.clipRect(rect);

        // 绘制
        canvas.drawColor(Color.YELLOW);

    }

    private void drawRects(Canvas canvas, int offset) {
        int left;
        int width;

        // 绿色 150
        int top = (int) dp2px(30);
        width  = (int) dp2px(150);
        left = centerX - width / 2 - offset;
        canvas.clipRect(new Rect(left, top, left + width, top + width));
        canvas.drawColor(Color.GREEN);
        canvas.save();

        // 蓝色 120
        width  = (int) dp2px(120);
        top = (int) dp2px(45);
        left = centerX - width / 2 - offset;
        canvas.clipRect(new Rect(left, top, left + width, top + width));
        canvas.drawColor(Color.BLUE);
        canvas.save();

        // 黑色 90
        width  = (int) dp2px(90);
        top = (int) dp2px(60);
        left = centerX - width / 2 - offset;
        canvas.clipRect(new Rect(left, top, left + width, top + width));
        canvas.drawColor(Color.BLACK);
        canvas.save();

        // 白色 60
        width  = (int) dp2px(60);
        top = (int) dp2px(75);
        left = centerX - width / 2 - offset;
        canvas.clipRect(new Rect(left, top, left + width, top + width));
        canvas.drawColor(Color.WHITE);
        canvas.save();

    }

}
