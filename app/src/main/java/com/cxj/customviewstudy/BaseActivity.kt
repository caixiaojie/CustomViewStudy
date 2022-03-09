package com.cxj.customviewstudy

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlin.reflect.KClass

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    abstract fun getLayoutId(): Int

    abstract fun init()

    private fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    fun View.start(clazz: Class<out Activity>) {
        this@start.setOnClickListener {
            startActivity(clazz)
        }
    }

    protected var permissionMap: HashMap<Int,Array<String>> = HashMap()
    private fun savePermission(code: Int,per: Array<String>) {
        permissionMap[code] = per
    }

    protected fun permissionCheck(permissions: Array<String>,requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionMap.containsKey(requestCode)) {
                return
            }
            savePermission(requestCode,permissions)
            ActivityCompat.requestPermissions(this,permissions, requestCode)
        }else {
            onPermissionGranted(requestCode, permissions)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val pers = permissionMap[requestCode]
        if (pers!!.size == permissions.size && grantResults.size == permissions.size) {
            permissionMap.remove(requestCode)
            for (code: Int in grantResults) {
                if (code == PackageManager.PERMISSION_DENIED) {
                    onPermissionDenide(requestCode,pers)
                    return
                }
            }
            onPermissionGranted(requestCode,pers)
        }
    }

    open fun onPermissionDenide(code: Int, permissions: Array<String>) {
        // TODO: 2018/11/5 权限被拒绝
    }

    open fun onPermissionGranted(code: Int, permissions: Array<String>) {
        // TODO: 2018/11/5 权限请求被允许
    }

}
