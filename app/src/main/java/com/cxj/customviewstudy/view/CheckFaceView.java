package com.cxj.customviewstudy.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.cxj.customviewstudy.R;

public class CheckFaceView extends View {

    private Paint mPaint;
    private Bitmap mInnerCircleBitmap = null;//内环圆
    private Bitmap mOutCircleBitmap = null;//外环圆
    private float mDegress = 0;//旋转角度
    private float mTextSize = 60f;
    private Paint mTextPaint;
    private ValueAnimator valueAnimator;

    public CheckFaceView(Context context) {
        this(context,null);
    }

    public CheckFaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CheckFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.BLACK);

        valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(6000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDegress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleMask(canvas);
        drawBitmapCircle(canvas);
    }

    private void drawBitmapCircle(Canvas canvas) {
        if (mInnerCircleBitmap == null) {
            int desWidthAndHeight = (int) (getWidth() / 1.5f + getWidth() / 1.5f / 4);
            mInnerCircleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_checkface_innercircle);
            mInnerCircleBitmap = Bitmap.createScaledBitmap(mInnerCircleBitmap,desWidthAndHeight,desWidthAndHeight,true);
        }
        if(mOutCircleBitmap == null){
            int dstWidthAndHeight = (int) (getWidth() / 1.5f + getWidth() / 1.5f / 4);
            mOutCircleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_checkface_outcircle);
            mOutCircleBitmap = Bitmap.createScaledBitmap(mOutCircleBitmap,dstWidthAndHeight,dstWidthAndHeight,true);
        }
        int left = (getWidth() - mInnerCircleBitmap.getWidth()) / 2;
        int top = (int) (getWidth() / 2 - getWidth() / 3 - getWidth() / 1.5f / 8);

        canvas.save();
        canvas.rotate(mDegress,getWidth() / 2, getWidth() / 2);
        canvas.drawBitmap(mInnerCircleBitmap,left,top,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(-mDegress,getWidth() / 2, getWidth() / 2);
        canvas.drawBitmap(mOutCircleBitmap,left,top,mPaint);
        canvas.restore();
    }

    /**
     * //设置混合模式
     *         PorterDuff.Mode.ADD //将源像素添加到目标像素并使结果饱和。
     *         PorterDuff.Mode.CLEAR //源覆盖的目标像素被清除为 0。
     *         PorterDuff.Mode.DARKEN //保留源像素和目标像素的最小分量。
     *         PorterDuff.Mode.DST //源像素被丢弃，而目的地保持不变。
     *         PorterDuff.Mode.DST_ATOP //丢弃未被源像素覆盖的目标像素。
     *         PorterDuff.Mode.DST_IN //保留覆盖源像素的目标像素，丢弃剩余的源像素和目标像素。
     *         PorterDuff.Mode.DST_OUT //保留未被源像素覆盖的目标像素。
     *         PorterDuff.Mode.DST_OVER //源像素绘制在目标像素后面。
     *         PorterDuff.Mode.LIGHTEN //保留源像素和目标像素的最大分量。
     *         PorterDuff.Mode.MULTIPLY //将源像素和目标像素相乘。
     *         PorterDuff.Mode.OVERLAY //将源像素和目标像素相乘。
     *         PorterDuff.Mode.SCREEN //添加源像素和目标像素，然后减去源像素乘以目标。
     *         PorterDuff.Mode.SRC //源像素替换目标像素。
     *         PorterDuff.Mode.SRC_ATOP //丢弃不覆盖目标像素的源像素。
     *         PorterDuff.Mode.SRC_IN //保留覆盖目标像素的源像素，丢弃剩余的源像素和目标像素。
     *         PorterDuff.Mode.SRC_OUT //保留不覆盖目标像素的源像素。
     *         PorterDuff.Mode.SRC_OVER //源像素被绘制在目标像素上。
     *         PorterDuff.Mode.XOR //丢弃源像素覆盖目标像素的源像素和目标像素。
     */

    /**
     * 绘制圆圈遮罩
     * @param canvas
     *
     *
     */
    private void drawCircleMask(Canvas canvas) {
        canvas.save();
        //目标Dst
        canvas.drawRect(new Rect(0,0,getWidth(),getHeight()),mPaint);
        //设置混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));//源覆盖的目标像素被清除为 0。 即显示为透明 看到的就是圆形的预览界面
        //源图Src
        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/3,mPaint);
        //清除混合模式
        mPaint.setXfermode(null);
        canvas.restore();
    }

    public void resumeAnim(){
        if(valueAnimator == null){
            return;
        }
        if(valueAnimator.isStarted()){
            valueAnimator.resume();
        }else {
            valueAnimator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(valueAnimator != null){
            valueAnimator.cancel();
        }
    }

    public void pauseAnim(){
        if(valueAnimator != null && valueAnimator.isRunning()){
            valueAnimator.pause();
        }
    }
}
