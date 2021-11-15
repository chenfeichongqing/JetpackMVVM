package com.github.chenfeichongqing.mvvmlib.ext.view

import android.app.Activity
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import com.github.chenfeichongqing.mvvmlib.R

/**
 * 设置view显示
 */
fun View.visible() {
    visibility = View.VISIBLE
}


/**
 * 设置view占位隐藏
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * 根据条件设置view显示隐藏 为true 显示，为false 隐藏
 */
fun View.visibleOrGone(flag:Boolean) {
    visibility = if(flag){
        View.VISIBLE
    }else{
        View.GONE
    }
}

/**
 * 根据条件设置view显示隐藏 为true 显示，为false 隐藏
 */
fun View.visibleOrInvisible(flag:Boolean) {
    visibility = if(flag){
        View.VISIBLE
    }else{
        View.INVISIBLE
    }
}

/**
 * 设置view隐藏
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * 将view转为bitmap
 */
@Deprecated("use View.drawToBitmap()")
fun View.toBitmap(scale: Float = 1f, config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap? {
    if (this is ImageView) {
        if (drawable is BitmapDrawable) return (drawable as BitmapDrawable).bitmap
    }
    this.clearFocus()
    val bitmap = createBitmapSafely(
        (width * scale).toInt(),
        (height * scale).toInt(),
        config,
        1
    )
    if (bitmap != null) {
        Canvas().run {
            setBitmap(bitmap)
            save()
            drawColor(Color.WHITE)
            scale(scale, scale)
            this@toBitmap.draw(this)
            restore()
            setBitmap(null)
        }
    }
    return bitmap
}

fun createBitmapSafely(width: Int, height: Int, config: Bitmap.Config, retryCount: Int): Bitmap? {
    try {
        return Bitmap.createBitmap(width, height, config)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        if (retryCount > 0) {
            System.gc()
            return createBitmapSafely(width, height, config, retryCount - 1)
        }
        return null
    }
}


/**
 * 防止重复点击事件 默认0.5秒内不可重复点击
 * @param interval 时间间隔 默认0.5秒
 * @param action 执行方法
 */
var lastClickTime = 0L
fun View.clickNoRepeat(interval: Long = 500, action: (view: View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)) {
            return@setOnClickListener
        }
        lastClickTime = currentTime
        action(it)
    }
}

//同一个界面共用点击事件，上面的方法有一个问题，
// 比如使用两个手指同时点击两个不同的按钮，按钮的功能都是新开页面，
// 那么有可能会新开两个页面。因为 Rxjava 这种方式是针对单个控件实现防止重复点击，不是多个控件。
fun View.onSingleClick(
    interval: Int = 500,
    isShareSingleClick: Boolean = true,
    listener: (View) -> Unit
) {
    setOnClickListener {
        val target = if (isShareSingleClick) getActivity(this)?.window?.decorView ?: this else this
        val millis = target.getTag(R.id.single_click_tag_last_single_click_millis) as? Long ?: 0
        if (SystemClock.uptimeMillis() - millis >= interval) {
            target.setTag(
                R.id.single_click_tag_last_single_click_millis, SystemClock.uptimeMillis()
            )
            listener.invoke(this)
        }
    }
}

private fun getActivity(view: View): Activity? {
    var context = view.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}
fun Any?.notNull(notNullAction:(value:Any) ->Unit,nullAction1:() ->Unit){
    if(this!=null){
        notNullAction.invoke(this)
    }else{
        nullAction1.invoke()
    }
}
