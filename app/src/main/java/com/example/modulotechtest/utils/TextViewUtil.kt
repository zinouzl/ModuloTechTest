package com.example.modulotechtest.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView

object TextViewUtil {

    fun scrollingText(view: View,margin:Float):Animation{
        val context  = view.context
        view.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)

        val width = view.measuredWidth
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        var toXDelta = width - (screenWidth - margin)

        if(toXDelta < 0 ){
            toXDelta = 0F
        }
        else{
            toXDelta = 0 - toXDelta
        }
        val mAnimation = TranslateAnimation(0F,toXDelta,0F,0F)
        mAnimation.duration = 15000
        mAnimation.repeatCount = Animation.INFINITE
        mAnimation.repeatMode = Animation.RESTART

        return mAnimation
    }
}