package com.cxj.customviewstudy

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_move_image.*

class MoveImageActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_move_image
    }

    override fun init() {
        floatButton.bindView(scrollView).setClickListen {
            Toast.makeText(this@MoveImageActivity,"点击跳转", Toast.LENGTH_SHORT).show()
        }
    }
}