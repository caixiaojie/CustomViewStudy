package com.cxj.customviewstudy

import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat

class ImageBasicActivity : BaseActivity(){
    private lateinit var constraintLayout: ConstraintLayout
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val myImage: Drawable? = ResourcesCompat.getDrawable(resources,R.drawable.ic_checkface_innercircle,null)
//        val i = ImageView(this).apply {
//            setImageResource(R.drawable.ic_checkface_innercircle)
//            contentDescription = resources.getString(R.string.my_image_desc)
//            adjustViewBounds = true
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        }
//        constraintLayout = ConstraintLayout(this@ImageBasicActivity).apply {
//            addView(i)
//        }
//        setContentView(constraintLayout)
//        init()
//    }

    override fun getLayoutId(): Int {
        return R.layout.activity_image_basic
    }

    override fun init() {
        transition()
    }

    fun transition() {
        val transition= ResourcesCompat.getDrawable(
        resources,
        R.drawable.expand_collapse,
        null
    ) as TransitionDrawable

        val image: ImageView = findViewById(R.id.toggle_image)
        image.setImageDrawable(transition)

        // Description of the initial state that the drawable represents.
        image.contentDescription = resources.getString(R.string.collapsed)

        // Then you can call the TransitionDrawable object's methods.
        transition.startTransition(3000)
    }
}