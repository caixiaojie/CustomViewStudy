package com.cxj.customviewstudy.view


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cxj.customviewstudy.R


/**
 *
 *  @author wangjun
 *  @date  2021/7/27 9:47
 *  @Des  :可上下移动拖拽的广告button
 *  注意点：一定不要设置View的点击事件
 *
 */
class GyMovedImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr) {
    private var lastX: Int = 0
    private var lastY: Int = 0

    /**
     * 按下时的距离父控件的距离
     */
    private var lastTop: Int = 0

    /**
     * 透明度值
     */
    private var alphaData = 0.5f

    /**
     * view突出所占百分比
     */
    private var extrudeSize = 0.5f

    /**
     * 动画执行时长
     */
    private var animTime = 400

    /**
     * 当前控件的高度/宽度
     */
    private var mViewHeight: Int = 0
    private var mVieWidth: Int = 0

    /**
     * 父控件的高度/宽度
     */
    private var parentHeight: Int = 0
    private var parentWith: Int = 0

    /**
     * 是否可以滑动
     */
    private var canSlide: Boolean = true
    private var showButton: Boolean = true

    /**
     * 显示与隐藏
     */
    private var showOrHide = true

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.GyMovedImageButton)
        alphaData =
            ta.getFloat(
                R.styleable.GyMovedImageButton_alpha,
                0.5f
            )
        extrudeSize =
            ta.getFloat(
                R.styleable.GyMovedImageButton_extrudeSize,
                0.5f
            )
        animTime =
            ta.getInt(
                R.styleable.GyMovedImageButton_animTime,
                400
            )
        ta.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mViewHeight = measuredHeight
        mVieWidth = measuredWidth
        if (parent is ViewGroup) {
            parentHeight = (parent as ViewGroup).measuredHeight
            parentWith = (parent as ViewGroup).measuredWidth
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        val y = event.y.toInt()
        // 是否可滑动
        if (canSlide) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastTop = top
                    lastX = event.x.toInt()
                    lastY = event.y.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    val offsetY = y - lastY
                    var realTop = 0
                    var realBottom = 0
                    if (top + offsetY <= 0) {
                        realTop = 0
                        realBottom = mViewHeight
                    } else if (bottom + offsetY >= parentHeight) {
                        realTop = parentHeight - mViewHeight
                        realBottom = parentHeight
                    } else {
                        realTop = top + offsetY
                        realBottom = bottom + offsetY
                    }
                    layout(left, realTop, right, realBottom)
                }

                MotionEvent.ACTION_UP -> {
                    if (top == lastTop) {
                        clickCallBack?.invoke()
                    }
                }

            }
            return true
        } else {
            return false
        }
    }

    /**
     * 设置是否可以上下滑动
     */
    private fun setEnableSlide(canSlide: Boolean) {
        this.canSlide = canSlide
    }

    /**
     * 隐藏
     */
    private fun hideButton() {
        showAnim(
            parentWith - mVieWidth * 1.0f,
            parentWith - mVieWidth * extrudeSize,
            false,
            1f,
            alphaData
        )
        showButton = false
    }

    /**
     * 显示
     */
    private fun showButton() {
        showAnim(
            parentWith - mVieWidth * extrudeSize,
            parentWith - mVieWidth * 1.0f,
            true,
            alphaData,
            1f
        )
        showButton = true
    }

    /**
     * 执行动画
     */
    private fun showAnim(
        from: Float,
        to: Float,
        enableSlide: Boolean,
        fromAlpha: Float,
        toAlpha: Float
    ) {
        val animator = ObjectAnimator.ofFloat(
            this,
            "x",
            from,
            to
        ).apply {
            interpolator = DecelerateInterpolator()
            duration = animTime.toLong()
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    setEnableSlide(enableSlide)
                }
            })
        }
        val alphaAnimator = ObjectAnimator.ofFloat(
            this,
            "alpha",
            fromAlpha,
            toAlpha
        ).apply {
            duration = animTime.toLong()
            start()
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator, alphaAnimator)

    }

    /**
     * 绑定滚动的view
     */
    fun bindView(view: View?): GyMovedImageButton {
        if (view != null) {
            if (view is RecyclerView) {
                view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            if (showOrHide) {
                                hideButton()
                                showOrHide = !showOrHide
                            }

                        } else {
                            if (!showOrHide) {
                                showButton()
                                showOrHide = !showOrHide
                            }
                        }
                    }
                })
            } else if (view is NestedScrollView) {
                view.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                    var dy = scrollY - oldScrollY
                    if (dy > 0) {
                        if (showOrHide) {
                            hideButton()
                            showOrHide = !showOrHide
                        }

                    } else {
                        if (!showOrHide) {
                            showButton()
                            showOrHide = !showOrHide
                        }
                    }

                }
            } else {
                throw Throwable("请添加绑定的View，可以是RecyclerView和NestedScrollView")
            }
        } else {
            throw Throwable("请添加绑定的View，可以是RecyclerView和NestedScrollView")
        }
        return this
    }

    /**
     * 设置数据
     */
    fun setImageData(img: String): GyMovedImageButton {
        Glide.with(context).load(img).into(this)
        return this
    }

    /**
     * 设置view的收起透明度
     */
    fun setBtnAlpha(alpha: Float): GyMovedImageButton {
        this.alphaData = alpha
        return this
    }

    /**
     * 设置控件突出百分比
     */
    fun setExtrudeSize(extrudeSize: Float): GyMovedImageButton {
        this.extrudeSize = extrudeSize
        return this
    }

    /**
     * 设置动画执行时长
     */
    fun setAnimTime(animTime: Int): GyMovedImageButton {
        this.animTime = animTime
        return this
    }

    private var clickCallBack: (() -> Unit)? = null

    @SuppressLint("ClickableViewAccessibility")
    fun setClickListen(block: () -> Unit) {
        this.clickCallBack = block
        this.setOnTouchListener { _, _ ->
            if (!showButton) {
                showOrHide = true
                showButton()
            }
            false
        }
    }


}
