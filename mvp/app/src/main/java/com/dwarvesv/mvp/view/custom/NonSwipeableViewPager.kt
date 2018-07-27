package com.dwarvesv.mvvm.view.custom

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class NonSwipeableViewPager : ViewPager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }
}