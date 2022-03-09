package com.cxj.customviewstudy

import android.Manifest
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_preview_circle_camera.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PreviewCircleCameraActivity : BaseActivity() {
    private lateinit var cameraExecutor: ExecutorService
    private var isAnim = true
    private val requestCode = 1000
    private val permissions = arrayOf(Manifest.permission.CAMERA)
    override fun getLayoutId(): Int {
        return R.layout.activity_preview_circle_camera
    }

    override fun init() {
        permissionCheck(permissions,requestCode)
//        viewFinder.post {
//            val layoutParams = viewFinder.layoutParams
//            layoutParams.width = (viewFinder.width / 1.5f).toInt()
//            layoutParams.height = (viewFinder.height / 1.5f).toInt()
//            viewFinder.layoutParams = layoutParams
//        }
        checkView.setOnClickListener {
            if(isAnim){
                checkView.pauseAnim()
            }else{
                checkView.resumeAnim()
            }
            isAnim = !isAnim
        }
    }

    override fun onPermissionGranted(code: Int, permissions: Array<String>) {
        super.onPermissionGranted(code, permissions)
        initCamera()
    }

    private fun initCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        startCamera()

    }

    private fun startCamera() {
        val instance = ProcessCameraProvider.getInstance(this)
        instance.addListener({
            val cameraProvider: ProcessCameraProvider = instance.get()
            val preview = Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }
            //打开前置摄像头
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,cameraSelector,preview)
            }catch (e: Exception) {

            }
        },ContextCompat.getMainExecutor(this))
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}