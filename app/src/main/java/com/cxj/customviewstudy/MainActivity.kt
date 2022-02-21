package com.cxj.customviewstudy

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var firstDialogFragment: FirstDialogFragment? = null
    var isShowLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showFragment(view: View) {
        showLoading()
    }

    private fun showLoading() {
        try {
            if (firstDialogFragment == null) {
                firstDialogFragment = FirstDialogFragment.newInstance()
                isShowLoading = false
            }
            if (isLoadingEnable()) {
                supportFragmentManager.beginTransaction().add(firstDialogFragment!!,FirstDialogFragment.newInstance().tag).commitAllowingStateLoss()
                isShowLoading = true
            }
        }catch (e: Exception) {
            e.printStackTrace()
            isShowLoading = false
        }

    }
    protected fun isLoadingEnable(): Boolean {
        return firstDialogFragment != null && !firstDialogFragment!!.isAdded && !isShowLoading
    }
    protected fun isRemoveEnable(): Boolean {
        return firstDialogFragment != null && firstDialogFragment!!.isAdded && isShowLoading
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (isRemoveEnable()) {
                supportFragmentManager.beginTransaction().remove(firstDialogFragment!!).commitAllowingStateLoss()
                isShowLoading = false
                firstDialogFragment = null
            }
        }
        return super.onKeyDown(keyCode, event)

    }

    fun toBasisView(view: View) {
        BasicViewActivity.start(this)
    }

    fun toBasisCanvasView(view: View) {
        BasicCanvasActivity.start(this)
    }

    fun toRectPointView(view: View) {
        RectPointViewActivity.start(this)
    }
}