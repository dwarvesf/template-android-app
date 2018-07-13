package com.dwarves.template.support

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ColorRes
import android.support.annotation.PluralsRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat

/**
 * When using this consider the context gonna be Service, Activity or Application
 * Different context may provider different result
 */
class ResourceProvider(private val context: Context) {
    private val resources: Resources = context.resources

    fun getString(@StringRes id: Int): String {
        return resources.getString(id)
    }

    fun getString(@StringRes id: Int, vararg args: Any): String {
        return resources.getString(id, *args)
    }

    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg args: Any): String {
        return resources.getQuantityString(id, quantity, *args)
    }

    fun getStringArray(id: Int): Array<String> {
        return resources.getStringArray(id)
    }

    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    fun getDimension(id: Int): Float {
        return resources.getDimension(id)
    }

    fun getDimensionPixelOffset(id: Int): Int {
        return resources.getDimensionPixelOffset(id)
    }
}
