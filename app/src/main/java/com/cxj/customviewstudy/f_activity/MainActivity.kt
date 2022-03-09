package com.cxj.customviewstudy.f_activity

import com.cxj.customviewstudy.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        btn_a_basic.start(ABasicActivity::class.java)
        btn_b_path.start(BPathActivity::class.java)
        btn_c_text.start(CTextActivity::class.java)
        btn_d_region.start(DRegionActivity::class.java)
        btn_e_canvas.start(ECanvasActivity::class.java)
        btn_preview_circle_camera.start(PreviewCircleCameraActivity::class.java)
        btn_clock.start(ClockViewActivity::class.java)
        btn_image.start(ImageBasicActivity::class.java)
        btn_StickerTextView.start(StickerTextViewActivity::class.java)
    }

}
